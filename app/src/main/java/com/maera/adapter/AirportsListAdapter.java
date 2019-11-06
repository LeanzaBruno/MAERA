package com.maera.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.maera.R;
import com.maera.activity.AirportActivity;
import com.maera.core.Airport;
import java.util.ArrayList;
import java.util.Iterator;
import com.maera.core.TYPE_OF_SEARCH;
/***
 * Esta clase es la que se encarga de administrar los datos de la lista principal en el ActivityMain
 *
 */
public
final
class AirportsListAdapter extends RecyclerView.Adapter<AirportsListAdapter.AirportViewHolder> implements Filterable {
    private final ArrayList<Airport> _airports;
    private ArrayList<Airport> _filteredAirports;

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

    public
    AirportsListAdapter( ArrayList<Airport> airports ){
        _airports = airports;
        _filteredAirports = new ArrayList<>();
        _filteredAirports.addAll(_airports);
    }

    @Override
    public AirportsListAdapter.AirportViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType){
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_airport, container, false);

        return new AirportsListAdapter.AirportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportsListAdapter.AirportViewHolder viewHolder, int position){
        final Airport airport = _filteredAirports.get(position);
        viewHolder._icao.setText(airport.getIcaoCode());
        viewHolder._name.setText(airport.getAirportName());
        viewHolder._fir.setText(airport.getFir());
    }

    @Override
    public int getItemCount(){
        return _filteredAirports.size();
    }

    @Override
    public AirportFilter getFilter() { return new AirportFilter(); }

    public
    void showOnly(String fir){
        _filteredAirports.clear();
        _filteredAirports.addAll(_airports);

        Iterator<Airport> airport = _filteredAirports.iterator();
        while(airport.hasNext()){
            if( !airport.next().getFir().equals(fir) ) airport.remove();
        }
        this.notifyDataSetChanged();
    }

    public
    void showAll(){
        _filteredAirports.clear();
        _filteredAirports.addAll(_airports);
        this.notifyDataSetChanged();
    }

    public
    class AirportFilter extends Filter {
        private TYPE_OF_SEARCH _type_of_search = TYPE_OF_SEARCH.CODE;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Airport> filterList = new ArrayList<>();

            if (constraint.length() < 3) {
                filterList.clear();
                filterList.addAll(_filteredAirports);
            } else {
                for (Airport airport : _filteredAirports) {
                    final String target = constraint.toString().toLowerCase();

                    final String search;
                    switch (_type_of_search) {
                        case NAME:
                            search = airport.getAirportName().toLowerCase();
                            break;
                        case LOCATION:
                            search = airport.getLocation().toLowerCase();
                            break;
                        case CODE:
                        default:
                            search = airport.getIcaoCode().toLowerCase();
                    }

                    if (search.contains(target))
                        filterList.add(airport);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            _filteredAirports = (ArrayList<Airport>) results.values;
            notifyDataSetChanged();
        }

        public void setTypeOfSearch(TYPE_OF_SEARCH typeOfSearch) {
            _type_of_search = typeOfSearch;
        }
    }
}
