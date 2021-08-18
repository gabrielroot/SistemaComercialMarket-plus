/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author gabriel, kesley
 */
@Entity
@Table(name="pessoa")
@Inheritance(strategy=InheritanceType.JOINED)  
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length=250, nullable=false)
    private String nome;
    
    @Column(length=250)
    private String endereco;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity=Telefone.class)
    private List<Telefone> telefones;
    
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private TipoPessoa tipoPessoa;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private TipoDocumento tipoDocumento;
    
    @Column(length=250, nullable=false)
    private String numeroDocumento;
    
    @Version
    private long versao;
    
    public Pessoa(){
        this.id = 0L;
        this.nome = "";
        this.telefones = new ArrayList<>();
        this.tipoPessoa = TipoPessoa.Fisica;
        this.tipoDocumento = TipoDocumento.CertidaoNascimento;
        this.numeroDocumento = "";
        this.versao = 1;
    }

    public Pessoa(String nome, String endereco, List<Telefone> telefones, Date dataNascimento, TipoPessoa tipoPessoa, TipoDocumento tipoDocumento, String numeroDocumento) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefones = telefones;
        this.dataNascimento = dataNascimento;
        this.tipoPessoa = tipoPessoa;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.versao = 1;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<Telefone> getTelefones() { return telefones; }
    public void setTelefones(List<Telefone> telefones) { this.telefones = telefones; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento;}

    public TipoPessoa getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(TipoPessoa tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    public TipoDocumento getTipoDocumento() { return tipoDocumento;}
    public void setTipoDocumento(TipoDocumento tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public long getVersao() { return versao; }
    public void setVersao(long versao) { this.versao = versao; }
    
    @java.lang.Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @java.lang.Override
    public boolean equals(java.lang.Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return this.nome;
    }
    
}
