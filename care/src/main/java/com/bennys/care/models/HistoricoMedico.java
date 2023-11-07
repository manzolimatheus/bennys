package com.bennys.care.models;

import java.util.List;
import java.util.ArrayList;

public class HistoricoMedico {
    private final List<RelatorioMedico> relatorios;

    public void novoRelatorio(String medicoResponsavel, String diagnostico, String receita){
        relatorios.add(new RelatorioMedico(relatorios.size() + 1, medicoResponsavel, diagnostico, receita));
    }

    public String imprimirHistorico(){
        StringBuilder historico = new StringBuilder();
        for (RelatorioMedico r : relatorios) {
            historico.append(r);
        }
        return historico.toString();
    }

    public String imprimirRelatorioPorIndice(int indice){
        return relatorios.get(indice).toString();
    }

    public String imprimirRelatorioPorId(int id){
        for (RelatorioMedico r : relatorios) {
            if (r.getId() == id) {
                return r.toString();
            }
        }
        return "Relatório não encontrado.\n";
    }

    public HistoricoMedico() {
        this.relatorios = new ArrayList<>();
    }
}
