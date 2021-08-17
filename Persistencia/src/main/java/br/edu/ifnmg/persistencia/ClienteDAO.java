package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Cliente;
import br.edu.ifnmg.logicaAplicacao.ClienteRepositorio;
import java.util.List;

public class ClienteDAO extends DataAccessObject<Cliente> implements ClienteRepositorio{

    public ClienteDAO(){
        super(Cliente.class);
    }

    @Override
    public List<Cliente> Buscar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}