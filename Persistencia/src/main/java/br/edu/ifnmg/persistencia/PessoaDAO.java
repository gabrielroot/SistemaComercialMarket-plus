/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Pessoa;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

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

}
