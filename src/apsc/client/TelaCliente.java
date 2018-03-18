package apsc.client;

import apsc.core.ChatProtocol;
import apsc.core.Msg;
import apsc.core.User;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class TelaCliente extends javax.swing.JFrame {

    PrintStream saida;
    Socket clienteskt;
    private String host;
    private int porta;
    private String id;
    private String nome;
    private ArrayList<User> usuarios;

    public TelaCliente() {
        initComponents();
        //Bloqueia o redimensionamento
        setResizable(false);
        usuarios = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textIP = new javax.swing.JTextField();
        textPorta = new javax.swing.JTextField();
        textCaixadeEntrada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaUser = new javax.swing.JList();
        textNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btEnviar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btConectar = new javax.swing.JButton();
        btDesconectar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APSC - Cliente");

        textIP.setText("127.0.0.1");
        textIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIPActionPerformed(evt);
            }
        });

        textPorta.setText("12345");
        textPorta.setToolTipText("");

        textCaixadeEntrada.setToolTipText("");

        jScrollPane1.setViewportView(listaUser);

        textNome.setText("Teste");
        textNome.setToolTipText("");

        jLabel1.setText("Porta:");

        btEnviar.setText("Enviar");
        btEnviar.setEnabled(false);
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });

        jLabel2.setText("IP :");

        jLabel3.setText("Nome :");

        btConectar.setText("Conectar");
        btConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConectarActionPerformed(evt);
            }
        });

        btDesconectar.setText("Desconectar");
        btDesconectar.setEnabled(false);
        btDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesconectarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textIP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textPorta, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textCaixadeEntrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btDesconectar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btEnviar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btConectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textIP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textPorta, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textCaixadeEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIPActionPerformed
    }//GEN-LAST:event_textIPActionPerformed

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed

        User u = (User) listaUser.getSelectedValue();


        //Constrói um objeto mensagem
        Msg createMessage = ChatProtocol.createMessage(getId(), u.getId(), textCaixadeEntrada.getText(), false, ChatProtocol.OP_101);

        //Transmite o objeto
        saida.println(ChatProtocol.MsgToString(createMessage));
        //limpa caixade texto
        textCaixadeEntrada.setText("");


}//GEN-LAST:event_btEnviarActionPerformed

    private void btConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConectarActionPerformed


        if (textPorta.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Porta não pode estar em branco!");
        } else {
            if (textIP.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "IP não pode estar em branco!");
            } else {
                if (textNome.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Digite seu nome.");
                } else {
                    textPorta.setEditable(false);
                    textIP.setEditable(false);
                    textNome.setEditable(false);
                    btConectar.setEnabled(false);
                    btDesconectar.setEnabled(true);
                    btEnviar.setEnabled(true);


                    try {
                        this.porta = Integer.parseInt(textPorta.getText());
                        this.host = textIP.getText();
                        this.nome = textNome.getText();
                        executa();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Não foi possível conectar ao servidor!");
                        this.dispose();
                        new TelaCliente().setVisible(true);
                    }
                }
            }
        }

    }//GEN-LAST:event_btConectarActionPerformed

    private void btDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesconectarActionPerformed


        Msg createMessage = ChatProtocol.createMessage(getId(), "servidor", "", true, ChatProtocol.OP_204);
        //Msg createMessage = ChatProtocol.createMessage(getId(), "*", "", true, ChatProtocol.OP_204);

        saida.println(ChatProtocol.MsgToString(createMessage));

        saida.close();
        try {
            clienteskt.close();
        } catch (IOException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();


    }//GEN-LAST:event_btDesconectarActionPerformed

    public void executa() throws UnknownHostException, IOException {


        clienteskt = new Socket(getHost(), getPorta());

        append("Conectado ao servidor!");

        saida = new PrintStream(clienteskt.getOutputStream());

        ComunicCli r = new ComunicCli(clienteskt.getInputStream(), this);
        new Thread(r).start();

    }

//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new TelaCliente().setVisible(true);
//            }
//        });
//    }
    public void append(String mensagen) {
        textArea.setText(textArea.getText() + "\n" + mensagen);
    }

    public JEditorPane getTextArea() {
        return textArea;

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPorta() {
        return Integer.parseInt(textPorta.getText());
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConectar;
    private javax.swing.JButton btDesconectar;
    private javax.swing.JButton btEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaUser;
    private javax.swing.JTextPane textArea;
    private javax.swing.JTextField textCaixadeEntrada;
    private javax.swing.JTextField textIP;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textPorta;
    // End of variables declaration//GEN-END:variables

    public String getNome() {
        return nome;
    }

    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public String getUserName(String id) {
        String s = "";

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                s = usuarios.get(i).getNome();
            }
        }
        return s;
    }

    public JList getjList1() {
        return listaUser;
    }

    public void setjList1(JList jList1) {
        this.listaUser = jList1;
    }
}
