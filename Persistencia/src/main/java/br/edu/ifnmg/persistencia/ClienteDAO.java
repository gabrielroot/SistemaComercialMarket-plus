package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Cliente; 
import br.edu.ifnmg.logicaAplicacao.ClienteRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ClienteDAO extends DataAccessObject<Cliente> implements ClienteRepositorio{

    public ClienteDAO(){
        super(Cliente.class);
    }
    
    public Cliente Autenticar(String identificacaoCliente, String senha){
        Query sql =  this.manager.createQuery("SELECT cliente from Cliente cliente WHERE cliente.identificacaoCliente = :email and cliente.senha = :senha");
        sql.setParameter("identificacaoCliente", identificacaoCliente);
        sql.setParameter("senha", senha);
        
        Cliente user = null;
        
        try{
            List<Cliente> users = null;
            users = sql.getResultList();
            if(users.size() > 0){
                user = (Cliente) users.get(0);
            }
        }catch(NoResultException ex){
            System.out.println(ex);
        }
        
        return user;
    }

    @Override
    public List<Cliente> Buscar(Cliente obj) {
        
        String jpql = "select cliente from Cliente cliente";
        
        Hashtable<String, Object> parametros = new Hashtable<>();
                
        if(obj != null){
            if(obj.getNome() != null && !obj.getNome().isEmpty()){
                parametros.put("nome", obj.getNome() + "%");
                
            }
        }

        if(!parametros.isEmpty()){         
            String filtros = "";
            jpql += " where ";      
            for (String campo : parametros.keySet()) {
                if(!filtros.isEmpty())
                    filtros += " and ";
                jpql += "cliente." + campo + " LIKE :" + campo;
            }
            jpql += filtros;
        }
        
        Query sql = this.manager.createQuery(jpql);
        
        if(!parametros.isEmpty()){ /// se não ta fazio faça 
            for (String campo : parametros.keySet()) {
                sql.setParameter(campo,parametros.get(campo));
            }
        }
        
        return sql.getResultList();
    }

}