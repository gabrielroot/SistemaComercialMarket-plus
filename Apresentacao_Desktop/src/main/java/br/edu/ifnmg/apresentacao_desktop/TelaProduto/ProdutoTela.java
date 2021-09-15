/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop.TelaProduto;

import Util.Util;
import br.edu.ifnmg.auxiliares.Estoque;
import br.edu.ifnmg.auxiliares.Lote;
import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabriel
 */
public class ProdutoTela extends javax.swing.JInternalFrame implements InternalFrameListener{

    private static Produto produto;
    private Lote lote;
    private ProdutoRepositorio produtoRepositorio;
    private Util util;
    
    /**
     * Creates new form ProdutoEditar
     */
    public ProdutoTela() {
        initComponents();
        ProdutoTela.produto = new Produto();
        Estoque estoque = new Estoque();
        List lotes = new ArrayList<>();
        Lote lote = new Lote();
        lotes.add(lote);
        estoque.setLotes(lotes);
        ProdutoTela.produto.setEstoque(estoque);
        this.produtoRepositorio = RepositorioFactory.getProdutoRepositorio();
        this.util = new Util();
        this.buscarProduto();
        initTabProdutos();
        jDesktopPane1.setBackground(null);
    }

    public static Produto getProduto() { return produto; }
    public static void setProduto(Produto produto) { ProdutoTela.produto = produto; }
    
    private void initTab(){
        switch(jTabbedPane1.getSelectedIndex()){
            case 0:
                initTabProdutos();
                break;
            case 1:
                initTabEstoque();
                break;
        }
    }
    
    private void initTabProdutos(){
        buscarProduto();
    }
    
    private void initTabEstoque(){
        List<Produto> resultado = this.produtoRepositorio.Buscar(produto);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("#");
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Local");
        modelo.addColumn("QTDE. em Estoque");
        modelo.addColumn("QTDE. Min Desejada");
        
        for(int i=0;i<resultado.size(); i++){
            Vector linha = new Vector();
            
            linha.add((i+1));
            linha.add(resultado.get(i).getId());
            linha.add(resultado.get(i).getNome());
            linha.add(resultado.get(i).getEstoque().getLocalizacaoProduto());
            linha.add(resultado.get(i).getEstoque().getSomaLotes());
            linha.add(resultado.get(i).getEstoque().getQuantidadeMinimaDesejada());
            modelo.addRow(linha);
        }
        tableResultadoEstoque.setModel(modelo);
    }
    
    public void buscarProduto(){
        List<Produto> resultado = this.produtoRepositorio.Buscar(ProdutoTela.produto);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("#");
        modelo.addColumn("Código");
        if(checkNome.isSelected()) modelo.addColumn("Nome");
        if(checkDescricao.isSelected()) modelo.addColumn("Descrição");
        modelo.addColumn("QTDE. nas Prateleiras");
        modelo.addColumn("QTDE. MÍN. Atacado");
        if(checkUNDCompra.isSelected()) modelo.addColumn("UND. Compra");
        if(checkUNDVenda.isSelected()) modelo.addColumn("UND. Venda");
        modelo.addColumn("VAL. Varejo");
        modelo.addColumn("VAL. Atacado");
        modelo.addColumn("VAL. Compra");
        modelo.addColumn("Local");
        if(checkQTDEstoque.isSelected()) modelo.addColumn("QTDE. em Estoque");
        modelo.addColumn("QTDE. MÍN. em Estoque");
        
        for(int i=0;i<resultado.size(); i++){
            Vector linha = new Vector();
            
            linha.add((i+1));
            linha.add(resultado.get(i).getId());
            if(checkNome.isSelected())
                linha.add(resultado.get(i).getNome());
            if(checkDescricao.isSelected())
                linha.add(resultado.get(i).getDescricao());
            linha.add(resultado.get(i).getQuantidadePrateleira());
            linha.add(resultado.get(i).getMinimoParaAtacado());
            if(checkUNDCompra.isSelected())
                linha.add(resultado.get(i).getUnidadeMedidaCusto());
            if(checkUNDVenda.isSelected())
                linha.add(resultado.get(i).getUnidadeMedidaVenda());
            linha.add(resultado.get(i).getValorVarejo().toString().replace(".", ","));
            linha.add(resultado.get(i).getValorAtacado().toString().replace(".", ","));
            linha.add(resultado.get(i).getValorCusto().toString().replace(".", ","));
            linha.add(resultado.get(i).getEstoque().getLocalizacaoProduto());
            if(checkQTDEstoque.isSelected())
                linha.add(resultado.get(i).getEstoque().getSomaLotes());
            linha.add(resultado.get(i).getEstoque().getQuantidadeMinimaDesejada());
            modelo.addRow(linha);
        }
        tblResultadoProdutos.setModel(modelo);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new java.awt.Color(208, 208, 208));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jpBG = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultadoProdutos = new javax.swing.JTable();
        checkDescricao = new javax.swing.JCheckBox();
        checkNome = new javax.swing.JCheckBox();
        checkUNDCompra = new javax.swing.JCheckBox();
        checkUNDVenda = new javax.swing.JCheckBox();
        checkQTDEstoque = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableResultadoEstoque = new javax.swing.JTable();

