/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.auxiliares.TelefoneRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Murilo
 */
public class TelefoneDAO extends DataAccessObject<Telefone> implements TelefoneRepositorio{

    public TelefoneDAO() {
        super(Telefone.class);
    }

    @Override
    public List<Telefone> Buscar(Telefone obj) {
        String jpql = "SELECT telefone FROM Telefone telefone";
        
        Hashtable<String, Object> parametros = new Hashtable<>();
        
        if(!obj.getNumero().isEmpty() && obj.getNumero() != null){
            parametros.put("numero", obj.getNumero());
            jpql += " where telefone.numero = :numero"; 
        }
        
        Query sql = this.manager.createQuery(jpql);
        
        if(!parametros.isEmpty()){ 
            for (String campo : parametros.keySet()) {
                sql.setParameter(campo,parametros.get(campo));
            }
        }
        
      //  return sql.getResultList();
    return null;
    }
    
}
