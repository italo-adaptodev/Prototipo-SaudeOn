package com.example.prototipo2.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.prototipo2.Modelos.Agenda;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "cpf_profissional_SaudeOn";

    private static SharedPrefManager instance;
    private Context ctx;

    private SharedPrefManager(Context ctx){
        this.ctx = ctx;
    }

    public static synchronized SharedPrefManager getInstance(Context ctx){
        if(instance == null){
            instance = new SharedPrefManager(ctx);
        }
        return instance;
    }

    public void saveCPF(Agenda agenda){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cpf", agenda.getCpf_profissional());

        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("cpf", "-1") != "-1";
    }

    public Agenda getProfissionalCpf(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Agenda agenda = new Agenda(
                sharedPreferences.getString("cpf", "-1")
        );
        return agenda;
    }

    public void clear(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
