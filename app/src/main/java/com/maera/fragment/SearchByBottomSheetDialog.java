package com.maera.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.maera.R;
import com.maera.core.AirportFilter;

public class SearchByBottomSheetDialog extends BottomSheetDialogFragment {
    private AirportFilter _filter;

    public SearchByBottomSheetDialog(@NonNull  AirportFilter type){
        _filter = type;
        setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state){
        final View view = inflater.inflate(R.layout.fragment_searchby, container, false);

        ConstraintLayout oaci = view.findViewById(R.id.oaci);
        oaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setType(AirportFilter.TYPE_OF_FILTER.ICAO);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });

        ConstraintLayout anac = view.findViewById(R.id.anac);
        anac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setType(AirportFilter.TYPE_OF_FILTER.ANAC);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });


        ConstraintLayout name = view.findViewById(R.id.name);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setType(AirportFilter.TYPE_OF_FILTER.NAME);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });


        ConstraintLayout locality = view.findViewById(R.id.locality);
        locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setType(AirportFilter.TYPE_OF_FILTER.LOCALITY);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });

        ConstraintLayout province = view.findViewById(R.id.province);
        province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setType(AirportFilter.TYPE_OF_FILTER.PROVINCE);
                SearchByBottomSheetDialog.this.dismiss();
            }
        });
        return view;
    }
}
