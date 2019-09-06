package com.agendaSaudeOn.AgendaProfissionalPackage;

import java.util.List;

public class AgendaProfissionalResponse {

    private List<ProntuarioProfissional> prontuarioProfissionals;
    private String profissional;

    public AgendaProfissionalResponse(List<ProntuarioProfissional> prontuarioProfissionals, String profissional) {
        this.prontuarioProfissionals = prontuarioProfissionals;
        this.profissional = profissional;
    }

    public List<ProntuarioProfissional> getAgenda() {
        return prontuarioProfissionals;
    }

    public String getNome() {
        return profissional;
    }

    public String getEnderecoMaps(int i) {
        return prontuarioProfissionals.get(i).endereco;
    }



}
