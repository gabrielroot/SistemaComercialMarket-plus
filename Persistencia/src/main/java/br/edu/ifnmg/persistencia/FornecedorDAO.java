/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Fornecedor;
import br.edu.ifnmg.logicaAplicacao.FornecedorRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
public class FornecedorDAO extends DataAccessObject<Fornecedor> implements FornecedorRepositorio{

    public FornecedorDAO(){
        super(Fornecedor.class);
    }

    @Override
    public List<Fornecedor> Buscar(Fornecedor obj) {
        String jpql = "SELECT fornecedor FROM Fornecedor fornecedor";
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
        
        if(obj != null){
            if(obj.getSegmento() != null){
                filtros += "fornecedor.segmento = :segmento";
                parametros.put("segmento", obj.getSegmento() );
            }
            
            if(obj.getNome() != null && obj.getNome().length() > 0){
                if(filtros.length() > 0) filtros += " and ";
                filtros += "fornecedor.nome LIKE :nome";
                parametros.put("nome", obj.getNome() + "%");
            }

            if(obj.getId() != null && obj.getId() > 0){
                if(filtros.length() > 0) filtros += " and ";
                filtros += "fornecedor.id LIKE :id";
                parametros.put("id", obj.getId()+ "%");
            }

        }
        
        if(filtros.length() > 0){
            jpql = jpql + " WHERE " + filtros;
        }
        
        jpql += " ORDER BY fornecedor.nome";
        
        Query consulta = this.manager.createQuery(jpql);
        
        for(String chave : parametros.keySet()){
            consulta.setParameter(chave, parametros.get(chave));
        }
        
        return consulta.getResultList();
    }

}
