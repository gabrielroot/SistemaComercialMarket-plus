/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.enums;

/**
 *
 * @author kesley, murilo
 */
public enum TipoPagamento {
    AVista("À vista"),
    APrazo("A Prazo");
    
    private String descricao;

    private TipoPagamento(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
