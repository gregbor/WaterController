package com.gmb.watercontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by grzegorz on 2016-04-26.
 */
public class Splash extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread t=new Thread(){
            public void run(){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally{
                    //Intent i=new Intent("com.gmb.watercontroller.MAINACTIVITY");
                    Intent i=new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                }

            }

        };
        t.start();


    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
