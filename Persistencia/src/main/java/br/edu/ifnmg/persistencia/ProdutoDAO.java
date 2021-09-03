/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
public class ProdutoDAO extends DataAccessObject<Produto> implements ProdutoRepositorio{

    public ProdutoDAO(){
        super(Produto.class);
    }

    @Override
    public List<Produto> Buscar(Produto obj) {
        String jpql = "SELECT produto FROM Produto produto";
        String filtros = "";
        Hashtable<String, Object> parametros = new Hashtable<>();
        
        if(obj != null){
            if(obj.getNome() != null && obj.getNome().length() > 0){
                filtros += "pessoa.nome LIKE :nome";
                parametros.put("nome", obj.getNome() + "%");
            }

            if(obj.getUnidadeMedidaVarejo()!= null){
                filtros += "produto.unidadeMedidaVarejo = :varejo";
                parametros.put("varejo", obj.getUnidadeMedidaVarejo() );
            }

            if(obj.getUnidadeMedidaCusto()!= null){
                filtros += "produto.unidadeMedidaCusto = :produto";
                parametros.put("produto", obj.getUnidadeMedidaVarejo() );
            }
            
            if(obj.getValorVarejo() != null){
                filtros += "produto.vv LIKE :vv";
                parametros.put("vv", obj.getValorVarejo() + "%");
            }
            
            if(obj.getValorVarejo() != null){
                filtros += "produto.vv LIKE :va";
                parametros.put("va", obj.getValorVarejo() + "%");
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

}
