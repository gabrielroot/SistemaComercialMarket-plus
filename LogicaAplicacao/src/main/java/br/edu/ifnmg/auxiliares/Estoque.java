/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.auxiliares;

import br.edu.ifnmg.enums.LocalizacaoProduto;
import br.edu.ifnmg.logicaAplicacao.Produto;
import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private int quantidadeMinimaDesejada;

    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.EAGER ,mappedBy = "estoque")
    private List<Produto> produtos;
    
    @OneToMany( cascade=CascadeType.MERGE, fetch = FetchType.EAGER ,mappedBy = "estoque")
    private List<Lote> lotes;

    public Estoque() {
        this.id = 0L;
        this.localizacaoProduto = null;
        this.quantidadeMinimaDesejada = -1;
        this.produtos = new ArrayList<>();
        this.lotes = new ArrayList<>();
    }


    public Estoque(LocalizacaoProduto localizacaoProduto, int quantidadeMinimaDesejada) {
        this.localizacaoProduto = localizacaoProduto;
        this.quantidadeMinimaDesejada = quantidadeMinimaDesejada;
        this.lotes = new ArrayList<>();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalizacaoProduto getLocalizacaoProduto() { return localizacaoProduto; }
    public void setLocalizacaoProduto(LocalizacaoProduto localizacaoProduto) { this.localizacaoProduto = localizacaoProduto; }

    public int getQuantidadeMinimaDesejada() { return quantidadeMinimaDesejada; }
    public void setQuantidadeMinimaDesejada(int quantidadeMinimaDesejada) { this.quantidadeMinimaDesejada = quantidadeMinimaDesejada; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produto) { this.produtos = produto; }

    public List<Lote> getLotes() { return lotes; }
    public void setLotes(List lotes) { this.lotes = lotes; }
    
    public int getSomaLotes() { 
        int sum = 0;
        for(Lote lote: this.lotes){
            sum += lote.getEmEstoque();
        }
        return sum; 
    }
    
    public int getSomaPrateleiras() { 
        int sum = 0;
        for(Lote lote: this.lotes){
            sum += lote.getNasPrateleiras();
        }
        return sum; 
    }

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
