/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop.TelaPessoas;

import Util.Util;
import br.edu.ifnmg.apresentacao_desktop.TelaPrincipal;
import br.edu.ifnmg.enums.UsuarioTipo;
import br.edu.ifnmg.logicaAplicacao.Funcionario;
import br.edu.ifnmg.logicaAplicacao.Usuario;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class UsuarioEditar extends javax.swing.JInternalFrame {

    private TelaPrincipal telaPrincipal;
    private Usuario usuario;
    private UsuarioRepositorio usuarioRepositorio;
    private Funcionario funcionarioSelecionado;
    private Util util;
    
    /**
     * Creates new form TelaNovoUsuario
     */
    public UsuarioEditar(TelaPrincipal telaPrincipal, String title, Usuario usuario) {
        this.usuarioRepositorio = RepositorioFactory.getUsuarioRepositorio();
        this.usuario = usuario;
        this.telaPrincipal = telaPrincipal;
        this.funcionarioSelecionado = null;
        initComponents();
        setComponentes();
        this.labelTitulo.setText(title);
        this.util  = new Util();
    }

    private void setComponentes(){
        
        this.psswdSenha1.setText(this.usuario.getSenha());
        this.psswdSenha2.setText(this.usuario.getSenha());
        
        this.txtSelecionar.setText("FUNCIONARIO FIXO");
        
        this.comboTipo.removeAllItems();
        for(int i = 0; i < UsuarioTipo.values().length ; i++){
            this.comboTipo.addItem(UsuarioTipo.values()[i].toString());
            if(this.usuario.getUsuarioTipo()== UsuarioTipo.values()[i]){
                this.comboTipo.setSelectedIndex(i);
            }
        }
    }

    private boolean getComponentes(){
        if(
                this.psswdSenha1.getPassword() == null ||
                this.psswdSenha2.getPassword() == null ||
                this.txtSelecionar == null
          ){
            return false;
        }
        this.funcionarioSelecionado = new Funcionario();
        this.funcionarioSelecionado.setNome("FUNCIONARIO FIXO");
        
        this.usuario.setSenha(this.psswdSenha1.getPassword().toString());
        
        for(UsuarioTipo usuarioTipo : UsuarioTipo.values()){
            if(usuarioTipo.toString().equals(this.comboTipo.getSelectedItem())){
                this.usuario.setUsuarioTipo(usuarioTipo);
            }
        }
        
       
        Usuario novoUsuario = (Usuario) funcionarioSelecionado;
        novoUsuario.setSenha(usuario.getSenha());
        novoUsuario.setUsuarioTipo(usuario.getUsuarioTipo());
        
        return true;
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
        labelTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtSelecionar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        psswdSenha1 = new javax.swing.JPasswordField();
        psswdSenha2 = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(208, 208, 208));
        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(140, 71, 71));

        labelTitulo.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setText("Editar Usuário");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelTitulo)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(208, 208, 208));

        txtSelecionar.setBackground(new java.awt.Color(255, 255, 255));
        txtSelecionar.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtSelecionar.setText("[Selecionar Funcionario]");
        txtSelecionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtSelecionar.setEnabled(false);
        txtSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSelecionarMouseClicked(evt);
            }
        });
        txtSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSelecionarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(140, 71, 71));
        jButton1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(54, 54, 54));
        jLabel2.setText("Tipo de Usuário");

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(54, 54, 54));
        jLabel3.setText("Senha");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(54, 54, 54));
        jLabel4.setText("Confirme a Senha");

        jButton2.setBackground(new java.awt.Color(109, 46, 46));
        jButton2.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(181, 181, 181));
        jButton3.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        comboTipo.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Caixa" }));

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(54, 54, 54));
        jLabel5.setText("Nome do Funcionário");

        psswdSenha1.setBackground(new java.awt.Color(255, 255, 255));
        psswdSenha1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        psswdSenha1.setForeground(new java.awt.Color(0, 0, 0));

        psswdSenha2.setBackground(new java.awt.Color(255, 255, 255));
        psswdSenha2.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        psswdSenha2.setForeground(new java.awt.Color(0, 0, 0));

        jButton4.setBackground(new java.awt.Color(208, 208, 208));
        jButton4.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(psswdSenha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(psswdSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(psswdSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(psswdSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(this.util.abrirJOptionPane("confirma", "Deseja realmente Salvar?",this)){
            if(this.getComponentes()){
                if(this.usuarioRepositorio.Salvar(this.usuario)){
                    util.abrirJOptionPane("sucesso","",this);
                    this.dispose();
                }else{
                    util.abrirJOptionPane("erro","",this);
                }
            }else{
                util.abrirJOptionPane("erro","Preencha todos os campos!",this);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FuncionarioSelecionar selecionar = new FuncionarioSelecionar();
        TelaPessoas.getjDesktopPane1().add(selecionar);
        selecionar.setVisible(true);
        Util.centralizaInternalFrame(selecionar, TelaPessoas.getjDesktopPane1().getSize());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSelecionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSelecionarActionPerformed

    private void txtSelecionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSelecionarMouseClicked
        FuncionarioSelecionar selecionar = new FuncionarioSelecionar();
        TelaPessoas.getjDesktopPane1().add(selecionar);
        selecionar.setVisible(true);
        Util.centralizaInternalFrame(selecionar, TelaPessoas.getjDesktopPane1().getSize());
    }//GEN-LAST:event_txtSelecionarMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(this.util.abrirJOptionPane("confirma","",this)){
            if(this.getComponentes()){
                if(this.usuarioRepositorio.Apagar(this.usuario)){
                    util.abrirJOptionPane("sucesso","",this);
                    this.dispose();
                }else{
                    util.abrirJOptionPane("erro","",this);
                }
            }else{
                util.abrirJOptionPane("erro","Erro ao carregar usuário.",this);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPasswordField psswdSenha1;
    private javax.swing.JPasswordField psswdSenha2;
    private javax.swing.JTextField txtSelecionar;
    // End of variables declaration//GEN-END:variables
}
