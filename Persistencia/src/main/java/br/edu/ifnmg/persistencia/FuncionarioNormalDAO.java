package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.FuncionarioNormal;
import br.edu.ifnmg.logicaAplicacao.FuncionarioNormalRepositorio;
import java.util.List;

public class FuncionarioNormalDAO extends DataAccessObject<FuncionarioNormal> implements FuncionarioNormalRepositorio{

    public FuncionarioNormalDAO(){
        super(FuncionarioNormal.class);
    }

    @Override
    public List<FuncionarioNormal> Buscar(FuncionarioNormal obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}