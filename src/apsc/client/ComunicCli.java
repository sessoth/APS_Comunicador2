package apsc.client;

import apsc.core.ChatProtocol;
import apsc.core.Msg;
import apsc.core.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class ComunicCli implements Runnable {

    private InputStream servidor;
    private TelaCliente tela;

    public ComunicCli(InputStream servidor, TelaCliente tela) {
        this.servidor = servidor;
        this.tela = tela;
    }

    @Override
    public void run() {

        Scanner s = new Scanner(this.servidor);
        while (s.hasNextLine()) {
            Msg msg = ChatProtocol.parse(s.nextLine());
            System.out.println("[DEBUG-RecebedorCli] " + msg);
            //Define o ID do cliente

            if (msg.isInterna()) {
                if (msg.getOp().equals(ChatProtocol.OP_201)) {
                    tela.setId(msg.getMsg());

                    msg = ChatProtocol.createMessage(tela.getId(), "*", tela.getId() + tela.getNome(), true, ChatProtocol.OP_202);

                    tela.saida.println(ChatProtocol.MsgToString(msg));
                } else if (msg.getOp().equals(ChatProtocol.OP_203)) {
                    //Recebi a lista de todos os usuarios
                    //Limpa os clientes
                    tela.getjList1().removeAll();

                    DefaultListModel<User> tmpUsers = new DefaultListModel<>();

                    ArrayList<User> tmpListUsers = ChatProtocol.toUserList(msg);

                    for (User user : tmpListUsers) {
                        tmpUsers.addElement(user);
                    }

                    tela.getjList1().setModel(tmpUsers);
                    tela.setUsuarios(tmpListUsers);

                    tela.getjList1().setSelectedIndex(0);
                } else if (msg.getOp().equals(ChatProtocol.OP_204)) {
                    try {
                        //Encerra a conexão
                        servidor.close();
                        tela.saida.close();
                        tela.clienteskt.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ComunicCli.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (msg.getOp().equals(ChatProtocol.OP_205)) {
                    //Operação de desconexão
                    JOptionPane.showMessageDialog(tela, msg.getMsg());
                    //Elimina as Threads
                    tela.dispose();
                    try {
                        //Encerra a conexão
                        servidor.close();
                        tela.saida.close();
                        tela.clienteskt.close();
                        System.out.println("[DEBUG-ComunicCli] Encerradas as conexões");
                    } catch (IOException ex) {
                        Logger.getLogger(ComunicCli.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                if (msg.getOp().equals(ChatProtocol.OP_101)) {
                    Date d = new Date();
                    //Imprime o nome do cliente que enviou + a mensagem
                    tela.append("(" + d.getHours() + ":" + d.getMinutes() + ") " + tela.getUserName(msg.getFrom()) + " : " + msg.getMsg());
                }
            }
        }
    }
}
