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
    
    public Cliente Autenticar(String identificacaoDoCliente, String senha){
        Query sql =  this.manager.createQuery("SELECT cliente from Cliente cliente WHERE cliente.identificacaoDoCliente = :identificacaoDoCliente and cliente.senha = :senha");
        sql.setParameter("identificacaoDoCliente", identificacaoDoCliente);
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
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
                
        if(obj != null){
            if(obj.getNome() != null && !obj.getNome().isEmpty()){
                parametros.put("nome", obj.getNome() + "%");
                if(filtros.length() > 0)
                    filtros += "AND";
                filtros += "cliente.nome LIKE :nome";
            }
            
            if(obj.getNumeroDocumento() != null && !obj.getNumeroDocumento().isEmpty()){
                parametros.put("numeroDocumento", obj.getNumeroDocumento());
                if(filtros.length() > 0)
                    filtros += " AND ";
                filtros +="cliente.numeroDocumento = :numeroDocumento";
            }   
        }

        if(filtros.length() > 0)
            jpql = jpql + " WHERE " + filtros;
        
        Query sql = this.manager.createQuery(jpql); 
        
        if(!parametros.isEmpty()){    
            for (String campo : parametros.keySet()) {
                sql.setParameter(campo,parametros.get(campo));
            }
        }
        return sql.getResultList();
    }

    @Override
    public boolean Abrir(String identificacaoCliente) {
        Query sql =  this.manager.createQuery("SELECT cliente from Cliente cliente WHERE"
                + " cliente.identificacaoDoCliente = :identificacaoDoCliente");
        sql.setParameter("identificacaoDoCliente", identificacaoCliente);
        try {
            
            Cliente resultado = (Cliente)sql.getSingleResult();
            
            if(resultado != null){
                return true;
            }
            else{
                return false;
            }
            
        } catch(Exception ex){
            return false;
        }
     
    }

}