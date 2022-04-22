package com.example.airbas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

public interface MyReservationCall {
    @GET("5e376928-6d00-404f-ad91-28a179ddcb25")
    Call<List<Reservation>> getReservation();


}
