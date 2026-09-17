/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem;

import com.mycompany.estudiodetatuagem.model.Clientes;
import com.mycompany.estudiodetatuagem.DAO.ClientesDAO;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author bruno
 */
public class Teste {
    public static void main(String[] args) throws SQLException {
        ClientesDAO dao = new ClientesDAO();

        Clientes novo = new Clientes("Maria Silva", "12345678900", "1995-04-12", "11999998888", "maria@email.com");
        dao.insere(novo);
        System.out.println("Cliente inserido!");

        Collection<Clientes> clientes = dao.listar("");
        Clientes ultimo = null;
        for (Clientes c : clientes) {
            System.out.println(c.getId() + " - " + c.getNome() + " - " + c.getCpf());
            ultimo = c;
        }

        if (ultimo != null) {
            boolean removido = dao.remove(ultimo);
            System.out.println("Removido " + removido);
        }
    }
}