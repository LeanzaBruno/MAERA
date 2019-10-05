package com.maera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

public final class TafFragment extends BaseFragment {
    private ExpandableListView _firEzeList;


    @Override
    protected void setUpViewsReferences(View view) {
        _checkBoxes.add((CheckBox)view.findViewById(R.id.sabe));
        _checkBoxes.add((CheckBox)view.findViewById(R.id.sazb));
        _checkBoxes.add((CheckBox)view.findViewById(R.id.sazs));
        _checkBoxes.add((CheckBox)view.findViewById(R.id.sazy));
        _getBtn = view.findViewById(R.id.metarBtn);
    }

    @Override
    protected void setUpEvents(){
        _getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { TafFragment.super.getMessage(Message.TAF); }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance )
    {
        View view = inflater.inflate(R.layout.fragment_taf, container,false);

        setUpViewsReferences(view);
        setUpEvents();

        return view;
    }
}
