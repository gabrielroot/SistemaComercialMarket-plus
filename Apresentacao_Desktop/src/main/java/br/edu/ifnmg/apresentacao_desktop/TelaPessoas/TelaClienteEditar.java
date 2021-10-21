/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_desktop.TelaPessoas;

import Util.Util;
import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import br.edu.ifnmg.logicaAplicacao.Cliente;
import br.edu.ifnmg.logicaAplicacao.ClienteRepositorio;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Murilo
 */
public class TelaClienteEditar extends javax.swing.JInternalFrame {

    private Cliente cliente;
    private ClienteRepositorio clienteRepositorio;
    private Util util;
    
    /**
     * Creates new form TelaCliente
     * @param cliente
     */
    public TelaClienteEditar(Cliente cliente, String titulo) {
        this.cliente = cliente;
        this.clienteRepositorio = RepositorioFactory.getClienteRepositorio();
        initComponents();
        setComponentes();
        this.lblTitulo.setText(titulo);
        this.util = new Util();
    }
    
    private void setComponentes(){
        if(!this.cliente.getNome().isEmpty() && !this.cliente.getNome().equals("0")){
            System.out.println("");
            this.txtNome.setText(this.cliente.getNome());

            if(cliente.getEndereco() != null){
                StringTokenizer enderecoCompleto = new StringTokenizer(cliente.getEndereco(), ",");
                this.txtEndereco.setText(enderecoCompleto.nextToken());
                this.txtNumeroCasa.setText(enderecoCompleto.nextToken());
                this.txtBairro.setText(enderecoCompleto.nextToken());
                
                if(this.TxtComplemento.getText() != null)
                    this.TxtComplemento.setText(enderecoCompleto.nextToken());
            }

            this.txtDataNascimento.setValue(Util.getStringDateFromCalendar(this.cliente.getDataNascimento()));
            this.cbxTipoPessoa.setSelectedItem(this.cliente.getTipoPessoa().toString());

            for (TipoDocumento tipoDocumento : TipoDocumento.values()) {
                if(tipoDocumento.toString().equals(this.cliente.getTipoDocumento().toString())){
                    this.cbxTipoDocumento.setSelectedItem(cliente.getTipoDocumento().toString());
                    break;
                }
            }
            this.txtNumeroDocumento.setText(this.cliente.getNumeroDocumento());
            if(this.cliente.getTelefones().size() > 0){
                this.txtTelefone1.setValue(this.cliente.getTelefones().get(0).getNumero());

                if(this.cliente.getTelefones().size() > 1){
                    this.txtTelefone2.setValue(this.cliente.getTelefones().get(1).getNumero());
                }
            }
            this.txtIdentificacaoCliente.setText(this.cliente.getIdentificaoDoCliente());
            this.txtSenhaCliente.setText(this.cliente.getSenha());
        } 
    }
    
    private boolean getComponentes(){
        if(
                this.txtNome.getText() == null ||
                this.txtDataNascimento.getText() == null ||
                this.txtEndereco.getText() == null ||
                this.txtNumeroCasa.getText() == null ||
                this.txtBairro.getText() == null ||
                this.txtNumeroDocumento.getText() == null ||
                this.txtIdentificacaoCliente.getText() == null || 
                this.txtSenhaCliente.getPassword().length == 0 || 
                this.txtTelefone1.getValue() == null &&
                this.txtTelefone2.getValue() == null
          ){
            return false;
        }
        
        StringBuilder endereco = new StringBuilder();
     
        endereco.append(txtEndereco.getText());
        endereco.append(", ");
        endereco.append(txtNumeroCasa.getText());
        endereco.append(", ");
        endereco.append(txtBairro.getText());
        endereco.append(", ");
        endereco.append(TxtComplemento.getText());
        
        ArrayList<Telefone> telefones = new ArrayList();
        if(this.txtTelefone1.getValue() != null){
            Telefone telefone1 = new Telefone(this.txtTelefone1.getValue().toString() );
            telefones.add(telefone1);
        }
        
        if(this.txtTelefone2.getValue() != null){
            Telefone telefone2 = new Telefone(this.txtTelefone2.getValue().toString() );
            telefones.add(telefone2);
        }
        
        Calendar data = Calendar.getInstance();
        data = Util.getCalendarDateFromString(this.txtDataNascimento.getText());
        
        this.cliente.setNome(this.txtNome.getText());
        this.cliente.setDataNascimento(data);
        this.cliente.setEndereco(endereco.toString());
        this.cliente.setTipoPessoa(TipoPessoa.values()[this.cbxTipoPessoa.
                getSelectedIndex()]);
        for (TipoDocumento tipoDocumento : TipoDocumento.values()) {
            if(tipoDocumento.toString().equals(this.cbxTipoDocumento.getSelectedItem().toString())){
                this.cliente.setTipoDocumento(tipoDocumento);
                break;
            }
        }
        
        this.cliente.setNumeroDocumento(txtNumeroDocumento.getText());
        this.cliente.setTelefones(telefones);
        this.cliente.setIdentificaoDoCliente(this.txtIdentificacaoCliente.getText());
        this.cliente.setSenha(String.valueOf(this.txtSenhaCliente.getPassword()));
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

        jTextField1 = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroCasa = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        cbxTipoPessoa = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxTipoDocumento = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TxtComplemento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIdentificacaoCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSenhaCliente = new javax.swing.JPasswordField();
        txtTelefone2 = new javax.swing.JFormattedTextField();
        txtTelefone1 = new javax.swing.JFormattedTextField();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(219, 219, 219));
        setClosable(true);

