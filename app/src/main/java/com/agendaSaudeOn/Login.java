package com.agendaSaudeOn;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agendaSaudeOn.Agendas.AgendaCuidador;
import com.agendaSaudeOn.Agendas.AgendaProfissional;
import com.agendaSaudeOn.Storage.SharedPrefManager;

public class Login extends AppCompatActivity {


    EditText cpfUsuario;
    Button profissionalBtn, cuidadorBtn, carregarAgendaBtn;
    Integer tipoAgenda = -1, PROFISSIONAL = 0, CUIDADOR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        cpfUsuario = findViewById(R.id.txt_cpf);
        profissionalBtn = findViewById(R.id.btn_prof);
        cuidadorBtn = findViewById(R.id.btn_cuidador);
        carregarAgendaBtn = findViewById(R.id.btn_carregarAgenda);


        profissionalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //0 = profissional
                if (tipoAgenda.equals(CUIDADOR)) {
                    tipoAgenda = PROFISSIONAL;
                    profissionalBtn.setTypeface(Typeface.DEFAULT_BOLD);
                    profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
                    profissionalBtn.setPadding(10, 10, 10, 10);
                    cuidadorBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado);

                } else if (tipoAgenda.equals(PROFISSIONAL)) {
                    tipoAgenda = -1;
                    profissionalBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado);
                    profissionalBtn.setPadding(10, 10, 10, 10);
                } else {
                    tipoAgenda = PROFISSIONAL;
                    profissionalBtn.setTypeface(Typeface.DEFAULT_BOLD);
                    profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
                    profissionalBtn.setPadding(10, 10, 10, 10);

                }
            }
        });

        cuidadorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1 = cuidador.
                if (tipoAgenda.equals(PROFISSIONAL)) {
                    tipoAgenda = CUIDADOR;
                    cuidadorBtn.setTypeface(Typeface.DEFAULT_BOLD);
                    cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
                    cuidadorBtn.setPadding(10, 10, 10, 10);
                    profissionalBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado);

                } else if (tipoAgenda.equals(CUIDADOR)) {
                    tipoAgenda = -1;
                    cuidadorBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado);
                    cuidadorBtn.setPadding(10, 10, 10, 10);

                } else {
                    tipoAgenda = CUIDADOR;
                    cuidadorBtn.setTypeface(Typeface.DEFAULT_BOLD);
                    cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
                    cuidadorBtn.setPadding(10, 10, 10, 10);

                }
            }
        });

        carregarAgendaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findAgenda(tipoAgenda);
            }
        });
    }


    private void findAgenda(Integer tipoAgenda) {
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

        if (tipoAgenda.equals(-1)) {
            carregarAgendaBtn.setError("Escolha seu perfil");
            carregarAgendaBtn.requestFocus();
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

    private void escolherPerfil(Button perfilBtn) {

    }
}