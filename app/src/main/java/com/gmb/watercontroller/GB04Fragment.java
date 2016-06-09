package com.gmb.watercontroller;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gborozynski on 2016-04-21.
 */
public class GB04Fragment extends Fragment {

    public static GB04Fragment newInstance() {
        GB04Fragment fragment = new GB04Fragment();
        return fragment;
    }

    public GB04Fragment() {
    }

    Button ClickMe;
    TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gb04, container, false);



        //return super.onCreateView(inflater, container, savedInstanceState);
        return rootView; //??
    }
}
