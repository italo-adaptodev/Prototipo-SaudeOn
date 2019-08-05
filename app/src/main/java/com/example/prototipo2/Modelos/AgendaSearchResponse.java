package com.example.prototipo2.Modelos;

import java.util.List;

public class AgendaSearchResponse {

    private String message;
    private List<Agenda> Agenda;
    private Boolean error;

    public AgendaSearchResponse(String message, List<Agenda> agenda, Boolean error) {
        this.message = message;
        this.Agenda = agenda;
        this.error = error;
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
}
