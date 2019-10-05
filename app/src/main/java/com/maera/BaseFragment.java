package com.maera;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

abstract class BaseFragment extends Fragment {
    ArrayList<CheckBox> _checkBoxes = new ArrayList<>();
    Button _getBtn;
    private TextView _result;


    abstract protected void setUpViewsReferences(View view);
    abstract protected void setUpEvents();

    void getMessage( Message message ) {
        new MessageDownloader(getCodes(), message, this).execute();
    }

    String getCodes()
    {
        StringBuilder builder = new StringBuilder();
        for( CheckBox checkBox : _checkBoxes )
            if( checkBox.isChecked() )
                builder.append(checkBox.getText()).append("+");
        return builder.toString();
    }


}
