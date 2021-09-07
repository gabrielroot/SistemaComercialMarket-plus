/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.auxiliares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name="lote")
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column()
    private int quantidade;
    
    @Temporal(TemporalType.DATE)
    private Calendar dataValidade;

    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "lote")
    private List<Estoque> estoque;

    public Lote() {
        this.id = 0L;
        this.quantidade = -1;
        this.dataValidade = null;
        this.estoque = new ArrayList<>();
    }

    public Lote(int quantidade, Calendar dataValidade) {
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Calendar getDataValidade() { return dataValidade; }
    public void setDataValidade(Calendar dataValidade) { this.dataValidade = dataValidade; }

    public List<Estoque> getEstoque() { return estoque; }
    public void setEstoque(List<Estoque> estoque) { this.estoque = estoque; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
    
}
