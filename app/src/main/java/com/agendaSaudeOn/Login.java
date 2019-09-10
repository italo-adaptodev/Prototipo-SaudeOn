package com.agendaSaudeOn;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agendaSaudeOn.AgendaCuidadorPackage.AgendaCuidador;
import com.agendaSaudeOn.AgendaProfissionalPackage.AgendaProfissional;
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
        profissionalBtn.setPadding(10,10,10,10);
        cuidadorBtn.setPadding(10,10,10,10);


        profissionalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //0 = profissional
                profissionalBtnClick();
            }
        });

        cuidadorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1 = cuidador.
                cuidadorBtnClick();
            }
        });

        carregarAgendaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findAgenda(tipoAgenda);
            }
        });
    }

    private void cuidadorBtnClick() {
        if (tipoAgenda.equals(PROFISSIONAL)) {
            tipoAgenda = CUIDADOR;
            cuidadorBtn.setTypeface(Typeface.DEFAULT_BOLD);
            cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
            profissionalBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado);
            cuidadorBtn.setPadding(10, 10, 10, 10);
            profissionalBtn.setPadding(10, 10, 10, 10);


        } else if (tipoAgenda.equals(CUIDADOR)) {
            tipoAgenda = -1;
            cuidadorBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado);
            cuidadorBtn.setPadding(10, 10, 10, 10);
            profissionalBtn.setPadding(10, 10, 10, 10);

        } else {
            tipoAgenda = CUIDADOR;
            cuidadorBtn.setTypeface(Typeface.DEFAULT_BOLD);
            cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
            cuidadorBtn.setPadding(10, 10, 10, 10);
            profissionalBtn.setPadding(10, 10, 10, 10);

        }
    }

    private void profissionalBtnClick() {
        if (tipoAgenda.equals(CUIDADOR)) {
            tipoAgenda = PROFISSIONAL;
            profissionalBtn.setTypeface(Typeface.DEFAULT_BOLD);
            profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
            cuidadorBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            cuidadorBtn.setBackgroundResource(R.drawable.txt_arredondado);
            profissionalBtn.setPadding(10, 10, 10, 10);
            cuidadorBtn.setPadding(10, 10, 10, 10);
        } else if (tipoAgenda.equals(PROFISSIONAL)) {
            tipoAgenda = -1;
            profissionalBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado);
            profissionalBtn.setPadding(10, 10, 10, 10);
            cuidadorBtn.setPadding(10, 10, 10, 10);
        } else {
            tipoAgenda = PROFISSIONAL;
            profissionalBtn.setTypeface(Typeface.DEFAULT_BOLD);
            profissionalBtn.setBackgroundResource(R.drawable.txt_arredondado_selected);
            profissionalBtn.setPadding(10, 10, 10, 10);
            cuidadorBtn.setPadding(10, 10, 10, 10);

        }
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
                break;
            case 1:
                intent = new Intent(Login.this, AgendaCuidador.class);
                startActivity(intent);
        }


    }

}