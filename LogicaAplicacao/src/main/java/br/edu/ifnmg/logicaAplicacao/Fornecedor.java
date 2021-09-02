/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.Telefone;
import br.edu.ifnmg.enums.Segmento;
import br.edu.ifnmg.enums.TipoDocumento;
import br.edu.ifnmg.enums.TipoPessoa;
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
@Table(name="fornecedor")
@DiscriminatorValue(value="1")
public class Fornecedor extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Enumerated(EnumType.ORDINAL)
    @Column
    private Segmento segmento;
    
    @Version
    private int versao;
     
    public Fornecedor() {
        super();
        this.segmento = Segmento.Enlatados;
        this.versao=1;
    }
    

    
    public Fornecedor(
        String nome, 
        String endereco, 
        List<Telefone> telefones, 
        Calendar dataNascimento, 
        TipoPessoa tipoPessoa, 
        TipoDocumento tipoDocumento, 
        String numeroDocumento, 
        Segmento segmento) {
        
        super(nome, endereco, telefones, dataNascimento, tipoPessoa, tipoDocumento, numeroDocumento);
        
        this.segmento = segmento;
        this.versao=1;
        
    }

    
    public Segmento getSegmento() { return segmento; }
    
    public void setSegmento(Segmento segmento) { this.segmento = segmento; }

    public int getVersao() {       return versao;    }

    public void setVersao(int versao) {     this.versao = versao;    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.segmento);
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
        final Fornecedor other = (Fornecedor) obj;
        if (this.segmento != other.segmento) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return this.segmento.toString();
    }
    
}
