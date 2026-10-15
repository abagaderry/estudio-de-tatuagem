/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.DAO;

import com.mycompany.estudiodetatuagem.model.Designs;
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
public class DesignsDAO implements DAO<Designs> {

    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Designs model) throws SQLException {
        String sql = "INSERT INTO design (nome, estilo, duracaoEstimadaMinutos, idTatuador) "
                + "VALUES (?, ?, ?, ?);";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, model.getNome());
        pst.setString(2, model.getEstilo());
        pst.setInt(3, model.getDuracaoEstimadaMinutos());
        pst.setInt(4, model.getIdTatuador());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean remove(Designs model) throws SQLException {
        String sql = "DELETE FROM design WHERE id = ?;";

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
    public boolean altera(Designs model) throws SQLException {
        String sql = "UPDATE design SET nome = ?, estilo = ?, duracaoEstimadaMinutos = ?, idTatuador = ? "
                + "WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, model.getNome());
        pst.setString(2, model.getEstilo());
        pst.setInt(3, model.getDuracaoEstimadaMinutos());
        pst.setInt(4, model.getIdTatuador());
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
    public Designs buscarID(Designs model) throws SQLException {
        Designs design = null;

        String sql = "SELECT * FROM design WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, model.getId());

        rs = pst.executeQuery();

        if (rs.next()) {
            design = new Designs();
            design.setId(rs.getInt("id"));
            design.setNome(rs.getString("nome"));
            design.setEstilo(rs.getString("estilo"));
            design.setDuracaoEstimadaMinutos(rs.getInt("duracaoEstimadaMinutos"));
            design.setIdTatuador(rs.getInt("idTatuador"));
        }

        rs.close();
        Banco.desconectar();

        return design;
    }

    @Override
    public Collection<Designs> listar(String criterio) throws SQLException {
        Collection<Designs> listagem = new ArrayList<>();

        String sql = "SELECT * FROM design";
        if (criterio != null && criterio.length() != 0) {
            sql += " WHERE " + criterio;
        }

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            Designs design = new Designs();
            design.setId(rs.getInt("id"));
            design.setNome(rs.getString("nome"));
            design.setEstilo(rs.getString("estilo"));
            design.setDuracaoEstimadaMinutos(rs.getInt("duracaoEstimadaMinutos"));
            design.setIdTatuador(rs.getInt("idTatuador"));
            listagem.add(design);
        }

        rs.close();
        Banco.desconectar();

        return listagem;
    }
}