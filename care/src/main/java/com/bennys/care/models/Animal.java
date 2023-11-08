package com.bennys.care.models;

public class Animal {
    private String nome;
    private String especie;
    private String dono;
    public HistoricoMedico historico;

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

    public Animal(String nome, String especie, String dono){
        this.nome = nome;
        this.especie = especie;
        this.dono = dono;
        historico = new HistoricoMedico();
    }
}
