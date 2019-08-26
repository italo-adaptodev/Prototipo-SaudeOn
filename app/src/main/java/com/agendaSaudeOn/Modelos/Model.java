package com.agendaSaudeOn.Modelos;

public class Model {

  public String nome, motivo, turno;

  public Model(String nome, String motivo, String turno) {
    this.nome = nome;
    this.motivo = motivo;
    this.turno = turno;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

    public String getTurno() {
    return turno;
  }

  public void setTurno(String turno) {
    this.turno = turno;
  }
}
