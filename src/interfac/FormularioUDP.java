package interfac;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class FormularioUDP extends javax.swing.JDialog {

    public FormularioUDP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnReq1 = new javax.swing.JButton();
        btnReq2 = new javax.swing.JButton();
        btnReq3 = new javax.swing.JButton();
        btnReq4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResposta = new javax.swing.JTextArea();
        txtCampo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplicação Distribuída com API Java UDP");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nome de usuário");

        btnReq1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReq1.setText("1. Solicitar título de filme não avaliado");
        btnReq1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReq1ActionPerformed(evt);
            }
        });

        btnReq2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReq2.setText("2. Avaliar filme/série");
        btnReq2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReq2ActionPerformed(evt);
            }
        });

        btnReq3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReq3.setText("3. Recomendar filme/série");
        btnReq3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReq3ActionPerformed(evt);
            }
        });

        btnReq4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReq4.setText("4. Lista de avaliação");
        btnReq4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReq4ActionPerformed(evt);
            }
        });

        txtResposta.setEditable(false);
        txtResposta.setColumns(20);
        txtResposta.setRows(5);
        jScrollPane1.setViewportView(txtResposta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReq1)
                            .addComponent(btnReq2))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReq3)
                            .addComponent(btnReq4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReq1)
                    .addComponent(btnReq3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReq2)
                    .addComponent(btnReq4))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReq1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReq1ActionPerformed
//        txtResposta.setText();
    }//GEN-LAST:event_btnReq1ActionPerformed

    private void btnReq2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReq2ActionPerformed
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 3, 1);
        JSpinner spinner = new JSpinner(spinnerModel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Nota do Filme:"));
        panel.add(spinner);

        int result = JOptionPane.showConfirmDialog(null, panel, "Avalie o Filme", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int nota = (int) spinner.getValue();
            if(txtCampo.getText().isEmpty()){
                txtResposta.setText("2;Usuário não existe.\n");
            } else {
                txtResposta.setText("2;" + txtCampo.getText() + ": Filme - " + nota + ".\n");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Avaliação cancelada.");
        }
    }//GEN-LAST:event_btnReq2ActionPerformed

    private void btnReq3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReq3ActionPerformed
//        txtResposta.setText();
    }//GEN-LAST:event_btnReq3ActionPerformed

    private void btnReq4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReq4ActionPerformed
//        txtResposta.setText();
    }//GEN-LAST:event_btnReq4ActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioUDP dialog = new FormularioUDP(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReq1;
    private javax.swing.JButton btnReq2;
    private javax.swing.JButton btnReq3;
    private javax.swing.JButton btnReq4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCampo;
    private javax.swing.JTextArea txtResposta;
    // End of variables declaration//GEN-END:variables
}
