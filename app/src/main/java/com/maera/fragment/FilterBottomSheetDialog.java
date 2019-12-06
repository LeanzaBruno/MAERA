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

public final class FilterBottomSheetDialog extends BottomSheetDialogFragment {
    private MetafFragment.AdapterFilter _filter;

    public FilterBottomSheetDialog(@NonNull MetafFragment.AdapterFilter filter) {
        _filter = filter;
        setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data){
        View view = inflater.inflate(R.layout.layout_filter, container, false);

        ConstraintLayout all = view.findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.EZE);
                FilterBottomSheetDialog.this.dismiss();
            }
        });

        ConstraintLayout favs = view.findViewById(R.id.favourites);
        favs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.FAVS);
                FilterBottomSheetDialog.this.dismiss();
            }
        });

        ConstraintLayout eze = view.findViewById(R.id.eze);
        eze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.EZE);
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout cba = view.findViewById(R.id.cba);
        cba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.CBA);
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout doz = view.findViewById(R.id.doz);
        doz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.DOZ);
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout sis = view.findViewById(R.id.sis);
        sis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.SIS);
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout crv = view.findViewById(R.id.crv);
        crv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.CRV);
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout ant = view.findViewById(R.id.ant);
        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.filterBy(AirportFilter.TYPE_OF_FILTER.ANT);
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        return view;
    }
}
