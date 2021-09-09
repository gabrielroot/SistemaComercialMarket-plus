/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_console;

import br.edu.ifnmg.auxiliares.CargoFuncionario;
import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import br.edu.ifnmg.enums.FuncionarioSituacao;
import br.edu.ifnmg.enums.Segmento;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import br.edu.ifnmg.logicaAplicacao.Fornecedor;
import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.FornecedorRepositorio;
import br.edu.ifnmg.logicaAplicacao.Funcionario;
import br.edu.ifnmg.logicaAplicacao.FuncionarioRepositorio;
import br.edu.ifnmg.logicaAplicacao.Pessoa;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.LocalizacaoProduto;
import br.edu.ifnmg.enums.UnidadeMedida;
import br.edu.ifnmg.enums.UsuarioTipo;
import br.edu.ifnmg.logicaAplicacao.Cliente;
import br.edu.ifnmg.logicaAplicacao.ClienteRepositorio;
import br.edu.ifnmg.auxiliares.Estoque;
import br.edu.ifnmg.auxiliares.Lote;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import br.edu.ifnmg.logicaAplicacao.Usuario;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class Console {
    static PessoaRepositorio repositorioPessoa = RepositorioFactory.getPessoaRepositorio();
    static UsuarioRepositorio repositorioUsuario = RepositorioFactory.getUsuarioRepositorio();
    static FornecedorRepositorio repositorioFornecedor = RepositorioFactory.getFornecedorRepositorio();
    static ClienteRepositorio repositorioCliente = RepositorioFactory.getClienteRepositorio();
    static ProdutoRepositorio repositorioProduto = RepositorioFactory.getProdutoRepositorio();
    static FuncionarioRepositorio repositorioFuncionario = RepositorioFactory.getFuncionarioRepositorio();
    
    public static void main(String[] args){
        if(popularBD()){
            System.out.println("Banco de dados populado com SUCESSO!!");
        }else{
            System.out.println("FALHA ao popular o banco de dados!!");
        }
        

        queries();
    }
    
    public static void queries(){
        
        
        System.out.println("-- Buscar produtos em que a quantidade mínima para atacado == 5 --");
        for(Produto produto : repositorioProduto.Buscar(new Produto(
                null, 
                null, 
                -1, 
                5, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null
        )
        )){
            System.out.println("    "+produto.getNome());
        }
        
        System.out.println("-- Buscar usuarios em que o email == admin & UserType == Administrador --");
        for(Usuario usuario : repositorioUsuario.Buscar(new Usuario(
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null,
                null,
                null,
                "admin",
                null,
                UsuarioTipo.Administrador
        ))){
            System.out.println("    "+usuario.getNome());
        }
        
        System.out.println("-- Buscar pessoa em que nome == 'Sebastião Codeiro' & NDocumento == '123456'--");
        for(Pessoa pessoa : repositorioPessoa.Buscar(new Pessoa(
                "Sebastião Codeiro", 
                null, 
                null, 
                null, 
                null, 
                null, 
                "123456"))){
            System.out.println("    "+pessoa.getNome());
        }
        
        System.out.println("-- Buscar fornecedor em que nome == 'Enrico Santos' --");
        for(Fornecedor fornecedor : repositorioFornecedor.Buscar(new Fornecedor(
                "Enrico Santos", 
                null, 
                null, 
                null, 
                null, 
                null, 
                null,
                null
        ))){
            System.out.println("    "+fornecedor.getNome());
        }
        
    }
     
    public static boolean popularBD(){
        List telefones = new ArrayList<Telefone>();
        telefones.add(new Telefone("3899991111"));
        telefones.add(new Telefone("3896291131"));

        Pessoa pessoa = new Pessoa(
            "Sebastião Codeiro",
            "Januária, Minas Gerais. Avenida Deodoro da Fonseca N° 111",
            telefones,
            Calendar.getInstance(),
            TipoPessoa.Fisica,
            TipoDocumento.CertidaoNascimento,
            "123456"
        );        
        
        CargoFuncionario cargo1 = new CargoFuncionario("Faxineiro", "Limpar todo o estabelecimento", new BigDecimal("0.00"), new BigDecimal("1000.00"));
        CargoFuncionario cargo2 = new CargoFuncionario("Caixa", "Realizar vendas", new BigDecimal("0.00"), new BigDecimal("1600.00"));
        CargoFuncionario cargo3 = new CargoFuncionario("Administrador", "Administrar", new BigDecimal("0.00"), new BigDecimal("2200.00"));
        CargoFuncionario cargo4 = new CargoFuncionario("Segurança", "Controle do fluxo de pessoas", new BigDecimal("0.00"), new BigDecimal("1200.00"));
        
        Funcionario funcionario = new Funcionario(
            "Antônio Gomes",
            "Lontra, Minas Gerais. Avenida Água viva N° 145",
            null,
            Calendar.getInstance(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "8123427854",
            FuncionarioSituacao.Ativo,
            cargo1
        );
        
        Fornecedor fornecedor = new Fornecedor(
            "João Geraldo",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            Calendar.getInstance(),
            TipoPessoa.Juridica,
            TipoDocumento.CNPJ,
            "564673",
            Segmento.Enlatados
        );
        
        Usuario usuarioAdmin = new Usuario(
            "AdminUser",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            Calendar.getInstance(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "564612173",
            FuncionarioSituacao.Ativo,
            cargo2,    
            "admin",
            "123",
            UsuarioTipo.Administrador
        );
        
//        CargoFuncionario cargo22 = new CargoFuncionario(cargo1.getTitulo(), cargo1.getFuncao(), cargo1.getComissao(), cargo1.getSalario());
//        repositorioFuncionario.Apagar(funcionario);
//        Usuario novoUsuario = new Usuario(
//                funcionario.getId(),
//                funcionario.getNome(), 
//                funcionario.getEndereco(), 
//                funcionario.getTelefones(), 
//                funcionario.getDataNascimento(),         
//                funcionario.getTipoPessoa(), 
//                funcionario.getTipoDocumento(), 
//                funcionario.getNumeroDocumento(), 
//                funcionario.getSituacao(),
//                cargo22,
//                "emailFunc",
//                "123",
//                UsuarioTipo.Administrador
//        ); 
        
 
        Usuario usuarioCaixa = new Usuario(
            "CaixaUser",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            Calendar.getInstance(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "564612173",
            FuncionarioSituacao.Ativo,
            cargo3,    
            "caixa",
            "123",
            UsuarioTipo.Caixa
        );
        
        Usuario usuarioGerente = new Usuario(
            "GerenteUser",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            Calendar.getInstance(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "564612173",
            FuncionarioSituacao.Ativo,
            cargo4,    
            "gerente",
            "123",
            UsuarioTipo.Gerente
        );
        
        Usuario usuarioBalconista = new Usuario(
            "BalconistaUser",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            Calendar.getInstance(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "564612173",
            FuncionarioSituacao.Ativo,
            cargo4,    
            "balconista",
            "123",
            UsuarioTipo.Balconista
        );
        
        Cliente cliente1 = new Cliente("zeroberto", 
                "1234",
                "Ze Roberto", 
                "\"Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12\"", 
                null,
                Calendar.getInstance(), 
                TipoPessoa.Fisica, 
                TipoDocumento.CertidaoNascimento,
                "333333"
        );
        
        Cliente cliente2 = new Cliente("mila", 
                "1234", 
                "Kamila", 
                "\"Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12\"",
                null,
                Calendar.getInstance(),
                TipoPessoa.Fisica, 
                TipoDocumento.CertidaoNascimento, 
                "333333"
        );
        
        usuariosAleatorios();
        fornecedoresAleatorios();
        produtosAleatorios();
        
        return repositorioPessoa.Salvar(pessoa) &&
               repositorioFuncionario.Salvar(funcionario) &&
               repositorioUsuario.Salvar(usuarioAdmin) &&
               repositorioUsuario.Salvar(usuarioCaixa) &&
               repositorioUsuario.Salvar(usuarioGerente) &&
               repositorioUsuario.Salvar(usuarioBalconista) &&
               repositorioCliente.Salvar(cliente1) &&
               repositorioCliente.Salvar(cliente2);
     }
    
    public static void fornecedoresAleatorios(){
        CargoFuncionario cargo1 = new CargoFuncionario("Faxineiro", "Limpar todo o estabelecimento", new BigDecimal("0.00"), new BigDecimal("1000.00"));
        CargoFuncionario cargo2 = new CargoFuncionario("Caixa", "Realizar vendas", new BigDecimal("1.00"), new BigDecimal("1600.00"));
        CargoFuncionario cargo3 = new CargoFuncionario("Administrador", "Administrar", new BigDecimal("5.00"), new BigDecimal("2200.00"));
        CargoFuncionario cargo4 = new CargoFuncionario("Segurança", "Controle do fluxo de pessoas", new BigDecimal("0.00"), new BigDecimal("1200.00"));
        
        
        Object[] nomes = {"Marina Dias", "Ana Julia Santos", "Lucas da Luz", "Leandro Costa", "Maria Sophia Campos", "Evelyn Lopes", "Enrico Santos", "Marina Pinto", "Marcela Fernandes", "Gustavo Barbosa", "Ana Laura Castro", "Ana Carolina Silveira", "Maria Luiza Barros", "Gustavo Rocha", "Luiz Felipe Moura", "Thiago Castro", "Pietro da Paz", "Yago da Costa", "Pietro da Mota", "Gabriel da Mata", "João Miguel Peixoto", "Breno da Luz", "André Peixoto", "Yuri Fogaça", "Sabrina Moreira", "Bárbara Dias", "Vitor Gabriel Barbosa", "Ana Castro", "Emilly Barbosa", "Vitória Silveira", "Vitor Gomes", "Bruno Moreira", "Ana Lívia Farias", "Benício Pires", "Lara Castro", "Sabrina Moraes", "Fernanda Porto", "Pietra Viana", "Luiz Fernando Ribeiro", "Maitê Pinto", "Sophie Almeida", "Stephany da Cunha", "Raul da Rocha", "Maria Vitória Viana", "Gustavo Henrique Nogueira", "Theo Cavalcanti", "Enzo Rodrigues", "Guilherme Rocha", "Davi Lucca Rodrigues", "Bruno da Mata"};
        Object[] c = {cargo1, cargo2, cargo3, cargo4};
        List cargos = Arrays.asList(c);
        Object[] enderecos = {"Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12", "Lontra, Minas Gerais. Avenida Água viva N° 145", "Januária, Minas Gerais. Avenida Deodoro da Fonseca N° 111"};
        
        for(int i=0; i<50; i++){
            Telefone telefone01 = new Telefone("(38) 9 9991-8711");
            Telefone telefone02 = new Telefone("(38) 9 9629-1131");

            List telefones = new ArrayList<Telefone>();
            telefones.add(telefone01);
            telefones.add(telefone02);
            Fornecedor fornecedor = new Fornecedor(
            nomes[i].toString(),
            enderecos[(int) (Math.random()*3)].toString(),
            telefones,
            Calendar.getInstance(),
            TipoPessoa.Juridica,
            TipoDocumento.CNPJ,
            (int) (Math.random()*4)+"1.334.543/"+(int) (Math.random()*4)+"355-0"+(int) (Math.random()*4),
            (Segmento) Segmento.values()[((int) (Math.random()*Segmento.values().length))]
        );
            repositorioFornecedor.Salvar(fornecedor);
        }
    }
    public static void usuariosAleatorios(){
        CargoFuncionario cargo1 = new CargoFuncionario("Faxineiro", "Limpar todo o estabelecimento", new BigDecimal("0.00"), new BigDecimal("1000.00"));
        CargoFuncionario cargo2 = new CargoFuncionario("Caixa", "Realizar vendas", new BigDecimal("1.00"), new BigDecimal("1600.00"));
        CargoFuncionario cargo3 = new CargoFuncionario("Administrador", "Administrar", new BigDecimal("5.00"), new BigDecimal("2200.00"));
        CargoFuncionario cargo4 = new CargoFuncionario("Segurança", "Controle do fluxo de pessoas", new BigDecimal("0.00"), new BigDecimal("1200.00"));
        
        
        Object[] nomes = {"Marina Dias", "Ana Julia Santos", "Lucas da Luz", "Leandro Costa", 
            "Maria Sophia Campos", "Evelyn Lopes", "Enrico Santos", "Marina Pinto", 
            "Marcela Fernandes", "Gustavo Barbosa", "Ana Laura Castro", "Ana Carolina Silveira",
            "Maria Luiza Barros", "Gustavo Rocha", "Luiz Felipe Moura", "Thiago Castro",
            "Pietro da Paz", "Yago da Costa", "Pietro da Mota", "Gabriel da Mata", 
            "João Miguel Peixoto", "Breno da Luz", "André Peixoto", "Yuri Fogaça", 
            "Sabrina Moreira", "Bárbara Dias", "Vitor Gabriel Barbosa", "Ana Castro", 
            "Emilly Barbosa", "Vitória Silveira", "Vitor Gomes", "Bruno Moreira", 
            "Ana Lívia Farias", "Benício Pires", "Lara Castro", "Sabrina Moraes", 
            "Fernanda Porto", "Pietra Viana", "Luiz Fernando Ribeiro", "Maitê Pinto",
            "Sophie Almeida", "Stephany da Cunha", "Raul da Rocha", "Maria Vitória Viana",
            "Gustavo Henrique Nogueira", "Theo Cavalcanti", "Enzo Rodrigues", "Guilherme Rocha",
            "Davi Lucca Rodrigues", "Bruno da Mata"};
        
        Object[] c = {cargo1, cargo2, cargo3, cargo4};
        List cargos = Arrays.asList(c);
        Object[] enderecos = {"Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            "Lontra, Minas Gerais. Avenida Água viva N° 145",
            "Januária, Minas Gerais. Avenida Deodoro da Fonseca N° 111"};
        
        for(int i=0; i<50; i++){
            Telefone telefone01 = new Telefone("3899991111");        
            Telefone telefone02 = new Telefone("3896291131");

            List telefones = new ArrayList<Telefone>();
            telefones.add(telefone01);
            telefones.add(telefone02);
            Usuario usuario = new Usuario(
                    nomes[i].toString(), 
                    enderecos[(int) (Math.random()*3)].toString(), 
                    telefones,
                    Calendar.getInstance(),
                    TipoPessoa.Fisica,
                    TipoDocumento.CertidaoNascimento,
                    "1578095689", 
                    FuncionarioSituacao.Ativo,
                    (CargoFuncionario)  cargos.get((int) (Math.random()*4)), 
                    (CargoFuncionario) cargos.get((int) (Math.random()*4)) + 
                            String.valueOf((int) (Math.random()*3000000)) + 
                            "@marketplus.com",
                    "123",
                    UsuarioTipo.values()[(int) (Math.random()*4)]);
            repositorioUsuario.Salvar(usuario);
        }
    }
    
    public static void produtosAleatorios(){
        for(int i=0; i<50; i++){
            Estoque estoque = new Estoque(
                    LocalizacaoProduto.values()[(int) (Math.random()*10)], 
                    (int) (Math.random()*200)
            );
            
            List lotes = new ArrayList();
            Lote lote = new Lote((int) (Math.random()*20)+"0b", (int) (Math.random()*20), Calendar.getInstance(), Calendar.getInstance(), estoque);
            lotes.add(lote);
            
            estoque.setLotes(lotes);

            Produto produto = new Produto("Sandália Havaianas"+((int) (Math.random()*10)+10)+" Polegadas ["+i+"]", 
                "Feita com borracha de pneu de trator, acompanhada de um kit prego para pequenos reparos", 
                (int) (Math.random()*20), 
                (int) (Math.random()*10), 
                    UnidadeMedida.values()[(int) (Math.random()*11)], 
                    UnidadeMedida.values()[(int) (Math.random()*11)], 
                new BigDecimal("32.00"), 
                new BigDecimal("30.00"), 
                new BigDecimal("25.00"), 
                estoque
            );

            repositorioProduto.Salvar(produto);
        }
    }
}
