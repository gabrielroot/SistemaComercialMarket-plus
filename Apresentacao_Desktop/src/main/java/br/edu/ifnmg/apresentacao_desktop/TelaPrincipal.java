/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop;

import br.edu.ifnmg.enums.UsuarioTipo;
import br.edu.ifnmg.logicaAplicacao.Usuario;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author gabriel
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    private static JInternalFrame currentFrame;
    private static Usuario usuario;
    private static Map<UsuarioTipo, ArrayList<String>> permissions;

    
    public TelaPrincipal(Usuario usuario) {
        initComponents();
       
        this.usuario = usuario;
        initDesenvolvedor();
   
        this.permissions = new HashMap<>();
        this.initRemovePermissions();
        
        //Centralizando a tela        
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setTitle("MARKET +");
        
        TelaInicio telaPrincipal = new TelaInicio(this);
        this.currentFrame = telaPrincipal;
        TelaPrincipal.jDesktopPane1.setBackground(null);
        this.renderJInternalInicio(telaPrincipal);
    }
    
    TelaPrincipal(){
        
    }

    public static JInternalFrame getCurrentFrame() { return currentFrame; }
    public static void setCurrentFrame(JInternalFrame currentFrame) { TelaPrincipal.currentFrame = currentFrame; }

    public static Usuario getUsuario() { return usuario; }
    public static void setUsuario(Usuario usuario) { TelaPrincipal.usuario = usuario; }

    
    
    public static void renderJInternalInicio(JInternalFrame frame){
        TelaPrincipal.jDesktopPane1.add(frame);

        try {
            frame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
        frame.setVisible(true);
    }
    public static void renderJInternalFrame(JInternalFrame frame, boolean maximize){
        if(TelaPrincipal.jDesktopPane1.getSelectedFrame().getClass() != frame.getClass()){
            TelaPrincipal.setCurrentFrame(frame);
            TelaPrincipal.jDesktopPane1.add(frame);
            
            if(maximize){
                TelaPrincipal.jDesktopPane1.getSelectedFrame().dispose();
                try {
                    frame.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

                frame.setBorder(null);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
            }
                
            
            frame.setVisible(true);
        }
    }
    public void internalFrameIconified(InternalFrameEvent e) {
        System.out.println("Internal frame iconified"+ e);
    }
    public static void initRemovePermissions(){
        String TELA_PESSOAS = "TELA_PESSOAS";
        String TELA_RELATORIOS = "TELA_RELATORIOS";
        String TELA_COMPRA = "TELA_COMPRA";
        String TELA_PRODUTO = "TELA_PRODUTO";
        String TELA_FINANCEIRO = "TELA_FINANCEIRO";
        String TELA_VENDAS = "TELA_VENDAS";
        String TELA_PESSOAS__ABA_USUARIOS = "TELA_PESSOAS__ABA_USUARIOS";
        String TELA_PESSOAS__ABA_FORNECEDORES = "TELA_PESSOAS__ABA_FORNECEDORES";
        String TELA_PESSOAS__ABA_FUNCIONARIOS = "TELA_PESSOAS__ABA_FUNCIONARIOS";
        
        ArrayList<String> removePermissaoGerente = new ArrayList();

        ArrayList<String> removePermissaoAdm = new ArrayList();
        removePermissaoAdm.add(TELA_VENDAS);
        removePermissaoAdm.add(TELA_RELATORIOS);
        
        ArrayList<String> removePermissaoBalconista = new ArrayList();
        removePermissaoBalconista.add(TELA_COMPRA);
        removePermissaoBalconista.add(TELA_FINANCEIRO);
        removePermissaoBalconista.add(TELA_PRODUTO);
        removePermissaoBalconista.add(TELA_RELATORIOS);
        removePermissaoBalconista.add(TELA_PESSOAS__ABA_USUARIOS);
        removePermissaoBalconista.add(TELA_PESSOAS__ABA_FORNECEDORES);
        removePermissaoBalconista.add(TELA_PESSOAS__ABA_FUNCIONARIOS);
        
        ArrayList<String> removePermissaoCaixa = new ArrayList();
        removePermissaoCaixa.add(TELA_PESSOAS);
        removePermissaoCaixa.add(TELA_RELATORIOS);
        removePermissaoCaixa.add(TELA_COMPRA);
        removePermissaoCaixa.add(TELA_PRODUTO);
        removePermissaoCaixa.add(TELA_FINANCEIRO);
        
        TelaPrincipal.permissions.put(UsuarioTipo.Gerente, removePermissaoGerente);
        TelaPrincipal.permissions.put(UsuarioTipo.Administrador, removePermissaoAdm);
        TelaPrincipal.permissions.put(UsuarioTipo.Balconista, removePermissaoBalconista);
        TelaPrincipal.permissions.put(UsuarioTipo.Caixa, removePermissaoCaixa);
    }
    public static boolean initDesenvolvedor(){
        if(TelaPrincipal.usuario.getId() == 0){
            TelaPrincipal.usuario.setNome("Desenvolvedor");
            TelaPrincipal.usuario.setUsuarioTipo(UsuarioTipo.Gerente);
            System.out.println("[DEV] Iniciando usuário Desenvolvedor");
            
            return true;
        }
        return false;
    }
    public boolean temPermissao(String query, boolean bloquearPai){
        for(String permissaoNegada : permissions.get(TelaPrincipal.usuario.getUsuarioTipo())){
            if(permissaoNegada.equals(query)){
                DialogPermissao acesso = new DialogPermissao(this, bloquearPai);
                acesso.setVisible(true);
                return false;
            }
        }
        
        return true;
    }
    
    public static void centralizaInternalFrame(JInternalFrame frame,Dimension desktopSize) {
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMarketPlus = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuGerenciamento = new javax.swing.JMenu();
        menuItemPessoas = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(169, 124, 124));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar1.setMinimumSize(new java.awt.Dimension(185, 40));
        jMenuBar1.setName(""); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(185, 40));

        menuMarketPlus.setBackground(new java.awt.Color(48, 150, 52));
        menuMarketPlus.setForeground(new java.awt.Color(61, 16, 16));
        menuMarketPlus.setText("Market +");
        menuMarketPlus.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N

        jMenuItem6.setText("Início");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuMarketPlus.add(jMenuItem6);

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuMarketPlus.add(jMenuItem1);

        jMenuBar1.add(menuMarketPlus);

        menuGerenciamento.setForeground(new java.awt.Color(61, 16, 16));
        menuGerenciamento.setText("Gerenciamento");

        menuItemPessoas.setText("Pessoas");
        menuItemPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPessoasActionPerformed(evt);
            }
        });
        menuGerenciamento.add(menuItemPessoas);

        jMenuItem3.setText("Produtos");
        menuGerenciamento.add(jMenuItem3);

        jMenuItem5.setText("Financeiro");
        menuGerenciamento.add(jMenuItem5);

        jMenuItem4.setText("Crediário");
        menuGerenciamento.add(jMenuItem4);

        jMenuBar1.add(menuGerenciamento);

        menuRelatorios.setBackground(new java.awt.Color(48, 150, 52));
        menuRelatorios.setForeground(new java.awt.Color(61, 16, 16));
        menuRelatorios.setText("Relatórios");
        menuRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRelatoriosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuRelatoriosMousePressed(evt);
            }
        });
        jMenuBar1.add(menuRelatorios);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuItemPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPessoasActionPerformed
        if(temPermissao("TELA_PESSOAS", true)){
            TelaPessoas pessoas = new TelaPessoas(this);
            this.renderJInternalFrame(pessoas, true);
        }
    }//GEN-LAST:event_menuItemPessoasActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        TelaInicio telaPrincipal = new TelaInicio(this);
        this.renderJInternalFrame(telaPrincipal, true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void menuRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMouseClicked
    }//GEN-LAST:event_menuRelatoriosMouseClicked

    private void menuRelatoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMousePressed
        if(temPermissao("TELA_RELATORIOS", true)){
            TelaRelatorios telaRelatorios = new TelaRelatorios();
            this.renderJInternalFrame(telaRelatorios, true);
        }
    }//GEN-LAST:event_menuRelatoriosMousePressed
    
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Usuario usuario = new Usuario();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenu menuGerenciamento;
    private javax.swing.JMenuItem menuItemPessoas;
    private javax.swing.JMenu menuMarketPlus;
    private javax.swing.JMenu menuRelatorios;
    // End of variables declaration//GEN-END:variables
}
