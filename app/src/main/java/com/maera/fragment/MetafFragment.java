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
import com.maera.activity.AeropuertoActivity;
import com.maera.core.Aeropuerto;
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
     * La lista allAeropuertos contiene, como su nombre lo indica, todos los aeropuertos y es inmutable.
     * Se usa de backup para poder volver a la lista de aeropuertos original.
     */
    private List<Aeropuerto> _allAeropuertos;

    /**
     * La lista sublist es otra lista a modo de backup. Cuando el usuario filtra los aeropuertos por FIR(u otra etiqueta),
     * sublist pasa a contener todos los aeropuertos que coincidan con ese criterio. De esta forma, cuando se haga un filtrado
     * a esta sublista, se puede volver hacia atrás si el usuario cancela la búsqueda o sus criterios son menos específicos.
     * Si no hay ningun criterio de separación en uso, entonces sublist es igual a la lista allAeropuertos.
     */
    private List<Aeropuerto> _subList;

    /**
     * Esta lista es la que se mostrará al usuario, es el resultado de los filtrados realizados en las listas anteriores.
     */
    private List<Aeropuerto> _filteredList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.fragment_metaf, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.Aeropuertos);
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
        _allAeropuertos = _dataBase.getAllAeropuertos();
        if( _subList == null ) _subList = new ArrayList<>();
        if(_filteredList == null ) _filteredList = new ArrayList<>();
        _subList.addAll(_allAeropuertos);
        _filteredList.addAll(_allAeropuertos);
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
    private class MetafAdapter extends RecyclerView.Adapter<AeropuertoViewHolder> implements Filterable {

        /**
         * El adaptador encargado de filtrar.
         */
        private AdapterFilter _filter = new AdapterFilter();

        @NonNull
        @Override
        public AeropuertoViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType) {
            final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_Aeropuerto, container, false);
            return new AeropuertoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final AeropuertoViewHolder viewHolder, int position) {
            final Aeropuerto Aeropuerto = _filteredList.get(position);
            final String codes = Aeropuerto.obtenerOACI() + "/" + Aeropuerto.obtenerCodLocal();
            viewHolder.code.setText(codes);
            viewHolder.name.setText(Aeropuerto.obtenerNombre());
            final String fir = "FIR " + Aeropuerto.obtenerFIR().toString();
            viewHolder.fir.setText(fir);
            viewHolder.location.setText(Aeropuerto.obtenerLocalizacion().toString());
            if (Aeropuerto.esFavorito())
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
                for(Aeropuerto aeropuerto : _subList )
                    if( AeropuertoMatches(aeropuerto, consulta))
                        _filteredList.add(aeropuerto);
            }
            else {
                _subList.clear();
                switch (_filtroActual) {
                    case TODOS:
                        _subList.addAll(_allAeropuertos);
                        _filteredList.addAll(_allAeropuertos);
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
        private boolean AeropuertoMatches(Aeropuerto aeropuerto, CharSequence consulta) {
            consulta = consulta.toString().toLowerCase();

            return (
                    aeropuerto.obtenerOACI().toLowerCase().contains(consulta) ||
                    aeropuerto.obtenerCodLocal().toLowerCase().contains(consulta) ||
                    aeropuerto.obtenerNombre().toLowerCase().contains(consulta) ||
                    aeropuerto.obtenerLocalizacion().obtenerLocalidad().toLowerCase().contains(consulta) ||
                    aeropuerto.obtenerLocalizacion().obtenerProvincia().toLowerCase().contains(consulta)
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
            for (Aeropuerto Aeropuerto : _allAeropuertos)
                if (Aeropuerto.obtenerFIR() == fir) {
                    _subList.add(Aeropuerto);
                    _filteredList.add(Aeropuerto);
                }
        }

        /*
        private void filterFavourites() {
            for (Aeropuerto.java Aeropuerto.java : _allAeropuertos)
                if (Aeropuerto.java.esFavorito()) {
                    _subList.add(Aeropuerto.java);
                    _filteredList.add(Aeropuerto.java);
                }
        }
        */
    }

    /**
     * Implementación del ViewHolder, cuya función es mostrar cada elemento de la lista
     */
    private class AeropuertoViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView code, name, fir, location;
        ToggleButton _favouriteBtn;
        Button metar, taf;

        AeropuertoViewHolder(View view) {
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
                    final Aeropuerto Aeropuerto = _filteredList.get(getAdapterPosition());
                    if (_favouriteBtn.isChecked())
                        _dataBase.setFavourite(Aeropuerto, true);
                    else
                        _dataBase.setFavourite(Aeropuerto, false);
                }
            });


            /*
             * Este evento se activa al presionar sobre el ViewHolder
             */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Aeropuerto Aeropuerto = _filteredList.get(AeropuertoViewHolder.this.getAdapterPosition());
                    final Context context = view.getContext();
                    Intent intent = new Intent(context, AeropuertoActivity.class);
                    intent.putExtra("Aeropuerto.java", Aeropuerto);
                    context.startActivity(intent);
                }
            });
        }
    }
}
