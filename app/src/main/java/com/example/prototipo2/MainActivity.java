package com.example.prototipo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prototipo2.API_classes.RetrofitClient;
import com.example.prototipo2.Modelos.Agenda;
import com.example.prototipo2.Modelos.AgendaSearchResponse;
import com.example.prototipo2.Storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    private void findAgenda(){
        String cpf = cpf_profissional.getText().toString().trim();

        if(cpf.isEmpty()){
            cpf_profissional.setError("CPF obrigatório");
            cpf_profissional.requestFocus();
            return;
        }

        if(cpf.length() > 11){
            cpf_profissional.setError("Tamanho de cpf inválido");
            cpf_profissional.requestFocus();
            return;
        }

        Call<AgendaSearchResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .carregarAgenda(cpf);

        call.enqueue(new Callback<AgendaSearchResponse>() {
            @Override
            public void onResponse(Call<AgendaSearchResponse> call, Response<AgendaSearchResponse> response) {
                AgendaSearchResponse agendaSearchResponse = response.body();

                if(!agendaSearchResponse.getError()){
                    SharedPrefManager.getInstance(MainActivity.this)
                            .saveCPF((Agenda) agendaSearchResponse.getAgenda());

                    Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, agendaSearchResponse.getMessage(), Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this, agendaSearchResponse.getMessage(), Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<AgendaSearchResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                findAgenda();
                break;
        }
    }
}