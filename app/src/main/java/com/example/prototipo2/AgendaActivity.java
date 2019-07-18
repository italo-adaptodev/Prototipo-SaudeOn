package com.example.prototipo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.prototipo2.API_classes.RetrofitClient;
import com.example.prototipo2.Modelos.Agenda;
import com.example.prototipo2.Modelos.AgendaSearchResponse;
import com.example.prototipo2.Storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<Agenda> agenda;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        Call<AgendaSearchResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .carregarAgenda(SharedPrefManager.getInstance(this).getProfissionalCpf());

        call.enqueue(new Callback<AgendaSearchResponse>() {
            @Override
            public void onResponse(Call<AgendaSearchResponse> call, Response<AgendaSearchResponse> response) {
                agenda = response.body().getAgenda();
                adapter = new MainAdapter((ArrayList<Agenda>) agenda);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AgendaSearchResponse> call, Throwable throwable) {

            }
        });

    }
}