        setBackground(new java.awt.Color(208, 208, 208));
        setClosable(true);

        jDesktopPane1.setBackground(new java.awt.Color(208, 208, 208));

        jpBG.setBackground(new java.awt.Color(140, 71, 71));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gestão de Produtos");

        javax.swing.GroupLayout jpBGLayout = new javax.swing.GroupLayout(jpBG);
        jpBG.setLayout(jpBGLayout);
        jpBGLayout.setHorizontalGroup(
            jpBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpBGLayout.setVerticalGroup(
            jpBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBGLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(208, 208, 208));

        tblResultadoProdutos.setBackground(new java.awt.Color(255, 255, 255));
        tblResultadoProdutos.setForeground(new java.awt.Color(0, 0, 0));
        tblResultadoProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null", "null", "null", "null", "null", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, true, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultadoProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultadoProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblResultadoProdutos);

        checkDescricao.setBackground(new java.awt.Color(163, 124, 124));
        checkDescricao.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        checkDescricao.setForeground(new java.awt.Color(8, 8, 8));
        checkDescricao.setText("Descrição");
        checkDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDescricaoActionPerformed(evt);
            }
        });

        checkNome.setBackground(new java.awt.Color(163, 124, 124));
        checkNome.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        checkNome.setForeground(new java.awt.Color(8, 8, 8));
        checkNome.setSelected(true);
        checkNome.setText("Nome");
        checkNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNomeActionPerformed(evt);
            }
        });

        checkUNDCompra.setBackground(new java.awt.Color(163, 124, 124));
        checkUNDCompra.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        checkUNDCompra.setForeground(new java.awt.Color(8, 8, 8));
        checkUNDCompra.setText("Unidade de Compra");
        checkUNDCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUNDCompraActionPerformed(evt);
            }
        });

        checkUNDVenda.setBackground(new java.awt.Color(163, 124, 124));
        checkUNDVenda.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        checkUNDVenda.setForeground(new java.awt.Color(8, 8, 8));
        checkUNDVenda.setText("Unidade de Venda");
        checkUNDVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUNDVendaActionPerformed(evt);
            }
        });

        checkQTDEstoque.setBackground(new java.awt.Color(163, 124, 124));
        checkQTDEstoque.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        checkQTDEstoque.setForeground(new java.awt.Color(8, 8, 8));
        checkQTDEstoque.setSelected(true);
        checkQTDEstoque.setText("Quantidade em Estoque");
        checkQTDEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkQTDEstoqueActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(8, 8, 8));
        jLabel5.setText("Visualização:");

        jButton1.setBackground(new java.awt.Color(109, 46, 46));
        jButton1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(208, 208, 208));
        jButton2.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(8, 8, 8));
        jButton2.setText("Busca Avançada");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(181, 181, 181));
        jButton3.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(8, 8, 8));
        jButton3.setText("Novo Produto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtNome.setBackground(new java.awt.Color(255, 255, 255));
        txtNome.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtNome.setForeground(new java.awt.Color(0, 0, 0));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(8, 8, 8));
        jLabel2.setText("Nome:");

        jButton4.setBackground(new java.awt.Color(208, 208, 208));
        jButton4.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(8, 8, 8));
        jButton4.setText("Limpar Filtros");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(checkNome, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkUNDCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkUNDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkQTDEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkDescricao)
                    .addComponent(checkNome)
                    .addComponent(checkUNDCompra)
                    .addComponent(checkUNDVenda)
                    .addComponent(checkQTDEstoque)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Produtos", jPanel2);

        tableResultadoEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableResultadoEstoque.setToolTipText("Clique para ver os lotes do produto");
        tableResultadoEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResultadoEstoqueMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableResultadoEstoque);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1194, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Estoque", jPanel3);

        jDesktopPane1.setLayer(jpBG, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jpBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTabbedPane1))
        );

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ProdutoTela.produto.setNome(this.txtNome.getText());
        this.buscarProduto();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblResultadoProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultadoProdutosMouseClicked
        int linha = this.tblResultadoProdutos.getSelectedRow();
        long id = (long)this.tblResultadoProdutos.getValueAt(linha, 1);
        Produto p = produtoRepositorio.Abrir(id); 
        ProdutoEditar produtoEditar = new ProdutoEditar(p, "Editar Produto");
        ProdutoTela.jDesktopPane1.add(produtoEditar);
        Util.centralizaInternalFrame(produtoEditar, this.getSize());
        produtoEditar.addInternalFrameListener(this);
        produtoEditar.setVisible(true);
    }//GEN-LAST:event_tblResultadoProdutosMouseClicked

    private void checkDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDescricaoActionPerformed
        this.buscarProduto();
    }//GEN-LAST:event_checkDescricaoActionPerformed

    private void checkNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNomeActionPerformed
        this.buscarProduto();
    }//GEN-LAST:event_checkNomeActionPerformed

    private void checkUNDCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUNDCompraActionPerformed
        this.buscarProduto();
    }//GEN-LAST:event_checkUNDCompraActionPerformed

    private void checkUNDVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUNDVendaActionPerformed
        this.buscarProduto();
    }//GEN-LAST:event_checkUNDVendaActionPerformed

    private void checkQTDEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkQTDEstoqueActionPerformed
        this.buscarProduto();
    }//GEN-LAST:event_checkQTDEstoqueActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        initTab();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void tableResultadoEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResultadoEstoqueMouseClicked
        int linha = this.tableResultadoEstoque.getSelectedRow();
        long id = (long)this.tableResultadoEstoque.getValueAt(linha, 1);
        Estoque estoque = produtoRepositorio.Abrir(id).getEstoque();
        LoteTela lote = new LoteTela(estoque);
        jDesktopPane1.add(lote);
        Util.centralizaInternalFrame(lote, this.getSize());
        lote.setVisible(true);
    }//GEN-LAST:event_tableResultadoEstoqueMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       ProdutoEditar produtoEditar = new ProdutoEditar(new Produto(), "Novo Produto");
       jDesktopPane1.add(produtoEditar);
       Util.centralizaInternalFrame(produtoEditar, this.getSize());
       produtoEditar.addInternalFrameListener(this);
       produtoEditar.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       ProdutoEditar produtoEditar = new ProdutoEditar(ProdutoTela.produto, "Buscar Produto");
       jDesktopPane1.add(produtoEditar);
       produtoEditar.addInternalFrameListener(this);
       Util.centralizaInternalFrame(produtoEditar, this.getSize());
       produtoEditar.setVisible(true);
       this.buscarProduto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(util.abrirJOptionPane("confirma", "Deseja realmente limpar filtros?", null)){
            ProdutoTela.produto = new Produto();
            this.txtNome.setText("");
            this.buscarProduto();
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkDescricao;
    private javax.swing.JCheckBox checkNome;
    private javax.swing.JCheckBox checkQTDEstoque;
    private javax.swing.JCheckBox checkUNDCompra;
    private javax.swing.JCheckBox checkUNDVenda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpBG;
    private javax.swing.JTable tableResultadoEstoque;
    private javax.swing.JTable tblResultadoProdutos;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        this.buscarProduto();
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
