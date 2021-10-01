/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.Estoque;
import br.edu.ifnmg.auxiliares.ItemVenda;
import br.edu.ifnmg.enums.UnidadeMedida;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Murilo, Kesley
 */
@Entity
@Table(name="produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length=250, nullable=false, unique=true)
    private String nome;
    
    @Column(length=1000, nullable=true)
    private String descricao;

    @Column()
    private int minimoParaAtacado;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private UnidadeMedida unidadeMedidaCusto;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private UnidadeMedida unidadeMedidaVenda;

    @Column(precision=8, scale=2)
    private BigDecimal valorVarejo;

    @Column(precision=8, scale=2)
    private BigDecimal valorAtacado;

    @Column(precision=8, scale=2)
    private BigDecimal valorCusto;
    
    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "estoque_id", nullable= false)
    private Estoque estoque;

    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "produto")
    private List<ItemVenda> itens;
    
    public Produto() {
        this.id = 0L;
        this.nome = "";
        this.descricao = "";
        this.minimoParaAtacado = -1;
        this.unidadeMedidaCusto = null;
        this.unidadeMedidaVenda = null;
        this.valorVarejo = null;
        this.valorAtacado= null;
        this.valorCusto = null;
        this.estoque = new Estoque();
        this.itens = new ArrayList<>();
    }

    public Produto(
            String nome, 
            String descricao, 
            int minimoParaAtacado, 
            UnidadeMedida unidadeMedidaCusto, 
            UnidadeMedida unidadeMedidaVenda, 
            BigDecimal valorVarejo, 
            BigDecimal valorAtacado,
            BigDecimal valorCusto,
            Estoque estoque
    ) {
        this.nome = nome;
        this.descricao = descricao;
        this.minimoParaAtacado = minimoParaAtacado;
        this.unidadeMedidaCusto = unidadeMedidaCusto;
        this.unidadeMedidaVenda = unidadeMedidaVenda;
        this.valorVarejo = valorVarejo;
        this.valorAtacado = valorAtacado;
        this.valorCusto = valorCusto;
        this.estoque = estoque;
        this.itens = new ArrayList<>();
    }
    
    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getMinimoParaAtacado() { return this.minimoParaAtacado; }
    public void setMinimoParaAtacado(int minimoParaAtacado) { this.minimoParaAtacado = minimoParaAtacado; }
    
    public UnidadeMedida getUnidadeMedidaCusto() { return this.unidadeMedidaCusto; }
    public void setUnidadeMedidaCusto(UnidadeMedida unidadeMedidaCusto) { this.unidadeMedidaCusto = unidadeMedidaCusto; }
    
    public UnidadeMedida getUnidadeMedidaVenda() { return this.unidadeMedidaVenda; }
    public void setUnidadeMedidaVenda(UnidadeMedida unidadeMedidaVenda) { this.unidadeMedidaVenda = unidadeMedidaVenda; }

    public BigDecimal getValorAtacado() { return valorAtacado; }
    public void setValorAtacado(BigDecimal valorAtacado) { this.valorAtacado = valorAtacado; }
    
    public BigDecimal getValorCusto() { return this.valorCusto; }
    public void setValorCusto(BigDecimal valorCusto) { this.valorCusto = valorCusto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public BigDecimal getValorVarejo() { return valorVarejo; }
    public void setValorVarejo(BigDecimal valorVarejo) { this.valorVarejo = valorVarejo; }

    public Estoque getEstoque() { return estoque; }
    public void setEstoque(Estoque estoque) { this.estoque = estoque; }

    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens;}
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
    
}
