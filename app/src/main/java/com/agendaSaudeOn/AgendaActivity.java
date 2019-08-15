package com.agendaSaudeOn;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.agendaSaudeOn.API_classes.RetrofitClient;
import com.agendaSaudeOn.Modelos.AgendaSearchResponse;
import com.agendaSaudeOn.Storage.SharedPrefManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter;
    SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);
    TextView nomeProf, mes;
    Button btn_setDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        nomeProf = findViewById(R.id.txt_nome_prof);
        mes = findViewById(R.id.txt_month);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        loadName();

        findViewById(R.id.btn_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });



    }


    public void callAgenda(String data) {
        Call<AgendaSearchResponse> callAgenda = RetrofitClient
                .getInstance()
                .getApi()
                .carregarAgenda(sharedPrefManager.getProfissionalCpf(), data);


        callAgenda.enqueue(new Callback<AgendaSearchResponse>() {
            @Override
            public void onResponse(Call<AgendaSearchResponse> call, Response<AgendaSearchResponse> response) {
                if (response.body() == null) {
                    alertaCpfWrong();
                } else {
                    adapter = new MainAdapter(response.body().getAgenda());
                    recyclerView.setAdapter(adapter);

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


    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        String oldData = +year + "/" + month + "/" + dayOfMonth;
        callAgenda(oldData);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date newDate = null;
        try {
            newDate = dateFormat.parse(oldData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String newData = dateFormat.format(newDate);

        mes.setText(newData);

    }


}
