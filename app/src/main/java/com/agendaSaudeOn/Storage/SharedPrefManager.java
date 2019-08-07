package com.agendaSaudeOn.Storage;

import android.content.Context;
import android.content.SharedPreferences;

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

    public void saveCPF(String cpf){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cpf", cpf);

        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("cpf", "-1") != "-1";
    }

    public String getProfissionalCpf(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("cpf", "-1");
    }

    public void clear(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}