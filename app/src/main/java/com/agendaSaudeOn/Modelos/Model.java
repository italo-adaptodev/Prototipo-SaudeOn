package com.agendaSaudeOn.Modelos;

public class Model {

  public String nome, motivo, data, turno;

  public Model(String nome, String motivo, String data, String turno) {
    this.nome = nome;
    this.motivo = motivo;
    this.data = data;
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

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getTurno() {
    return turno;
  }

  public void setTurno(String turno) {
    this.turno = turno;
  }
}
