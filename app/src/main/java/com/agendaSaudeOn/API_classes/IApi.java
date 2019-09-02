package com.agendaSaudeOn.API_classes;

import com.agendaSaudeOn.Modelos.AgendaSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApi {

    @GET("carregarAgenda?cpf=")
    Call<AgendaSearchResponse> carregarAgenda(@Query("cpf") String cpf, @Query("date") String date);

    @GET("buscarNome?cpf=")
    Call<AgendaSearchResponse> buscarNome(@Query("cpf") String cpf);

    @GET("check?cpf=")
    Call<AgendaSearchResponse> check(@Query("cpf") String cpf);

}