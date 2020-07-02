package com.maera.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.maera.R;
import com.maera.activity.AirportActivity;
import com.maera.core.Airport;
import com.maera.core.DataBaseManager;
import com.maera.core.FIR;
import java.util.ArrayList;
import java.util.List;


/**
 * El fragmento que se encarga de mostrar los aeropuertos.
 */
public class MetafFragment extends Fragment{

    /**
     * Para filtrar por FIR.
     */
    public enum TIPO_FILTRO{EZE, CBA, DOZ, SIS, CRV, FAVS, TODOS}

    /**
     * La base de datos donde se guardan los aeropuertos.
     */
    private DataBaseManager _dataBase;

    /**
     * El adaptador encargado de mostrar cada uno de los aeropuertos
     */
    private MetafAdapter _adapter;

    /**
     * La lista allAirports contiene, como su nombre lo indica, todos los aeropuertos y es inmutable.
     * Se usa de backup para poder volver a la lista de aeropuertos original.
     */
    private List<Airport> _allAirports;

    /**
     * La lista sublist es otra lista a modo de backup. Cuando el usuario filtra los aeropuertos por FIR(u otra etiqueta),
     * sublist pasa a contener todos los aeropuertos que coincidan con ese criterio. De esta forma, cuando se haga un filtrado
     * a esta sublista, se puede volver hacia atrás si el usuario cancela la búsqueda o sus criterios son menos específicos.
     * Si no hay ningun criterio de separación en uso, entonces sublist es igual a la lista allAirports.
     */
    private List<Airport> _subList;

