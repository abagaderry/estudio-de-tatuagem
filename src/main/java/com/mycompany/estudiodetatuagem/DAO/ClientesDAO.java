/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.DAO;

import com.mycompany.estudiodetatuagem.model.Clientes;
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


public class ClientesDAO implements DAO<Clientes> {

    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Clientes model) throws SQLException {
        String sql = "INSERT INTO cliente (nome, cpf, dataNascimento, contato, email) "
                + "VALUES (?, ?, ?, ?, ?);";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, model.getNome());
        pst.setString(2, model.getCpf());
        pst.setDate(3, java.sql.Date.valueOf(model.getDataNascimento()));
        pst.setString(4, model.getContato());
        pst.setString(5, model.getEmail());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean remove(Clientes model) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?;";

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
    public boolean altera(Clientes model) throws SQLException {
        String sql = "UPDATE cliente SET nome = ? WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setString(1, model.getNome());
        pst.setInt(2, model.getId());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public Clientes buscarID(Clientes model) throws SQLException {
        Clientes clientes = null;

        String sql = "SELECT * FROM cliente WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, model.getId());

        rs = pst.executeQuery();

        if (rs.next()) {
            clientes = new Clientes();
            clientes.setId(rs.getInt("id"));
            clientes.setNome(rs.getString("nome"));
            clientes.setCpf(rs.getString("cpf"));
            clientes.setDataNascimento(rs.getString("dataNascimento"));
            clientes.setContato(rs.getString("contato"));
            clientes.setEmail(rs.getString("email"));
        }

        rs.close();
        Banco.desconectar();

        return clientes;
    }

    @Override
    public Collection<Clientes> listar(String criterio) throws SQLException {
        Collection<Clientes> listagem = new ArrayList<>();

        String sql = "SELECT * FROM cliente";
        if (criterio != null && criterio.length() != 0) {
            sql += " WHERE " + criterio;
        }

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            Clientes clientes = new Clientes();
            clientes.setId(rs.getInt("id"));
            clientes.setNome(rs.getString("nome"));
            clientes.setCpf(rs.getString("cpf"));
            clientes.setDataNascimento(rs.getString("dataNascimento"));
            clientes.setContato(rs.getString("contato"));
            clientes.setEmail(rs.getString("email"));
            listagem.add(clientes);
        }

        rs.close();
        Banco.desconectar();

        return listagem;
    }
}