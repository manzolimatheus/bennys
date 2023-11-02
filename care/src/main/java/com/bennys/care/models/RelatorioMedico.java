package com.bennys.care.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelatorioMedico {
    private final int id;
    private final LocalDateTime dataHora;
    private final String medicoResponsavel;
    private final String diagnostico;
    private final String receita;

    public RelatorioMedico(int id, String medicoResponsavel, String diagnostico, String receita) {
        this.id = id;
        this.dataHora = LocalDateTime.now();
        this.medicoResponsavel = medicoResponsavel;
        this.diagnostico = diagnostico;
        this.receita = receita;
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

    public int getId() {
        return id;
    }
}
