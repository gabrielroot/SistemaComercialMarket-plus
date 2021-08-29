
package br.edu.ifnmg.logicaAplicacao;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name="cliente")
@DiscriminatorValue("C")
public class Cliente extends Pessoa implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    @Column(length=250, nullable=false, unique=true)
    private String identificaoDoCliente;
   
    @Column(length=250, nullable=false)
    private String senha;
    
    @Version
    private int versao;
    
    public Cliente() {
        super();
        
        this.identificaoDoCliente = "";
        this.senha = "";
        this.versao=1;
    }
    
    public String getIdentificaoDoCliente() { return this.identificaoDoCliente; }

    public void setIdentificaoDoCliente(String identificaoDoCliente) { this.identificaoDoCliente = identificaoDoCliente; }

    public void setSenha(String senha) { this.senha = senha; }

    public int getVersao() { return versao;  }

    public void setVersao(int versao) { this.versao = versao; }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.identificaoDoCliente);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.identificaoDoCliente, other.identificaoDoCliente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.identificaoDoCliente;
    }
    
}