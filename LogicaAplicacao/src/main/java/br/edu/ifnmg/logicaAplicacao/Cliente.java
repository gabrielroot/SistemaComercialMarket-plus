
package br.edu.ifnmg.logicaAplicacao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="cliente")
public class Cliente extends Pessoa implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length=250, nullable=false, unique=true)
    private String identificaoDoCliente;
    @Column(length=250, nullable=false)
    private String senha;
    
    public Cliente() {
        super();
        
        this.identificaoDoCliente = "";
        this.senha = "";

    }
    
    public String getIdentificaoDoCliente() { return this.identificaoDoCliente; }

    public void setIdentificaoDoCliente(String identificaoDoCliente) { this.identificaoDoCliente = identificaoDoCliente; }

    public void setSenha(String senha) { this.senha = senha; }
    
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

    @Override
    public String toString() {
        return this.identificaoDoCliente;
    }
    
}