package br.edu.ifnmg.auxiliares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="validade")
public class Validade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar dataValidade;
    
    @Column()
    private String lote;

    @Column()
    private int loteQuantidade; 
    
    public Validade() {
        this.dataValidade = null;;
        this.lote = "";
        this.loteQuantidade = -1;
    }

    public Validade(Calendar dataValidade,  String lote, int loteQuantidade) {
        this.dataValidade = dataValidade;
        this.lote = lote;
        this.loteQuantidade = loteQuantidade;
    }

    
    
    public void setId(Long id) { this.id = id; }
    public Long getId() { return this.id; }

    public Calendar getDataValidade() { return dataValidade; }
    public void setDataValidade(Calendar dataValidade) { this.dataValidade = dataValidade; }

    public String getLote() { return lote; }
    public void setLote(String Lote) { this.lote = lote; }
    
    public int getLoteQuantidade() { return loteQuantidade; }
    public void setLoteQuantidade(int loteQuantidade) { this.loteQuantidade = loteQuantidade; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Validade)) {
            return false;
        }
        Validade other = (Validade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.lote;
    }
    
}