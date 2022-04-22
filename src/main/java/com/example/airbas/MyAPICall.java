package com.example.airbas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAPICall {
    //https://run.mocky.io/v3/      5cc133ae-9adc-4a26-96b6-bdff5d92ae99

    @GET("details/{email}")

    Call<Profile> getProfile(@Path("email") String email);

}
