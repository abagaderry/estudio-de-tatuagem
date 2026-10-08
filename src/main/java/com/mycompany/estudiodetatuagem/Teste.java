/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem;

import com.mycompany.estudiodetatuagem.model.Estoque;
import com.mycompany.estudiodetatuagem.DAO.EstoqueDAO;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author bruno
 */
public class Teste {
public static void main(String[] args) throws SQLException {
        EstoqueDAO dao = new EstoqueDAO();

        // 1. Inserir
        dao.insere(new Estoque("Tinta vermelha", "tinta", 10, 3));

        // 2. Listar tudo (o último da lista é o que acabou de ser inserido)
        Estoque ultimo = null;
        for (Estoque e : dao.listar("")) {
            System.out.println(e.getId() + " - " + e.getNome()
                    + " - qtd: " + e.getQuantidade() + " (mín: " + e.getQuantidadeMinima() + ")");
            ultimo = e;
        }

        if (ultimo != null) {
            // 3. Baixa válida: 10 - 4 = 6
            System.out.println("Baixa de 4: " + dao.baixa(ultimo, 4));

            // 4. Baixa maior que o saldo: deve ser recusada
            System.out.println("Baixa de 999: " + dao.baixa(ultimo, 999));

            // 5. Conferir o saldo no banco
            Estoque conferido = dao.buscarID(ultimo);
            System.out.println("Saldo atual: " + conferido.getQuantidade());
        }

        // 6. Alerta de reposição
        System.out.println("--- Em falta ---");
        for (Estoque e : dao.listar("quantidade <= quantidadeMinima")) {
            System.out.println(e.getNome() + " (" + e.getQuantidade() + "/" + e.getQuantidadeMinima() + ")");
        }

        // 7. Remover o item de teste, pra não acumular a cada execução
        if (ultimo != null) {
            System.out.println("Removido? " + dao.remove(ultimo));
        }
    }
}