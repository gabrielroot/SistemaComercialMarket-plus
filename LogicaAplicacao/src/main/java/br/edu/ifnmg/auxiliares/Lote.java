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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    @Column(length=254)
    private String codigo;
    
    @Column()
    private int emEstoque;
    
    @Column()
    private int nasPrateleiras;
    
    @Temporal(TemporalType.DATE)
    private Calendar dataFabricacao;
    
    @Temporal(TemporalType.DATE)
    private Calendar dataValidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estoque_id", nullable= false)
    private Estoque estoque;

    public Lote() {
        this.id = 0L;
        this.codigo = "";
        this.emEstoque = -1;
        this.nasPrateleiras = -1;
        this.dataValidade = null;
        this.dataFabricacao = null;
        this.estoque = new Estoque();
    }

    public Lote(String codigo, int emEstoque,int nasPrateleiras, Calendar dataValidade, Calendar dataFabricacao, Estoque estoque) {
        this.codigo = codigo;
        this.emEstoque = emEstoque;
        this.nasPrateleiras = nasPrateleiras;
        this.dataValidade = dataValidade;
        this.dataFabricacao = dataFabricacao;
        this.estoque = estoque;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Calendar getDataValidade() { return dataValidade; }
    public void setDataValidade(Calendar dataValidade) { this.dataValidade = dataValidade; }

    public Estoque getEstoque() { return estoque; }
    public void setEstoque(Estoque estoque) { this.estoque = estoque; }

    public int getEmEstoque() { return emEstoque; }
    public void setEmEstoque(int quantidade) { this.emEstoque = quantidade; }

    public int getNasPrateleiras() { return nasPrateleiras; }
    public void setNasPrateleiras(int nasPrateleiras) { this.nasPrateleiras = nasPrateleiras; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Calendar getDataFabricacao() { return dataFabricacao; }
    public void setDataFabricacao(Calendar dataFabricacao) { this.dataFabricacao = dataFabricacao;}

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
