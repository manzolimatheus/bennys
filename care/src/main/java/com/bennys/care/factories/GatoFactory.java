package com.bennys.care.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.bennys.care.models.Gato;

public class GatoFactory {

    public static List<Gato> gerar(Integer quantidade) {

        List<Gato> gatos = new ArrayList<>();

        List<String> donos = new ArrayList<String>(Arrays.asList("Matheus", "Raíne", "Guilherme", "Tiago", "Matheus B.", "Maromo"));
        List<String> nomes = new ArrayList<String>(Arrays.asList("Snowball", "Jake", "Bolinha", "Bola", "Bolinha", "Bola"));
        List<String> tiposPelagem = new ArrayList<String>(Arrays.asList("Curta", "Longa", "Média"));

        for (int i = 0; i < quantidade; i++) {
            Random random = new Random();
            Gato gato = new Gato();
            gato.setDono(donos.get(random.nextInt(donos.size())));
            gato.setEspecie("Gato");
            gato.setNome(nomes.get(random.nextInt(nomes.size())));
            gato.setTipoPelagem(tiposPelagem.get(random.nextInt(tiposPelagem.size())));
            gatos.add(gato);
        }

        return gatos;
    }

}
