package com.agendaSaudeOn.AgendaProfissionalPackage;

import java.util.List;

public class AgendaProfissionalResponse {

    private List<ProntuarioProfissional> Agendas;
    private String profissional;

    public AgendaProfissionalResponse(List<ProntuarioProfissional> prontuarioProfissionals, String profissional) {
        this.Agendas = prontuarioProfissionals;
        this.profissional = profissional;
    }

    public List<ProntuarioProfissional> getAgenda() {
        return Agendas;
    }

    public String getNome() {
        return profissional;
    }

    public String getEnderecoMaps(int i) {
        return Agendas.get(i).endereco;
    }



}
