/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop;

import Util.Util;
import br.edu.ifnmg.apresentacao_desktop.TelaProduto.ProdutoTela;
import br.edu.ifnmg.apresentacao_desktop.TelaPessoas.TelaPessoas;
import br.edu.ifnmg.enums.UsuarioTipo;
import br.edu.ifnmg.logicaAplicacao.Usuario;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

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
    private  Util util;
    
    public TelaPrincipal(Usuario usuario) {
        initComponents();
       
        TelaPrincipal.usuario = usuario;
        this.util = new Util();
        
        initDesenvolvedor(true);
   
        TelaPrincipal.permissions = new HashMap<>();
        TelaPrincipal.initRemovePermissions();
        
        //Centralizando a tela        
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setTitle("Market +");
        
        
        TelaInicio telaPrincipal = new TelaInicio(this);
        TelaPrincipal.currentFrame = telaPrincipal;
        this.renderJInternalInicio(telaPrincipal);
         Toolkit tk = Toolkit.getDefaultToolkit();
                            tk.beep();
                            
    }
    
    public TelaPrincipal(){
        
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
            TelaPrincipal.getCurrentFrame().dispose();
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
        String TELA_CAIXA = "TELA_CAIXA";
        String TELA_PESSOAS__ABA_USUARIOS = "TELA_PESSOAS__ABA_USUARIOS";
        String TELA_PESSOAS__ABA_FORNECEDORES = "TELA_PESSOAS__ABA_FORNECEDORES";
        String TELA_PESSOAS__ABA_FUNCIONARIOS = "TELA_PESSOAS__ABA_FUNCIONARIOS";
        
        ArrayList<String> removePermissaoGerente = new ArrayList();

        ArrayList<String> removePermissaoAdm = new ArrayList();
        removePermissaoAdm.add(TELA_CAIXA);
        removePermissaoAdm.add(TELA_RELATORIOS);
        
        ArrayList<String> removePermissaoBalconista = new ArrayList();
        removePermissaoBalconista.add(TELA_CAIXA);
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
    public void initDesenvolvedor(boolean iniciarDev){
        if(iniciarDev){
            if(TelaPrincipal.usuario.getId() == 0){
                System.out.println("[DEV]: Iniciando usuário Desenvolvedor");
                UsuarioRepositorio usuarioRepositorio = RepositorioFactory.getUsuarioRepositorio();
                Usuario dev = usuarioRepositorio.Abrir(1000L);
                TelaPrincipal.setUsuario(dev);
            }
        }else if(TelaPrincipal.usuario.getId() == 0){
            util.abrirJOptionPane("informacao", "Tentando iniciar o programa sem autenticação. encerrando...", null);
            System.exit(0);
        }
    }
    public boolean temPermissao(String query){
        for(String permissaoNegada : permissions.get(TelaPrincipal.usuario.getUsuarioTipo())){
            if(permissaoNegada.equals(query)){      
                return false;
            }
        }
        
        return true;
    }

    private void carregarRelatorio(String caminho_relatorio, Map parametros) {
        try {
            // Carrega o Driver do MySQL
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/marketplus","root","market+");
            
            // Abrindo e compilando o arquivo do relatório
            JasperReport relatorio = JasperCompileManager.compileReport(caminho_relatorio);
            
            // Preencher com dados o relatório
            JasperPrint relatorio_preenchido = JasperFillManager.fillReport(relatorio, parametros, conexao);
            
            // Mostra a tela de visualização do relatório
            JasperViewer.viewReport(relatorio_preenchido,false);
            conexao.close();
        } catch (JRException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            util.abrirJOptionPane("erro", "Erro ao carregar relatório!",null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            util.abrirJOptionPane("erro", "Erro ao carregar Driver do Banco de Dados!",null);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            util.abrirJOptionPane("erro", "Erro ao carregar dados do relatório!",null);
        }
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

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
        menuGerenciamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
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
        menuRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatoriosActionPerformed(evt);
            }
        });

        jMenuItem1.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jMenuItem1.setText("Produtos Vencidos");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.setPreferredSize(new java.awt.Dimension(240, 33));
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuRelatorios.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jMenuItem2.setText("Produtos a Vencer");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.setPreferredSize(new java.awt.Dimension(240, 33));
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuRelatorios.add(jMenuItem2);

        jMenuItem6.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jMenuItem6.setText("Produtos para Repor");
        jMenuItem6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem6.setPreferredSize(new java.awt.Dimension(240, 33));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuRelatorios.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuItem7.setText("Vendas por funcionarios");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuRelatorios.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuItem8.setText("Produtos mais e menos vendidos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuRelatorios.add(jMenuItem8);

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
        if(temPermissao("TELA_PESSOAS")){
            TelaPessoas pessoas = new TelaPessoas(this);
            this.renderJInternalFrame(pessoas);
        }
    }//GEN-LAST:event_menuItemPessoasActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if(temPermissao("TELA_RELATORIOS")){
            this.carregarRelatorio("Relatorios/ProdutosParaRepor.jrxml", null);
        }else{
            util.abrirJOptionPane("permissao", "",null);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void menuRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMouseClicked
    }//GEN-LAST:event_menuRelatoriosMouseClicked

    private void menuRelatoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMousePressed

    }//GEN-LAST:event_menuRelatoriosMousePressed

    private void menuMarketPlusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMarketPlusMousePressed
        TelaInicio telaPrincipal = new TelaInicio(this);
        this.renderJInternalFrame(telaPrincipal);
    }//GEN-LAST:event_menuMarketPlusMousePressed

    private void menuMarketPlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMarketPlusMouseExited
        menuMarketPlus.setSelected(false);
    }//GEN-LAST:event_menuMarketPlusMouseExited

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       if(temPermissao("TELA_PRODUTO")){
            ProdutoTela produtoEditar = new ProdutoTela();
            this.renderJInternalFrame(produtoEditar);
        }else{
            util.abrirJOptionPane("permissao", "",null);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void menuRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatoriosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRelatoriosActionPerformed

    private void menuRelatoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatoriosMouseExited
        menuRelatorios.setSelected(false);
    }//GEN-LAST:event_menuRelatoriosMouseExited

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(temPermissao("TELA_RELATORIOS")){
            this.carregarRelatorio("Relatorios/ProdutosAVencer.jrxml", null);
        }else{
            util.abrirJOptionPane("permissao", "",null);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked

    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(temPermissao("TELA_RELATORIOS")){
            this.carregarRelatorio("Relatorios/ProdutosVencidos.jrxml", null);
        }else{
            util.abrirJOptionPane("permissao", "",null);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
         if(temPermissao("TELA_RELATORIOS")){
            this.carregarRelatorio("Relatorios/Vendasporfuncionario.jrxml", null);
        }else{
            util.abrirJOptionPane("permissao", "",null);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if(temPermissao("TELA_RELATORIOS")){
            this.carregarRelatorio("Relatorios/maisemenos.jrxml", null);
        }else{
            util.abrirJOptionPane("permissao", "",null);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    
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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenu menuGerenciamento;
    private javax.swing.JMenuItem menuItemPessoas;
    private javax.swing.JMenu menuMarketPlus;
    private javax.swing.JMenu menuRelatorios;
    // End of variables declaration//GEN-END:variables
}
