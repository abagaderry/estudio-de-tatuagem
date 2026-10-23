/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.model;

import java.time.LocalDateTime;

/**
 *
 * @author bruno
 */
public class Sessao {
    private int id, idCliente, idTatuador, duracaoMinutos;
    private Integer idDesign; // opcional
    private LocalDateTime dataHora;
    private double valor;
    private String status = "agendada";

    public Sessao() {
    }

    public Sessao(int idCliente, int idTatuador, Integer idDesign,
                  LocalDateTime dataHora, int duracaoMinutos, double valor) {
        this.idCliente = idCliente;
        this.idTatuador = idTatuador;
        this.idDesign = idDesign;
        this.dataHora = dataHora;
        this.duracaoMinutos = duracaoMinutos;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTatuador() {
        return idTatuador;
    }

    public void setIdTatuador(int idTatuador) {
        this.idTatuador = idTatuador;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Integer getIdDesign() {
        return idDesign;
    }

    public void setIdDesign(Integer idDesign) {
        this.idDesign = idDesign;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}