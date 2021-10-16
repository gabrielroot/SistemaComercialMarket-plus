/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.ItemVenda;
import br.edu.ifnmg.enums.TransacaoStatus;
import br.edu.ifnmg.enums.TransacaoTipo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name="transacao_financeira")
public class TransacaoFinanceira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private TransacaoTipo transacaoTipo;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private TransacaoStatus transacaoStatus;
    
    @Temporal(TemporalType.DATE)
    private Calendar data;
    
    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "transacaoFinanceira")
    private List<ItemVenda> itens;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable= false)
    private Usuario usuario;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable= false)
    private Cliente cliente;

    public TransacaoFinanceira() {
        this.id = 0L;
        this.transacaoTipo = null;
        this.transacaoStatus = null;
        this.data = Calendar.getInstance();
        this.itens = new ArrayList<>();
        this.usuario = new Usuario();
        this.cliente = new Cliente();
    }

    public TransacaoFinanceira(
            TransacaoTipo transacaoTipo, 
            TransacaoStatus transacaoStatus,
            Usuario usuario,
            Calendar data,
            Cliente cliente
    ) {
        this.transacaoTipo = transacaoTipo;
        this.transacaoStatus = transacaoStatus;
        this.data = data;
        this.usuario = usuario;
        this.itens = new ArrayList<>();
        this.cliente = cliente;
    }

    public TransacaoTipo getTransacaoTipo() { return transacaoTipo; }
    public void setTransacaoTipo(TransacaoTipo transacaoTipo) { this.transacaoTipo = transacaoTipo; }

    public TransacaoStatus getTransacaoStatus() { return transacaoStatus; }
    public void setTransacaoStatus(TransacaoStatus transacaoStatus) { this.transacaoStatus = transacaoStatus; }

    public Calendar getData() { return data; }
    public void setData(Calendar data) { this.data = data; }

    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens;}

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public BigDecimal getValorTotal(){
        BigDecimal sum = null;
        for(int i = 0; i < this.itens.size(); i++){
            sum = sum == null ? 
                    itens.get(i).getSubTotal() : 
                    sum.add(itens.get(i).getSubTotal());
        }
        
        return sum == null ? new BigDecimal("0.0") : sum;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransacaoFinanceira)) {
            return false;
        }
        TransacaoFinanceira other = (TransacaoFinanceira) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.transacaoStatus.toString();
    }
    
}
