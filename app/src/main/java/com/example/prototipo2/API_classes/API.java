package com.example.prototipo2.API_classes;

import com.example.prototipo2.Modelos.AgendaSearchResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface API {


  @GET("carregarAgenda")
  Call<AgendaSearchResponse> carregarAgenda(
          @Field("cpf") String cpf

  );





}