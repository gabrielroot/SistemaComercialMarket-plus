/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.enums.UsuarioTipo;
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
            if(obj.getUsuarioTipo() != null){
                filtros += "usuario.usuarioTipo = :tipo";
                parametros.put("tipo", obj.getUsuarioTipo() );
            }
            
            if(obj.getNome() != null && obj.getNome().length() > 0){
                if(filtros.length() > 0) filtros += " and ";
                filtros += "usuario.nome LIKE :nome";
                parametros.put("nome", obj.getNome() + "%");
            }
            
            if(obj.getEmail() != null && obj.getEmail().length() > 0){
                if(filtros.length() > 0) filtros += " and ";
                filtros += "usuario.email LIKE :email";
                parametros.put("email", obj.getEmail() + "%");
            }

            if(obj.getId() != null && obj.getId() > 0){
                if(filtros.length() > 0) filtros += " and ";
                filtros += "usuario.id LIKE :id";
                parametros.put("id", obj.getId()+ "%");
            }

        }
        
        if(filtros.length() > 0){
            jpql = jpql + " WHERE " + filtros;
        }
        
        jpql += " ORDER BY usuario.nome";
        
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
            List<Usuario> users = sql.getResultList();
            if(users.size() > 0){
                user = (Usuario) users.get(0);
            }
        }catch(NoResultException ex){
            System.out.println(ex);
        }
        
        return user;
    }
    
    @Override
    public Usuario ValidarAdmin(String email, String senha){
        Query sql =  this.manager.createQuery("SELECT usuario from Usuario usuario WHERE usuario.email = :email and usuario.senha = :senha and usuario.usuarioTipo = :tipo");
        sql.setParameter("email", email);
        sql.setParameter("senha", senha);
        sql.setParameter("tipo", UsuarioTipo.Administrador);
        
        Usuario user = null;
        
        try{
            List<Usuario> users = sql.getResultList();
            if(users.size() > 0){
                user = (Usuario) users.get(0);
            }
        }catch(NoResultException ex){
            System.out.println(ex);
        }
        
        return user;
    }
}
