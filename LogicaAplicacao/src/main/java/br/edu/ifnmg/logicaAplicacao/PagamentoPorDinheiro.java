/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Murilo
 */
@Entity
@Table(name="pagamentopordinheiro")
@DiscriminatorValue("1")
public class PagamentoPorDinheiro extends Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(precision=8, scale=2,nullable = false)
    private BigDecimal valorRecebido;
    
    @Column(precision=8, scale=2,nullable = false)
    private BigDecimal troco;

    public PagamentoPorDinheiro() {
        this.valorRecebido = null;
        this.troco = null;
    }

    public BigDecimal getValorRecebido() { return valorRecebido; }
    public void setValorRecebido(BigDecimal valorRecebido) { this.valorRecebido = valorRecebido; }

    public BigDecimal getTroco() { return troco; }
    public void setTroco(BigDecimal troco) { this.troco = troco; } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.valorRecebido);
        hash = 97 * hash + Objects.hashCode(this.troco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PagamentoPorDinheiro other = (PagamentoPorDinheiro) obj;
        if (!Objects.equals(this.valorRecebido, other.valorRecebido)) {
            return false;
        }
        if (!Objects.equals(this.troco, other.troco)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return valorRecebido.toString();
    }
    
}
