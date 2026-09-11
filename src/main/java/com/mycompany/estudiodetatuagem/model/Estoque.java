/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudiodetatuagem.model;

/**
 *
 * @author bruno
 */
public class Estoque {
    private int tinta, agulhas, luvas;

    public Estoque(int tinta, int agulhas, int luvas) {
        this.tinta = tinta;
        this.agulhas = agulhas;
        this.luvas = luvas;
    }

    public int getTinta() {
        return tinta;
    }

    public void setTinta(int tinta) {
        this.tinta = tinta;
    }

    public int getAgulhas() {
        return agulhas;
    }

    public void setAgulhas(int agulhas) {
        this.agulhas = agulhas;
    }

    public int getLuvas() {
        return luvas;
    }

    public void setLuvas(int luvas) {
        this.luvas = luvas;
    }
    

}
