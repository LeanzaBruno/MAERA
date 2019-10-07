package com.maera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de organizar los datos de las FIR
 *
 */
final class AirportsListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private MODE _mode;
    private List<FirInfo> _firInfoList;

    class FirInfo{
        FIR fir;
        List<CheckBox> checkBoxes = null;
        FirInfo(FIR fir){ this.fir = fir; }
    }

    AirportsListAdapter(@NonNull Context context, @NonNull MODE mode){
        _context = context;
        _mode = mode;
        _firInfoList = new ArrayList<>();
        final List<FIR> titles = FIR.getFirList();
        for( FIR fir : titles ) _firInfoList.add( new FirInfo(fir));
    }

    @Override
    public View getGroupView(int group, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_list_title, viewGroup, false);
        }
        final String name = _firInfoList.get(group).fir.getFirName();
        ((TextView) view.findViewById(R.id.title)).setText(name);
        return view;
    }

    @Override
    public View getChildView(int group, int child, boolean b, View view, ViewGroup viewGroup) {
        if( view != null ) return view;

        final FirInfo firInfo = _firInfoList.get(group);
        final LayoutInflater layoutInflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch( firInfo.fir ){
            case EZE:
                view = layoutInflater.inflate(R.layout.fragment_eze, viewGroup, false);
                setUpEzeizaCheckBoxes(view, firInfo);
                break;
            case CBA:
                break;
            case DOZ:
                break;
            case CRV:
                break;
            case SIS:
                break;
        }
        return view;
    }

    @Override
    public int getGroupCount() { return _firInfoList.size(); }

    /**
     *
     * @param group posición del grupo
     * @return Retorna 1 siempre porque simula ser un "fragmento"
     */
    @Override
    public int getChildrenCount(int group) { return 1; }

    @Override
    public Object getGroup(int group) { return _firInfoList.get(group).fir; }

    @Override
    public Object getChild(int group, int child) { return _firInfoList.get(group).checkBoxes; }

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

    private void setUpEzeizaCheckBoxes(View view, FirInfo firInfo){
        firInfo.checkBoxes = new ArrayList<>();
        firInfo.checkBoxes.add( (CheckBox) view.findViewById(R.id.saav));
        firInfo.checkBoxes.add( (CheckBox) view.findViewById(R.id.saap));
        firInfo.checkBoxes.add( (CheckBox) view.findViewById(R.id.sabe));
        firInfo.checkBoxes.add( (CheckBox) view.findViewById(R.id.saez));
    }
}
