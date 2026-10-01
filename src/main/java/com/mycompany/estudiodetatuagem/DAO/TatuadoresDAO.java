/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.DAO;

/**
 *
 * @author bruno
 */

import com.mycompany.estudiodetatuagem.model.Tatuadores;
import com.mycompany.estudiodetatuagem.banco.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class TatuadoresDAO implements DAO<Tatuadores> {

    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Tatuadores model) throws SQLException {
        String sql = "INSERT INTO tatuador (nome, especialidade, comissao, contato, ativo) "
                + "VALUES (?, ?, ?, ?, ?);";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, model.getNome());
        pst.setString(2, model.getEspecialidade());
        pst.setDouble(3, model.getComissao());
        pst.setString(4, model.getContato());
        pst.setBoolean(5, model.isAtivo());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean remove(Tatuadores model) throws SQLException {
        String sql = "DELETE FROM tatuador WHERE id = ?;";

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
    public boolean altera(Tatuadores model) throws SQLException {
        String sql = "UPDATE tatuador SET nome = ?, especialidade = ?, comissao = ?, contato = ?, ativo = ? "
                + "WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, model.getNome());
        pst.setString(2, model.getEspecialidade());
        pst.setDouble(3, model.getComissao());
        pst.setString(4, model.getContato());
        pst.setBoolean(5, model.isAtivo());
        pst.setInt(6, model.getId());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public Tatuadores buscarID(Tatuadores model) throws SQLException {
        Tatuadores tatuador = null;

        String sql = "SELECT * FROM tatuador WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, model.getId());

        rs = pst.executeQuery();

        if (rs.next()) {
            tatuador = new Tatuadores();
            tatuador.setId(rs.getInt("id"));
            tatuador.setNome(rs.getString("nome"));
            tatuador.setEspecialidade(rs.getString("especialidade"));
            tatuador.setComissao(rs.getDouble("comissao"));
            tatuador.setContato(rs.getString("contato"));
            tatuador.setAtivo(rs.getBoolean("ativo"));
        }

        rs.close();
        Banco.desconectar();

        return tatuador;
    }

    @Override
    public Collection<Tatuadores> listar(String criterio) throws SQLException {
        Collection<Tatuadores> listagem = new ArrayList<>();

        String sql = "SELECT * FROM tatuador";
        if (criterio != null && criterio.length() != 0) {
            sql += " WHERE " + criterio;
        }

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            Tatuadores tatuador = new Tatuadores();
            tatuador.setId(rs.getInt("id"));
            tatuador.setNome(rs.getString("nome"));
            tatuador.setEspecialidade(rs.getString("especialidade"));
            tatuador.setComissao(rs.getDouble("comissao"));
            tatuador.setContato(rs.getString("contato"));
            tatuador.setAtivo(rs.getBoolean("ativo"));
            listagem.add(tatuador);
        }

        rs.close();
        Banco.desconectar();

        return listagem;
    }
}