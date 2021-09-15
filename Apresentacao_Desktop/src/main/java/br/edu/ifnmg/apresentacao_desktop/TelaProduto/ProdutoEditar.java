/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop.TelaProduto;

import br.edu.ifnmg.enums.LocalizacaoProduto;
import br.edu.ifnmg.enums.UnidadeMedida;
import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import Util.Util;
import br.edu.ifnmg.auxiliares.Lote;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author gabriel
 */
public class ProdutoEditar extends javax.swing.JInternalFrame{
    static Produto produto;
    private Util util;
    private ProdutoRepositorio produtoRepositorio;
    
    /**
     * Creates new form ProdutoEditar
     */
    public ProdutoEditar(Produto produto, String title) {
        ProdutoEditar.produto = produto;
        this.produtoRepositorio = RepositorioFactory.getProdutoRepositorio();
        this.util = new Util();
        initComponents();
        this.txtTitle.setText(title);
        if(!title.equalsIgnoreCase("editar produto")){
            btnDelete.setVisible(false);
        }
        if(!title.equalsIgnoreCase("buscar produto")){
            btnBuscar.setVisible(false);
        }
        if(title.equalsIgnoreCase("buscar produto")){
            btnAddLote.setVisible(false);
        }
        if(title.equalsIgnoreCase("buscar produto")){
            btnSalvar.setVisible(false);
        }
        
        this.setComponentes();
    }

    public static Produto getProduto() { return produto; }
    public static void setProduto(Produto produto) { ProdutoEditar.produto = produto; }
    
    private void setComponentes(){
        if(this.txtTitle.getText().equalsIgnoreCase("buscar produto")){
            this.comboLocal.addItem("Todas");
            this.comboUNDVenda.addItem("Todas");
            this.comboUNDCompra.addItem("Todos");
        }
        
        for(int i = 0; i < UnidadeMedida.values().length ; i++){
            this.comboUNDCompra.addItem(UnidadeMedida.values()[i].toString());
            this.comboUNDVenda.addItem(UnidadeMedida.values()[i].toString());

            if(ProdutoEditar.produto.getUnidadeMedidaCusto() == UnidadeMedida.values()[i]){
                this.comboUNDCompra.setSelectedIndex(i);
            }
            
            if(ProdutoEditar.produto.getUnidadeMedidaVenda() == UnidadeMedida.values()[i]){
                this.comboUNDVenda.setSelectedIndex(i);
            }
        }

        for(int i = 0; i < LocalizacaoProduto.values().length ; i++){
            this.comboLocal.addItem(LocalizacaoProduto.values()[i].toString());

            if(ProdutoEditar.produto.getEstoque().getLocalizacaoProduto() == LocalizacaoProduto.values()[i]){
                this.comboLocal.setSelectedIndex(i);
            }
        }

        this.txtNome.setText(ProdutoEditar.produto.getNome());
        this.txtCode.setText(ProdutoEditar.produto.getId().toString());
        this.txtDescricao.setText(ProdutoEditar.produto.getDescricao());
        this.txtQTDEAtacado.setText(
                String.valueOf(ProdutoEditar.produto.getMinimoParaAtacado() == -1? 
                "":
                ProdutoEditar.produto.getMinimoParaAtacado()
        ));
        this.txtQTDEEstoque.setText(
                String.valueOf(ProdutoEditar.produto.getEstoque().getQuantidadeMinimaDesejada() == -1? 
                "":
                ProdutoEditar.produto.getEstoque().getQuantidadeMinimaDesejada()
        ));
        this.txtQTDEPrateleiras.setText(
                String.valueOf(ProdutoEditar.produto.getQuantidadePrateleira() == -1? 
                "":
                ProdutoEditar.produto.getQuantidadePrateleira()
        ));

        this.txtValorCompra.setText(
                ProdutoEditar.produto.getValorCusto() == null?
                null:
                ProdutoEditar.produto.getValorCusto().toString().replace(".", ",")
        );
        this.txtValorAtacado.setText(
                ProdutoEditar.produto.getValorAtacado() == null?
                null:
                ProdutoEditar.produto.getValorAtacado().toString().replace(".", ",")
        );
        this.txtValorVarejo.setText(
                ProdutoEditar.produto.getValorVarejo() == null?
                null:
                ProdutoEditar.produto.getValorVarejo().toString().replace(".", ",")
        );
    }

