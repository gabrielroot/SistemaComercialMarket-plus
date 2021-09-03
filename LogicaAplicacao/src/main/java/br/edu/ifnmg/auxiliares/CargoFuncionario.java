package br.edu.ifnmg.auxiliares;

import br.edu.ifnmg.logicaAplicacao.Funcionario;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@Table(name="cargoFuncionario")
public class CargoFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=250, nullable=false)
    private String titulo;

    @Column(length=250, nullable=false)
    private String funcao;

    @Column(precision=8, scale=2)
    private BigDecimal comissao;

    @Column(precision=8, scale=2)
    private BigDecimal salario;
    
    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "cargo")
    private List<Funcionario> funcionarios;

     
    public CargoFuncionario() {
        this.id=0L;
        this.titulo = "";
        this.funcao = "";
        this.comissao =  new BigDecimal("0.00");
        this.salario =  new BigDecimal("0.00");
        this.funcionarios=new ArrayList<>();
    }
    
    public CargoFuncionario(String titulo, String funcao, BigDecimal comissao, BigDecimal salario) {
        this.titulo = titulo;
        this.funcao = funcao;
        this.comissao = comissao;
        this.salario = salario;
    }
    
    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return this.titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    public BigDecimal getComissao() { return this.comissao; }
    public void setComissao(BigDecimal comissao) { this.comissao = comissao; }

    public BigDecimal getSalario() { return this.salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    
    public List<Funcionario> getFuncionarios() {   return funcionarios;    }

    public void setFuncionarios(List<Funcionario> funcionarios) { this.funcionarios = funcionarios; }

    public boolean add(Funcionario funcionario){
        if(!(this.funcionarios.contains(funcionario))){
            this.funcionarios.add(funcionario);
            return true;
        }
        return false; 
    }
    
    public boolean remove(Funcionario funcionario){
        if(this.funcionarios.contains(funcionario)){
            this.funcionarios.remove(funcionario);
            return true;
        }
        return false; 
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
        if (!(object instanceof CargoFuncionario)) {
            return false;
        }
        CargoFuncionario other = (CargoFuncionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
    
}