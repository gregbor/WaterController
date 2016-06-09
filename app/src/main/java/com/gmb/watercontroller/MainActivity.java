package com.gmb.watercontroller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    //bluetooth===========================================================================================
    BluetoothSPP bt;
    Boolean connectionStatus=false;
    TextView textConnectionStatus, textRead;



    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
//


    @Override
    protected void onStart() {
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textConnectionStatus = (TextView)findViewById(R.id.textViewConnectionStatus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//obsługa bluetooth---------------------------------------------------------------------------------------
        bt = new BluetoothSPP(this);

        if(!bt.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext()
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();
            finish();
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
                textRead.append(message + "\n");
                final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollView));
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });


                //sprawdza czy setup
                int result=message.compareTo("#setup#");
                if (result==0){
                    Toast.makeText(getApplicationContext(), "#setup received#", Toast.LENGTH_SHORT).show();
                    Log.i("#setup received#", "#setup received#");

                    //setupFlag=true;
                    //okFlag=false;
                }
                //sprawdza czy ok
                result=message.compareTo("ok#>");
                if (result==0){
                    Toast.makeText(getApplicationContext(), "#ok received#", Toast.LENGTH_SHORT).show();
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //if(id == R.id.menu_android_connect) {
        //	bt.setDeviceTarget(BluetoothState.DEVICE_ANDROID);
			/*
			if(bt.getServiceState() == BluetoothState.STATE_CONNECTED)
    			bt.disconnect();*/
        //	Intent intent = new Intent(getApplicationContext(), DeviceList.class);
        //   startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
        //else}
        if(id == R.id.action_connect) {
            bt.setDeviceTarget(BluetoothState.DEVICE_OTHER);
			/*
			if(bt.getServiceState() == BluetoothState.STATE_CONNECTED)
    			bt.disconnect();*/
            Intent intent = new Intent(getApplicationContext(), DeviceList.class);
            startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
            Toast.makeText(getApplicationContext(),"Bluetooth connected", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.action_disconnect) {
            if(bt.getServiceState() == BluetoothState.STATE_CONNECTED)
                bt.disconnect();
            Toast.makeText(getApplicationContext(),"Bluetooth disconnected", Toast.LENGTH_SHORT).show();
            //buttonColorChange(false);
        } else if(id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);


/*
        switch (item.getItemId()) {
            case R.id.action_connect:
                bt.setDeviceTarget(BluetoothState.DEVICE_OTHER);
                Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
*/
    }
//========================================================================================================
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if(resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if(requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if(resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_ANDROID);
                //setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

//========================================================================================================


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch(position){
                case 0: return GB01Fragment.newInstance();
                case 1: return GB02Fragment.newInstance();
                case 2: return GB03Fragment.newInstance();
                case 3: return GB04Fragment.newInstance();
                case 4: return GB05Fragment.newInstance();
            }


            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";



            }
            return null;
        }
    }
}
