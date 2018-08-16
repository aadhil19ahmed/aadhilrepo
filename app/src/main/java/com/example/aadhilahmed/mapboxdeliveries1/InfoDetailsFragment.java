package com.example.aadhilahmed.mapboxdeliveries1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by aadhil.ahmed on 08-Nov-17.
 */

public class InfoDetailsFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams p=getDialog().getWindow().getAttributes();
        p.verticalMargin=.10f;
        getDialog().getWindow().setAttributes(p);
        getDialog().setTitle("Markers key");
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view=inflater.inflate(R.layout.layout_infodetail,container);
        return view;
    }
}
