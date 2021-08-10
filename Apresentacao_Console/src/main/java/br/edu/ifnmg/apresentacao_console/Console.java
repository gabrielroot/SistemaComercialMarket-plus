/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_console;

import br.edu.ifnmg.logicaAplicacao.Pessoa;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import br.edu.ifnmg.logicaAplicacao.TipoDocumento;
import br.edu.ifnmg.logicaAplicacao.TipoPessoa;
import br.edu.ifnmg.persistencia.PessoaDAO;

/**
 *
 * @author gabriel
 */
public class Console {
     public static void main(String[] args){
        PessoaRepositorio repo = new PessoaDAO();
        
//        Pessoa person = repo.Abrir(201L);
//        System.out.println("LOGIN="+user.getLogin());
//        repo.Apagar(person);
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Pessoa01");
        pessoa.setTipoPessoa(TipoPessoa.Juridica);
        pessoa.setTipoDocumento(TipoDocumento.CertidaoNascimento);
        pessoa.setNumeroDocumento("123424");
        System.out.println(pessoa);
        if(repo.Salvar(pessoa)){
            System.out.println("Sucesso!!");
        }else{
            System.out.println("Falha!!");
        }
        
    }
}
