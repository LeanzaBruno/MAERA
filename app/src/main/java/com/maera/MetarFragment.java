package com.maera;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public final class MetarFragment extends Fragment {
    private Context _context;
    private ExpandableListView _firList;
    private AirportsListAdapter _adapter;
    private Button _getBtn;

    MetarFragment(Context context){ _context = context; }

    private void setUpViewsReferences(@NonNull View view)
    {
        _firList = view.findViewById(R.id.list);
        _getBtn = view.findViewById(R.id.get);
    }

    private void setUpViews()
    {
        _adapter = new AirportsListAdapter( _context, MODE.SHOW_METAR);
        _firList.setAdapter( _adapter );
        setUpEvents();
    }

    private void setUpEvents() {
        _getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup viewGroup,
                             @Nullable Bundle savedInstance) {

        final View view = inflater.inflate(R.layout.fragment_metar, viewGroup, false);

        setUpViewsReferences(view);
        setUpViews();
        return view;
    }
}
