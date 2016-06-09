package com.gmb.watercontroller;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gborozynski on 2016-04-21.
 */
public class GB02Fragment extends Fragment {

    public static GB02Fragment newInstance() {
        GB02Fragment fragment = new GB02Fragment();
        return fragment;
    }

    public GB02Fragment() {
    }

    //Button ClickMe;
    //TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gb02, container, false);

        //return super.onCreateView(inflater, container, savedInstanceState);
        return rootView; //??
    }
}
