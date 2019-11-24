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
import com.maera.core.TYPE_OF_SEARCH;
import java.util.ArrayList;
import java.util.List;

public class BaseListFragment extends Fragment{
    private final DataBaseManager _dataBase;
    private final FRAGMENT_TYPE _fragment_type;
    private boolean _firstInit;
    private String _title;
    private AirportsListAdapter _adapter;
    private TextView _noAirports;
    private String _noAirportsMessage;
    private List<Airport> _airports, _shownList;

    public BaseListFragment(FRAGMENT_TYPE type, @NonNull DataBaseManager manager ){
        _dataBase = manager;
        _fragment_type = type;
        _firstInit = true;
        setTitleAndMessage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_airport, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        _adapter = new AirportsListAdapter();
        recyclerView.setAdapter(_adapter);

        _noAirports = view.findViewById(R.id.no_airports);
        _noAirports.setText(_noAirportsMessage);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        if( _firstInit ) {
            refreshData();
            _firstInit = false;
            return;
        }

        //La lista de aeropuertos se va a actualizar unicamente si se trata del fragmento de favoritos, todas las listas
        // de los demás fragmentos van a permanecer estáticas. Entonces
        //Si se trata del fragmento de favoritos y la base de datos contiene datos nuevos
        if(_fragment_type == FRAGMENT_TYPE.FAVOURITE && !_dataBase.dataUpdated() ) {
            refreshData();
            _dataBase.reportDataUpdated();
        }
    }

    private void refreshData(){
        switch(_fragment_type){
            case FAVOURITE:
                _airports = _dataBase.getFavouriteAirports();
                break;
            case AIRPORTS:
                _airports = _dataBase.getAllAirports();
                break;
            case EZE:
                _airports = _dataBase.getAirportsByFir("EZE");
                break;
            case CBA:
                _airports = _dataBase.getAirportsByFir("CBA");
                break;
            case DOZ:
                _airports = _dataBase.getAirportsByFir("DOZ");
                break;
            case SIS:
                _airports = _dataBase.getAirportsByFir("SIS");
                break;
            case CRV:
                _airports = _dataBase.getAirportsByFir("CRV");
                break;
            case ANT:
                _airports = _dataBase.getAirportsByFir("ANT");
                break;
        }

        if( _shownList == null ) _shownList = new ArrayList<>();
        _shownList.clear();
        _shownList.addAll(_airports);

        //Se chequea sea diferente de null para no llamar a un objeto nulo, por ejemplo, cuando el fragmento recién se inicializa.
        if( _adapter != null )_adapter.notifyDataSetChanged();
    }

    public String getTitle(){
        return _title;
    }

    public void filter(String query){
        _adapter.getFilter().filter(query);
    }

    /**
     * Enum utilizado para inicializar los fragmentos y saber qué tipo de lista mostrará en este.
     */
    public enum FRAGMENT_TYPE{FAVOURITE, AIRPORTS, EZE, CBA, DOZ, SIS, CRV, ANT}

    /**
     * El adaptador se encarga de administrar laa lista que va a ser mostrada dentro del fragmento.
     * También implementa Filterable para así poder filtrar los aeropuertos para una búsqueda
     */
    private class AirportsListAdapter extends RecyclerView.Adapter<AirportsListAdapter.AirportViewHolder> implements Filterable {

        @NonNull
        @Override
        public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType) {
            final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_airport, container, false);
            return new AirportsListAdapter.AirportViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AirportViewHolder viewHolder, int position) {
            final Airport airport = _shownList.get(position);
            viewHolder.icao.setText(airport.getIcaoCode());
            viewHolder.national.setText(airport.getLocalCode());
            viewHolder.name.setText(airport.getName());
            String location = airport.getLocation().getCity() + ", " + airport.getLocation().getProvince();
            viewHolder.location.setText(location);
            if (airport.isFavourite())
                viewHolder._favouriteBtn.setChecked(true);
            else
                viewHolder._favouriteBtn.setChecked(false);
        }

        @Override
        public int getItemCount() {
            return _shownList.size();
        }

        /**
         * Chequea si la lista siendo mostrada está vacía. Útil para el fragmento de favoritos.
         * @return true si está vacío, false si no.
         */
        boolean isEmpty() {
            return _shownList.isEmpty();
        }

        @Override
        public AirportFilter getFilter() {
            return new AirportFilter();
        }

        /**
         * Clase utilizada para realizar búsquedas
         */
        class AirportFilter extends Filter {
            private TYPE_OF_SEARCH _type_of_search = TYPE_OF_SEARCH.ICAO;

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                _shownList.clear();
                final String target = constraint.toString().toLowerCase();
                for (Airport airport : _airports) {
                    final String search;
                    switch (_type_of_search) {
                        case LOCAL:
                            search = airport.getLocalCode().toLowerCase();
                            break;
                        case NAME:
                            search = airport.getName().toLowerCase();
                            break;
                        case LOCATION:
                            search = airport.getLocation().toString();
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

        /**
         * Implementación del ViewHolder, cuya función es mostrar cada elemento de la lista
         *
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


    private void setTitleAndMessage(){
        _noAirportsMessage = "Error: no airports found";
        switch(_fragment_type){
            case FAVOURITE:_title = "Favoritos"; _noAirportsMessage = "Aún no tenés favoritos"; break;
            case AIRPORTS: _title = "Aeropuertos"; break;
            case EZE: _title = "Ezeiza"; break;
            case CBA: _title = "Córdoba"; break;
            case DOZ: _title = "Mendoza"; break;
            case SIS: _title = "Resistencia"; break;
            case CRV: _title = "Comodoro Rivadavia"; break;
            case ANT: _title = "Antártida"; break;
            default: _title = "";
        }
    }

    private void showMessageIfEmpty(){
        if( _adapter.isEmpty() )
            _noAirports.setVisibility(View.VISIBLE);
        else
            _noAirports.setVisibility(View.GONE);
    }
}
