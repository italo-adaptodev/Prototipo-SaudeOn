package com.agendaSaudeOn.API_classes;

import com.agendaSaudeOn.Modelos.AgendaSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {




  @GET("carregarAgenda?cpf=")
  Call<AgendaSearchResponse> carregarAgenda(@Query("cpf") String cpf, @Query("btnprs") int btnprs);

  @GET("buscarNome?cpf=")
  Call<AgendaSearchResponse> buscarNome(@Query("cpf") String cpf);



}