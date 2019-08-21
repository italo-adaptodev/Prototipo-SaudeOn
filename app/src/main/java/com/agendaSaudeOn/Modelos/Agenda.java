package com.agendaSaudeOn.Modelos;


public class Agenda {

    public String data, hora, turno, paciente, motivo, cpf_profissional, endereco;

    public Agenda(String data, String hora, String turno, String paciente, String motivo, String cpf_profissional, String endereco) {
        this.data = data;
        this.hora = hora;
        this.turno = turno;
        this.paciente = paciente;
        this.motivo = motivo;
        this.cpf_profissional = cpf_profissional;
        this.endereco = endereco;
    }


    public String getData() {
        return data;
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
