/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.DAO;

import com.mycompany.estudiodetatuagem.model.Estoque;
import com.mycompany.estudiodetatuagem.banco.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author bruno
 */
public class EstoqueDAO implements DAO<Estoque> {

    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Estoque model) throws SQLException {
        String sql = "INSERT INTO estoque (nome, categoria, quantidade, quantidadeMinima) "
                + "VALUES (?, ?, ?, ?);";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, model.getNome());
        pst.setString(2, model.getCategoria());
        pst.setInt(3, model.getQuantidade());
        pst.setInt(4, model.getQuantidadeMinima());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean remove(Estoque model) throws SQLException {
        String sql = "DELETE FROM estoque WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, model.getId());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean altera(Estoque model) throws SQLException {
        String sql = "UPDATE estoque SET nome = ?, categoria = ?, quantidade = ?, quantidadeMinima = ? "
                + "WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, model.getNome());
        pst.setString(2, model.getCategoria());
        pst.setInt(3, model.getQuantidade());
        pst.setInt(4, model.getQuantidadeMinima());
        pst.setInt(5, model.getId());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public Estoque buscarID(Estoque model) throws SQLException {
        Estoque estoque = null;

        String sql = "SELECT * FROM estoque WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, model.getId());

        rs = pst.executeQuery();

        if (rs.next()) {
            estoque = new Estoque();
            estoque.setId(rs.getInt("id"));
            estoque.setNome(rs.getString("nome"));
            estoque.setCategoria(rs.getString("categoria"));
            estoque.setQuantidade(rs.getInt("quantidade"));
            estoque.setQuantidadeMinima(rs.getInt("quantidadeMinima"));
        }

        rs.close();
        Banco.desconectar();

        return estoque;
    }

    @Override
    public Collection<Estoque> listar(String criterio) throws SQLException {
        Collection<Estoque> listagem = new ArrayList<>();

        String sql = "SELECT * FROM estoque";
        if (criterio != null && criterio.length() != 0) {
            sql += " WHERE " + criterio;
        }

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            Estoque estoque = new Estoque();
            estoque.setId(rs.getInt("id"));
            estoque.setNome(rs.getString("nome"));
            estoque.setCategoria(rs.getString("categoria"));
            estoque.setQuantidade(rs.getInt("quantidade"));
            estoque.setQuantidadeMinima(rs.getInt("quantidadeMinima"));
            listagem.add(estoque);
        }

        rs.close();
        Banco.desconectar();

        return listagem;
    }

    // Método extra (não faz parte da interface DAO<T>): dá baixa no estoque
    public boolean baixa(Estoque model, int qtd) throws SQLException {
        String sql = "UPDATE estoque SET quantidade = quantidade - ? "
                + "WHERE id = ? AND quantidade >= ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, qtd);
        pst.setInt(2, model.getId());
        pst.setInt(3, qtd);

        boolean ok = pst.executeUpdate() >= 1;
        Banco.desconectar();
        return ok;
    }
}