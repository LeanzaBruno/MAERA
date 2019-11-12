package com.maera.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.maera.R;
import com.maera.activity.AirportActivity;
import com.maera.core.Airport;
import java.util.ArrayList;
import java.util.List;
import com.maera.core.DataBaseManager;
import com.maera.core.FIR;
import com.maera.core.TYPE_OF_SEARCH;



/***
 * Esta clase es la que se encarga de administrar los datos de la lista de aeropuertos
 *
 *
 */
public
class AirportsListAdapter extends RecyclerView.Adapter<AirportsListAdapter.AirportViewHolder> implements Filterable {
    private final List<Airport> _allAirports;
    private ArrayList<Airport> _auxOriginalList, _shownList;

    public
    AirportsListAdapter(DataBaseManager manager){
        _allAirports = manager.getAllAirports();
        _auxOriginalList = new ArrayList<>();
        _auxOriginalList.addAll(_allAirports);
        _shownList = new ArrayList<>();
        _shownList.addAll(_allAirports);
    }

    @NonNull
    @Override
    public AirportsListAdapter.AirportViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType){
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_airport, container, false);
        return new AirportsListAdapter.AirportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportsListAdapter.AirportViewHolder viewHolder, int position){
        final Airport airport = _shownList.get(position);
        viewHolder.icao.setText(airport.getIcaoCode());
        viewHolder.name.setText(airport.getName());
        String location = airport.getLocation().getCity() + ", " + airport.getLocation().getProvince().name();
        viewHolder.location.setText(location);
        if( airport.isFavourite() )
            viewHolder._favouriteBtn.setChecked(true);
        else
            viewHolder._favouriteBtn.setChecked(false);
    }

    @Override
    public int getItemCount(){
        return _shownList.size();
    }

    @Override
    public AirportFilter getFilter() { return new AirportFilter(); }

    public
    void showOnly(FIR fir){
        _auxOriginalList.clear();
        _shownList.clear();
        for(int it = 0 ; it < _allAirports.size() ; ++it ){
            final Airport airport = _allAirports.get(it);
            if( airport.getFir() == fir ){
                _auxOriginalList.add(airport);
                _shownList.add(airport);
            }
        }
        notifyDataSetChanged();
    }

    public
    void showAll(){
        _auxOriginalList.clear();
        _auxOriginalList.addAll(_allAirports);
        _shownList.clear();
        _shownList.addAll(_allAirports);
        notifyDataSetChanged();
    }

    public
    class AirportFilter extends Filter {
        private TYPE_OF_SEARCH _type_of_search = TYPE_OF_SEARCH.ICAO;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            _shownList.clear();
            final String target = constraint.toString().toLowerCase();
            for (Airport airport : _auxOriginalList) {
                final String search;
                switch (_type_of_search) {
                    case LOCAL:
                        search = airport.getLocalCode().toLowerCase();
                        break;
                    case NAME:
                        search = airport.getName().toLowerCase();
                        break;
                    case LOCATION:
                        search = airport.getLocation().getCity().toLowerCase() + airport.getLocation().getProvince().name().toLowerCase();
                        break;
                    case ICAO:
                    default:
                        search = airport.getIcaoCode().toLowerCase();
                }
                if (search.contains(target)) _shownList.add(airport);
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = _shownList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }

        public void setTypeOfSearch(TYPE_OF_SEARCH typeOfSearch) {
            _type_of_search = typeOfSearch;
        }
    }

    class AirportViewHolder extends RecyclerView.ViewHolder{
        TextView icao, name, location;
        ToggleButton _favouriteBtn;

        AirportViewHolder(View view){
            super(view);
            icao = view.findViewById(R.id.icao);
            name = view.findViewById(R.id.name);
            location = view.findViewById(R.id.location);
            _favouriteBtn = view.findViewById(R.id.favourite);
            view.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    final Airport airport = _shownList.get(AirportViewHolder.this.getAdapterPosition());
                    final Context context = view.getContext();
                    Intent intent = new Intent(context, AirportActivity.class);
                    intent.putExtra("AIRPORT", airport);
                    context.startActivity(intent);
                }
            });
        }
    }
}
