/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.ItemVenda;
import br.edu.ifnmg.auxiliares.ItemVendaRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
public class ItemVendaDAO extends DataAccessObject<ItemVenda> implements ItemVendaRepositorio{

    public ItemVendaDAO(){
        super(ItemVenda.class);
    }

    @Override
    public List<ItemVenda> Buscar(ItemVenda obj) {
        manager.clear();
        String jpql = "SELECT itemVenda FROM ItemVenda itemVenda";
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
        if(obj != null){
            if(obj.getId() != null && obj.getId() > 0){
                filtros += "itemVenda.id = :id";
                parametros.put("id", obj.getId());
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
