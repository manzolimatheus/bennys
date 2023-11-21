package com.bennys.care.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.bennys.care.models.Cachorro;

public class CachorroFactory {

    public static List<Cachorro> gerar(Integer quantidade) {

        List<Cachorro> Cachorros = new ArrayList<>();

        List<String> donos = new ArrayList<String>(
                Arrays.asList("Matheus", "Raíne", "Guilherme", "Tiago", "Matheus B.", "Maromo"));
        List<String> nomes = new ArrayList<String>(
                Arrays.asList("Snowball", "Jake", "Bolinha", "Bola", "Bolinha", "Bola"));
        List<String> racas = new ArrayList<String>(
                Arrays.asList("Puddle", "Labrador", "Vira-lata", "Pastor Alemão", "Bulldog"));

        for (int i = 0; i < quantidade; i++) {
            Random random = new Random();
            Cachorro Cachorro = new Cachorro();
            Cachorro.setDono(donos.get(random.nextInt(donos.size())));
            Cachorro.setEspecie("Cachorro");
            Cachorro.setNome(nomes.get(random.nextInt(nomes.size())));
            Cachorro.setRaca(racas.get(random.nextInt(racas.size())));
            Cachorros.add(Cachorro);
        }

        return Cachorros;
    }

}
