package com.example.airbas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyReservationCall {
    @GET("get/{email}")
    Call<List<Reservation>> getReservation(@Path("email") String email);

    @GET("delete/{email}/{cod}")
    Call<Reservation> deleteReservation(@Path("email") String email,@Path("cod") String cod);




}
