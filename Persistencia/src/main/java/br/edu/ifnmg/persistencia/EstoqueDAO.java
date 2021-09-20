/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.Estoque;
import br.edu.ifnmg.auxiliares.EstoqueRepositorio;
import java.util.List;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