    private boolean getComponentes(boolean preencherTudo){
        if(
            preencherTudo &&
            (
                this.txtNome.getText().isEmpty() ||
                this.txtValorCompra.getText().isEmpty() ||
                this.txtDescricao.getText().isEmpty() ||
                this.txtQTDEAtacado.getText().isEmpty() ||
                this.txtQTDEEstoque.getText().isEmpty() ||
                this.txtQTDEPrateleiras.getText().isEmpty() ||
                this.txtValorAtacado.getText().isEmpty() ||
                this.txtValorVarejo.getText().isEmpty()
            )
          ){
            return false;
        }
        ProdutoEditar.produto.setNome(this.txtNome.getText());
        ProdutoEditar.produto.setDescricao(this.txtDescricao.getText());

        ProdutoEditar.produto.setMinimoParaAtacado(
                Integer.parseInt(this.txtQTDEAtacado.getText().length() == 0?
                    "-1":
                    this.txtQTDEAtacado.getText()
                ));
        ProdutoEditar.produto.getEstoque().setQuantidadeMinimaDesejada(
                Integer.parseInt(this.txtQTDEEstoque.getText().length() == 0?
                    "-1":
                    this.txtQTDEEstoque.getText()
                ));
        ProdutoEditar.produto.setQuantidadePrateleira(Integer.parseInt(
                this.txtQTDEPrateleiras.getText().length() == 0?
                "-1":
                this.txtQTDEPrateleiras.getText()
        ));

        ProdutoEditar.produto.setValorCusto(
            this.txtValorCompra.getText().length() == 0?
            null:
            new BigDecimal(this.txtValorCompra.getText().replace(",", "."))
        );

        ProdutoEditar.produto.setValorAtacado(
            this.txtValorAtacado.getText().length() == 0?
            null:
            new BigDecimal(this.txtValorAtacado.getText().replace(",", "."))
        );

        ProdutoEditar.produto.setValorVarejo(
                this.txtValorVarejo.getText().length() == 0?
                null:
                new BigDecimal(
                this.txtValorVarejo.getText().replace(",", "."))
        );
        
        boolean venda = false;
        boolean compra = false;
        boolean local = false;
        
        for(int i = 0; i<LocalizacaoProduto.values().length; i++){
            if(LocalizacaoProduto.values()[i].toString().equals(this.comboLocal.getSelectedItem())){
                ProdutoEditar.produto.getEstoque().setLocalizacaoProduto(LocalizacaoProduto.values()[i]);
                local = true;
            }
        }
        
        for(UnidadeMedida unidadeMedida : UnidadeMedida.values()){
            if(unidadeMedida.toString().equals(this.comboUNDCompra.getSelectedItem())){
                ProdutoEditar.produto.setUnidadeMedidaCusto(unidadeMedida);
                compra = true;
            }

            if(unidadeMedida.toString().equals(this.comboUNDVenda.getSelectedItem())){
                ProdutoEditar.produto.setUnidadeMedidaVenda(unidadeMedida);
                venda = true;
            }
        }
        
        if(this.txtTitle.getText().equalsIgnoreCase("buscar produto")){
            if(!venda){
                ProdutoEditar.produto.setUnidadeMedidaVenda(null);
            }
            if(!compra){
                ProdutoEditar.produto.setUnidadeMedidaCusto(null);
            }
            if(!local){
                ProdutoEditar.produto.getEstoque().setLocalizacaoProduto(null);
            }
        }
        
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
        txtTitle = new javax.swing.JLabel();
        Bg = new javax.swing.JPanel();
        Components = new javax.swing.JPanel();
        txtCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboUNDVenda = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        comboUNDCompra = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtQTDEEstoque = new javax.swing.JFormattedTextField();
        txtQTDEAtacado = new javax.swing.JFormattedTextField();
        txtQTDEPrateleiras = new javax.swing.JFormattedTextField();
        txtValorAtacado = new javax.swing.JFormattedTextField();
        txtValorVarejo = new javax.swing.JFormattedTextField();
        txtValorCompra = new javax.swing.JFormattedTextField();
        comboLocal = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        footer = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnAddLote = new javax.swing.JButton();

        setBackground(new java.awt.Color(208, 208, 208));
        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(140, 71, 71));

