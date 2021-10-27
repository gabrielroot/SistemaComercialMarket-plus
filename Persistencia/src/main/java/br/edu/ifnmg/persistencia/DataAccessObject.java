/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;
import br.edu.ifnmg.logicaAplicacao.Repositorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author gabriel
 * @param <T>
 */
public abstract class DataAccessObject<T> implements Repositorio<T>{

    protected EntityManager manager;
    private Class type;
    
    public DataAccessObject(Class type){
        var factory = Persistence.createEntityManagerFactory("UP");
        this.manager = factory.createEntityManager();
        this.type = type;
    }
    
    @Override
    public boolean Salvar(T obj) {
        EntityTransaction transacao = this.manager.getTransaction();
        try {
            transacao.begin();

            if(!Merge(obj)){
                transacao.rollback();
                transacao.begin();
                this.manager.persist(obj);
            }

            transacao.commit();
            
            return true;
            
        } catch(Exception ex){
            transacao.rollback();
            System.out.println(ex);
            
            return false;
        }
    }
    
    private boolean Merge(T obj){
        try{
            this.manager.merge(obj);
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean Apagar(T obj) {
        EntityTransaction transacao = this.manager.getTransaction();
        try {
            transacao.begin();
            T entity = this.manager.merge(obj);
            this.manager.remove(entity);
            
            transacao.commit();
            
            return true;
            
        } catch(Exception ex){
            System.out.println(ex);
            transacao.rollback();
            
            return false;
        }
    }

    @Override
    public T Abrir(Long id) {
        try {
            T obj = (T)this.manager.find(this.type, id);
            
            return obj;
            
        } catch(Exception ex){
            return null;
        }
    }   
    
    public abstract List<T> Buscar(T obj);
}
