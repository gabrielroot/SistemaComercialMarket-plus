

package br.edu.ifnmg.logicaAplicacao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name="cliente")
public class Cliente extends Pessoa implements Serializable  {

    @Enumerated(EnumType.ORDINAL)
    
    @Column(length=250, nullable=false)
    private String identificaoDoCliente;
    @Column(length=250, nullable=false)
    private String senha;
    
    public Cliente() {
        super();
        
        this.identificaoDoCliente = identificaoDoCliente;
        this.senha = senha;

    }
    
    public String getIdentificaoDoCliente() { return this.identificaoDoCliente; }

    public void setIdentificaoDoCliente(String x) { this.identificaoDoCliente = x; }

    public String getSenha() { return this.senha; }

    public void setSenha(String x) { this.senha = x; }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

}