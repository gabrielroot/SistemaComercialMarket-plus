/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.TransacaoFinanceira;
import br.edu.ifnmg.logicaAplicacao.TransacaoFinanceiraRepositorio;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class TransacaoFinanceiraDAO extends DataAccessObject<TransacaoFinanceira> implements TransacaoFinanceiraRepositorio{

    public TransacaoFinanceiraDAO(){
        super(TransacaoFinanceira.class);
    }

    @Override
    public List<TransacaoFinanceira> Buscar(TransacaoFinanceira obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
