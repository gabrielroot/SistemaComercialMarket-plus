/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop;

import br.edu.ifnmg.apresentacao_desktop.TelaProduto.ProdutoEditar;
import br.edu.ifnmg.logicaAplicacao.Usuario;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import java.awt.Frame;
import javax.persistence.NoResultException;

/**
 *
 * @author gabriel
 */
public class Login extends javax.swing.JFrame {
    UsuarioRepositorio usuarioRepositorio;

    /**
     * Creates new form Login
     */
    public Login() {
        usuarioRepositorio = RepositorioFactory.getUsuarioRepositorio();
        initComponents();
        //Centralizando a tela        
        this.setLocationRelativeTo(null);
        this.setTitle("LOGIN");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        imgLogo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        inputEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inputSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(96, 16, 16));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jPanel2.add(imgLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 510, 520));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 520));

        jPanel3.setBackground(new java.awt.Color(244, 188, 188));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel3KeyPressed(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputEmail.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        inputEmail.setForeground(new java.awt.Color(0, 0, 0));
        inputEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 30, 30)));
        inputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEmailActionPerformed(evt);
            }
        });
        inputEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEmailKeyPressed(evt);
            }
        });
        jPanel3.add(inputEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 290, 37));

        lblEmail.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(62, 14, 14));
        lblEmail.setText("Email");
        jPanel3.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        lblSenha.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(62, 14, 14));
        lblSenha.setText("Senha");
        jPanel3.add(lblSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 0, 0));
        jLabel3.setFont(new java.awt.Font("sansserif", 0, 40)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(77, 59, 59));
        jLabel3.setText("LOGIN");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        inputSenha.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        inputSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 30, 30)));
        inputSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSenhaActionPerformed(evt);
            }
        });
        inputSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputSenhaKeyPressed(evt);
            }
        });
        jPanel3.add(inputSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 290, 35));

        btnEntrar.setBackground(new java.awt.Color(102, 16, 16));
        btnEntrar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        btnEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEntrarKeyPressed(evt);
            }
        });
        jPanel3.add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 134, 33));

        lblError.setBackground(new java.awt.Color(160, 63, 65));
        lblError.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 63, 65));
        jPanel3.add(lblError, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 455, -1, -1));

        jLabel2.setBackground(new java.awt.Color(160, 63, 65));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 40, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minimize.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 40, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 400, 519));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyPressed

    }//GEN-LAST:event_jPanel3KeyPressed

    private void btnEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEntrarKeyPressed

    }//GEN-LAST:event_btnEntrarKeyPressed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        this.logar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void inputSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSenhaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            this.logar();
        }
    }//GEN-LAST:event_inputSenhaKeyPressed

    private void inputSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSenhaActionPerformed

    private void inputEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEmailKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            inputSenha.requestFocus();
        }
    }//GEN-LAST:event_inputEmailKeyPressed

    private void inputEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmailActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void logar(){
        Usuario user;
        
        try{
            user = usuarioRepositorio.Autenticar(inputEmail.getText(), String.valueOf(inputSenha.getPassword()));
        }catch(NoResultException ex){
            user = null;
        }
        
        if(user != null){
            TelaPrincipal telaPrincipal = new TelaPrincipal(user);
            this.setVisible(false);
            telaPrincipal.setVisible(true);
        }else{
            inputSenha.selectAll();
            lblError.setText("Usuário e/ou senha inválidos!");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel imgLogo;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JPasswordField inputSenha;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblSenha;
    // End of variables declaration//GEN-END:variables
}
