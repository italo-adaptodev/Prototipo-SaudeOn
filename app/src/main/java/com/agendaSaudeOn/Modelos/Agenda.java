package com.agendaSaudeOn.Modelos;


public class Agenda {

    public String turno, paciente, motivo, cpf_profissional, endereco;

    public Agenda(String turno, String paciente, String motivo, String cpf_profissional, String endereco) {
        this.turno = turno;
        this.paciente = paciente;
        this.motivo = motivo;
        this.cpf_profissional = cpf_profissional;
        this.endereco = endereco;
    }

    public String getTurno() {
        return turno;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEndereco() {
        return endereco;
    }


}
