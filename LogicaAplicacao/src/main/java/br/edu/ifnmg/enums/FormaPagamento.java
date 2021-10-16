/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.enums;

/**
 *
 * @author kesley
 */
public enum FormaPagamento {
    Cartao("Cartão"),
    Crediario("Crediário"),
    Dinheiro("Dinheiro");
    
    private String descricao;

    private FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
