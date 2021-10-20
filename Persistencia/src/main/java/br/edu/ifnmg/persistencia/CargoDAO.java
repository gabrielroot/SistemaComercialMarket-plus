/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.CargoFuncionario;
import br.edu.ifnmg.auxiliares.CargoRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author luana
 */
public class CargoDAO extends DataAccessObject<CargoFuncionario> implements CargoRepositorio{

    public CargoDAO() {
        super(CargoFuncionario.class);
    }

    /**
     *
     * @param obj
     * @return
     */
    
    

    @Override
    public List<CargoFuncionario> Buscar(CargoFuncionario obj) {
        String jpql=("SELECT cargo FROM CargoFuncionario cargo ORDER BY cargo.titulo");
        
        Query consulta=this.manager.createQuery(jpql);  
        return consulta.getResultList();
    }
    
}
