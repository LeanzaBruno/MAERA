package com.maera;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

final class AirportsListAdapter extends RecyclerView.Adapter<AirportsListAdapter.AirportViewHolder> {
    private ArrayList<Airport> _airports;

    class AirportViewHolder extends RecyclerView.ViewHolder{
        TextView _icao, _name, _fir;

        AirportViewHolder(View view){
            super(view);
            view.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    final Airport airport = _airports.get(AirportViewHolder.this.getAdapterPosition());
                    final Context context = view.getContext();
                    Intent intent = new Intent(context, AirportActivity.class);
                    intent.putExtra("AIRPORT", airport);
                    context.startActivity(intent);
                }
            });
            _icao = view.findViewById(R.id.icao);
            _name = view.findViewById(R.id.name);
            _fir = view.findViewById(R.id.fir);
        }
    }

    AirportsListAdapter( ArrayList<Airport> airports ){
        _airports = airports;
    }

    @Override
    public AirportsListAdapter.AirportViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType){
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_airport, container, false);

        return new AirportsListAdapter.AirportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportsListAdapter.AirportViewHolder viewHolder, int position){
        final Airport airport = _airports.get(position);
        viewHolder._icao.setText(airport.getIcaoCode());
        viewHolder._name.setText(airport.getAirportName());
        viewHolder._fir.setText(airport.getFir());
    }

    @Override
    public int getItemCount(){
        return _airports.size();
    }



}
