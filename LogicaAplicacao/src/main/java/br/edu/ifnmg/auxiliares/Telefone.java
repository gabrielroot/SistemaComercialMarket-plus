/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.auxiliares;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name="telefone")
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=250, nullable=false)
    private String numero;
    
    @Version
    private long versao;
    
    public Telefone() {
        this.numero = "";
        this.versao = 1;
    }

    public Telefone(String numero) {
        this.numero = numero;
    }

    
    
    public void setId(Long id) { this.id = id; }
    public Long getId() { return this.id; }

    public void setNumero(String numero) { this.numero = numero; }
    public String getNumero() { return this.numero; }

    public long getVersao() { return versao; }
    public void setVersao(long versao) { this.versao = versao; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.numero;
    }
    
}
