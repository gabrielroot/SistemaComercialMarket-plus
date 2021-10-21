/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.Parcela;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Murilo
 */
@Entity
@Table(name="pagamentoporcrediario")
@DiscriminatorValue("2")
public class PagamentoPorCrediario extends Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar vencimento;
    
    @Column(nullable = false)
    private int numeroParcelas;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity=Parcela.class)
    @JoinColumn(nullable = false)
    private List<Parcela> parcelas;
    
    public PagamentoPorCrediario() {
        this.vencimento = Calendar.getInstance();
        this.numeroParcelas = 1;
        this.parcelas = new ArrayList<>();
    }

    public List<Parcela> getParcelas() { return parcelas; }
    public void setParcelas(List<Parcela> parcelas) { this.parcelas = parcelas; }
    
    public Calendar getVencimento() {return vencimento; }
    public void setVencimento(Calendar vencimento) {this.vencimento = vencimento; }

    public int getNumeroParcelas() { return numeroParcelas; }
    public void setNumeroParcelas(int numeroParcelas) { this.numeroParcelas = numeroParcelas; }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.vencimento);
        hash = 53 * hash + this.numeroParcelas;
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
        final PagamentoPorCrediario other = (PagamentoPorCrediario) obj;
        if (this.numeroParcelas != other.numeroParcelas) {
            return false;
        }
        if (!Objects.equals(this.vencimento, other.vencimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return vencimento.toString();
    }
    
}
