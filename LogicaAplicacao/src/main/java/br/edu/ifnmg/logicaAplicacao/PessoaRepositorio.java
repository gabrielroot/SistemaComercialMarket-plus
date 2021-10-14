/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

import br.edu.ifnmg.auxiliares.Telefone;
import java.util.List;

/**
 *
 * @author gabriel
 */
public interface PessoaRepositorio extends Repositorio<Pessoa> {
    public Pessoa buscarPessoaTelefone(Telefone telefone);
}
