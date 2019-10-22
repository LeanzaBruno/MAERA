package com.maera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

final class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder> {
    private ArrayList<Airport> _airports;
    private String _type;

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView _airportName;
        TextView _weatherReport;

        ViewHolder(View view){
            super(view);
            _airportName = view.findViewById(R.id.airportName);
            _weatherReport = view.findViewById(R.id.weatherReport);
        }
    }

    ResultListAdapter(ArrayList<Airport> airports, String type ){
        _airports = airports;
        _type = type;
    }


    @NonNull
    @Override
    public ResultListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Airport airport = _airports.get(position);
        holder._airportName.setText(airport.getAirportName());

        if( _type.equals(WeatherReport.TYPE.METAR.toString()) )
            holder._weatherReport.setText(airport.getMetar());
        else if( _type.equals(WeatherReport.TYPE.TAF.toString()))
            holder._weatherReport.setText(airport.getTaf());
    }

    @Override
    public int getItemCount() {
        return _airports.size();
    }
}
