package com.agendaSaudeOn.AgendaProfissionalPackage;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.agendaSaudeOn.API_classes.RetrofitClient;
import com.agendaSaudeOn.Login;
import com.agendaSaudeOn.R;
import com.agendaSaudeOn.Storage.SharedPrefManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaProfissional extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProfissionalAdapter adapter;
    SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);
    TextView nomeProf, mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_profissional);

        check();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        nomeProf = findViewById(R.id.txt_nome_prof);
        mes = findViewById(R.id.txt_month);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.btn_searchDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

    }


    public void callAgenda(String data) {
        Call<AgendaProfissionalResponse> callAgenda = RetrofitClient.getInstance().getApi().carregarAgendaProfissional(sharedPrefManager.getCpf(), data);

        callAgenda.enqueue(new Callback<AgendaProfissionalResponse>() {
            @Override
            public void onResponse(@NonNull Call<AgendaProfissionalResponse> call, @NonNull Response<AgendaProfissionalResponse> response) {
                if (response.body().getAgenda().isEmpty()) {
                    emptyAgenda();
                } else {
                    adapter = new ProfissionalAdapter(response.body().getAgenda());
                    recyclerView.setAdapter(adapter);
                    String str = "Olá, " + response.body().getNome() + "!";
                    nomeProf.setText(str);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AgendaProfissionalResponse> call, @NonNull Throwable throwable) {

            }
        });
    }

    public void alertaCpfWrong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AgendaProfissional.this);
        builder.setTitle("Ops");
        builder.setMessage("Não foi possível achar a sua agenda. Por favor, verifique se o CPF inserido foi digitado corretamente");
        builder.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AgendaProfissional.this, Login.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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

        month += 1;
        String oldData = year + "/" + month + "/" + dayOfMonth;
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

    public void check() {
        Call<AgendaProfissionalResponse> checkProfissional = RetrofitClient.getInstance().getApi().validarCpfProfissional(sharedPrefManager.getCpf());

        checkProfissional.enqueue(new Callback<AgendaProfissionalResponse>() {
            @Override
            public void onResponse(Call<AgendaProfissionalResponse> call, Response<AgendaProfissionalResponse> response) {
                if (response.body() == null) {
                    alertaCpfWrong();
                }
            }

            @Override
            public void onFailure(Call<AgendaProfissionalResponse> call, Throwable throwable) {

            }
        });
    }

    public void emptyAgenda() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AgendaProfissional.this);
        builder.setTitle("Ops");
        builder.setMessage("Não há nenhuma marcação para esse dia!");
        builder.setNeutralButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
