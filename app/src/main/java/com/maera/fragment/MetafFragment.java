package com.maera.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
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

public class MetafFragment extends Fragment{
    private DataBaseManager _dataBase;
    private RecyclerView _recyclerView;
    private MetafAdapter _adapter;
    private List<Airport> _allAirports;
    private List<Airport> _subList, _filteredList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_metaf, container, false);

        _recyclerView = view.findViewById(R.id.airports);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        _recyclerView.setLayoutManager(manager);
        Divider divider = new Divider(getContext(), R.drawable.divider);
        _recyclerView.addItemDecoration(divider);

        _adapter = new MetafAdapter();
        _recyclerView.setAdapter(_adapter);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void refresh() {
        _allAirports = _dataBase.getAllAirports();
        if( _subList == null ) _subList = new ArrayList<>();
        if(_filteredList == null ) _filteredList = new ArrayList<>();
        _adapter.getFilter().filter();
        _recyclerView.smoothScrollToPosition(0);
    }

    public void setDataBaseContext(@NonNull Context context){
        _dataBase = DataBaseManager.getInstance(context);
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
            final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_airport, container, false);
            return new AirportViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final AirportViewHolder viewHolder, int position) {
            final Airport airport = _filteredList.get(position);
            final String codes = airport.getIcaoCode()+"/"+airport.getLocalCode();
            viewHolder.code.setText(codes);
            viewHolder.name.setText(airport.getName());
            final String fir = airport.getFir().name();
            viewHolder.fir.setText(fir);
            viewHolder.location.setText(airport.getLocation().toString());
            if (airport.isFavourite())
                viewHolder._favouriteBtn.setChecked(true);
            else
                viewHolder._favouriteBtn.setChecked(false);
        }

        @Override
        public int getItemCount() {
            if( _filteredList != null )
                return _filteredList.size();
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
        private FILTER_TYPE _currentFilter = FILTER_TYPE.ALL;
        private SEARCH_TYPE _searchType;
        private boolean _searching = true;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            _filteredList.clear();
            if (_searching) {
                switch (_searchType) {
                    case ANAC:
                        for (Airport airport : _subList)
                            if (airportMatches(airport.getLocalCode(), constraint))
                                _filteredList.add(airport);
                        break;
                    case NAME:
                        for (Airport airport : _subList)
                            if (airportMatches(airport.getName(), constraint))
                                _filteredList.add(airport);
                        break;
                    case LOCALITY:
                        for (Airport airport : _subList)
                            if (airportMatches(airport.getLocation().getLocality(), constraint))
                                _filteredList.add(airport);
                        break;
                    case PROVINCE:
                        for (Airport airport : _subList)
                            if (airportMatches(airport.getLocation().getProvince(), constraint))
                                _filteredList.add(airport);
                        break;
                    case ICAO:
                    default:
                        for (Airport airport : _subList)
                            if (airportMatches(airport.getIcaoCode(), constraint))
                                _filteredList.add(airport);
                }
            } else {
                _subList.clear();
                switch (_currentFilter) {
                    case ALL:
                        _filteredList.addAll(_allAirports);
                        _subList.addAll(_allAirports);
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
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = _filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            _adapter.notifyDataSetChanged();
        }

        void setSearchType(@NonNull SEARCH_TYPE searchType) {
            _searchType = searchType;
        }

        void setFilterType(FILTER_TYPE type) {
            _currentFilter = type;
        }

        FILTER_TYPE getFilterType() {
            return _currentFilter;
        }

        public void search(@NonNull String query) {
            _searching = true;
            filter(query);
        }

        void filter() {
            _searching = false;
            filter("");
        }

        private void filterByFir(FIR fir) {
            for (Airport airport : _allAirports)
                if (airport.getFir() == fir) {
                    _subList.add(airport);
                    _filteredList.add(airport);
                }
        }

        private void filterFavourites() {
            for (Airport airport : _allAirports)
                if (airport.isFavourite()) {
                    _subList.add(airport);
                    _filteredList.add(airport);
                }
        }

        private boolean airportMatches(@NonNull String target, @NonNull CharSequence query) {
            return target.toLowerCase().contains(query.toString().toLowerCase());
        }
    }

    /**
     * Implementación del ViewHolder, cuya función es mostrar cada elemento de la lista
     */
    private class AirportViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView code, name, fir, location;
        ToggleButton _favouriteBtn;

        AirportViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            code = view.findViewById(R.id.codes);
            name = view.findViewById(R.id.name);
            fir = view.findViewById(R.id.fir);
            location = view.findViewById(R.id.location);
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
             * Este evento es quien inicia la AirportActivity, donde se muetran los mensajes del aeropuerto seleccionado
             */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Airport airport = _filteredList.get(AirportViewHolder.this.getAdapterPosition());
                    final Context context = view.getContext();
                    Intent intent = new Intent(context, AirportActivity.class);
                    //Bundle options = ActivityOptionsCompat.makeScaleUpAnimation(AirportViewHolder.this, 0, AirportViewHolder.this.g)
                    intent.putExtra("AIRPORT", airport);
                    //context.startActivity(intent);
                    ActivityCompat.startActivity(getActivity(),
                            intent,
                            ActivityOptionsCompat.makeScaleUpAnimation(view,0,0, view.getWidth(), view.getHeight() ).toBundle()
                    );
                }
            });
        }
    }

    class Divider extends RecyclerView.ItemDecoration {
        private Drawable _drawable;
        Divider(Context context, int resId){
            _drawable = ContextCompat.getDrawable(context, resId);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,@NonNull RecyclerView parent,@NonNull RecyclerView.State state){
            super.getItemOffsets(outRect,view,parent,state);
            if(parent.getChildAdapterPosition(view)==0)
                return;
            outRect.top = _drawable.getIntrinsicHeight();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, @NonNull RecyclerView.State state) {
            final int left = 0;
            final int right = parent.getWidth();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + _drawable.getIntrinsicHeight();

                _drawable.setBounds(left, top, right, bottom);
                _drawable.draw(c);
            }
        }
    }
    public enum FILTER_TYPE{EZE, CBA, DOZ, SIS, CRV, FAVS, ALL}
    public enum SEARCH_TYPE{ICAO, ANAC, LOCALITY, PROVINCE, NAME}
}
