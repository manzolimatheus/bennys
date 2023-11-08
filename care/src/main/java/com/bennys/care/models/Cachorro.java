package com.bennys.care.models;

public class Cachorro extends Animal {
    private String raca;

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        return this.getNome() + " é um cachorro da raça " + this.getRaca();
    }
}
