package com.maera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public final class MetarFragment extends Fragment {
    private ExpandableListView _firList;

    private void setUpViewsReferences(@NonNull View view)
    {
        _firList = view.findViewById(R.id.list);
    }

    private void setUpViews() {
        _firList.setAdapter(new AirportsListAdapter( this.getContext(), MODE.SHOW_ONLY_METAR));
    }

    private void setUpEvents() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup viewGroup,
                             @Nullable Bundle savedInstance) {

        final View view = inflater.inflate(R.layout.fragment_metar, viewGroup, false);

        setUpViewsReferences(view);
        setUpViews();
        setUpEvents();
        return view;
    }
}
