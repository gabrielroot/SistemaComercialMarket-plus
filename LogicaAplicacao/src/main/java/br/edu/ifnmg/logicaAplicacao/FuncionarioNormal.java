
package br.edu.ifnmg.logicaAplicacao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="funcionarioNormal")
public class FuncionarioNormal extends Funcionario implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length=250, nullable=false, unique=true)
    private int idFuncionario;

    @Column(length=250, nullable=false)
    private String cargo;
    
    public FuncionarioNormal() {
        super();
        
        this.idFuncionario = idFuncionario;
        this.cargo = "";

    }
    public int getIdFuncionario() { return this.idFuncionario; }

    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
    
    public String getCargo() { return this.cargo; }

    public void setCargo(String cargo) { this.cargo = cargo; }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionarioNormal)) {
            return false;
        }
        FuncionarioNormal other = (FuncionarioNormal) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
    
}