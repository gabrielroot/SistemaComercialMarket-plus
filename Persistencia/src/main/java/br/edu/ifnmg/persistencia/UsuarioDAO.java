/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Usuario;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
public class UsuarioDAO extends DataAccessObject<Usuario> implements UsuarioRepositorio{

    public UsuarioDAO(){
        super(Usuario.class);
    }

    @Override
    public List<Usuario> Buscar(Usuario obj) {
        String jpql = "SELECT usuario FROM Usuario usuario";
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
        
        if(obj != null){
            if(obj.getEmail() != null && obj.getEmail().length() > 0){
                filtros += "usuario.nome LIKE :nome";
                parametros.put("nome", obj.getEmail() + "%");
            }

            if(obj.getId() > 0){
                if(filtros.length() > 0) filtros += " and ";
                filtros += "usuario.id LIKE :id";
                parametros.put("id", obj.getId()+ "%");
            }

        }
        
        if(filtros.length() > 0){
            jpql = jpql + " WHERE " + filtros;
        }
        
        Query consulta = this.manager.createQuery(jpql);
        
        for(String chave : parametros.keySet()){
            consulta.setParameter(chave, parametros.get(chave));
        }
        
        return consulta.getResultList();
    }

    @Override
    public Usuario Autenticar(String email, String senha){
        Query sql =  this.manager.createQuery("SELECT usuario from Usuario usuario WHERE usuario.email = :email and usuario.senha = :senha");
        sql.setParameter("email", email);
        sql.setParameter("senha", senha);
        
        Usuario user = null;
        
        try{
            user = (Usuario) sql.getResultList().get(0);
        }catch(NoResultException ex){
            return user; 
        }
        
        return user;
    }
}
