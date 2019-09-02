package com.agendaSaudeOn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agendaSaudeOn.Storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


 EditText cpf_usuario;
 Button profissional_btn, cuidador_btn;


 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  getSupportActionBar().hide();
  setContentView(R.layout.activity_main);

  cpf_usuario = findViewById(R.id.txt_cpf);
  findViewById(R.id.btn_carregarAgenda).setOnClickListener(this);
  profissional_btn = findViewById(R.id.btn_prof);

  profissional_btn.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    profissional_btn.setBackgroundColor(Color.parseColor("#229C9DBB"));
    //TODO IMPLEMENTAR CHAMADA DA API PARA PROFISSIONAL

   }
  });
 }

 @Override
 public void onClick(View v) {
  switch (v.getId()) {
   case R.id.btn_carregarAgenda:
    findAgendaProfissional();
    break;
  }
 }


 private void findAgendaProfissional() {
  String cpf = cpf_usuario.getText().toString().trim();

  if (cpf.isEmpty()) {
   cpf_usuario.setError("CPF obrigatório");
   cpf_usuario.requestFocus();
   return;
  }

  if (cpf.length() != 11) {
   cpf_usuario.setError("Tamanho de cpf inválido");
   cpf_usuario.requestFocus();
   return;
  }

  SharedPrefManager.getInstance(this).saveCPF(cpf);

  Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
  startActivity(intent);

 }
}