        txtTitle.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        txtTitle.setForeground(new java.awt.Color(255, 255, 255));
        txtTitle.setText("Editar Produto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(txtTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(txtTitle)
                .addGap(15, 15, 15))
        );

        Bg.setBackground(new java.awt.Color(208, 208, 208));

        Components.setBackground(new java.awt.Color(208, 208, 208));

        txtCode.setEditable(false);
        txtCode.setBackground(new java.awt.Color(255, 255, 255));
        txtCode.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtCode.setForeground(new java.awt.Color(8, 8, 8));
        txtCode.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(8, 8, 8));
        jLabel2.setText("Código");

        txtNome.setBackground(new java.awt.Color(255, 255, 255));
        txtNome.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtNome.setForeground(new java.awt.Color(8, 8, 8));

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(8, 8, 8));
        jLabel3.setText("Nome");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(8, 8, 8));
        jLabel4.setText("Valor para Atacado");

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(8, 8, 8));
        jLabel5.setText("Descrição");

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(8, 8, 8));
        jLabel6.setText("Valor para Varejo");

        comboUNDVenda.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(8, 8, 8));
        jLabel7.setText("Unidade de Venda");

        comboUNDCompra.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(8, 8, 8));
        jLabel8.setText("Unidade de Compra");

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(8, 8, 8));
        jLabel9.setText("Quantidade nas prateleiras");

        jLabel10.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(8, 8, 8));
        jLabel10.setText("Quantidade min. para Atacado");

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(8, 8, 8));
        jLabel11.setText("Quantidade min. para Estoque");

        jLabel12.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(8, 8, 8));
        jLabel12.setText("Valor de Compra");

        txtQTDEEstoque.setBackground(new java.awt.Color(255, 255, 255));
        txtQTDEEstoque.setForeground(new java.awt.Color(8, 8, 8));
        txtQTDEEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        txtQTDEEstoque.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtQTDEAtacado.setBackground(new java.awt.Color(255, 255, 255));
        txtQTDEAtacado.setColumns(10);
        txtQTDEAtacado.setForeground(new java.awt.Color(8, 8, 8));
        txtQTDEAtacado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        txtQTDEAtacado.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtQTDEPrateleiras.setBackground(new java.awt.Color(255, 255, 255));
        txtQTDEPrateleiras.setForeground(new java.awt.Color(8, 8, 8));
        txtQTDEPrateleiras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        txtQTDEPrateleiras.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtValorAtacado.setBackground(new java.awt.Color(255, 255, 255));
        txtValorAtacado.setForeground(new java.awt.Color(8, 8, 8));
        txtValorAtacado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.##"))));
        txtValorAtacado.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtValorVarejo.setBackground(new java.awt.Color(255, 255, 255));
        txtValorVarejo.setForeground(new java.awt.Color(8, 8, 8));
        txtValorVarejo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.##"))));
        txtValorVarejo.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtValorCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtValorCompra.setForeground(new java.awt.Color(8, 8, 8));
        txtValorCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtValorCompra.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        comboLocal.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(8, 8, 8));
        jLabel15.setText("Localização no estoque");

        txtDescricao.setBackground(new java.awt.Color(255, 255, 255));
        txtDescricao.setColumns(18);
        txtDescricao.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtDescricao.setForeground(new java.awt.Color(8, 8, 8));
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        javax.swing.GroupLayout ComponentsLayout = new javax.swing.GroupLayout(Components);
        Components.setLayout(ComponentsLayout);
        ComponentsLayout.setHorizontalGroup(
            ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComponentsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ComponentsLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ComponentsLayout.createSequentialGroup()
                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(ComponentsLayout.createSequentialGroup()
                                .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(ComponentsLayout.createSequentialGroup()
                                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(26, 26, 26))
                                    .addGroup(ComponentsLayout.createSequentialGroup()
                                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(txtQTDEPrateleiras, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ComponentsLayout.createSequentialGroup()
                                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtQTDEAtacado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtQTDEEstoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3)
                                    .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(comboUNDVenda, javax.swing.GroupLayout.Alignment.LEADING, 0, 278, Short.MAX_VALUE)
                                        .addComponent(txtNome)
                                        .addComponent(comboUNDCompra, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel15)
                                        .addComponent(comboLocal, 0, 278, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtValorAtacado)
                                    .addComponent(txtValorVarejo)
                                    .addGroup(ComponentsLayout.createSequentialGroup()
                                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel12)
                                            .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        ComponentsLayout.setVerticalGroup(
            ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComponentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ComponentsLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorAtacado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorVarejo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorCompra))
                    .addComponent(comboUNDCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ComponentsLayout.createSequentialGroup()
                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ComponentsLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ComponentsLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ComponentsLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQTDEPrateleiras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ComponentsLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboUNDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ComponentsLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ComponentsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(ComponentsLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(txtQTDEAtacado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(18, 18, 18)
                .addGroup(ComponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ComponentsLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ComponentsLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQTDEEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        footer.setBackground(new java.awt.Color(208, 208, 208));
        footer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(181, 181, 181));
        jButton2.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(8, 8, 8));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        footer.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 158, 37));

        btnBuscar.setBackground(new java.awt.Color(109, 46, 46));
        btnBuscar.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        footer.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 158, 37));

        btnDelete.setBackground(new java.awt.Color(208, 208, 208));
        btnDelete.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(8, 8, 8));
        btnDelete.setText("Remover");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        footer.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 158, 37));

        btnSalvar.setBackground(new java.awt.Color(109, 46, 46));
        btnSalvar.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        footer.add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 158, 37));

        btnAddLote.setBackground(new java.awt.Color(184, 139, 139));
        btnAddLote.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        btnAddLote.setForeground(new java.awt.Color(8, 8, 8));
        btnAddLote.setText("Cadastrar Lote");
        btnAddLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLoteActionPerformed(evt);
            }
        });
        footer.add(btnAddLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 158, 37));

        javax.swing.GroupLayout BgLayout = new javax.swing.GroupLayout(Bg);
        Bg.setLayout(BgLayout);
        BgLayout.setHorizontalGroup(
            BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Components, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(footer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        BgLayout.setVerticalGroup(
            BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgLayout.createSequentialGroup()
                .addComponent(Components, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.getComponentes(false);
        ProdutoTela.setProduto(ProdutoEditar.produto);
        this.dispose();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(this.util.abrirJOptionPane("confirma","",this)){
            if(this.produtoRepositorio.Apagar(ProdutoEditar.produto)){
                util.abrirJOptionPane("sucesso","",this);
                this.dispose();
            }else{
                util.abrirJOptionPane("erro","",this);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(this.util.abrirJOptionPane("confirma","Deseja realmente persistir as alterações?",this)){
            if(this.getComponentes(true)){
                if(this.produtoRepositorio.Salvar(ProdutoEditar.produto)){
                    util.abrirJOptionPane("sucesso","",this);
                    this.dispose();
                }else{
                    util.abrirJOptionPane("erro","",this);
                }
            }else{
                util.abrirJOptionPane("erro","Preencha todos os campos!",this);
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAddLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLoteActionPerformed
                this.getComponentes(false);
                LoteEditar loteEditar = new LoteEditar();
                ProdutoTela.jDesktopPane1.add(loteEditar);
                Util.centralizaInternalFrame(loteEditar, this.getParent().getSize());
                loteEditar.setVisible(true);
    }//GEN-LAST:event_btnAddLoteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bg;
    private javax.swing.JPanel Components;
    private javax.swing.JButton btnAddLote;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> comboLocal;
    private javax.swing.JComboBox<String> comboUNDCompra;
    private javax.swing.JComboBox<String> comboUNDVenda;
    private javax.swing.JPanel footer;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtQTDEAtacado;
    private javax.swing.JFormattedTextField txtQTDEEstoque;
    private javax.swing.JFormattedTextField txtQTDEPrateleiras;
    private javax.swing.JLabel txtTitle;
    private javax.swing.JFormattedTextField txtValorAtacado;
    private javax.swing.JFormattedTextField txtValorCompra;
    private javax.swing.JFormattedTextField txtValorVarejo;
    // End of variables declaration//GEN-END:variables

}
