/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.FuncionarioSituacao;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author kesley
 */
@Entity
@Table(name="funcionario")
public class Funcionario extends Pessoa implements Serializable  {

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private FuncionarioSituacao situacao;
    
    @Version
    private long versao;
    
    public Funcionario() {
        super();
        
        this.situacao = FuncionarioSituacao.Ativo;
        this.versao = 1;
    }

    public Funcionario(
        String nome, 
        String endereco, 
        List<Telefone> telefones, 
        Date dataNascimento, 
        TipoPessoa tipoPessoa, 
        TipoDocumento tipoDocumento, 
        String numeroDocumento, 
        FuncionarioSituacao situacao) {
        
        super(nome, endereco, telefones, dataNascimento, tipoPessoa, tipoDocumento, numeroDocumento);

        this.versao = 1;
        this.situacao = situacao;
    }
    
    public FuncionarioSituacao getSituacao() { return this.situacao; }
    public void setSituacao(FuncionarioSituacao fs) { this.situacao = fs; }
    
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
        return this.situacao.toString();
    }
    
}
