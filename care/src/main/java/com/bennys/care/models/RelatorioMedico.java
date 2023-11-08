package com.bennys.care.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RelatorioMedico {
    private final String id;
    private final LocalDateTime dataHora;
    private final String medicoResponsavel;
    private final String diagnostico;
    private final String receita;
    private final String animalId;

    public RelatorioMedico(String medicoResponsavel, String diagnostico, String receita, String animalId) {
        this.id = UUID.randomUUID().toString();
        this.dataHora = LocalDateTime.now();
        this.medicoResponsavel = medicoResponsavel;
        this.diagnostico = diagnostico;
        this.receita = receita;
        this.animalId = animalId;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        return "Relatório do dia " + dataHora.format(formatoData) + "\n" +
                "Horário de atendimento: " + dataHora.format(formatoHora) + "\n" +
                "Médico responsável: " + medicoResponsavel + "\n" +
                "Diagnóstico: " + diagnostico + "\n" +
                "Receita: " + receita + "\n";
    }

    public String getId() {
        return this.id;
    }

    public String getAnimalId() {
        return animalId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
