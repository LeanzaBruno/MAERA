package com.maera.fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.maera.R;

public final class FilterBottomSheetDialog extends BottomSheetDialogFragment {
    private MetafFragment.AdapterFilter _filter;
    private TextView _infoText;

    public FilterBottomSheetDialog(@NonNull MetafFragment.AdapterFilter filter, @NonNull TextView infoText) {
        _filter = filter;
        _infoText = infoText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data){
        View view = inflater.inflate(R.layout.fragment_filterby, container, false);
        selectCurrentFilter(view);
        setUpListeners(view);
        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        super.onDismiss(dialog);
        _filter.filter();
        if(_filter.getFilterType() != MetafFragment.FILTER_TYPE.ALL)
            _infoText.setVisibility(View.VISIBLE);
        else
            _infoText.setVisibility(View.GONE);
    }

    private void selectCurrentFilter(@NonNull View view){
        int textId, imageId;
        switch(_filter.getFilterType()){
            case ALL:
                textId = R.id.allText;
                imageId = R.id.allCheck;
                break;
            case FAVS:
                textId = R.id.favsText;
                imageId = R.id.favsCheck;
                break;
            case EZE:
                textId = R.id.ezeText;
                imageId = R.id.ezeCheck;
                break;
            case CBA:
                textId = R.id.cbaText;
                imageId = R.id.cbaCheck;
                break;
            case DOZ:
                textId = R.id.dozText;
                imageId = R.id.dozCheck;
                break;
            case SIS:
                textId = R.id.sisText;
                imageId = R.id.sisCheck;
                break;
            case CRV:
            default:
                textId = R.id.crvText;
                imageId = R.id.crvCheck;
                break;
        }
        view.findViewById(imageId).setVisibility(View.VISIBLE);
        TextView text = view.findViewById(textId);
        text.setTextColor(getResources().getColor(R.color.selected));
    }


    private void setUpListeners(@NonNull View view){
        ConstraintLayout all = view.findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setFilterType(MetafFragment.FILTER_TYPE.ALL);
                _infoText.setText("");
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout favs = view.findViewById(R.id.favourites);
        favs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setFilterType(MetafFragment.FILTER_TYPE.FAVS);
                _infoText.setText(getResources().getString(R.string.showingFavs));
                FilterBottomSheetDialog.this.dismiss();
            }
        });

        ConstraintLayout eze = view.findViewById(R.id.eze);
        eze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setFilterType(MetafFragment.FILTER_TYPE.EZE);
                _infoText.setText(getResources().getString(R.string.showingEZE));
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout cba = view.findViewById(R.id.cba);
        cba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setFilterType(MetafFragment.FILTER_TYPE.CBA);
                _infoText.setText(getResources().getString(R.string.showingCBA));
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout doz = view.findViewById(R.id.doz);
        doz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setFilterType(MetafFragment.FILTER_TYPE.DOZ);
                _infoText.setText(getResources().getString(R.string.showingDOZ));
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout sis = view.findViewById(R.id.sis);
        sis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setFilterType(MetafFragment.FILTER_TYPE.SIS);
                _infoText.setText(getResources().getString(R.string.showingSIS));
                FilterBottomSheetDialog.this.dismiss();
            }
        });
        ConstraintLayout crv = view.findViewById(R.id.crv);
        crv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _filter.setFilterType(MetafFragment.FILTER_TYPE.CRV);
                _infoText.setText(getResources().getString(R.string.showingCRV));
                FilterBottomSheetDialog.this.dismiss();
            }
        });
    }
}
