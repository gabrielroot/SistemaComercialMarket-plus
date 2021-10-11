/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.auxiliares;

import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.logicaAplicacao.TransacaoFinanceira;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name="itemVenda")
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(precision=8, scale=2)
    private BigDecimal quantidade;
    
    @Column(precision=8, scale=2)
    private BigDecimal subTotal;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable= false)
    private Produto produto;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "transacaoFinanceira_id", nullable= false)
    private TransacaoFinanceira transacaoFinanceira;

    public ItemVenda() {
        this.id = 0L;
        this.quantidade = null;
        this.subTotal = null;
        this.produto = new Produto();
        this.transacaoFinanceira = new TransacaoFinanceira();
    }

    public ItemVenda(BigDecimal quantidade, BigDecimal valorUnitario) {
        this.quantidade = quantidade;
        this.subTotal = quantidade.multiply(valorUnitario);
        this.produto = new Produto();
        this.transacaoFinanceira = new TransacaoFinanceira();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public BigDecimal getSubTotal() { return subTotal; }
    public BigDecimal getSubTotal(BigDecimal valorUnitario) { return quantidade.multiply(valorUnitario); }
    
    /**
     *
     * @param subTotal
     */
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public TransacaoFinanceira getTransacaoFinanceira() { return transacaoFinanceira; }
    public void setTransacaoFinanceira(TransacaoFinanceira transacaoFinanceira) { this.transacaoFinanceira = transacaoFinanceira; }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVenda)) {
            return false;
        }
        ItemVenda other = (ItemVenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.subTotal.toString();
    }
    
}
