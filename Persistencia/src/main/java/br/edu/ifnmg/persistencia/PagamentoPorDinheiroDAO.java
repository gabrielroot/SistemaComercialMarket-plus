/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.PagamentoPorDinheiro;
import br.edu.ifnmg.logicaAplicacao.PagamentoPorDinheiroRepositorio;
import java.util.List;

/**
 *
 * @author Murilo
 */
public class PagamentoPorDinheiroDAO 
        extends DataAccessObject<PagamentoPorDinheiro> 
        implements PagamentoPorDinheiroRepositorio {

    public PagamentoPorDinheiroDAO() {
        super(PagamentoPorDinheiro.class);
    }

    @Override
    public List<PagamentoPorDinheiro> Buscar(PagamentoPorDinheiro obj) {
        return null;
    }
    
}
