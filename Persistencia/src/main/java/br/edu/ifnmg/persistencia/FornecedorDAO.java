/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Fornecedor;
import br.edu.ifnmg.logicaAplicacao.FornecedorRepositorio;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class FornecedorDAO extends DataAccessObject<Fornecedor> implements FornecedorRepositorio{

    public FornecedorDAO(){
        super(Fornecedor.class);
    }

    @Override
    public List<Fornecedor> Buscar(Fornecedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
