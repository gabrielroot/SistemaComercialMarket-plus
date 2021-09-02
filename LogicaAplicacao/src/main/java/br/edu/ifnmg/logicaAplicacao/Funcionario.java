/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.CargoFuncionario;
import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.FuncionarioSituacao;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author kesley
 */
@Entity
@Table(name="funcionario")
@DiscriminatorValue(value="0")
//@Inheritance(strategy=InheritanceType.JOINED) 
//@DiscriminatorColumn(discriminatorType=DiscriminatorType.INTEGER, name="usuario")
public class Funcionario extends Pessoa implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private FuncionarioSituacao situacao;
    
    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cargoFuncionario_id", nullable= false)
    private CargoFuncionario cargo;
    
    @Version
    private int versao;
     
    public Funcionario() {
        super();
        this.situacao = FuncionarioSituacao.Ativo;
        this.cargo = new CargoFuncionario();
        this.versao=1;
    }

    public Funcionario(
        String nome, 
        String endereco, 
        List<Telefone> telefones, 
        Calendar dataNascimento, 
        TipoPessoa tipoPessoa, 
        TipoDocumento tipoDocumento, 
        String numeroDocumento, 
        FuncionarioSituacao situacao, 
        CargoFuncionario cargo) {
        
        super(nome, endereco, telefones, dataNascimento, tipoPessoa, tipoDocumento, numeroDocumento);
        this.situacao = situacao;
        this.cargo = cargo;
        this.versao=1;
    }
    
    public FuncionarioSituacao getSituacao() { return this.situacao; }
    public void setSituacao(FuncionarioSituacao fs) { this.situacao = fs; }

    public CargoFuncionario getCargo() { return cargo; }
    public void setCargo(CargoFuncionario cargo) { this.cargo = cargo; }

    public int getVersao() {  return versao;   }

    public void setVersao(int versao) {    this.versao = versao;  }

    
   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome().toString();
    }
    
}
