/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.enums.FuncionarioSituacao;
import br.edu.ifnmg.enums.Segmento;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name="fornecedor")
public class Fornecedor extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.ORDINAL)
    @Column
    private Segmento segmento;

    public Fornecedor() {
        super();
        this.segmento = Segmento.Enlatados;
    }

    public Fornecedor(
        String nome, 
        String endereco, 
        List<Telefone> telefones, 
        Date dataNascimento, 
        TipoPessoa tipoPessoa, 
        TipoDocumento tipoDocumento, 
        String numeroDocumento, 
        Segmento segmento) {
        
        super(nome, endereco, telefones, dataNascimento, tipoPessoa, tipoDocumento, numeroDocumento);
        
        this.segmento = segmento;
    }
    
    public Segmento getSegmento() { return segmento; }
    public void setSegmento(Segmento segmento) { this.segmento = segmento; }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.segmento.toString();
    }
    
}
