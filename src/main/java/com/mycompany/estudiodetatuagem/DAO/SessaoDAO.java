/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.DAO;

import com.mycompany.estudiodetatuagem.banco.Banco;
import com.mycompany.estudiodetatuagem.model.Sessao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author bruno
 */
public class SessaoDAO implements DAO<Sessao> {

    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Sessao model) throws SQLException {
        String sql = "INSERT INTO sessao (idCliente, idTatuador, idDesign, dataHora, duracaoMinutos, valor, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, model.getIdCliente());
        pst.setInt(2, model.getIdTatuador());
        if (model.getIdDesign() != null) {
            pst.setInt(3, model.getIdDesign());
        } else {
            pst.setNull(3, Types.INTEGER);
        }
        pst.setTimestamp(4, Timestamp.valueOf(model.getDataHora()));
        pst.setInt(5, model.getDuracaoMinutos());
        pst.setDouble(6, model.getValor());
        pst.setString(7, model.getStatus());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean remove(Sessao model) throws SQLException {
        String sql = "DELETE FROM sessao WHERE id = ?;";

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
    public boolean altera(Sessao model) throws SQLException {
        String sql = "UPDATE sessao SET idCliente = ?, idTatuador = ?, idDesign = ?, dataHora = ?, "
                + "duracaoMinutos = ?, valor = ?, status = ? WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, model.getIdCliente());
        pst.setInt(2, model.getIdTatuador());
        if (model.getIdDesign() != null) {
            pst.setInt(3, model.getIdDesign());
        } else {
            pst.setNull(3, Types.INTEGER);
        }
        pst.setTimestamp(4, Timestamp.valueOf(model.getDataHora()));
        pst.setInt(5, model.getDuracaoMinutos());
        pst.setDouble(6, model.getValor());
        pst.setString(7, model.getStatus());
        pst.setInt(8, model.getId());

        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public Sessao buscarID(Sessao model) throws SQLException {
        Sessao sessao = null;

        String sql = "SELECT * FROM sessao WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, model.getId());

        rs = pst.executeQuery();

        if (rs.next()) {
            sessao = montar(rs);
        }

        rs.close();
        Banco.desconectar();

        return sessao;
    }

    @Override
    public Collection<Sessao> listar(String criterio) throws SQLException {
        Collection<Sessao> listagem = new ArrayList<>();

        String sql = "SELECT * FROM sessao";
        if (criterio != null && criterio.length() != 0) {
            sql += " WHERE " + criterio;
        }
        sql += " ORDER BY dataHora";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            listagem.add(montar(rs));
        }

        rs.close();
        Banco.desconectar();

        return listagem;
    }

    // Métodos extras (não fazem parte da interface DAO<T>)

    // Cancelar mantém o histórico: só muda o status, não apaga a linha
    public boolean cancela(Sessao model) throws SQLException {
        String sql = "UPDATE sessao SET status = 'cancelada' WHERE id = ?;";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, model.getId());

        boolean ok = pst.executeUpdate() >= 1;
        Banco.desconectar();
        return ok;
    }

    // Verifica se o tatuador já tem outra sessão ativa que se sobrepõe ao horário
    public boolean existeConflito(Sessao nova) throws SQLException {
        String sql = "SELECT COUNT(*) FROM sessao "
                + "WHERE idTatuador = ? AND status <> 'cancelada' AND id <> ? "
                + "AND dataHora < ? "
                + "AND DATE_ADD(dataHora, INTERVAL duracaoMinutos MINUTE) > ?;";

        LocalDateTime inicio = nova.getDataHora();
        LocalDateTime fim = inicio.plusMinutes(nova.getDuracaoMinutos());

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, nova.getIdTatuador());
        pst.setInt(2, nova.getId());
        pst.setTimestamp(3, Timestamp.valueOf(fim));
        pst.setTimestamp(4, Timestamp.valueOf(inicio));

        rs = pst.executeQuery();
        rs.next();
        boolean conflito = rs.getInt(1) > 0;

        rs.close();
        Banco.desconectar();

        return conflito;
    }

    // Converte a linha atual do ResultSet em um objeto Sessao
    private Sessao montar(ResultSet rs) throws SQLException {
        Sessao sessao = new Sessao();
        sessao.setId(rs.getInt("id"));
        sessao.setIdCliente(rs.getInt("idCliente"));
        sessao.setIdTatuador(rs.getInt("idTatuador"));

        int idDesign = rs.getInt("idDesign");
        sessao.setIdDesign(rs.wasNull() ? null : idDesign);

        sessao.setDataHora(rs.getTimestamp("dataHora").toLocalDateTime());
        sessao.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
        sessao.setValor(rs.getDouble("valor"));
        sessao.setStatus(rs.getString("status"));
        return sessao;
    }
}