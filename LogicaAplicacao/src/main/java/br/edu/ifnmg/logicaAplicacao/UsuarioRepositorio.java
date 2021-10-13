/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.logicaAplicacao;

/**
 *
 * @author kesley
 */
public interface UsuarioRepositorio extends Repositorio<Usuario> {
    public Usuario Autenticar(String login, String senha);
    public Usuario ValidarAdmin(String login, String senha);
    
}
