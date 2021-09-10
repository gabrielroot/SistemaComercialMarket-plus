/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.repositorioFactory;

import br.edu.ifnmg.auxiliares.LoteRepositorio;
import br.edu.ifnmg.logicaAplicacao.ClienteRepositorio;
import br.edu.ifnmg.logicaAplicacao.FornecedorRepositorio;
import br.edu.ifnmg.logicaAplicacao.FuncionarioRepositorio;
import br.edu.ifnmg.logicaAplicacao.PessoaRepositorio;
import br.edu.ifnmg.logicaAplicacao.UsuarioRepositorio;
import br.edu.ifnmg.logicaAplicacao.ProdutoRepositorio;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class RepositorioFactory {
    private static Properties propriedades = new Properties();
    private static PessoaRepositorio pessoa;
    private static FuncionarioRepositorio funcionario;
    private static FornecedorRepositorio fornecedor;
    private static ClienteRepositorio cliente;
    private static UsuarioRepositorio usuario;
    private static ProdutoRepositorio produto;
    private static LoteRepositorio lote;
//    private static CargoFuncionario cargo;
    
    static {
        FileReader leitorArquivo = null;
        
        try {
            
            File arquivoPropriedades = new File("config.properties");
            leitorArquivo = new FileReader(arquivoPropriedades);    
            propriedades.load(leitorArquivo);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                leitorArquivo.close();
            } catch (IOException ex) {
                Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static Object getInstancia(String nomeclasse){
        try {
            Class classe = Class.forName(nomeclasse);
            Object instancia = classe.getDeclaredConstructor().newInstance();
            
            return instancia;
            
        } catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static PessoaRepositorio getPessoaRepositorio() {
        if(pessoa == null){
            String nomeclasse = propriedades.getProperty("PessoaRepositorio");
            pessoa = (PessoaRepositorio) getInstancia(nomeclasse);
        }
        return pessoa;
    }
    
    public static FuncionarioRepositorio getFuncionarioRepositorio() {
        if(funcionario == null){
            String nomeclasse = propriedades.getProperty("FuncionarioRepositorio");
            funcionario = (FuncionarioRepositorio) getInstancia(nomeclasse);
        }
        return funcionario;
    }
    
    public static FornecedorRepositorio getFornecedorRepositorio() {
        if(fornecedor == null){
            String nomeclasse = propriedades.getProperty("FornecedorRepositorio");
            fornecedor = (FornecedorRepositorio) getInstancia(nomeclasse);
        }
        return fornecedor;
    }
    
    public static ClienteRepositorio getClienteRepositorio() {
        if(cliente == null){
            String nomeclasse = propriedades.getProperty("ClienteRepositorio");
            cliente = (ClienteRepositorio) getInstancia(nomeclasse);
        }
        return cliente;
    }
    
    public static UsuarioRepositorio getUsuarioRepositorio() {
        if(usuario == null){
            String nomeclasse = propriedades.getProperty("UsuarioRepositorio");
            usuario = (UsuarioRepositorio) getInstancia(nomeclasse);
        }
        return usuario;
    }
    
    public static ProdutoRepositorio getProdutoRepositorio() {
        if(produto == null){
            String nomeclasse = propriedades.getProperty("ProdutoRepositorio");
            produto = (ProdutoRepositorio) getInstancia(nomeclasse);
        }
        return produto;
    }
    
    public static LoteRepositorio getLoteRepositorio() {
        if(lote == null){
            String nomeclasse = propriedades.getProperty("LoteRepositorio");
            lote = (LoteRepositorio) getInstancia(nomeclasse);
        }
        return lote;
    }
    
    /*public static CargoFuncionario getCargoFuncionario() {
        if(cargo == null){
            String nomeclasse = propriedades.getProperty("CargoFuncionario");
            cargo = (CargoFuncionario) getInstancia(nomeclasse);
        }
        return cargo;
    }*/
    
}
