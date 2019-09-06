package com.agendaSaudeOn.API_classes;

import com.agendaSaudeOn.AgendaCuidadorPackage.AgendaCuidadorResponse;
import com.agendaSaudeOn.AgendaProfissionalPackage.AgendaProfissionalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApi {

    @GET("carregarAgendaProfissional?cpf=")
    Call<AgendaProfissionalResponse> carregarAgendaProfissional(@Query("cpf") String cpf, @Query("date") String date);

    @GET("buscarNome?cpf=")
    Call<AgendaProfissionalResponse> buscarNome(@Query("cpf") String cpf);

    @GET("validarCpfProfissional?cpf=")
    Call<AgendaProfissionalResponse> validarCpfProfissional(@Query("cpf") String cpf);

    @GET("validarCpfCuidador?cpf=")
    Call<AgendaCuidadorResponse> validarCpfCuidador(@Query("cpf") String cpf);

}