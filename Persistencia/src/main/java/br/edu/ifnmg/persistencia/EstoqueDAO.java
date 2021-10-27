/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.Estoque;
import br.edu.ifnmg.auxiliares.EstoqueRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
public class EstoqueDAO extends DataAccessObject<Estoque> implements EstoqueRepositorio{

    public EstoqueDAO(){
        super(Estoque.class);
    }
    
    @Override
    public List<Estoque> Buscar(Estoque obj) {
        manager.clear();
        String jpql = "SELECT estoque FROM Estoque estoque";
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
        
        if(obj != null){
            if(obj.getQuantidadeMinimaDesejada() > -1){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.estoque.quantidadeMinimaDesejada = :qtdemin";
                parametros.put("qtdemin", obj.getQuantidadeMinimaDesejada());
            }

            if(obj.getLocalizacaoProduto() != null){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.estoque.localizacaoProduto = :local";
                parametros.put("local", obj.getLocalizacaoProduto());
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
