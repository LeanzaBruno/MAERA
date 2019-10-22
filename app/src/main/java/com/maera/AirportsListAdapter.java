package com.maera;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Esta clase se encarga de organizar los datos de las FIR
 *
 */
final class AirportsListAdapter extends BaseExpandableListAdapter{
    private Context _context;
    private WeatherReport.TYPE _type;
    private List<Pair<String, List<Airport>>> _data;    //first=fir title, second=airports asociated
    private HashMap<Airport, CheckBox> _airports = new HashMap<>();       //This container tell us the airport been shown and its checkbox associated

    AirportsListAdapter(@NonNull Context context,
                        @NonNull List<Pair<String, List<Airport>>> data,
                        @NonNull WeatherReport.TYPE type){
        _context = context;
        _data = data;
        _type = type;
        filterAirportsByReport();
    }

    private void filterAirportsByReport(){
        for( Pair<String,List<Airport>> pair : _data ) {
            Iterator<Airport> it = (pair.second).iterator();
            while( it.hasNext() ){
                if ( _type == WeatherReport.TYPE.TAF && !it.next().hasTaf() )
                    it.remove();
                else
                    it.next();
            }
        }
    }

    @Override
    public View getGroupView(int group, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_expandable_group, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.title)).setText(_data.get(group).first);
        return view;
    }

    @Override
    public View getChildView(int group, int child, boolean b, View view, ViewGroup viewGroup) {
        final LayoutInflater layoutInflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_expandable_child, viewGroup, false);

        final Airport airport = _data.get(group).second.get(child);

        TextView name = view.findViewById(R.id.name);
        name.setText(airport.getAirportName());

        TextView code = view.findViewById(R.id.icao);
        code.setText(airport.getIcaoCode());

        CheckBox checkBox = view.findViewById(R.id.checkBox);

        _airports.put(airport, checkBox);

        return view;
    }

    @Override
    public int getGroupCount() { return _data.size(); }

    /**
     *
     * @param group posición del grupo
     * @return Retorna 1 siempre porque simula ser un "fragmento"
     */
    @Override
    public int getChildrenCount(int group) { return _data.get(group).second.size(); }

    @Override
    public Object getGroup(int group) { return _data.get(group).first; }

    @Override
    public Object getChild(int group, int child) { return _data.get(group).second.get(child); }

    @Override
    public long getGroupId(int group) { return group; }

    @Override
    public long getChildId(int group, int child) { return child; }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) { return true; }

    /**
     *
     * Este método retorna los aeropuertos con checkbox selectadas
     * @return Retorna una lista de aeropuertos
     */
    ArrayList<Airport> getRequestedAirports(){
        ArrayList<Airport> airports = new ArrayList<>();
        for( HashMap.Entry<Airport,CheckBox> entry : _airports.entrySet() ) {
            if( entry.getValue().isChecked() )
                airports.add(entry.getKey());
        }
        return airports;
    }
}
