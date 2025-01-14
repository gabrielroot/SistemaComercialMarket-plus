/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop.TelaCaixa;

import Util.Util;
import br.edu.ifnmg.apresentacao_desktop.TelaPrincipal;
import br.edu.ifnmg.auxiliares.Estoque;
import br.edu.ifnmg.auxiliares.EstoqueRepositorio;
import br.edu.ifnmg.auxiliares.ItemVenda;
import br.edu.ifnmg.auxiliares.ItemVendaRepositorio;
import br.edu.ifnmg.auxiliares.Lote;
import br.edu.ifnmg.enums.FormaPagamento;
import br.edu.ifnmg.enums.TransacaoStatus;
import br.edu.ifnmg.enums.TransacaoTipo;
import br.edu.ifnmg.logicaAplicacao.Cliente;
import br.edu.ifnmg.logicaAplicacao.ClienteRepositorio;
import br.edu.ifnmg.logicaAplicacao.Pagamento;
import br.edu.ifnmg.logicaAplicacao.PagamentoPorCrediario;
import br.edu.ifnmg.logicaAplicacao.PagamentoPorCrediarioRepositorio;
import br.edu.ifnmg.logicaAplicacao.PagamentoPorDinheiro;
import br.edu.ifnmg.logicaAplicacao.PagamentoPorDinheiroRepositorio;
import br.edu.ifnmg.logicaAplicacao.PagamentoRepositorio;
import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import br.edu.ifnmg.logicaAplicacao.TransacaoFinanceira;
import br.edu.ifnmg.logicaAplicacao.TransacaoFinanceiraRepositorio;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author gabriel
 */
public class CaixaTela extends javax.swing.JInternalFrame implements KeyListener, InternalFrameListener{
    static TransacaoFinanceira transacaoFinanceira;
    static Cliente cliente;
    PagamentoPorCrediarioRepositorio pagamentoCrediarioRepositorio;
    PagamentoPorDinheiroRepositorio pagamentoPorDinheiroRepositorio;
    PagamentoRepositorio pagamentoRepositorio;
    ClienteRepositorio clienteRepositorio;
    ProdutoRepositorio produtoRepositorio;
    EstoqueRepositorio estoqueRepositorio;
    ItemVendaRepositorio itemVendaRepositorio;
    TransacaoFinanceiraRepositorio transacaoFinanceiraRepositorio;
    
