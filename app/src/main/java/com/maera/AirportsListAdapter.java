package com.maera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.List;


final class AirportsListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<FIR> _titles = FIR.getFirList();
    private HashMap<FIR, List<Airport>> _children = new HashMap<>();

    AirportsListAdapter(@NonNull Context context, @NonNull MODE mode){
        _context = context;

        if( mode == MODE.SHOW_ONLY_METAR )
            setMetarMode();
        else if( mode == MODE.SHOW_ONLY_TAF)
            setTafMode();
    }

    private void setMetarMode(){
        for( FIR fir : _titles )
            _children.put( fir, AirportsDataBase.getInstance().getAirportsByMetar(fir));
    }

    private void setTafMode(){
        _children = new HashMap<>();
        for( FIR fir : _titles )
            _children.put( fir, AirportsDataBase.getInstance().getAirportsByTaf(fir));
    }

    @Override
    public int getGroupCount() { return _titles.size(); }

    @Override
    public int getChildrenCount(int pos) { return _children.get(_titles.get(pos)).size(); }

    @Override
    public Object getGroup(int pos) { return _titles.get(pos); }

    @Override
    public Object getChild(int pos, int child) { return _children.get(_titles.get(pos)).get(child); }

    @Override
    public long getGroupId(int pos) { return pos; }

    @Override
    public long getChildId(int pos, int child) { return child; }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int pos, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_list_title, viewGroup, false);
        }
        final String name = _titles.get(pos).getFirName();
        ((TextView) view.findViewById(R.id.title)).setText(name);
        return view;
    }

    @Override
    public View getChildView(int pos, int child, boolean b, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_list_item, viewGroup, false);
        }
        final String airportCode = _children.get(pos).get(child).getAirportName();
        ((TextView)view.findViewById(R.id.child)).setText(airportCode);
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
