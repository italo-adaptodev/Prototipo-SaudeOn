package com.agendaSaudeOn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.agendaSaudeOn.API_classes.RetrofitClient;
import com.agendaSaudeOn.Modelos.AgendaSearchResponse;
import com.agendaSaudeOn.Storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter;
    SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);
    TextView nomeProf;
    ImageButton setaAnt, setaProx;
    int qtdBtnPrss = 0;
    TextView mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        nomeProf = findViewById(R.id.txt_nome_prof);
        mes = findViewById(R.id.txt_month);
        setaAnt = findViewById(R.id.btn_preview_month);
        setaProx = findViewById(R.id.btn_next_month);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        setaAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtdBtnPrss += -1;
                callAgenda(qtdBtnPrss);
            }
        });

        setaProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtdBtnPrss += +1;
                callAgenda(qtdBtnPrss);
            }
        });

        callAgenda(qtdBtnPrss);


        loadName();



    }


    public void callAgenda(int qtdBtnPrss) {
        Call<AgendaSearchResponse> callAgenda = RetrofitClient
                .getInstance()
                .getApi()
                .carregarAgenda(sharedPrefManager.getProfissionalCpf(), qtdBtnPrss);


        callAgenda.enqueue(new Callback<AgendaSearchResponse>() {
            @Override
            public void onResponse(Call<AgendaSearchResponse> call, Response<AgendaSearchResponse> response) {
                if (response.body() == null) {
                    alertaCpfWrong();
                } else {
                    adapter = new MainAdapter(response.body().getAgenda());
                    recyclerView.setAdapter(adapter);
                    mes.setText(response.body().getMes());
                }
            }

            @Override
            public void onFailure(Call<AgendaSearchResponse> call, Throwable throwable) {

            }
        });
    }

    public void alertaCpfWrong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AgendaActivity.this);
        builder.setTitle("Ops");
        builder.setMessage("Não foi possível achar a sua agenda. Por favor, verifique se o CPF inserido foi digitado corretamente");
        builder.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AgendaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void hasNoData() {

    }

    public void loadName() {
        Call<AgendaSearchResponse> callNome = RetrofitClient
                .getInstance()
                .getApi()
                .buscarNome(sharedPrefManager.getProfissionalCpf());

        callNome.enqueue(new Callback<AgendaSearchResponse>() {
            @Override
            public void onResponse(Call<AgendaSearchResponse> call, Response<AgendaSearchResponse> response) {
                String strconcat = "Olá, " + response.body().getNome() + "!";
                nomeProf.setText(strconcat);
            }

            @Override
            public void onFailure(Call<AgendaSearchResponse> call, Throwable throwable) {

            }
        });
    }


}
