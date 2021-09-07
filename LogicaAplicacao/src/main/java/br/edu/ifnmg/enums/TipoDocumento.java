/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.enums;

/**
 *
 * @author gabriel
 */
        
public enum TipoDocumento {
    RG("RG"),
    CNPJ("CNPJ"),
    CPF("CPF"),
    CNH("CNH"),
    CTPS("CTPS"),
    Passaporte("Passaporte"),
    CertidaoNascimento("Certidão de Nascimento"),
    CertidaoCasamento("Certidão de Casamento"),
    CertidaoProntuario("Certidão de Pronduário"),
    CarteiraMilitar("Carteira Militar");
    
    private String descricao;

    private TipoDocumento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    @Override
    public String toString() {
        return this.descricao;
    }
}