    Util util;
    /**
     * Creates new form CaixaTela
     */
    public CaixaTela() {
        this.cliente = new Cliente();
        this.transacaoFinanceira = new TransacaoFinanceira(TransacaoTipo.Venda, TransacaoStatus.Criada, TelaPrincipal.getUsuario(), Calendar.getInstance(), cliente);
        
        this.transacaoFinanceiraRepositorio = RepositorioFactory.getTransacaoFinanceiraRepositorio();
        this.produtoRepositorio = RepositorioFactory.getProdutoRepositorio();
        this.itemVendaRepositorio = RepositorioFactory.getItemVendaRepositorio();
        this.estoqueRepositorio = RepositorioFactory.getEstoqueRepositorio();
        this.clienteRepositorio = RepositorioFactory.getClienteRepositorio();
        this.pagamentoCrediarioRepositorio = RepositorioFactory.getPagamentoCrediarioRepositorio();
        this.pagamentoPorDinheiroRepositorio = RepositorioFactory.getPagamentoDinheiroRepositorio();
        this.pagamentoRepositorio = RepositorioFactory.getPagamentoRepositorio();
        this.util = new Util();
        initComponents();
        this.txtCode.addKeyListener(this);
        this.painelRealizarPgto.addKeyListener(this);
        this.painelRepetir.addKeyListener(this);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              txtCode.requestFocusInWindow();
            }
        });
        listagemRapidaProdutos();
    }

    public static boolean isAtacado(ItemVenda itemVenda){
        return itemVenda.getQuantidade().compareTo(BigDecimal.valueOf(itemVenda.getProduto().getMinimoParaAtacado())) >= 0;
    }

    private void renderProdutos(List<ItemVenda> item){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("#");
        modelo.addColumn("CÓD. Produto");
        modelo.addColumn("Nome");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Min. P/ Atacado");
        modelo.addColumn("Valor");
        modelo.addColumn("Subtotal");
        
        for( int i=0; i < item.size(); i++ ){
            Vector linha = new Vector();
            
            linha.add((i+1));
            linha.add(item.get(i).getProduto().getId());
            linha.add(item.get(i).getProduto().getNome());
            linha.add(item.get(i).getQuantidade());
            linha.add(item.get(i).getProduto().getMinimoParaAtacado());
            if(isAtacado(item.get(i))){
                linha.add("Atacado: " + Util.formatStringToReal(item.get(i).getProduto().getValorAtacado().toString()));
            }else{
                linha.add("Varejo: " + Util.formatStringToReal(item.get(i).getProduto().getValorVarejo().toString()));
            }
            linha.add(Util.formatStringToReal(item.get(i).getSubTotal().toString()));
            modelo.addRow(linha);
        }
        tableResultadoProdutos.setModel(modelo);
    }
    
     private void realizarPagamento() {
        PagamentoTela pagamentoTela = new PagamentoTela(cliente);
        pagamentoTela.addInternalFrameListener(this);
        CaixaTela.jDesktopPane1.add(pagamentoTela);
        Util.centralizaInternalFrame(pagamentoTela, this.getSize());
        pagamentoTela.setVisible(true);
    }
     
    private void telaPagamentoDinheiro(){
        PagamentoDinheiro pagamentoDinheiro = new PagamentoDinheiro();
        pagamentoDinheiro.addInternalFrameListener(this);
        jDesktopPane1.add(pagamentoDinheiro);
        Util.centralizaInternalFrame(pagamentoDinheiro,this.getSize());
        pagamentoDinheiro.setVisible(true);
    }
    
    private void telaPagamentoCrediario(){
        PagamentoCrediario pagamentoCrediario = new PagamentoCrediario();
        pagamentoCrediario.addInternalFrameListener(this);
        jDesktopPane1.add(pagamentoCrediario);
        Util.centralizaInternalFrame(pagamentoCrediario,this.getSize());
        pagamentoCrediario.setVisible(true);
        
    }
    
    private void telaPagamentoCartao(){
        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.addInternalFrameListener(this);
        jDesktopPane1.add(pagamentoCartao);
        Util.centralizaInternalFrame(pagamentoCartao,this.getSize());
        pagamentoCartao.setVisible(true);
    }
    
    private void autenticarCliente(){
        AutenticarCliente autenticarCliente = new AutenticarCliente(this.cliente);
        autenticarCliente.addInternalFrameListener(this);
        jDesktopPane1.add(autenticarCliente);
        autenticarCliente.setVisible(true);
        Util.centralizaInternalFrame(autenticarCliente,this.getSize());
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
        body = new javax.swing.JPanel();
        head = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        txtCode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResultadoProdutos = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNotFound = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bottom = new javax.swing.JPanel();
        painelRepetir = new javax.swing.JPanel();
        txtCancelItem = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtProdutos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtCancelCompra = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        painelRealizarPgto = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAllProducts = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(208, 208, 208));

        jDesktopPane1.setBackground(new java.awt.Color(208, 208, 208));
        jDesktopPane1.setFocusable(false);

        body.setBackground(new java.awt.Color(208, 208, 208));

        head.setBackground(new java.awt.Color(140, 71, 71));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CAIXA");

        javax.swing.GroupLayout headLayout = new javax.swing.GroupLayout(head);
        head.setLayout(headLayout);
        headLayout.setHorizontalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headLayout.createSequentialGroup()
                .addContainerGap(579, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(579, Short.MAX_VALUE))
        );
        headLayout.setVerticalGroup(
            headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        content.setBackground(new java.awt.Color(208, 208, 208));

        txtCode.setBackground(new java.awt.Color(255, 255, 255));
        txtCode.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtCode.setForeground(new java.awt.Color(0, 0, 0));
        txtCode.setFocusCycleRoot(true);
        txtCode.setFocusTraversalPolicyProvider(true);
        txtCode.requestFocusInWindow();
        txtCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodeKeyPressed(evt);
            }
        });

        jScrollPane1.setFocusable(false);

        tableResultadoProdutos.setBackground(new java.awt.Color(255, 255, 255));
        tableResultadoProdutos.setForeground(new java.awt.Color(0, 0, 0));
        tableResultadoProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "ID", "Nome", "Valor Varejo", "Quantidade"
            }
        ));
        tableResultadoProdutos.setFocusable(false);
        tableResultadoProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResultadoProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableResultadoProdutos);

        jPanel10.setBackground(new java.awt.Color(107, 45, 45));

        jLabel2.setBackground(new java.awt.Color(255, 51, 51));
        jLabel2.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Código:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtNotFound.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        txtNotFound.setForeground(new java.awt.Color(255, 51, 51));
        txtNotFound.setText("                   ");

        jPanel2.setBackground(new java.awt.Color(107, 45, 45));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enter.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        bottom.setBackground(new java.awt.Color(208, 208, 208));

        painelRepetir.setBackground(new java.awt.Color(240, 240, 240));
        painelRepetir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        painelRepetir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelRepetirMouseClicked(evt);
            }
        });

        txtCancelItem.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtCancelItem.setForeground(new java.awt.Color(8, 8, 8));
        txtCancelItem.setText("- Repetir último");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrowLeft.png"))); // NOI18N

        javax.swing.GroupLayout painelRepetirLayout = new javax.swing.GroupLayout(painelRepetir);
        painelRepetir.setLayout(painelRepetirLayout);
        painelRepetirLayout.setHorizontalGroup(
            painelRepetirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelRepetirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCancelItem)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        painelRepetirLayout.setVerticalGroup(
            painelRepetirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRepetirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelRepetirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtCancelItem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(240, 240, 240));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        txtProdutos.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtProdutos.setForeground(new java.awt.Color(8, 8, 8));
        txtProdutos.setText("- Produtos");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrowUp.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProdutos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProdutos)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(240, 240, 240));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        txtCancelCompra.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtCancelCompra.setForeground(new java.awt.Color(8, 8, 8));
        txtCancelCompra.setText("- Cancelar Compra");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/esc.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCancelCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCancelCompra)
                .addGap(16, 16, 16))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(167, 167, 167));

        txtTotal.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(8, 8, 8));
        txtTotal.setText("R$ 0,00");

        jPanel9.setBackground(new java.awt.Color(107, 45, 45));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TOTAL:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        painelRealizarPgto.setBackground(new java.awt.Color(107, 45, 45));
        painelRealizarPgto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        painelRealizarPgto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelRealizarPgtoMouseClicked(evt);
            }
        });
        painelRealizarPgto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                painelRealizarPgtoKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Realizar Pagamento");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrowRight.png"))); // NOI18N

        javax.swing.GroupLayout painelRealizarPgtoLayout = new javax.swing.GroupLayout(painelRealizarPgto);
        painelRealizarPgto.setLayout(painelRealizarPgtoLayout);
        painelRealizarPgtoLayout.setHorizontalGroup(
            painelRealizarPgtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelRealizarPgtoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painelRealizarPgtoLayout.setVerticalGroup(
            painelRealizarPgtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelRealizarPgtoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(painelRealizarPgtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(painelRealizarPgto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtTotal)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelRealizarPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(painelRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(bottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(contentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNotFound)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCode, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNotFound, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tableAllProducts.setBackground(new java.awt.Color(255, 255, 255));
        tableAllProducts.setForeground(new java.awt.Color(8, 8, 8));
        tableAllProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ));
        tableAllProducts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableAllProducts.setFocusable(false);
        tableAllProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAllProductsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableAllProducts);

        jLabel11.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Todos os Produtos:");

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(head, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bodyLayout.createSequentialGroup()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))))
        );

        jDesktopPane1.setLayer(body, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        cancelarCompra();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        ListarProdutos listarProdutos = new ListarProdutos();
        listarProdutos.addInternalFrameListener(this);
        CaixaTela.jDesktopPane1.add(listarProdutos);
        Util.centralizaInternalFrame(listarProdutos, this.getSize());
        listarProdutos.setVisible(true);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void tableResultadoProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResultadoProdutosMouseClicked
        int linha = tableResultadoProdutos.getSelectedRow();
        int idx = (int) tableResultadoProdutos.getValueAt(linha, 0);

        EditarListaPedido editarListaPedido = new EditarListaPedido(transacaoFinanceira.getItens().get(idx-1));
        System.out.println(transacaoFinanceira.getItens().get(idx-1).getProduto().getNome());
        editarListaPedido.addInternalFrameListener(this);
        CaixaTela.jDesktopPane1.add(editarListaPedido);
        Util.centralizaInternalFrame(editarListaPedido, this.getSize());
        editarListaPedido.setVisible(true);
    }//GEN-LAST:event_tableResultadoProdutosMouseClicked

    private void txtCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodeKeyPressed
        this.txtNotFound.setText("");
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            this.adicionarProduto();
        }
    }//GEN-LAST:event_txtCodeKeyPressed

    private void tableAllProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAllProductsMouseClicked
        this.txtNotFound.setText("");
        int linha = tableAllProducts.getSelectedRow();
        String id = tableAllProducts.getValueAt(linha, 0).toString();
        
        CaixaTela.txtCode.setText(id);
        this.adicionarProduto();
    }//GEN-LAST:event_tableAllProductsMouseClicked

    private void painelRealizarPgtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelRealizarPgtoMouseClicked
        autenticarCliente();
    }//GEN-LAST:event_painelRealizarPgtoMouseClicked

    private void painelRepetirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelRepetirMouseClicked
        repetirUltimoProduto();
    }//GEN-LAST:event_painelRepetirMouseClicked

    private void painelRealizarPgtoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_painelRealizarPgtoKeyPressed
        autenticarCliente();
    }//GEN-LAST:event_painelRealizarPgtoKeyPressed

    private void finalizarCompra(){
        transacaoFinanceira.setCliente(cliente);
        util.abrirJOptionPane("sucesso","Compra Finalizada!",this);
        
        
        for (ItemVenda item : transacaoFinanceira.getItens()) {
            for (Lote lote : item.getProduto().getEstoque().getLotes()){
                
                if(lote.getNasPrateleiras() >= Integer.parseInt(item.getQuantidade().toString()) ){
                    lote.setNasPrateleiras(lote.getNasPrateleiras() - Integer.parseInt(item.getQuantidade().toString()) );
                    
                    break;
                }else{
                
                    item.setQuantidade(item.getQuantidade().subtract(new BigDecimal(lote.getNasPrateleiras())));
                    lote.setNasPrateleiras(0);
                }
            }
        }
        
        
        if(PagamentoTela.pagamentoPorDinheiro != null){
            PagamentoTela.pagamentoPorDinheiro.setTransacaoFinanceira(transacaoFinanceira);
            pagamentoPorDinheiroRepositorio.Salvar(PagamentoTela.pagamentoPorDinheiro);
            PagamentoTela.pagamentoPorDinheiro = null;
            
        }else if(PagamentoTela.pagamento != null){
                    pagamentoRepositorio.Salvar(PagamentoTela.pagamento);
                    PagamentoTela.pagamento = null;

        }else if(PagamentoTela.pagamentoPorCrediario !=null){
                    PagamentoTela.pagamentoPorCrediario.setTransacaoFinanceira(CaixaTela.transacaoFinanceira);
                    pagamentoCrediarioRepositorio.Salvar(PagamentoTela.pagamentoPorCrediario);
                    PagamentoTela.pagamentoPorCrediario = null;
        }
        transacaoFinanceiraRepositorio.Salvar(transacaoFinanceira);

        

        transacaoFinanceira.getItens().clear();
        this.renderProdutos(transacaoFinanceira.getItens());
        this.atualizarTotal();
        this.listagemRapidaProdutos();
        this.cliente = new Cliente();
        this.transacaoFinanceira = new TransacaoFinanceira(TransacaoTipo.Venda, TransacaoStatus.Criada, TelaPrincipal.getUsuario(), Calendar.getInstance(), cliente);
    }
    
    private void adicionarProduto(){
        try{
            Produto produtoEncontrado = this.produtoRepositorio.Abrir(Long.parseLong(this.txtCode.getText()));
            if(produtoEncontrado != null){
                ItemVenda itemVenda = null;
                if(produtoEncontrado.getMinimoParaAtacado() > 1){
                    itemVenda = new ItemVenda(BigDecimal.valueOf(1),produtoEncontrado.getValorVarejo());
                }else{
                    itemVenda = new ItemVenda(BigDecimal.valueOf(1),produtoEncontrado.getValorAtacado());
                }
                
                boolean bool = false;
                
                for(ItemVenda item : transacaoFinanceira.getItens()){
                    if(Objects.equals(item.getProduto().getId(), produtoEncontrado.getId())){
                        Estoque itemEstoque = estoqueRepositorio.Abrir(item.getProduto().getEstoque().getId());
                        if(BigDecimal.valueOf(itemEstoque.getSomaPrateleiras()).compareTo(item.getQuantidade().add(BigDecimal.ONE)) >= 0){
                            item.setQuantidade(item.getQuantidade().add(BigDecimal.ONE));
                            if(isAtacado(item)){
                                item.setSubTotal(item.getSubTotal(item.getProduto().getValorAtacado()));
                            }else{
                                item.setSubTotal(item.getSubTotal(item.getProduto().getValorVarejo()));
                            }
                            bool = true;
                            break;
                        }else{
                            this.txtNotFound.setText("Sem mais nas prateleiras");
                            return;
                        }
                    }
                }
                
                if(!bool){
                    itemVenda.setProduto(produtoEncontrado);
                    itemVenda.setTransacaoFinanceira(transacaoFinanceira);
                    transacaoFinanceira.getItens().add(itemVenda);
                }

                this.renderProdutos(transacaoFinanceira.getItens());
                this.txtCode.setText("");
                
                CaixaTela.playSoundEffect();
                atualizarTotal();
            }else{
                this.txtCode.selectAll();
                txtNotFound.setText("Produto não encontrado!");
            }
        }catch(NumberFormatException ex){
            this.txtCode.selectAll();
            txtNotFound.setText("Apenas números, por favor!");
        }
    }
    
    public static void playSoundEffect(){
        Player player;
        try {
            FileInputStream fileInputStream = new FileInputStream("piscadinha.mp3");
                player = new Player(fileInputStream);
                player.play();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void repetirUltimoProduto(){
        if(this.transacaoFinanceira.getItens().size() > 0){
            ItemVenda it = this.transacaoFinanceira.getItens().get(this.transacaoFinanceira.getItens().size() -1);
            Estoque itemEstoque = estoqueRepositorio.Abrir(it.getProduto().getEstoque().getId());
            if(BigDecimal.valueOf(itemEstoque.getSomaPrateleiras()).compareTo(it.getQuantidade().add(BigDecimal.ONE)) >= 0){
                it.setQuantidade(it.getQuantidade().add(BigDecimal.ONE));
                if(CaixaTela.isAtacado(it)){
                    it.setSubTotal(it.getSubTotal(it.getProduto().getValorAtacado()));
                }else{
                    it.setSubTotal(it.getSubTotal(it.getProduto().getValorVarejo()));
                }

                this.renderProdutos(transacaoFinanceira.getItens());
                this.playSoundEffect();
                this.atualizarTotal();
            }else{
                this.txtNotFound.setText("Sem mais nas prateleiras");
            }
        }else{
                this.txtNotFound.setText("Nenhum produto foi adicionado ainda");
        }
    }
    
    private void atualizarTotal(){
        this.txtTotal.setText(Util.formatStringToReal(this.transacaoFinanceira.getValorTotal().toString()));
    }
    
    private void cancelarCompra(){
        UsuarioRepositorio usuarioRepositorio = RepositorioFactory.getUsuarioRepositorio();
        
        String email = util.abrirInputPasswordDialog("Informe o EMAIL do administrador", false, this);
        if(!email.isEmpty()){
            String senha = util.abrirInputPasswordDialog("Informe a SENHA do administrador", true, this);
            if(usuarioRepositorio.ValidarAdmin(email, senha) != null){
                CaixaTela.transacaoFinanceira.getItens().clear();
                this.renderProdutos(transacaoFinanceira.getItens());
                this.atualizarTotal();
                util.abrirJOptionPane("sucesso","",this);
            }else if(!senha.isEmpty()){
                util.abrirJOptionPane("erro", "Email ou senha incorretos. Certifique-se de que é um Administrador", this);
            }
        }
    }

    public void listagemRapidaProdutos(){
        List<Produto> resultado = this.produtoRepositorio.Buscar(new Produto());
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        
        for(int i=0;i<resultado.size(); i++){
            Estoque estoqueResultado = this.estoqueRepositorio.Abrir(resultado.get(i).getEstoque().getId());
            if(estoqueResultado.getSomaPrateleiras() > 0){
                Vector linha = new Vector();
            
                linha.add(resultado.get(i).getId());
                linha.add(resultado.get(i).getNome());
                modelo.addRow(linha);
            }
        }
        tableAllProducts.setModel(modelo);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JPanel bottom;
    private javax.swing.JPanel content;
    private javax.swing.JPanel head;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel painelRealizarPgto;
    private javax.swing.JPanel painelRepetir;
    private javax.swing.JTable tableAllProducts;
    private javax.swing.JTable tableResultadoProdutos;
    private javax.swing.JLabel txtCancelCompra;
    private javax.swing.JLabel txtCancelItem;
    public static javax.swing.JTextField txtCode;
    private javax.swing.JLabel txtNotFound;
    private javax.swing.JLabel txtProdutos;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_UP:
                ListarProdutos listarProdutos = new ListarProdutos();
                CaixaTela.jDesktopPane1.add(listarProdutos);
                listarProdutos.addInternalFrameListener(this);
                Util.centralizaInternalFrame(listarProdutos, this.getSize());
                listarProdutos.setVisible(true);
                break;
            case java.awt.event.KeyEvent.VK_ESCAPE:
                cancelarCompra();
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                autenticarCliente();
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                 repetirUltimoProduto();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              txtCode.requestFocusInWindow();
            }
            
        });
        
        listagemRapidaProdutos();
        
        if(e.getInternalFrame().getClass() == ListarProdutos.class){
            if(this.txtCode.getText().length() > 0){
                this.adicionarProduto();
            }
        }else if(e.getInternalFrame().getClass() == EditarListaPedido.class){
            this.renderProdutos(transacaoFinanceira.getItens());
            this.atualizarTotal();
        }
        
        if(e.getInternalFrame().getClass() == AutenticarCliente.class){
            if(!cliente.getIdentificaoDoCliente().isEmpty()){
                this.realizarPagamento();
            }
        }
        
        if(e.getInternalFrame().getClass() == PagamentoTela.class){
            if(PagamentoTela.pagamentoPorDinheiro != null){
                if(PagamentoTela.pagamentoPorDinheiro.getFormaPagamento().equals(FormaPagamento.Dinheiro))
                    this.telaPagamentoDinheiro();
                    
            }else if(PagamentoTela.pagamento != null){
                        if(PagamentoTela.pagamento.getFormaPagamento().equals(FormaPagamento.Cartao)){
                            this.telaPagamentoCartao();
                        }
                        
            }else if(PagamentoTela.pagamentoPorCrediario !=null){
                    if(PagamentoTela.pagamentoPorCrediario.getFormaPagamento().equals(FormaPagamento.Crediario)){
                        this.telaPagamentoCrediario();
                    }
            }
            
        }
        
        if(e.getInternalFrame().getClass() == PagamentoCrediario.class){
            if(CaixaTela.transacaoFinanceira.getTransacaoStatus().equals(TransacaoStatus.Concluida) &&
                    PagamentoTela.pagamentoPorCrediario.getFormaPagamento() != null)    
                this.finalizarCompra();
        }
        
        if(e.getInternalFrame().getClass() == PagamentoDinheiro.class){
            if(CaixaTela.transacaoFinanceira.getTransacaoStatus().equals(TransacaoStatus.Concluida) &&
                    PagamentoTela.pagamentoPorDinheiro.getFormaPagamento() != null)    
                this.finalizarCompra();
        }
        
        if(e.getInternalFrame().getClass() == PagamentoCartao.class){
            if(CaixaTela.transacaoFinanceira.getTransacaoStatus().equals(TransacaoStatus.Concluida) &&
                    PagamentoTela.pagamento.getFormaPagamento() != null)    
                this.finalizarCompra();
        }
        
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
