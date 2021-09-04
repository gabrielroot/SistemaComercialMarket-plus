
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="cliente")
@DiscriminatorValue(value="2")
public class Cliente extends Pessoa implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    @Column(length=250, nullable=false, unique=true)
    private String identificaoDoCliente;
   
    @Column(length=250, nullable=false)
    private String senha;
    
    
    public Cliente() {
        super();
        this.identificaoDoCliente = "";
        this.senha = "";
    }

    public Cliente(String identificaoDoCliente, 
            String senha, 
            String nome, 
            String endereco,
            List<Telefone> telefones,
            Calendar dataNascimento, 
            TipoPessoa tipoPessoa, 
            TipoDocumento tipoDocumento, 
            String numeroDocumento) {
        super(nome, endereco, telefones, dataNascimento, tipoPessoa, tipoDocumento, numeroDocumento);
        this.identificaoDoCliente = identificaoDoCliente;
        this.senha = senha;
    }
    
    public String getIdentificaoDoCliente() { return this.identificaoDoCliente; }

    public void setIdentificaoDoCliente(String identificaoDoCliente) { this.identificaoDoCliente = identificaoDoCliente; }

    public void setSenha(String senha) { this.senha = senha; }

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