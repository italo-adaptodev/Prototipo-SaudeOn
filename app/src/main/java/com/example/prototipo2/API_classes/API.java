package com.example.prototipo2.API_classes;

import com.example.prototipo2.Modelos.AgendaSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {




  @GET("carregarAgenda?cpf=")
  Call<AgendaSearchResponse> carregarAgenda(@Query("cpf") String cpf);



}