/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.model;

/**
 *
 * @author bruno
 */
public class Sessao {
    private String cliente, tatuador, design, data, duracao;
    private float valor;

    public Sessao(String cliente, String tatuador, String design, String data, String duracao, float valor) {
        this.cliente = cliente;
        this.tatuador = tatuador;
        this.design = design;
        this.data = data;
        this.duracao = duracao;
        this.valor = valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTatuador() {
        return tatuador;
    }

    public void setTatuador(String tatuador) {
        this.tatuador = tatuador;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
