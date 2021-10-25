/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.Parcela;
import br.edu.ifnmg.auxiliares.ParcelaRepositorio;
import java.util.List;

/**
 *
 * @author Murilo
 */
public class ParcelaDAO extends DataAccessObject<Parcela> 
        implements ParcelaRepositorio {

    public ParcelaDAO() {
        super(Parcela.class);
    }

    @Override
    public List<Parcela> Buscar(Parcela obj) {
        return null;
    }
    
}
