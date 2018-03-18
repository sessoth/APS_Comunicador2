package apsc.server;

import apsc.core.ChatProtocol;
import apsc.core.Msg;
import apsc.core.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class TelaServidor extends javax.swing.JFrame {

    private int porta;
    private ArrayList<User> usuarios;
    private ComunicServ st;

    public TelaServidor() {
        initComponents();
        usuarios = new ArrayList<>();
        //Bloqueia o redimensionamento
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textPorta = new javax.swing.JTextField();
        btIniciar = new javax.swing.JButton();
        btDesligar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaUsers = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        btDesconecta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APSC - Servidor");

        jLabel1.setText("Porta: ");

        textPorta.setText("12345");

        btIniciar.setText("Iniciar");
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });

        btDesligar.setText("Desligar");
        btDesligar.setEnabled(false);
        btDesligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesligarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listaUsers);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        btDesconecta.setText("Desconectar");
        btDesconecta.setEnabled(false);
        btDesconecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesconectaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btDesligar)
                        .addGap(0, 121, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btDesconecta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDesconecta))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btIniciar)
                            .addComponent(btDesligar))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarActionPerformed

        porta = Integer.parseInt(textPorta.getText());

        btIniciar.setEnabled(false);
        btDesligar.setEnabled(true);
        btDesconecta.setEnabled(true);


        st = new ComunicServ(this);
        new Thread(st).start();



    }//GEN-LAST:event_btIniciarActionPerformed

    private void btDesligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesligarActionPerformed

        this.dispose();

    }//GEN-LAST:event_btDesligarActionPerformed

    private void btDesconectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesconectaActionPerformed
        try {
            //pega usuario selecionado na lista
            User u = (User) listaUsers.getSelectedValue();

            //cria mensagen de desconexao
            Msg createMessage = ChatProtocol.createMessage("servidor", u.getId(), "Conexão encerrada.", true, ChatProtocol.OP_205);
            u.getSaidaCliente().println(ChatProtocol.MsgToString(createMessage));

            //Remove o usuário da tela
            DefaultListModel<User> tmpListUsers = (DefaultListModel<User>) listaUsers.getModel();
            tmpListUsers.remove(listaUsers.getSelectedIndex());
            listaUsers.setModel(tmpListUsers);
            
            //Remove o usuário Selecionado da Lista
            getUsuarios().remove(u);

            //fecha conexao do cliente
            u.getSocketCliente().close();
            
            //Atualiza a lista dos usuários (envia)
            st.sendListaUser();
            
        } catch (IOException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btDesconectaActionPerformed

    public int getPorta() {
        return porta;
    }

    public JTextArea getTextArea() {
        return textArea;

    }

    public JList getListaUsers() {
        return listaUsers;
    }

    public void setListaUsers(JList listaUsers) {
        this.listaUsers = listaUsers;
    }

    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<User> getUsuarios() {
        return usuarios;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDesconecta;
    private javax.swing.JButton btDesligar;
    private javax.swing.JButton btIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaUsers;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField textPorta;
    // End of variables declaration//GEN-END:variables

    public String getUserName(String id) {
        String s = "";

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                s = usuarios.get(i).getNome();
            }
        }
        return s;
    }
}