    /**
     * Esta lista es la que se mostrará al usuario, es el resultado de los filtrados realizados en las listas anteriores.
     */
    private List<Airport> _filteredList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.fragment_metaf, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.airports);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        _adapter = new MetafAdapter();
        recyclerView.setAdapter(_adapter);
        return view;
    }

    /**
     * Refresca la lista.
     */
    public void refrescar() {
        _allAirports = _dataBase.getAllAirports();
        if( _subList == null ) _subList = new ArrayList<>();
        if(_filteredList == null ) _filteredList = new ArrayList<>();
        _subList.addAll(_allAirports);
        _filteredList.addAll(_allAirports);
    }

    public void buscar(String consulta){
        _adapter.getFilter().buscar(consulta);
    }

    public void filtrar(TIPO_FILTRO tipo){
        _adapter.getFilter().filtrar(tipo);
    }

    /**
     * Setea el contexto para la base de datos. Muy importante para que sepa donde está la base de datos.
     */
    public void setDataBaseContext(@NonNull Context context){
        _dataBase = DataBaseManager.getInstance(context);
    }

    /**
     * El adaptador se encarga de administrar la lista que va a ser mostrada dentro del fragmento.
     * También implementa Filterable para así poder filtrar los aeropuertos para una búsqueda.
     */
    private class MetafAdapter extends RecyclerView.Adapter<AirportViewHolder> implements Filterable {

        /**
         * El adaptador encargado de filtrar.
         */
        private AdapterFilter _filter = new AdapterFilter();

        @NonNull
        @Override
        public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType) {
            final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_airport, container, false);
            return new AirportViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final AirportViewHolder viewHolder, int position) {
            final Airport airport = _filteredList.get(position);
            final String codes = airport.getIcaoCode() + "/" + airport.getLocalCode();
            viewHolder.code.setText(codes);
            viewHolder.name.setText(airport.getName());
            final String fir = "FIR " + airport.getFir().toString();
            viewHolder.fir.setText(fir);
            viewHolder.location.setText(airport.getLocation().toString());
            if (airport.isFavourite())
                viewHolder._favouriteBtn.setChecked(true);
            else
                viewHolder._favouriteBtn.setChecked(false);
        }

        @Override
        public int getItemCount() {
            if (_filteredList != null)
                return _filteredList.size();
            else
                return 0;
        }

        @Override
        public AdapterFilter getFilter(){
            return _filter;
        }
    }

    /**
     * El filtro para realizar las búsquedas
     */
    public class AdapterFilter extends Filter {

        /**
         * Para filtrar con un FILTRO.
         */
        private TIPO_FILTRO _filtroActual = TIPO_FILTRO.TODOS;

        /**
         * Booleano para saber si se está realizando una búsqueda o un filtrado(con filtro)
         */
        private boolean _busqueda = true;

        /**
         * En este método se realiza la búsqueda
         * @param consulta La consulta del usuario
         * @return Los resultados de la búsqueda
         */
        @Override
        protected FilterResults performFiltering(CharSequence consulta) {
             _filteredList.clear();
            if (_busqueda) {
                for(Airport aeropuerto : _subList )
                    if( airportMatches(aeropuerto, consulta))
                        _filteredList.add(aeropuerto);
            }
            else {
                _subList.clear();
                switch (_filtroActual) {
                    case TODOS:
                        _subList.addAll(_allAirports);
                        _filteredList.addAll(_allAirports);
                        break;
                    case EZE:
                        filtrarPorFir(FIR.EZE);
                        break;
                    case CBA:
                        filtrarPorFir(FIR.CBA);
                        break;
                    case DOZ:
                        filtrarPorFir(FIR.DOZ);
                        break;
                    case SIS:
                        filtrarPorFir(FIR.SIS);
                        break;
                    case CRV:
                        filtrarPorFir(FIR.CRV);
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = _filteredList;
            return filterResults;
        }

        /**
         * Comprueba si alguno de los valores del aeropuerto coincide con la consulta
         * @param aeropuerto el aeropuerto dado
         * @param consulta la consulta del usuario
         * @return verdadero si sí coincide, false sino.
         */
        private boolean airportMatches(Airport aeropuerto, CharSequence consulta) {
            consulta = consulta.toString().toLowerCase();

            return (
                    aeropuerto.getIcaoCode().toLowerCase().contains(consulta) ||
                    aeropuerto.getLocalCode().toLowerCase().contains(consulta) ||
                    aeropuerto.getName().toLowerCase().contains(consulta) ||
                    aeropuerto.getLocation().getLocality().toLowerCase().contains(consulta) ||
                    aeropuerto.getLocation().getProvince().toLowerCase().contains(consulta)
            );
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            _adapter.notifyDataSetChanged();
        }

        void filtrar(TIPO_FILTRO tipo ){
            _filtroActual = tipo;
            _busqueda = false;
            filter("");
        }

        TIPO_FILTRO getFilterType() {
            return _filtroActual;
        }

        void buscar(String consulta) {
            _busqueda = true;
            filter(consulta);
        }

        private void filtrarPorFir(FIR fir) {
            for (Airport airport : _allAirports)
                if (airport.getFir() == fir) {
                    _subList.add(airport);
                    _filteredList.add(airport);
                }
        }

        /*
        private void filterFavourites() {
            for (Airport airport : _allAirports)
                if (airport.isFavourite()) {
                    _subList.add(airport);
                    _filteredList.add(airport);
                }
        }
        */
    }

    /**
     * Implementación del ViewHolder, cuya función es mostrar cada elemento de la lista
     */
    private class AirportViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView code, name, fir, location;
        ToggleButton _favouriteBtn;
        Button metar, taf;

        AirportViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            code = view.findViewById(R.id.codes);
            name = view.findViewById(R.id.name);
            fir = view.findViewById(R.id.fir);
            location = view.findViewById(R.id.location);
            metar = view.findViewById(R.id.metar);
            taf = view.findViewById(R.id.taf);
            _favouriteBtn = view.findViewById(R.id.favourite);

            _favouriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    final Airport airport = _filteredList.get(getAdapterPosition());
                    if (_favouriteBtn.isChecked())
                        _dataBase.setFavourite(airport, true);
                    else
                        _dataBase.setFavourite(airport, false);
                }
            });


            /*
             * Este evento se activa al presionar sobre el ViewHolder
             */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Airport airport = _filteredList.get(AirportViewHolder.this.getAdapterPosition());
                    final Context context = view.getContext();
                    Intent intent = new Intent(context, AirportActivity.class);
                    intent.putExtra("AIRPORT", airport);
                    context.startActivity(intent);
                }
            });
        }
    }
}
