/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem;

import com.mycompany.estudiodetatuagem.DAO.DesignsDAO;
import com.mycompany.estudiodetatuagem.DAO.TatuadoresDAO;
import com.mycompany.estudiodetatuagem.model.Designs;
import com.mycompany.estudiodetatuagem.model.Tatuadores;
import java.sql.SQLException;

/**
 *
 * @author bruno
 */
public class Teste {
public static void main(String[] args) throws SQLException {
        TatuadoresDAO tatuadorDao = new TatuadoresDAO();
        DesignsDAO designDao = new DesignsDAO();

        // 0. Preparação: precisa existir um tatuador para ser o autor
        tatuadorDao.insere(new Tatuadores("Tatuador Teste", "Blackwork", 30.0, "11900000000"));
        Tatuadores autor = null;
        for (Tatuadores t : tatuadorDao.listar("nome = 'Tatuador Teste'")) {
            autor = t; // fica com o último, o mais recente
        }
        System.out.println("Tatuador criado com id " + autor.getId());

        // 1. Inserir design
        designDao.insere(new Designs("Leao geometrico", "Blackwork", 180, autor.getId()));
        System.out.println("Design inserido!");

        // 2. Listar só os designs desse tatuador
        Designs ultimo = null;
        for (Designs d : designDao.listar("idTatuador = " + autor.getId())) {
            System.out.println(d.getId() + " - " + d.getNome() + " - " + d.getEstilo()
                    + " - " + d.getDuracaoEstimadaMinutos() + " min - tatuador " + d.getIdTatuador());
            ultimo = d;
        }

        // 3. Alterar
        ultimo.setNome("Leao geometrico (v2)");
        ultimo.setDuracaoEstimadaMinutos(240);
        System.out.println("Alterado? " + designDao.altera(ultimo));

        // 4. Buscar por id para conferir a alteração
        Designs conferido = designDao.buscarID(ultimo);
        System.out.println("Conferindo: " + conferido.getNome() + " - "
                + conferido.getDuracaoEstimadaMinutos() + " min");

        // 5. Integridade: design com tatuador inexistente deve ser recusado
        try {
            designDao.insere(new Designs("Design orfao", "Realismo", 60, 999999));
            System.out.println("ERRO: o banco aceitou um design sem tatuador!");
        } catch (SQLException e) {
            System.out.println("Recusado pelo banco: " + e.getMessage());
        }

        // 6. Integridade: tatuador que tem design não pode ser removido
        try {
            tatuadorDao.remove(autor);
            System.out.println("ERRO: o banco removeu um tatuador que tem design!");
        } catch (SQLException e) {
            System.out.println("Recusado pelo banco: " + e.getMessage());
        }

        // 7. Limpeza: primeiro o design (filho), depois o tatuador (pai)
        System.out.println("Design removido? " + designDao.remove(ultimo));
        System.out.println("Tatuador removido? " + tatuadorDao.remove(autor));
    }
}