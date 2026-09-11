/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.banco;

/**
 *
 * @author bruno
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Banco {

    public static String bancoDados, usuario, senha, servidor;
    public static int porta;
    public static java.sql.Connection conexao = null;

    static {
        Properties props = new Properties();
        try (InputStream input = Banco.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (input == null) {
                throw new RuntimeException(
                    "database.properties não encontrado. Copie o "
                    + "database.properties.example e preencha seus dados.");
            }

            props.load(input);
            bancoDados = props.getProperty("bancoDados");
            usuario    = props.getProperty("usuario");
            senha      = props.getProperty("senha");
            servidor   = props.getProperty("servidor");
            porta      = Integer.parseInt(props.getProperty("porta"));

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar configurações do banco.", e);
        }
    }

    public static void conectar() throws SQLException {
        String url = "jdbc:mariadb://" + servidor + ":" + porta + "/" + bancoDados;
        conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public static void desconectar() throws SQLException {
        conexao.close();
    }

    public static java.sql.Connection obterConexao() throws SQLException {
        if (conexao == null) {
            throw new SQLException("Conexão está fechada..");
        } else {
            return conexao;
        }
    }
}