/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_console;

import br.edu.ifnmg.repositorioFactory.RepositorioFactory;
import br.edu.ifnmg.enums.FuncionarioSituacao;
import br.edu.ifnmg.enums.Segmento;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import br.edu.ifnmg.logicaAplicacao.Fornecedor;
import br.edu.ifnmg.logicaAplicacao.FornecedorRepositorio;
import br.edu.ifnmg.logicaAplicacao.Funcionario;
import br.edu.ifnmg.logicaAplicacao.FuncionarioRepositorio;
import br.edu.ifnmg.logicaAplicacao.Pessoa;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.UsuarioTipo;
import br.edu.ifnmg.logicaAplicacao.Usuario;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class Console {
    public static void main(String[] args){
        if(popularBD()){
            System.out.println("Banco de dados populado com SUCESSO!!");
        }else{
            System.out.println("FALHA ao popular o banco de dados!!");
        }

        queryPessoa();
    }
    
    public static void queryPessoa(){
        PessoaRepositorio repositorioPessoa = RepositorioFactory.getPessoaRepositorio();
        
        System.out.println("-- Buscar pessoa com Filtros --");
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
        
        System.out.println("-- Buscar todas as Pessoas --");
        for(Pessoa pessoa : repositorioPessoa.Buscar(new Pessoa())){
            System.out.println("    "+pessoa.getNome());
        }
    }
     
    public static boolean popularBD(){
        PessoaRepositorio repositorioPessoa = RepositorioFactory.getPessoaRepositorio();
        FuncionarioRepositorio repositorioFuncionario = RepositorioFactory.getFuncionarioRepositorio();
        FornecedorRepositorio repositorioFornecedor = RepositorioFactory.getFornecedorRepositorio();
        UsuarioRepositorio repositorioUsuario = RepositorioFactory.getUsuarioRepositorio();
        
        Telefone telefone01 = new Telefone("3899991111");        
        Telefone telefone02 = new Telefone("3896291131");

        List telefones = new ArrayList<Telefone>();
        telefones.add(telefone01);
        telefones.add(telefone02);

        Pessoa pessoa = new Pessoa(
            "Sebastião Codeiro",
            "Januária, Minas Gerais. Avenida Deodoro da Fonseca N° 111",
            telefones,
            new Date(),
            TipoPessoa.Fisica,
            TipoDocumento.CertidaoNascimento,
            "123456"
        );        
        
        Funcionario funcionario = new Funcionario(
            "Antônio Gomes",
            "Lontra, Minas Gerais. Avenida Água viva N° 145",
            null,
            new Date(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "8123427854",
            FuncionarioSituacao.Ativo,
            null);
        
        Fornecedor fornecedor = new Fornecedor(
            "João Geraldo",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            new Date(),
            TipoPessoa.Juridica,
            TipoDocumento.CNPJ,
            "564673",
            Segmento.Enlatados
        );
        
        Usuario usuarioAdmin = new Usuario(
            "AdminUser",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            new Date(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "564612173",
            FuncionarioSituacao.Ativo,
            null,    
            "admin",
            "123",
            UsuarioTipo.Administrador
        );
        
        Usuario usuarioCaixa = new Usuario(
            "CaixaUser",
            "Itacarambi, Minas Gerais. Avenida Floriano Peixoto N° 12",
            null,
            new Date(),
            TipoPessoa.Fisica,
            TipoDocumento.CNH,
            "564612173",
            FuncionarioSituacao.Ativo,
            null,    
            "caixa",
            "123",
            UsuarioTipo.Caixa
        );
        
        return repositorioPessoa.Salvar(pessoa) &&
               repositorioFuncionario.Salvar(funcionario) &&
               repositorioFornecedor.Salvar(fornecedor) &&
               repositorioUsuario.Salvar(usuarioAdmin) &&
               repositorioUsuario.Salvar(usuarioCaixa);
     }
}
