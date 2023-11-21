package com.bennys.care.models;

public class AnimalFormDTO {

    private String nome;
    private String dono;
    private String especie;
    private String tipoPelagem;
    private String raca;

    public AnimalFormDTO(String nome, String dono, String especie, String tipoPelagem, String raca) {
        this.nome = nome;
        this.dono = dono;
        this.especie = especie;
        this.tipoPelagem = tipoPelagem;
        this.raca = raca;
    }

    public String getDono() {
        return dono;
    }
    
    public String getEspecie() {
        return especie;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public String getTipoPelagem() {
        return tipoPelagem;
    }

}
