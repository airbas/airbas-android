package com.example.airbas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import net.cachapa.expandablelayout.ExpandableLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {
    private Context mcontext;
    private List<Reservation> reservationList;

    public ReservationAdapter(Context mcontext, List reservationList) {
        this.mcontext = mcontext;
        this.reservationList = reservationList;
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.reservation_item, parent, false);


        return new MyViewHolder(v).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        String s1="";
        String s2="";
        String s3="";
        String tratta="";
        String postoAereo="";
        String costoVolo="";


        String data = reservationList.get(position).getDate();
        s1 = data.substring(0, 10);
        s2 = data.substring(11, 16);
        s3=s1+" Alle  "+s2;
        String passeggero="";
        String nome=reservationList.get(position).getPassenger().getFirstname();
        String cognome=reservationList.get(position).getPassenger().getSecondname();
        String da= reservationList.get(position).getDapartureAirport();
        String a=reservationList.get(position).getArrivalAirport();
        tratta="Da: "+da+"   A:"+a;
        String postoo=reservationList.get(position).getSeatCord();
        String posto=postoo.replaceAll("[\\n\\t ]", "");

        String costo=reservationList.get(position).getRate();
        postoAereo="Posto:"+posto;
        passeggero="Nome Passeggero: "+nome+" "+cognome;
        costoVolo="Costo: "+ costo;





        holder.nomeVolo.setText(reservationList.get(position).getFlightName());
        holder.date.setText(s3);
        holder.tratta.setText(tratta);
        holder.posto.setText(postoAereo);
        holder.nome.setText(passeggero);
        holder.costo.setText(costoVolo);



        boolean isExpanded = reservationList.get(position).isExpanded();
        holder.expandable.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeVolo,date, tratta, posto, nome,costo;



        LinearLayout linear;
        RelativeLayout expandable;

        //new
        private ReservationAdapter adapter;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            nomeVolo=itemView.findViewById(R.id.volo);
            date=itemView.findViewById(R.id.date);
            posto=itemView.findViewById(R.id.posto);
            costo=itemView.findViewById(R.id.costo);
            tratta=itemView.findViewById(R.id.tratta);
            nome=itemView.findViewById(R.id.nome);



            linear=itemView.findViewById(R.id.linear);
            expandable=itemView.findViewById(R.id.expandable);

            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Reservation res = reservationList.get(getAdapterPosition());
                    res.setExpanded(!res.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });



            nomeVolo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Reservation res = reservationList.get(getAdapterPosition());
                    res.setExpanded(!res.isExpanded());
                    notifyItemChanged(getAdapterPosition());



                }
            });


            //new
            itemView.findViewById(R.id.delete).setOnClickListener(view -> {
                String cod = "";
                cod =reservationList.get(getAdapterPosition()).getName();
                String email = "";
                email=reservationList.get(getAdapterPosition()).getUserEmail();








                //Delete call
                Retrofit retrofit3 = new Retrofit.Builder().baseUrl("http://10.0.2.2:8084/reservation/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                //instance for interface

                MyReservationCall myReservationCall = retrofit3.create(MyReservationCall.class);
                Call<Reservation> call3 = myReservationCall.deleteReservation(email, cod);

                call3.enqueue(new Callback<Reservation>() {
                    @Override
                    public void onResponse(Call<Reservation> call3, Response<Reservation> response) {
                        if(response.code() != 200){
                            Log.d("output",response.message());


                        }










                    }

                    @Override
                    public void onFailure(Call<Reservation> call2, Throwable throwable) {

                    }


                });

                adapter.reservationList.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());




            });

        }

        public MyViewHolder linkAdapter (ReservationAdapter adapter){
            this.adapter=adapter;
            return this;
        }
    }
}
