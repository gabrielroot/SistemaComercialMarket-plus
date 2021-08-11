/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.apresentacao_console;

import br.edu.ifnmg.logicaAplicacao.Pessoa;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import br.edu.ifnmg.logicaAplicacao.Telefone;
import br.edu.ifnmg.logicaAplicacao.TipoDocumento;
import br.edu.ifnmg.logicaAplicacao.TipoPessoa;
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
        PessoaRepositorio repo = new PessoaDAO();
        
//        Pessoa person = repo.Abrir(201L);
//        System.out.println("LOGIN="+user.getLogin());
//        repo.Apagar(person);
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Lucas Santos");
        pessoa.setTipoPessoa(TipoPessoa.Juridica);
        pessoa.setTipoDocumento(TipoDocumento.CertidaoNascimento);
        pessoa.setNumeroDocumento("123456");
        pessoa.setDataNascimento(new Date());
        pessoa.setEndereco("Januária, Minas Gerais. Avenida Deodoro da Fonseca N° 111");
        
        Telefone telefone = new Telefone();
        telefone.setNumero("(38)9999-1111");
        
        Telefone telefon = new Telefone();
        telefon.setNumero("(38)9629-1131");
        List telefones = new ArrayList<Telefone>();
        
        telefones.add(telefone);
        telefones.add(telefon);
        pessoa.setTelefones(telefones);

        if(repo.Salvar(pessoa)){
            System.out.println("Sucesso!!");
        }else{
            System.out.println("Falha!!");
        }
    }
}
