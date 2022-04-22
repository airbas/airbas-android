package com.example.airbas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

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
        /*View v ;
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        v=layoutInflater.inflate(R.layout.reservation_item,parent,false);
        return new MyViewHolder(v);*/
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.reservation_item, parent, false);

        //return new MyViewHolder(v).linkAdapter(this);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        String s1="";
        String s2="";
        String data = reservationList.get(position).getDate();
        s1 = data.substring(0, 10);
        s2 = data.substring(11, 16);

        holder.nomeVolo.setText(reservationList.get(position).getFlightName());
        holder.date.setText(s1+" "+s2+"\n"+
                reservationList.get(position).getDate()+"  "+
                reservationList.get(position).getDapartureAirport()+"  "+
                reservationList.get(position).getArrivalAirport()+"\n"+
                "Seat: "+ reservationList.get(position).getSeatCord()+"  "+
                "Rate: "+ reservationList.get(position).getRate()+"\n"+
                "Name: "+reservationList.get(position).getPassenger().getFirstname()+"  "+
                "Second name: "+ reservationList.get(position).getPassenger().getSecondname());
        //holder.date.setText(reservationList.get(position).getDate()+"\n");


        /*holder.time.setText(s2);
        holder.from.setText(reservationList.get(position).getDapartureAirport());
        holder.to.setText(reservationList.get(position).getArrivalAirport());
        holder.seat.setText(reservationList.get(position).getSeatCord());
        holder.cost.setText(reservationList.get(position).getRate());
        holder.firstname.setText(reservationList.get(position).getPassenger().getFirstname());
        holder.lastname.setText(reservationList.get(position).getPassenger().getSecondname());*/

        boolean isExpanded = reservationList.get(position).isExpanded();
        holder.expandable.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeVolo,date, time, from, to;
        TextView seat;
        TextView cost;
        TextView firstname;
        TextView lastname;

        ConstraintLayout expandable;

        //new
        private ReservationAdapter adapter;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            nomeVolo=itemView.findViewById(R.id.voloAbc);
            date=itemView.findViewById(R.id.date);
            /*time=itemView.findViewById(R.id.time);
            from=itemView.findViewById(R.id.from);
            to=itemView.findViewById(R.id.to);
            seat=itemView.findViewById(R.id.seat);
            cost=itemView.findViewById(R.id.cost);
            firstname=itemView.findViewById(R.id.firstname);
            lastname=itemView.findViewById(R.id.lastname);*/

            expandable=itemView.findViewById(R.id.expandable);


            nomeVolo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Reservation res = reservationList.get(getAdapterPosition());
                    res.setExpanded(!res.isExpanded());
                    notifyItemChanged(getAdapterPosition());



                }
            });

/*
            //new
            itemView.findViewById(R.id.delete).setOnClickListener(view -> {
                adapter.reservationList.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());



            });*/

        }
        //new
        public MyViewHolder linkAdapter (ReservationAdapter adapter){
            this.adapter=adapter;
            return this;
        }
    }
}
