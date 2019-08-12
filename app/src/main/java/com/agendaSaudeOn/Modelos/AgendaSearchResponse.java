package com.agendaSaudeOn.Modelos;

import java.util.List;

public class AgendaSearchResponse {

    private String message;
    private List<Agenda> Agenda;
    private Boolean error;
    private String name;

    public AgendaSearchResponse(String message, List<Agenda> agenda, Boolean error, String name) {
        this.message = message;
        this.Agenda = agenda;
        this.error = error;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public List<Agenda> getAgenda() {
        return Agenda;
    }

    public Boolean getError() {
        return error;
    }

    public String getNome() {
        return name;
    }
}
