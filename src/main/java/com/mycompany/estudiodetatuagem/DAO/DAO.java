/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author bruno
 */
public interface DAO <Tipo> {
    public boolean insere(Tipo model) throws SQLException;
    public boolean remove(Tipo model) throws SQLException;
    public boolean altera(Tipo model) throws SQLException;
    public Tipo buscarID(Tipo model) throws SQLException;
    public Collection<Tipo> listar(String criterio) throws SQLException;
    
      
}
