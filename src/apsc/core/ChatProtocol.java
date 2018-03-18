package apsc.core;

import java.util.ArrayList;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class ChatProtocol {

    /*
     DE[|]PARA[|]MENSAGEM[|]INTERNA[|]OP
     */
    private static final String SEPARADOR = ";";
    private static final String PLACE_HOLDER = "\\;";
    /*
     OPERAÇÕES
     * 100 -> Mensagens de texto/html
     * 101 - Mensagem
     * 
     * 200 -> Configurações
     * 201 - Envia o ID do cliente
     * 202 - Envia o nome do cliente
     * 203 - Envia a lista de todos os clientes
     * 204 - Envia a solicitação de desconexão do cliente
     * 
     */
    public static final String OP_101 = "101";
    public static final String OP_201 = "201";
    public static final String OP_202 = "202";
    public static final String OP_203 = "203";
    public static final String OP_204 = "204";
    public static final String OP_205 = "205";

    public static Msg createMessage(String from, String to, String msg, boolean interna, String op) {
        Msg m = new Msg();
        m.setFrom(from);
        m.setTo(to);

        if (msg.contains(SEPARADOR)) {
            msg = msg.replace(SEPARADOR, PLACE_HOLDER);
        }
        m.setMsg(msg);
        m.setInterna(interna);
        m.setOp(op);
        return m;
    }

    public static Msg parse(String msg) {
        String[] split = msg.split(SEPARADOR);
        Msg m = new Msg();

        if (split.length == 5) {

            //ID de
            m.setFrom(split[0]);

            //ID para
            m.setTo(split[1]);

            if (split[2].contains(PLACE_HOLDER)) {
                split[2] = split[3].replace(PLACE_HOLDER, SEPARADOR);
            }
            //Mensagem
            m.setMsg(split[2]);

            //Interna
            m.setInterna(split[3].equals("T") ? true : false);

            //Operação
            m.setOp(split[4]);
        }
        return m;
    }

    public static String MsgToString(Msg msg) {
        String tmpMsg = "";

        tmpMsg += msg.getFrom();
        tmpMsg += SEPARADOR;
        tmpMsg += msg.getTo();
        tmpMsg += SEPARADOR;
        tmpMsg += msg.getMsg();
        tmpMsg += SEPARADOR;
        tmpMsg += msg.isInterna() ? "T" : "F";
        tmpMsg += SEPARADOR;
        tmpMsg += msg.getOp();

        return tmpMsg;
    }

    /*
     Retorna uma lista de clientes
     */
    public static String atualizaClientes(ArrayList<User> usuarios) {
        String s = "";

        for (User user : usuarios) {
            s += user.getId() + ":" + user.getNome() + "!";
        }

        return s;
    }

    public static ArrayList<User> toUserList(Msg msg) {
        ArrayList<User> usuarios = new ArrayList<>();
        String msg1 = "*:Todos!" + msg.getMsg();

        String users[] = msg1.split("!");

        User u;

        for (String string : users) {
            u = new User(string.split(":")[0], string.split(":")[1]);
            usuarios.add(u);
        }

        return usuarios;


    }

    public static String toServerView(Msg msg) {
        String s = "";
        if (!msg.isInterna()) {
            s += "\n";
            s += "De: " + msg.getFrom();
            s += " Para: " + (msg.getTo().equals("*") ? "Todos" : msg.getTo());
            s += " >>> " + msg.getMsg();
        }
        return s;
    }
}
