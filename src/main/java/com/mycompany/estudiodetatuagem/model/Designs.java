/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.model;

/**
 *
 * @author bruno
 */
public class Designs {
    private int id, duracaoEstimadaMinutos, idTatuador;
    private String nome, estilo;
    
    public Designs (){
    }

    public Designs(String nome, String estilo, int duracaoEstimadaMinutos, int idTatuador) {
        this.nome = nome;
        this.estilo = estilo;
        this.duracaoEstimadaMinutos = duracaoEstimadaMinutos;
        this.idTatuador = idTatuador;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracaoEstimadaMinutos() {
        return duracaoEstimadaMinutos;
    }

    public void setDuracaoEstimadaMinutos(int duracaoEstimadaMinutos) {
        this.duracaoEstimadaMinutos = duracaoEstimadaMinutos;
    }

    public int getIdTatuador() {
        return idTatuador;
    }

    public void setIdTatuador(int idTatuador) {
        this.idTatuador = idTatuador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
    
}
