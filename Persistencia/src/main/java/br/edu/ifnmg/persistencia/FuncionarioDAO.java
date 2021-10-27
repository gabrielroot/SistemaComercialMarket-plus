/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Funcionario;
import br.edu.ifnmg.logicaAplicacao.FuncionarioRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author kesley
 */
public class FuncionarioDAO extends DataAccessObject<Funcionario> implements FuncionarioRepositorio{
    
    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    @Override
    public List<Funcionario> Buscar(Funcionario obj) {
        String jpql=("SELECT funcionario FROM Funcionario funcionario");
        String filtros =""; 
        Hashtable<String, Object> parametros = new Hashtable<>();
        
        if(obj!=null){
            
            if(obj.getNome() !=null & obj.getNome().length()>0){
                if(filtros.length()>0)
                    filtros += " and ";
                filtros+="funcionario.nome LIKE :nome";
                parametros.put("nome",obj.getNome()+ "%");
            }
            
            if(obj.getCargo().getTitulo() != null & obj.getCargo().getTitulo().length()>0){
                if(filtros.length()>0)
                    filtros+=" and ";
                filtros+="funcionario.cargo.titulo LIKE :titulo";
                parametros.put("titulo", obj.getCargo().getTitulo()+"%");
            }
            if(obj.getSituacao()!= null){
                if(filtros.length()>0)
                    filtros+=" and ";
                filtros += "funcionario.situacao = :situacao";
                parametros.put("situacao", obj.getSituacao() );
            }
        }
        if(filtros.length()>0){
            jpql=jpql + " where " + filtros;
        }
            
        jpql += " ORDER BY funcionario.nome";  
         
        Query consulta=this.manager.createQuery(jpql);
        
        for(String campo: parametros.keySet())
            consulta.setParameter(campo,parametros.get(campo));
        
        return consulta.getResultList();
    }
    
    


    

  
    

  

    

   

    

    
    
    
}
