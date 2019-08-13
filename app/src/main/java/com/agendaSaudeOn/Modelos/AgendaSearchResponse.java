package com.agendaSaudeOn.Modelos;

import java.util.List;

public class AgendaSearchResponse {

    private String message;
    private List<Agenda> Agendas;
    private Boolean error;
    private String name;
    private String mesAtual;

    public AgendaSearchResponse(String message, List<Agenda> agenda, Boolean error, String name, String mesAtual) {
        this.message = message;
        this.Agendas = agenda;
        this.error = error;
        this.name = name;
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
        return name;
    }

    public String getMes() {
        return mesAtual;
    }



}
