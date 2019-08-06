package com.example.prototipo2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.prototipo2.API_classes.RetrofitClient;
import com.example.prototipo2.Modelos.AgendaSearchResponse;
import com.example.prototipo2.Storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter;
    SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);



        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        Call<AgendaSearchResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .carregarAgenda(sharedPrefManager.getProfissionalCpf());

        call.enqueue(new Callback<AgendaSearchResponse>() {
            @Override
            public void onResponse(Call<AgendaSearchResponse> call, Response<AgendaSearchResponse> response) {
                adapter = new MainAdapter(response.body().getAgenda());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<AgendaSearchResponse> call, Throwable throwable) {
                alerta();
            }
        });

    }

   public void alerta(){
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

}
