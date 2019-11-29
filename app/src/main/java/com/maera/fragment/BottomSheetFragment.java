package com.maera.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.maera.R;
import com.maera.core.TypeOfSearch;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private final String[] _options;
    private TypeOfSearch _type;

    public BottomSheetFragment(@NonNull  TypeOfSearch type){
        _type = type;
        _options = new String[]{"Código OACI", "Código ANAC", "Nombre del aeropuerto", "Ubicación"};
        setCancelable(false);
    }

    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state){
        final View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        ListView list = view.findViewById(R.id.searchOptions);
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.layout_searchby, R.id.text, _options);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: _type.setType(TypeOfSearch.TYPE_OF_SEARCH.ICAO); break;
                    case 1: _type.setType(TypeOfSearch.TYPE_OF_SEARCH.ANAC); break;
                    case 2: _type.setType(TypeOfSearch.TYPE_OF_SEARCH.NAME); break;
                    case 3: _type.setType(TypeOfSearch.TYPE_OF_SEARCH.LOCATION); break;
                }
                BottomSheetFragment.this.dismiss();
            }
        });
        return view;
    }
}
