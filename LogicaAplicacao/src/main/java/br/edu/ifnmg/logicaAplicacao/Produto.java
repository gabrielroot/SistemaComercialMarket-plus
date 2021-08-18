/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.enums.UnidadeMedida;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Murilo, Kesley
 */
@Entity
@Table(name="produtos")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length=250, nullable=false, unique=true)
    private String nome;
    
    @Column(precision=8, scale=2)
    private BigDecimal minimoParaAtacado;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private UnidadeMedida unidadeMedidaCusto;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private UnidadeMedida unidadeMedidaVenda;

    @Column(precision=8, scale=2)
    private BigDecimal valorVenda;

    @Column(precision=8, scale=2)
    private BigDecimal valorCusto;
    
    public Produto() {
        this.id = 0L;
        this.nome = "";
        this.minimoParaAtacado = new BigDecimal("0.0.");
        this.unidadeMedidaCusto = UnidadeMedida.Grama;
        this.unidadeMedidaVenda = UnidadeMedida.Grama;
        this.valorVenda = new BigDecimal("0.00");
        this.valorCusto = new BigDecimal("0.00");
    }
    
    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public BigDecimal getMinimoParaAtacado() { return this.minimoParaAtacado; }
    public void setMinimoParaAtacado(BigDecimal minimoParaAtacado) { this.minimoParaAtacado = minimoParaAtacado; }
    
    public UnidadeMedida getUnidadeMedidaCusto() { return this.unidadeMedidaCusto; }
    public void setUnidadeMedidaCusto(UnidadeMedida unidadeMedidaCusto) { this.unidadeMedidaCusto = unidadeMedidaCusto; }
    
    public UnidadeMedida getUnidadeMedidaVenda() { return this.unidadeMedidaVenda; }
    public void setUnidadeMedidaVenda(UnidadeMedida unidadeMedidaVenda) { this.unidadeMedidaVenda = unidadeMedidaVenda; }
    
    public BigDecimal getValorVenda() { return this.valorVenda; }
    public void setValorVenda(BigDecimal valorVenda) { this.valorVenda = valorVenda; }
    
    public BigDecimal getValorCusto() { return this.valorCusto; }
    public void setValorCusto(BigDecimal valorCusto) { this.valorCusto = valorCusto; }
    
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
