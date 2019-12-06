package com.maera.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.maera.R;
import com.maera.activity.AirportActivity;
import com.maera.core.Airport;
import com.maera.core.DataBaseManager;
import com.maera.core.AirportFilter;
import com.maera.core.FIR;

import java.util.ArrayList;
import java.util.List;

public class MetafFragment extends Fragment{
    private final DataBaseManager _dataBase;
    private MetafAdapter _adapter;
    private List<Airport> _airports, _shownList;

    public MetafFragment(@NonNull DataBaseManager manager ){
        _dataBase = manager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_metaf, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.airports);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        _adapter = new MetafAdapter();
        recyclerView.setAdapter(_adapter);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
            refreshData();
    }

    private void refreshData(){
        _airports = _dataBase.getAllAirports();
        if( _shownList == null ) _shownList = new ArrayList<>();
        _shownList.clear();
        _shownList.addAll(_airports);
        _dataBase.reportDataUpdated();
        _adapter.notifyDataSetChanged();
    }

    public AdapterFilter getFilter(){
        return _adapter.getFilter();
    }

    /**
     * El adaptador se encarga de administrar laa lista que va a ser mostrada dentro del fragmento.
     * También implementa Filterable para así poder filtrar los aeropuertos para una búsqueda
     */
    private class MetafAdapter extends RecyclerView.Adapter<AirportViewHolder> implements Filterable {
        private AdapterFilter _filter = new AdapterFilter();

        @NonNull
        @Override
        public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType) {
            final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_airport, container, false);
            return new AirportViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AirportViewHolder viewHolder, int position) {
            final Airport airport = _shownList.get(position);
            viewHolder.icao.setText(airport.getIcaoCode());
            viewHolder.national.setText(airport.getLocalCode());
            viewHolder.name.setText(airport.getName());
            String location = airport.getLocation().getLocality() + ", " + airport.getLocation().getProvince();
            viewHolder.location.setText(location);
            if (airport.isFavourite())
                viewHolder._favouriteBtn.setChecked(true);
            else
                viewHolder._favouriteBtn.setChecked(false);
        }

        @Override
        public int getItemCount() {
            if( _shownList != null )
                return _shownList.size();
            else
                return 0;
        }

        @Override
        public AdapterFilter getFilter() {
            return _filter;
        }
    }


    /**
     * Clase utilizada para realizar búsquedas y filtrados
     */
    public class AdapterFilter extends Filter {
        private AirportFilter _filter;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            _shownList.clear();
            switch (_filter.getType()) {
                case NONE:
                    _shownList.addAll(_airports);
                    break;
                case FAVS:
                    filterFavourites();
                    break;
                case EZE:
                    filterByFir(FIR.EZE);
                    break;
                case CBA:
                    filterByFir(FIR.CBA);
                    break;
                case DOZ:
                    filterByFir(FIR.DOZ);
                    break;
                case SIS:
                    filterByFir(FIR.SIS);
                    break;
                case CRV:
                    filterByFir(FIR.CRV);
                    break;
                case ANAC:
                    for(Airport airport : _airports )
                        if(airport.getLocalCode().toLowerCase().contains(constraint.toString().toLowerCase()))
                            _shownList.add(airport);
                    break;
                case NAME:
                    for(Airport airport : _airports )
                        if(airport.getName().toLowerCase().contains(constraint.toString().toLowerCase()))
                            _shownList.add(airport);
                    break;
                case LOCALITY:
                    for(Airport airport : _airports )
                        if(airport.getLocation().getLocality().toLowerCase().contains(constraint.toString().toLowerCase()))
                            _shownList.add(airport);
                    break;
                case PROVINCE:
                    for(Airport airport : _airports )
                        if(airport.getLocation().getProvince().toLowerCase().contains(constraint.toString().toLowerCase()))
                            _shownList.add(airport);
                    break;
                case ICAO:
                    for(Airport airport : _airports )
                        if(airport.getIcaoCode().toLowerCase().contains(constraint.toString().toLowerCase()))
                            _shownList.add(airport);
                    break;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = _shownList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            _adapter.notifyDataSetChanged();
        }

        public void searchBy(@NonNull AirportFilter type,@NonNull String query){
            _filter = type;
            filter(query);
        }

        void filterBy(AirportFilter.TYPE_OF_FILTER type){
            _filter = new AirportFilter();
            _filter.setType(type);
            filter("");
        }

        private void filterByFir(FIR fir){
            for(Airport airport : _airports)
                if(airport.getFir() == fir)
                    _shownList.add(airport);
        }

        private void filterFavourites(){
            for(Airport airport : _airports)
                if(airport.isFavourite())
                    _shownList.add(airport);
        }
    }

    /**
     * Implementación del ViewHolder, cuya función es mostrar cada elemento de la lista
     */
    private class AirportViewHolder extends RecyclerView.ViewHolder {
        TextView icao, national, name, location;
        ToggleButton _favouriteBtn;

        AirportViewHolder(View view) {
            super(view);
            icao = view.findViewById(R.id.icao);
            national = view.findViewById(R.id.national);
            name = view.findViewById(R.id.name);
            location = view.findViewById(R.id.location);
            _favouriteBtn = view.findViewById(R.id.favourite);

            _favouriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    Airport airport = _shownList.get(getAdapterPosition());
                    if (_favouriteBtn.isChecked()) {
                        _dataBase.setFavourite(airport, true);
                        Toast.makeText(v.getContext(), "Agregado a favoritos", Toast.LENGTH_SHORT).show();
                    } else {
                        _dataBase.setFavourite(airport, false);
                        Toast.makeText(v.getContext(), "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
                    }
                    refreshData();
                }
            });


            /*
             * Este evento es quien inicia la AirportActivity, donde se muetran los mensajes del aeropuerto seleccionado
             */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
