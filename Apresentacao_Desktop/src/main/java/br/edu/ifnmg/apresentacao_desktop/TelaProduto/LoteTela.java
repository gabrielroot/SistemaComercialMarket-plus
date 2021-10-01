/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop.TelaProduto;

import Util.Util;
import br.edu.ifnmg.auxiliares.Estoque;
import br.edu.ifnmg.auxiliares.EstoqueRepositorio;
import br.edu.ifnmg.auxiliares.Lote;
import br.edu.ifnmg.auxiliares.LoteRepositorio;
import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabriel
 */
public class LoteTela extends javax.swing.JInternalFrame implements InternalFrameListener{

    private Estoque estoque;
    private EstoqueRepositorio estoqueRepositorio;
    private LoteRepositorio loteRepositorio;
    
    /**
     * Creates new form Lote
     */
    public LoteTela(Estoque estoque) {
        this.estoqueRepositorio = RepositorioFactory.getEstoqueRepositorio();
        this.loteRepositorio = RepositorioFactory.getLoteRepositorio();
        this.estoque = estoque;
        initComponents();
        renderizarLotes(estoque.getLotes());
    }

   public void renderizarLotes(List<Lote> lotes){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("#");
        modelo.addColumn("ID");
        modelo.addColumn("Lote");
        modelo.addColumn("Em Estoque");
        modelo.addColumn("Nas Prateleiras");
        modelo.addColumn("Fabricação");
        modelo.addColumn("Vencimento");
        
        for(int i=0;i<lotes.size(); i++){
            Vector linha = new Vector();

            linha.add((i+1));
            linha.add(estoque.getLotes().get(i).getId());
            linha.add(estoque.getLotes().get(i).getCodigo());
            linha.add(estoque.getLotes().get(i).getEmEstoque());
            linha.add(estoque.getLotes().get(i).getNasPrateleiras());
            linha.add(Util.getStringDateFromCalendar(lotes.get(i).getDataFabricacao()));
            linha.add(Util.getStringDateFromCalendar(lotes.get(i).getDataValidade()));
            modelo.addRow(linha);
        }
        tableResultadoLote.setModel(modelo);
   }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboVencimento = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResultadoLote = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(208, 208, 208));
        setClosable(true);

        comboVencimento.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        comboVencimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "A Vencer", "Vencidos" }));
        comboVencimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboVencimentoMouseClicked(evt);
            }
        });
        comboVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVencimentoActionPerformed(evt);
            }
        });

        tableResultadoLote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableResultadoLote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResultadoLoteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableResultadoLote);

        jPanel1.setBackground(new java.awt.Color(140, 71, 71));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Lotes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(208, 208, 208));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(109, 46, 46));
        jButton1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Novo Lote");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 140, 40));

        jButton2.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 150, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(comboVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboVencimentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboVencimentoMouseClicked
        
    }//GEN-LAST:event_comboVencimentoMouseClicked

    private void comboVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVencimentoActionPerformed
        if(this.comboVencimento.getSelectedItem().toString().equalsIgnoreCase("A Vencer")){
            List<Lote> lotes = new ArrayList<>();
            for(Lote lote:estoque.getLotes()){
                if(lote.getDataValidade().after(Calendar.getInstance()) || lote.getDataValidade().compareTo(Calendar.getInstance()) == 0){
                    lotes.add(lote);
                }
            }
            renderizarLotes(lotes);
        }else if(this.comboVencimento.getSelectedItem().toString().equalsIgnoreCase("Vencidos")){
            List<Lote> lotes = new ArrayList<>();
            for( Lote lote : estoque.getLotes() ){
                if(lote.getDataValidade().before(Calendar.getInstance())){
                    lotes.add(lote);
                }
            }
            renderizarLotes(lotes);
        }else{
            renderizarLotes(estoque.getLotes());
        }
    }//GEN-LAST:event_comboVencimentoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoteEditar loteEditar = new LoteEditar(this.estoque, new Lote(), "Novo Lote");
        ProdutoTela.jDesktopPane1.add(loteEditar);
        Util.centralizaInternalFrame(loteEditar, this.getParent().getSize());
        loteEditar.addInternalFrameListener(this);
        loteEditar.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableResultadoLoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResultadoLoteMouseClicked
        int linha = this.tableResultadoLote.getSelectedRow();
        long id = (long)this.tableResultadoLote.getValueAt(linha, 1);
        Lote l = null;
        for(Lote find : this.estoque.getLotes()){
            if(find.getId() == id){
                l = find; 
            }
        }
        LoteEditar loteEditar = new LoteEditar(this.estoque, l, "Editar Lote");
        ProdutoTela.jDesktopPane1.add(loteEditar);
        Util.centralizaInternalFrame(loteEditar, this.getParent().getSize());
        loteEditar.addInternalFrameListener(this);
        loteEditar.setVisible(true);
    }//GEN-LAST:event_tableResultadoLoteMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboVencimento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableResultadoLote;
    // End of variables declaration//GEN-END:variables

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        this.estoqueRepositorio.Salvar(this.estoque);
        this.renderizarLotes(this.estoque.getLotes());
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
    }
}
