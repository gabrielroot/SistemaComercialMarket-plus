/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.Lote;
import br.edu.ifnmg.auxiliares.LoteRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
public class LoteDAO extends DataAccessObject<Lote> implements LoteRepositorio{

    public LoteDAO(){
        super(Lote.class);
    }

    @Override
    public List<Lote> Buscar(Lote obj) {
        manager.clear();
        String jpql = "SELECT lote FROM Lote lote";
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
        if(obj != null){
            if(obj.getId() != null && obj.getId() > 0){
                filtros += "lote.id = :id";
                parametros.put("id", obj.getId());
            }
            
            if(obj.getCodigo() != null && obj.getCodigo().length() > 0){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "lote.codigo LIKE :code";
                parametros.put("code", obj.getCodigo() + "%");
            }

            if(obj.getEmEstoque()> -1){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "lote.emEstoque = :emEstq";
                parametros.put("emEstq", obj.getEmEstoque());
            }

            if(obj.getNasPrateleiras()> -1){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "lote.nasPrateleiras = :nasPrat";
                parametros.put("nasPrat", obj.getNasPrateleiras());
            }

            if(obj.getDataValidade() != null){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "lote.dataValidade = :validade";
                parametros.put("validade", obj.getDataValidade());
            }

            if(obj.getDataFabricacao() != null){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "lote.dataFabricacao = :fab";
                parametros.put("fab", obj.getDataFabricacao());
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
