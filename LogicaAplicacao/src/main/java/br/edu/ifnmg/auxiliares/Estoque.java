/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.auxiliares;

import br.edu.ifnmg.enums.LocalizacaoProduto;
import br.edu.ifnmg.logicaAplicacao.Produto;
import br.edu.ifnmg.auxiliares.Validade;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name="estoque")
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private LocalizacaoProduto localizacaoProduto;
    
    @Column()
    private int quantidade;
    
    @Column()
    private int quantidadeMinimaDesejada;

    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "estoque")
    private List<Produto> produto;
    
    @Column()
    private  List<Validade> validade;

    public Estoque() {
        this.id = 0L;
        this.localizacaoProduto = null;
        this.quantidade = -1;
        this.quantidadeMinimaDesejada = -1;
        this.produto = new ArrayList<>();
        this.validade = new ArrayList<>();
    }

    public Estoque(LocalizacaoProduto localizacaoProduto, int quantidade, int quantidadeMinimaDesejada) {
        this.localizacaoProduto = localizacaoProduto;
        this.quantidade = quantidade;
        this.quantidadeMinimaDesejada = quantidadeMinimaDesejada;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalizacaoProduto getLocalizacaoProduto() { return localizacaoProduto; }
    public void setLocalizacaoProduto(LocalizacaoProduto localizacaoProduto) { this.localizacaoProduto = localizacaoProduto; }

    public int getQuantidadeMinimaDesejada() { return quantidadeMinimaDesejada; }
    public void setQuantidadeMinimaDesejada(int quantidadeMinimaDesejada) { this.quantidadeMinimaDesejada = quantidadeMinimaDesejada; }

    public List<Produto> getProduto() { return produto; }
    public void setProduto(List<Produto> produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public List<Validade> getValidade() { return validade; }
    public void setValidade(List<Validade> validade) { this.validade = validade; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(this.quantidadeMinimaDesejada);
    }
    
}