        txtNome.setBackground(new java.awt.Color(255, 255, 255));
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNome.setForeground(new java.awt.Color(0, 0, 0));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(0, 0, 0));
        lblNome.setText("Nome");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Data de Nascimento");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));

        txtEndereco.setBackground(new java.awt.Color(255, 255, 255));
        txtEndereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEndereco.setForeground(new java.awt.Color(0, 0, 0));
        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(140, 71, 71));

        lblTitulo.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Cadastro Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(285, 285, 285))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(19, 19, 19))
        );

        txtDataNascimento.setBackground(new java.awt.Color(255, 255, 255));
        txtDataNascimento.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Endereço");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Número");

        txtNumeroCasa.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeroCasa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNumeroCasa.setForeground(new java.awt.Color(0, 0, 0));
        txtNumeroCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCasaActionPerformed(evt);
            }
        });

        txtBairro.setBackground(new java.awt.Color(255, 255, 255));
        txtBairro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBairro.setForeground(new java.awt.Color(0, 0, 0));
        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Bairro");

        btnCancelar.setBackground(new java.awt.Color(181, 181, 181));
        btnCancelar.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(109, 46, 46));
        btnSalvar.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        cbxTipoPessoa.setBackground(new java.awt.Color(255, 255, 255));
        cbxTipoPessoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxTipoPessoa.setForeground(new java.awt.Color(0, 0, 0));
        cbxTipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fisica", "Juridica" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tipo Pessoa");

        cbxTipoDocumento.setBackground(new java.awt.Color(255, 255, 255));
        cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxTipoDocumento.setForeground(new java.awt.Color(0, 0, 0));
        cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RG", "CNPJ", "CPF", "CTPS", "CNH", "Passaporte", "Certidão de Nascimento", "Certidão de Casamento", "Certidão de Prontuário", "Carteira Militar" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tipo do Documento");

        txtNumeroDocumento.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeroDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNumeroDocumento.setForeground(new java.awt.Color(0, 0, 0));
        txtNumeroDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroDocumentoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Número do Documento");

        TxtComplemento.setBackground(new java.awt.Color(255, 255, 255));
        TxtComplemento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TxtComplemento.setForeground(new java.awt.Color(0, 0, 0));
        TxtComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtComplementoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Complemento");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Telefone 1");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Telefone 2");

        txtIdentificacaoCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtIdentificacaoCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIdentificacaoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacaoClienteActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Identificação do cliente para compra");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Senha para comprar");

        txtSenhaCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtSenhaCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSenhaCliente.setForeground(new java.awt.Color(0, 0, 0));

        txtTelefone2.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefone2.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtTelefone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTelefone1.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefone1.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(txtNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(txtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(txtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtIdentificacaoCliente, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtSenhaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdentificacaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSenhaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoActionPerformed

    private void txtNumeroCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCasaActionPerformed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ClienteBuscaAvancada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(this.getComponentes()){
            if(util.abrirJOptionPane("confirma", "Deseja realmente salvar o cadastro do cliente?",this)){
            
                if(clienteRepositorio.Salvar(this.cliente)){
                    util.abrirJOptionPane("sucesso", "Cliente salvo com Sucesso!",this);
                    this.dispose();
                    //depois que salvar deverar atualizar o cadastro!!
                }else{
                    util.abrirJOptionPane("erro", "Erro ao salvar!",this);
                } 
            }else{
            util.abrirJOptionPane("informacao", "Operação cancelada.",this);
            }  
        }else{
                util.abrirJOptionPane("erro", "Preencha todos os campos!",this);
            }
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtNumeroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroDocumentoActionPerformed

    private void TxtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtComplementoActionPerformed

    private void txtIdentificacaoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacaoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacaoClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtComplemento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxTipoDocumento;
    private javax.swing.JComboBox<String> cbxTipoPessoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtIdentificacaoCliente;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumeroCasa;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JPasswordField txtSenhaCliente;
    private javax.swing.JFormattedTextField txtTelefone1;
    private javax.swing.JFormattedTextField txtTelefone2;
    // End of variables declaration//GEN-END:variables
}
