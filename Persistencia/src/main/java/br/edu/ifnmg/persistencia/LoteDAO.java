/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.auxiliares.Lote;
import br.edu.ifnmg.auxiliares.LoteRepositorio;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
public class LoteDAO extends DataAccessObject<Lote> implements LoteRepositorio{

    public LoteDAO(){
        super(Lote.class);
    }

    @Override
    public List<Lote> Buscar(Lote obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
