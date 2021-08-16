/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_console;

import br.edu.ifnmg.enums.FuncionarioSituacao;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import br.edu.ifnmg.logicaAplicacao.Funcionario;
import br.edu.ifnmg.logicaAplicacao.FuncionarioRepositorio;
import br.edu.ifnmg.logicaAplicacao.Pessoa;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import br.edu.ifnmg.logicaAplicacao.Telefone;
import br.edu.ifnmg.persistencia.FuncionarioDAO;
import br.edu.ifnmg.persistencia.PessoaDAO;
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
    }
     
    public static boolean popularBD(){
        PessoaRepositorio repositorioPessoa = new PessoaDAO();
        FuncionarioRepositorio repositorioFuncionario = new FuncionarioDAO();
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Lucas Santos");
        pessoa.setTipoPessoa(TipoPessoa.Juridica);
        pessoa.setTipoDocumento(TipoDocumento.CertidaoNascimento);
        pessoa.setNumeroDocumento("123456");
        pessoa.setDataNascimento(new Date());
        pessoa.setEndereco("Januária, Minas Gerais. Avenida Deodoro da Fonseca N° 111");
        
        Telefone telefone01 = new Telefone();
        telefone01.setNumero("(38)9999-1111");
        
        Telefone telefone02 = new Telefone();
        telefone02.setNumero("(38)9629-1131");
        
        List telefones = new ArrayList<Telefone>();
        telefones.add(telefone01);
        telefones.add(telefone02);
        
        pessoa.setTelefones(telefones);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João Castro");
        funcionario.setSituacao(FuncionarioSituacao.Demitido);
        
        repositorioFuncionario.Salvar(funcionario);
        
        return repositorioPessoa.Salvar(pessoa);
     }
}
