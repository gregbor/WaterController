package com.gmb.watercontroller;

//import android.app.Fragment;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;


/**
 * Created by gborozynski on 2016-04-21.
 */
public class GB01Fragment extends Fragment {

    //bluetooth===========================================================================================
    BluetoothSPP bt;
    Boolean connectionStatus=false;
    TextView textConnectionStatus, textReadBT;


    public static GB01Fragment newInstance() {
        GB01Fragment fragment = new GB01Fragment();
        return fragment;
    }

    public GB01Fragment() {
    }

    Button ClickMe;
    TextView tv,apkVersionCode, apkVersionName;


    //PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getPackageName(), 0);
//    PackageInfo pInfo = (PackageInfo) getActivity().getPackageManager().getInstalledPackages(0);

//    String version = pInfo.versionName;
//    int verCode = pInfo.versionCode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        //obsługa bluetooth---------------------------------------------------------------------------------------
        bt = new BluetoothSPP(getActivity());

        if(!bt.isBluetoothAvailable()) {
            Toast.makeText(getActivity()
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();
            //finish();
        }

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceDisconnected() {
                textConnectionStatus.setText("Status : Not connect");
                connectionStatus=false;
                //buttonColorChange(false);

                //menu.clear();
                //getMenuInflater().inflate(R.menu.connection, menu);
            }

            public void onDeviceConnectionFailed() {
                textConnectionStatus.setText("Status : Connection failed");
                connectionStatus=false;
                //buttonColorChange(false);
            }

            public void onDeviceConnected(String name, String address) {
                textConnectionStatus.setText("Status : Connected to " + name);
                connectionStatus=true;
                //buttonColorChange(true);
                //menu.clear();
                //getMenuInflater().inflate(R.menu.disconnection, menu);
                //getSetupFromArduino();

            }
        });
        //obsługa bluetooth---------------------------------------------------------------------------------------
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                //podgląd message w terminalu
                textReadBT.append(message + "\n");
                final ScrollView scrollview = ((ScrollView) getView().findViewById(R.id.scrollView2));
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });


                //sprawdza czy setup
                int result=message.compareTo("#setup#");
                if (result==0){
                    Toast.makeText(getActivity(), "#setup received#", Toast.LENGTH_SHORT).show();
                    Log.i("#setup received#", "#setup received#");

                    //setupFlag=true;
                    //okFlag=false;
                }
                //sprawdza czy ok
                result=message.compareTo("ok#>");
                if (result==0){
                    Toast.makeText(getActivity(), "#ok received#", Toast.LENGTH_SHORT).show();
                    Log.i("#ok#", "#ok#");

                    //okFlag=true;
                }
                //Log.i("#flagi#", "S="+Boolean.valueOf(setupFlag)+" O="+Boolean.valueOf(okFlag));
                //


                //



            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceDisconnected() {
                textConnectionStatus.setText("Status : Not connect");
                connectionStatus=false;
                // buttonColorChange(false);

                //menu.clear();
                //getMenuInflater().inflate(R.menu.connection, menu);
            }

            public void onDeviceConnectionFailed() {
                textConnectionStatus.setText("Status : Connection failed");
                connectionStatus=false;
                //buttonColorChange(false);
            }

            public void onDeviceConnected(String name, String address) {
                textConnectionStatus.setText("Status : Connected to " + name);
                connectionStatus=true;
                // buttonColorChange(true);
                // menu.clear();
                //getMenuInflater().inflate(R.menu.disconnection, menu);
                //getSetupFromArduino();

            }
        });

//obsługa bluetooth-end--------------------------------------------------------------------------------------

        super.onCreate(savedInstanceState);



    }
    @Override
    public void onStart() {
        super.onStart();

        if (!bt.isBluetoothEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if(!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_ANDROID);
                //setup();
            }
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gb01, container, false);

        ClickMe = (Button) rootView.findViewById(R.id.button1);
        tv = (TextView) rootView.findViewById(R.id.tvFragment01Name);
        apkVersionCode = (TextView) rootView.findViewById(R.id.textView2);
        apkVersionName = (TextView) rootView.findViewById(R.id.tvVersion);

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        Toast.makeText(getActivity(),"VersionCode = " + versionCode + "\nVersionName = " + versionName, Toast.LENGTH_SHORT).show();
        apkVersionName.setText(versionName);
        apkVersionCode.setText(Integer.toString(versionCode));

        textConnectionStatus = (TextView)rootView.findViewById(R.id.textViewConnectionStatus);


        ClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv.getText().toString().contains("Hello1")){
                    tv.setText("Hi1");
                }else tv.setText("Hello1");
            }
        });

        //return super.onCreateView(inflater, container, savedInstanceState);
        return rootView; //??
    }
}
