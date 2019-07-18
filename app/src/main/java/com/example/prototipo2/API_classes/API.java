package com.example.prototipo2.API_classes;

import com.example.prototipo2.Modelos.AgendaSearchResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface API {

  @FormUrlEncoded
  @GET("carregarAgenda")
  Call<AgendaSearchResponse> carregarAgenda(
          @Field("cpf") String cpf

  );





}