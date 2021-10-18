/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Pagamento;
import br.edu.ifnmg.logicaAplicacao.PagamentoRepositorio;
import java.util.List;

/**
 *
 * @author Murilo
 */
public class PagamentoDAO extends DataAccessObject<Pagamento> implements PagamentoRepositorio{

    public PagamentoDAO() {
        super(Pagamento.class);
    }

    @Override
    public List<Pagamento> Buscar(Pagamento obj) {
        return null;
    }
    
}
