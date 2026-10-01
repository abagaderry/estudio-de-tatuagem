/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem;

import com.mycompany.estudiodetatuagem.model.Tatuadores;
import com.mycompany.estudiodetatuagem.DAO.TatuadoresDAO;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author bruno
 */
public class Teste {
    public static void main(String[] args) throws SQLException {
        TatuadoresDAO dao = new TatuadoresDAO();
        
        Tatuadores novo = new Tatuadores("João", "Realismo", 30, "11999999");
        dao.insere(novo);
        System.out.println("Novo tatuador adicionado");
        
        Collection<Tatuadores> tatuadores = dao.listar("");
        Tatuadores ultimo = null;
        for (Tatuadores t : tatuadores) {
            System.out.println(t.getId() + " - " + t.getNome() + " - " + t.getEspecialidade()
                    + " - " + t.getComissao() + "% - ativo: " + t.isAtivo());
            ultimo = t;
        }
        
        if (ultimo != null) {
            boolean removido = dao.remove(ultimo);
            System.out.println("Removido? " + removido);
        }
    }
}