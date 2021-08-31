/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop;

import br.edu.ifnmg.apresentacao_desktop.Dialogs.DialogPermissao;
import br.edu.ifnmg.apresentacao_desktop.TelaRelatorios.TelaRelatorios;
import br.edu.ifnmg.apresentacao_desktop.TelaPessoas.TelaPessoas;
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
        this.renderJInternalInicio(telaPrincipal);
    }
    
    TelaPrincipal(){
        
    }

    public static JInternalFrame getCurrentFrame() { return currentFrame; }
    public static void setCurrentFrame(JInternalFrame currentFrame) { TelaPrincipal.currentFrame = currentFrame; }

    public static Usuario getUsuario() { return usuario; }
    public static void setUsuario(Usuario usuario) { TelaPrincipal.usuario = usuario; }

    
    
    public void renderJInternalInicio(JInternalFrame frame){
        this.add(frame);
        frame.setVisible(true);

        try {
            frame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
    }
    public boolean renderJInternalFrame(JInternalFrame frame){
        if(TelaPrincipal.getCurrentFrame().getClass() != frame.getClass()){
            TelaPrincipal.getCurrentFrame().setVisible(false);
            TelaPrincipal.setCurrentFrame(frame);
            this.add(frame);
            frame.setVisible(true);
            
            try {
                frame.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            frame.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
            
            return true;
        }
            return false;
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
    public boolean initDesenvolvedor(){
        if(TelaPrincipal.usuario.getId() == 0){
            TelaPrincipal.usuario.setNome("Desenvolvedor");
            TelaPrincipal.usuario.setUsuarioTipo(UsuarioTipo.Gerente);
            System.out.println("[DEV]: Iniciando usuário Desenvolvedor");
            
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

        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        menuMarketPlus = new javax.swing.JMenu();
        menuGerenciamento = new javax.swing.JMenu();
        menuItemPessoas = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar1.setMinimumSize(new java.awt.Dimension(195, 40));
        jMenuBar1.setName(""); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(195, 50));
        jMenuBar1.setName(""); // NOI18N

        jMenuBar1.setPreferredSize(new java.awt.Dimension(195, 50));

        menuMarketPlus.setBackground(new java.awt.Color(48, 150, 52));
        menuMarketPlus.setForeground(new java.awt.Color(61, 16, 16));
        menuMarketPlus.setText("Market +");
        menuMarketPlus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuMarketPlus.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        menuMarketPlus.setMinimumSize(new java.awt.Dimension(3173, 30));
        menuMarketPlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuMarketPlusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuMarketPlusMousePressed(evt);
            }
        });
        jMenuBar1.add(menuMarketPlus);

        menuGerenciamento.setForeground(new java.awt.Color(61, 16, 16));
        menuGerenciamento.setText("Gerenciamento");
        menuGerenciamento.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        menuItemPessoas.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        menuItemPessoas.setText("Pessoas");
        menuItemPessoas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuItemPessoas.setPreferredSize(new java.awt.Dimension(240, 33));
        menuItemPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPessoasActionPerformed(evt);
            }
        });
        menuGerenciamento.add(menuItemPessoas);

        jMenuItem3.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jMenuItem3.setText("Produtos");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.setPreferredSize(new java.awt.Dimension(240, 33));
        menuGerenciamento.add(jMenuItem3);

        jMenuItem5.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jMenuItem5.setText("Financeiro");
        jMenuItem5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem5.setPreferredSize(new java.awt.Dimension(240, 33));
        menuGerenciamento.add(jMenuItem5);

        jMenuItem4.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jMenuItem4.setText("Crediário");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem4.setPreferredSize(new java.awt.Dimension(240, 33));
        menuGerenciamento.add(jMenuItem4);

        jMenuBar1.add(menuGerenciamento);

        menuRelatorios.setBackground(new java.awt.Color(48, 150, 52));
        menuRelatorios.setForeground(new java.awt.Color(61, 16, 16));
        menuRelatorios.setText("Relatórios");
        menuRelatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuRelatorios.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        menuRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRelatoriosMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRelatoriosMouseExited(evt);
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
            .addGap(0, 880, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPessoasActionPerformed
        if(temPermissao("TELA_PESSOAS", true)){
            TelaPessoas pessoas = new TelaPessoas(this);
            this.renderJInternalFrame(pessoas);
        }
    }//GEN-LAST:event_menuItemPessoasActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void menuRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMouseClicked
    }//GEN-LAST:event_menuRelatoriosMouseClicked

    private void menuRelatoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMousePressed
        if(temPermissao("TELA_RELATORIOS", true)){
            TelaRelatorios telaRelatorios = new TelaRelatorios();
            this.renderJInternalFrame(telaRelatorios);
        }
    }//GEN-LAST:event_menuRelatoriosMousePressed

    private void menuMarketPlusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMarketPlusMousePressed
        TelaInicio telaPrincipal = new TelaInicio(this);
        this.renderJInternalFrame(telaPrincipal);
    }//GEN-LAST:event_menuMarketPlusMousePressed

    private void menuMarketPlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMarketPlusMouseExited
        menuMarketPlus.setSelected(false);
    }//GEN-LAST:event_menuMarketPlusMouseExited

    private void menuRelatoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMouseExited
        menuRelatorios.setSelected(false);
    }//GEN-LAST:event_menuRelatoriosMouseExited
    
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
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenu menuGerenciamento;
    private javax.swing.JMenuItem menuItemPessoas;
    private javax.swing.JMenu menuMarketPlus;
    private javax.swing.JMenu menuRelatorios;
    // End of variables declaration//GEN-END:variables
}
