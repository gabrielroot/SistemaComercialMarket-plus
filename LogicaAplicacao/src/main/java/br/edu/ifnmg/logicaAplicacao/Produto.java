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
    private BigDecimal minimiParaAtacado;
    
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
            
    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    
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