package apsc.server;

import apsc.core.ChatProtocol;
import apsc.core.Msg;
import apsc.core.User;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class ComunicServ implements Runnable {

    private TelaServidor tela;
    private ArrayList<User> usuarios;// = new ArrayList();

    public ComunicServ(TelaServidor tela) {
        this.tela = tela;
        this.usuarios = tela.getUsuarios();
    }

    public void run() {

        try {
            ServerSocket servidor = null;

            servidor = new ServerSocket(tela.getPorta());

            tela.getTextArea().append("\n########################################");
            tela.getTextArea().append("\n           SERVIDOR EM EXECUÇÃO         ");
            tela.getTextArea().append("\n  PORTA: " + tela.getPorta());
            tela.getTextArea().append("\n########################################");

            while (true) {
                Socket cliente;
                cliente = servidor.accept();
                User u = new User(String.valueOf((int) (Math.random() * 10000)), "");
                u.setSocketCliente(cliente);
                tela.getTextArea().append("\n-> Nova conexão: " + cliente.getInetAddress().getHostAddress());

                u.setSaidaCliente(new PrintStream(cliente.getOutputStream()));

                this.usuarios.add(u);

                TrataCliente tc = new TrataCliente(u, cliente.getInputStream(), this);

                new Thread(tc).start();
                Msg createMessage = ChatProtocol.createMessage("servidor", "*", u.getId(), true, ChatProtocol.OP_201);

                u.getSaidaCliente().println(ChatProtocol.MsgToString(createMessage));

            }
        } catch (IOException ex) {
            Logger.getLogger(ComunicServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void interpretaMensagem(String msg) {
        Msg m = ChatProtocol.parse(msg);

        tela.getTextArea().append(ChatProtocol.toServerView(m));

        System.out.println("[DEBUG-ComunicServ] " + msg);

        if (m.isInterna()) {

            //se o OP 202, adiciona o cliente na lista, pega a lista e envia para todos
            if (m.getOp().equals(ChatProtocol.OP_202)) {
                for (User u : usuarios) {
                    if (m.getFrom().equals(u.getId())) {
                        u.setNome(m.getMsg().replace(m.getFrom(), ""));
                        tela.getTextArea().append("\nCliente [" + u.getId() + "] agora é '" + u.getNome() + "'.");
                    }
                }
                sendListaUser();

            } else if (m.getOp().equals(ChatProtocol.OP_204)) {

                for (int i = 0; i < usuarios.size(); i++) {
                    User usuario = usuarios.get(i);
                    if (m.getFrom().equals(usuario.getId())) {
                        usuarios.remove(i);
                    }
                }
                sendListaUser();
            }
        }
        for (User u : usuarios) {
            if (m.getTo().equals("*") || m.getTo().equals(u.getId()) || m.getFrom().equals(u.getId())) {
                u.getSaidaCliente().println(msg);
            }
        }
    }

    protected void sendListaUser() {
        //Procura pelo ID o usuario que mandou a mensagem
        //Limpa os clientes
        tela.getListaUsers().removeAll();

        DefaultListModel<User> tmpUsers = new DefaultListModel<>();

        for (User user : usuarios) {
            tmpUsers.addElement(user);
        }

        tela.getListaUsers().setModel(tmpUsers);

        //Manda a lista atualizada para todos os clientes
        String tmpListUsr = ChatProtocol.atualizaClientes(usuarios);
        Msg createMessage = ChatProtocol.createMessage("servidor", "*", tmpListUsr, true, ChatProtocol.OP_203);

        for (User user : usuarios) {
            user.getSaidaCliente().println(ChatProtocol.MsgToString(createMessage));
        }
    }
}
