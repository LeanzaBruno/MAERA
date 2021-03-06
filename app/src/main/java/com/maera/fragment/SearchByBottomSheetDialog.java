package com.maera.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.maera.R;
/*
public class SearchByBottomSheetDialog extends BottomSheetDialogFragment {
    private MetafFragment.AdapterFilter _filter;

    public SearchByBottomSheetDialog(@NonNull MetafFragment.AdapterFilter type){
        _filter = type;
        setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state){
        final View view = inflater.inflate(R.layout.fragment_searchby, container, false);
        setUpListeners(view);
        return view;
    }

    private void setUpListeners(@NonNull View view){
        ConstraintLayout oaci = view.findViewById(R.id.codes);
        oaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setSearchType(MetafFragment.SEARCH_TYPE.ICAO);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });

        ConstraintLayout codNacional = view.findViewById(R.id.codNacional);
        codNacional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setSearchType(MetafFragment.SEARCH_TYPE.ANAC);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });


        ConstraintLayout name = view.findViewById(R.id.name);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setSearchType(MetafFragment.SEARCH_TYPE.NAME);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });


        ConstraintLayout localidad = view.findViewById(R.id.localidad);
        localidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setSearchType(MetafFragment.SEARCH_TYPE.LOCALITY);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });

        ConstraintLayout provincia = view.findViewById(R.id.provincia);
        provincia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setSearchType(MetafFragment.SEARCH_TYPE.PROVINCE);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });

    }
}
*/
