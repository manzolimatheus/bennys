package com.bennys.care.models;

public class Gato extends Animal {
    private String tipoPelagem;

    public String getTipoPelagem() {
        return tipoPelagem;
    }

    public void setTipoPelagem(String tipoPelagem) {
        this.tipoPelagem = tipoPelagem;
    }

    @Override
    public String toString() {
        return this.getNome() + " é um gato com pelagem " + this.getTipoPelagem() + ".";
    }
}
