package com.agendaSaudeOn.Modelos;

import java.util.List;

public class AgendaSearchResponse {

    private String message;
    private List<Agenda> Agendas;
    private Boolean error;
    private String profissional;
    private String mesAtual;

    public AgendaSearchResponse(String message, List<Agenda> agenda, Boolean error, String profissional, String mesAtual) {
        this.message = message;
        this.Agendas = agenda;
        this.error = error;
        this.profissional = profissional;
        this.mesAtual = mesAtual;
    }

    public List<Agenda> getAgenda() {
        return Agendas;
    }

    public String getNome() {
        return profissional;
    }

    public String getEnderecoMaps(int i) {
        return Agendas.get(i).endereco;
    }



}
