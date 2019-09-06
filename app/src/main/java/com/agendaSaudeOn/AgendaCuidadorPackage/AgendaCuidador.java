package com.agendaSaudeOn.AgendaCuidadorPackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.agendaSaudeOn.API_classes.RetrofitClient;
import com.agendaSaudeOn.Login;
import com.agendaSaudeOn.R;
import com.agendaSaudeOn.Storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaCuidador extends AppCompatActivity {

    SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_cuidador);

        check();
    }


    //IMPLEMENTAR AGENDA CUIDADOR
    public void check() {
        Call<AgendaCuidadorResponse> checkCuidador = RetrofitClient.getInstance().getApi().validarCpfCuidador(sharedPrefManager.getCpf());

        checkCuidador.enqueue(new Callback<AgendaCuidadorResponse>() {
            @Override
            public void onResponse(Call<AgendaCuidadorResponse> call, Response<AgendaCuidadorResponse> response) {
                if (response.body() == null) {
                    alertaCpfWrong();
                }
            }

            @Override
            public void onFailure(Call<AgendaCuidadorResponse> call, Throwable throwable) {

            }
        });
    }

    public void alertaCpfWrong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AgendaCuidador.this);
        builder.setTitle("Ops");
        builder.setMessage("Não foi possível achar os dados. Por favor, verifique se o CPF inserido foi digitado corretamente");
        builder.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AgendaCuidador.this, Login.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
