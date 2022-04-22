package com.example.airbas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class SecondActivity extends AppCompatActivity {

    //private ListView lv;
    //https://run.mocky.io/v3/5cc133ae-9adc-4a26-96b6-bdff5d92ae99
    //String id, firstname, secondname, birthdate, creditcard, telephone, email;
    //ArrayList<HashMap<String,String>> profilelist;
    TextView txt;
    List<Reservation> reservationList;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        txt=findViewById(R.id.textView);
        recyclerView=findViewById(R.id.recyclerView);
        reservationList=new ArrayList<>();

/*


        //http://10.0.2.2:8081/profile/details/
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8081/profile/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Call<Profile> call = myAPICall.getProfile(email);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

                if(response.code() != 200){
                    txt.setText("Check the connection");
                    return;
                }

                //Get Data into Textview
                String jsony="";
                String s=response.body().getBirthdate();
                String s1="";
                String s2="";
                s1 = s.substring(0, 10); s2 = s.substring(11, 16);



                jsony = " First Name:            " + response.body().getFirstname() +
                        "\n Second Name:       " + response.body().getSecondname()+
                        "\n Birthdate:              "+ s1+
                        "\n Credit Card:           " + response.body().getCreditcard()+
                        "\n Telephone :           " + response.body().getTelephone() +
                        "\n Email:                     " + response.body().getEmail();


                //txt.append(jsonfn);
                //txt.append(jsonsn);
                txt.append(jsony);

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable throwable) {

            }
        });

*/





        //Second Call
        Retrofit retrofit2 = new Retrofit.Builder().baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        System.console();
        //instance for interface

        MyReservationCall myReservationCall = retrofit2.create(MyReservationCall.class);
        Call<List<Reservation>> call2 = myReservationCall.getReservation();

        call2.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call2, Response<List<Reservation>> response) {
                if(response.code() != 200){
                    Log.d("output",response.message());


                }
                List<Reservation> reservations = response.body();
                for (Reservation reservation : reservations){
                    reservationList.add(reservation);

                }

                PutDataIntoRecyclerView(reservationList);


            }

            @Override
            public void onFailure(Call<List<Reservation>> call2, Throwable throwable) {

            }


        });






    }

    private void PutDataIntoRecyclerView(List<Reservation> reservationList) {
        ReservationAdapter reservationAdapter= new ReservationAdapter(this,reservationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reservationAdapter);
    }



}
