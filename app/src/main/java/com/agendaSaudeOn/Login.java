package com.agendaSaudeOn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agendaSaudeOn.Agendas.AgendaCuidador;
import com.agendaSaudeOn.Agendas.AgendaProfissional;
import com.agendaSaudeOn.Storage.SharedPrefManager;

public class Login extends AppCompatActivity {


    public EditText cpfUsuario;
    protected Button profissionalBtn, cuidadorBtn, carregarAgendaBtn;
    public Integer tipoAgenda = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        cpfUsuario = findViewById(R.id.txt_cpf);
        profissionalBtn = findViewById(R.id.btn_prof);
        cuidadorBtn = findViewById(R.id.btn_cuidador);
        carregarAgendaBtn = findViewById(R.id.btn_carregarAgenda);


        profissionalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profissionalBtn.setBackgroundColor(Color.parseColor("#229C9DBB"));
                tipoAgenda = 0;
            }
        });

        cuidadorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cuidadorBtn.setBackgroundColor(Color.parseColor("#229C9DBB"));
                tipoAgenda = 1;
            }
        });

        carregarAgendaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findAgenda(tipoAgenda);
            }
        });


    }


    private void findAgenda(int tipoAgenda) {
        String cpf = cpfUsuario.getText().toString().trim();

        if (cpf.isEmpty()) {
            cpfUsuario.setError("CPF obrigatório");
            cpfUsuario.requestFocus();
            return;
        }

        if (cpf.length() != 11) {
            cpfUsuario.setError("Tamanho de cpf inválido");
            cpfUsuario.requestFocus();
            return;
        }

        SharedPrefManager.getInstance(this).saveCPF(cpf);

        Intent intent;
        switch (tipoAgenda) {
            case 0:
                intent = new Intent(Login.this, AgendaProfissional.class);
                startActivity(intent);
            case 1:
                intent = new Intent(Login.this, AgendaCuidador.class);
                startActivity(intent);
        }


    }
}