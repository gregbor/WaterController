package com.gmb.watercontroller;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by gborozynski on 2016-04-21.
 */
public class GB05Fragment extends Fragment {

    NumberPicker numberPickerMin01, numberPickerMin02,numberPickerMin03,numberPickerMin04,numberPickerMin05;
    NumberPicker numberPickerMax01, numberPickerMax02,numberPickerMax03,numberPickerMax04,numberPickerMax05;

    public static GB05Fragment newInstance() {
        GB05Fragment fragment = new GB05Fragment();
        return fragment;
    }

    public GB05Fragment() {
    }

    Button ClickMe;
    TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gb05, container, false);

        numberPickerMin01 = (NumberPicker) rootView.findViewById(R.id.numberPickerMin01);
        numberPickerMin01.setMaxValue(255);
        numberPickerMin01.setMinValue(0);
        numberPickerMin01.setValue(128);
        numberPickerMin01.setWrapSelectorWheel(false);
        numberPickerMin02 = (NumberPicker) rootView.findViewById(R.id.numberPickerMin02);
        numberPickerMin02.setMaxValue(255);
        numberPickerMin02.setMinValue(0);
        numberPickerMin02.setWrapSelectorWheel(false);
        numberPickerMin03 = (NumberPicker) rootView.findViewById(R.id.numberPickerMin03);
        numberPickerMin03.setMaxValue(255);
        numberPickerMin03.setMinValue(0);
        numberPickerMin03.setWrapSelectorWheel(false);
        numberPickerMin04 = (NumberPicker) rootView.findViewById(R.id.numberPickerMin04);
        numberPickerMin04.setMaxValue(255);
        numberPickerMin04.setMinValue(0);
        numberPickerMin04.setWrapSelectorWheel(false);
        numberPickerMin05 = (NumberPicker) rootView.findViewById(R.id.numberPickerMin05);
        numberPickerMin05.setMaxValue(255);
        numberPickerMin05.setMinValue(0);
        numberPickerMin05.setWrapSelectorWheel(false);

        numberPickerMax01 = (NumberPicker) rootView.findViewById(R.id.numberPickerMax01);
        numberPickerMax01.setMaxValue(255);
        numberPickerMax01.setMinValue(0);
        numberPickerMax01.setWrapSelectorWheel(false);
        numberPickerMax02 = (NumberPicker) rootView.findViewById(R.id.numberPickerMax02);
        numberPickerMax02.setMaxValue(255);
        numberPickerMax02.setMinValue(0);
        numberPickerMax02.setWrapSelectorWheel(false);
        numberPickerMax03 = (NumberPicker) rootView.findViewById(R.id.numberPickerMax03);
        numberPickerMax03.setMaxValue(255);
        numberPickerMax03.setMinValue(0);
        numberPickerMax03.setWrapSelectorWheel(false);
        numberPickerMax04 = (NumberPicker) rootView.findViewById(R.id.numberPickerMax04);
        numberPickerMax04.setMaxValue(255);
        numberPickerMax04.setMinValue(0);
        numberPickerMax04.setWrapSelectorWheel(false);
        numberPickerMax05 = (NumberPicker) rootView.findViewById(R.id.numberPickerMax05);
        numberPickerMax05.setMaxValue(255);
        numberPickerMax05.setMinValue(0);
        numberPickerMax05.setWrapSelectorWheel(true);


        //return super.onCreateView(inflater, container, savedInstanceState);
        return rootView; //??
    }
}
