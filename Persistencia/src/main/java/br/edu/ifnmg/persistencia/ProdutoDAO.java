/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import java.math.BigDecimal;
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
            if(obj.getId() != null && obj.getId() > 0){
                filtros += "produto.id = :id";
                parametros.put("id", obj.getId());
            }
            
            if(obj.getMinimoParaAtacado() >= 0){
                filtros += "produto.minimoParaAtacado = :min";
                parametros.put("min", obj.getMinimoParaAtacado());
            }
            
            if(obj.getQuantidadePrateleira() >= 0){
                filtros += "produto.quantidadePrateleira = :qtdePrat";
                parametros.put("qtdePrat", obj.getQuantidadePrateleira());
            }
            
            if(obj.getNome() != null && obj.getNome().length() > 0){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.nome LIKE :nome";
                parametros.put("nome", obj.getNome() + "%");
            }
            
            if(obj.getDescricao()!= null && obj.getDescricao().length() > 0){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.descricao LIKE :desc";
                parametros.put("desc", obj.getDescricao() + "%");
            }

            if(obj.getUnidadeMedidaVenda() != null){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.unidadeMedidaVenda = :vendaa";
                parametros.put("vendaa", obj.getUnidadeMedidaVenda());
            }

            if(obj.getUnidadeMedidaCusto() != null){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.unidadeMedidaCusto = :custo";
                parametros.put("custo", obj.getUnidadeMedidaCusto());
            }
            
            if(obj.getValorVarejo() != null){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.valorVarejo = :vv";
                parametros.put("vv", obj.getValorVarejo());
            }
            
            if(obj.getValorAtacado()!= null){
                if(filtros.length() > 0) filtros += " AND ";
                filtros += "produto.valorAtacado = :va";
                parametros.put("va", obj.getValorAtacado());
            }
            
            if(obj.getEstoque() != null){
                if(obj.getEstoque().getLocalizacaoProduto() != null){
                    if(filtros.length() > 0) filtros += " AND ";
                    filtros += "produto.estoque.localizacaoProduto = :local";
                    parametros.put("local", obj.getEstoque().getLocalizacaoProduto());
                }
                
                if(obj.getEstoque().getLotes() != null && obj.getEstoque().getLotes().size() > 0){
                    if(obj.getEstoque().getLotes().get(0).getCodigo() != null && obj.getEstoque().getLotes().get(0).getCodigo().length() > 0){
                        if(filtros.length() > 0) filtros += " AND ";
                        filtros += "produto.estoque.lote.codigo LIKE :code";
                        parametros.put("code", obj.getEstoque().getLotes().get(0).getCodigo() + "%");
                    }
                    
                    if(obj.getEstoque().getLotes().get(0).getQuantidade() > -1){
                        if(filtros.length() > 0) filtros += " AND ";
                        filtros += "produto.estoque.lote.quantidade = :qt";
                        parametros.put("qt", obj.getEstoque().getLotes().get(0).getQuantidade());
                    }

                    if(obj.getEstoque().getLotes().get(0).getDataValidade() != null){
                        if(filtros.length() > 0) filtros += " AND ";
                        filtros += "produto.estoque.lote.dataValidade = :validade";
                        parametros.put("validade", obj.getEstoque().getLotes().get(0).getDataValidade());
                    }

                    if(obj.getEstoque().getLotes().get(0).getDataFabricacao() != null){
                        if(filtros.length() > 0) filtros += " AND ";
                        filtros += "produto.estoque.lote.dataFabricacao = :fab";
                        parametros.put("fab", obj.getEstoque().getLotes().get(0).getDataFabricacao());
                    }
                }
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
