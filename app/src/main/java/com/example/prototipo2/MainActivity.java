package com.example.prototipo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.prototipo2.Storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


 EditText cpf_profissional;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  getSupportActionBar().hide();
  setContentView(R.layout.activity_main);

  cpf_profissional = findViewById(R.id.txt_cpf);
  findViewById(R.id.button).setOnClickListener(this);

 }

 @Override
 public void onClick(View v) {
  switch (v.getId()) {
   case R.id.button:
    findAgenda();
    break;
  }
 }

 private void findAgenda() {
  String cpf = cpf_profissional.getText().toString().trim();

  if (cpf.isEmpty()) {
   cpf_profissional.setError("CPF obrigatório");
   cpf_profissional.requestFocus();
   return;
  }

  if (cpf.length() != 11) {
   cpf_profissional.setError("Tamanho de cpf inválido");
   cpf_profissional.requestFocus();
   return;
  }

  SharedPrefManager.getInstance(this).saveCPF(cpf);

  Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
  startActivity(intent);

 }
}