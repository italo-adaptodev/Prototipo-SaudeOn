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

    public String getMessage() {
        return message;
    }

    public List<Agenda> getAgenda() {
        return Agendas;
    }

    public Boolean getError() {
        return error;
    }

    public String getNome() {
        return profissional;
    }

    public String getMes() {
        return mesAtual;
    }



}
