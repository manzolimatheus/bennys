package com.bennys.care.models;

import java.util.UUID;

public class Animal {
    private String id;
    private String nome;
    private String especie;
    private String dono;

    public Animal() {
        this.id = UUID.randomUUID().toString();
    }

    public Animal(String nome, String dono, String especie) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.dono = dono;
        this.especie = especie;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    @Override
    public String toString() {
        return nome;
    }
}
