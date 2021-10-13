/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.logicaAplicacao.Pessoa;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;
import org.eclipse.persistence.jpa.JpaHelper;

/**
 *
 * @author gabriel
 */
public class PessoaDAO extends DataAccessObject<Pessoa> implements PessoaRepositorio{

    public PessoaDAO(){
        super(Pessoa.class);
    }

    @Override
    public List<Pessoa> Buscar(Pessoa obj) {
        String jpql = "SELECT pessoa FROM Pessoa pessoa";
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
        
        if(obj != null){
            if(obj.getNome() != null && obj.getNome().length() > 0){
                filtros += "pessoa.nome LIKE :nome";
                parametros.put("nome", obj.getNome() + "%");
            }

            if(obj.getNumeroDocumento() != null){
                if(filtros.length() > 0) filtros += " and ";
                filtros += "pessoa.numeroDocumento LIKE :numero";
                parametros.put("numero", obj.getNumeroDocumento()+ "%");
            }

        }
        
        if(filtros.length() > 0){
            jpql = jpql + " WHERE " + filtros;
        }
        
        Query consulta = this.manager.createQuery(jpql);
        
        for(String chave : parametros.keySet()){
            consulta.setParameter(chave, parametros.get(chave));
        }
        
        return consulta.getResultList();
    }

    @Override
    public Pessoa buscarPessoaTelefone(Telefone telefone) {
       
        // recebe um telefone
       // procura a pessoa dona do telefone
       // retorna a pessoa 
       // dar um select em telefone, retornar o ID do telefone e buscar na tabela pessoa_telefone
       
        String jpqlTelefone = "SELECT telefone FROM Telefone telefone where ";
        String jpqlPessoaTelefone = "SELECT pessoa_id FROM Pessoa_telefone where ";
        Pessoa pessoa = new Pessoa();
       /*
        if(!telefone.getNumero().isEmpty()){
            jpqlTelefone += "telefone.numero = "+ telefone.getNumero();
            Query qTelefone = this.manager.createQuery(jpqlTelefone);
            Telefone telefoneEncontrado = (Telefone)qTelefone.getSingleResult();

            jpqlPessoaTelefone += "pessoa_telefone.telefones_id = "+telefoneEncontrado.getId();

            Query qPessoa = this.manager.createQuery(jpqlPessoaTelefone);     
            long Pessoa = (long)qPessoa.getSingleResult();

            Query pessoaProcurada = this.manager.createQuery("SELECT pessoa from Pessoa pessoa WHERE id ="+idPessoa);
            pessoa = (Pessoa)pessoaProcurada.getSingleResult();
       }
       */
       return pessoa;
    }
}
