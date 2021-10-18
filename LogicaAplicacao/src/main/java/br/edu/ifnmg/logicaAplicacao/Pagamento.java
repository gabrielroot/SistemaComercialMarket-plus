/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.enums.FormaPagamento;
import br.edu.ifnmg.enums.TipoPagamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author gabriel, murilo
 */
@Entity
@Table(name="pagamento")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(precision=8, scale=2, nullable = false)
    private BigDecimal valorPagamento;
    
    @Column(nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar data;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private TipoPagamento tipo;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private FormaPagamento formaPagamento;
    
    @Version
    private int versao;

    public Pagamento() {
        this.id = 0L;
        this.valorPagamento = null;
        this.data = Calendar.getInstance();
        this.tipo = null;
        this.formaPagamento = null;
        this.versao = 1;
    }

    public BigDecimal getValorPagamento() { return valorPagamento; }
    public void setValorPagamento(BigDecimal valorPagamento) { this.valorPagamento = valorPagamento; }

    public Calendar getData() { return data; }
    public void setData(Calendar data) { this.data = data; }

    public TipoPagamento getTipo() { return tipo; }
    public void setTipo(TipoPagamento tipo) { this.tipo = tipo; }

    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getVersao() { return versao; }
    public void setVersao(int versao) { this.versao = versao; }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return data.toString();
    }
    
}
