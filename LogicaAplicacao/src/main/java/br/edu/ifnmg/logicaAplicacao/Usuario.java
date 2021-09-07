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
import br.edu.ifnmg.enums.UsuarioTipo;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "usuario")
public class Usuario extends Funcionario implements Serializable {
    
    @Column(length = 250, nullable = false)
    private String email;
    
    @Column(length = 250, nullable = false)
    private String senha;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private UsuarioTipo usuarioTipo;
    
    @Version
    private long versao;

    public Usuario() {
        super();
        
        this.email = "";
        this.senha = "";
        this.usuarioTipo = UsuarioTipo.Administrador;
    }
    
    public Usuario(
            String nome, 
            String endereco, 
            List<Telefone> telefones, 
            Calendar dataNascimento, 
            TipoPessoa tipoPessoa, 
            TipoDocumento tipoDocumento, 
            String numeroDocumento, 
            FuncionarioSituacao situacao,
            CargoFuncionario cargo,
            String email, 
            String senha,
            UsuarioTipo usuarioTipo
    ) {
        super(
            nome, 
            endereco, 
            telefones, 
            dataNascimento, 
            tipoPessoa, 
            tipoDocumento, 
            numeroDocumento, 
            situacao,
            cargo
        );
        this.email = email;
        this.senha = senha;
        this.usuarioTipo = usuarioTipo;
    }
    
    public Usuario(
            Long id,
            String nome, 
            String endereco, 
            List<Telefone> telefones, 
            Calendar dataNascimento, 
            TipoPessoa tipoPessoa, 
            TipoDocumento tipoDocumento, 
            String numeroDocumento, 
            FuncionarioSituacao situacao,
            CargoFuncionario cargo,
            String email, 
            String senha,
            UsuarioTipo usuarioTipo
    ) {
        super(
            id,
            nome, 
            endereco, 
            telefones, 
            dataNascimento, 
            tipoPessoa, 
            tipoDocumento, 
            numeroDocumento, 
            situacao,
            cargo
        );
        this.email = email;
        this.senha = senha;
        this.usuarioTipo = usuarioTipo;
    }
    

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha;}
    public void setSenha(String senha) { this.senha = senha; }
    
    public UsuarioTipo getUsuarioTipo() { return usuarioTipo; }
    public void setUsuarioTipo(UsuarioTipo usuarioTipo) { this.usuarioTipo = usuarioTipo; }

        @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.senha);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return this.email;
    }
}
