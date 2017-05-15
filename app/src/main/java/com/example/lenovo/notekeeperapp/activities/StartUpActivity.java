package com.example.lenovo.notekeeperapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.notekeeperapp.R;
import com.example.lenovo.notekeeperapp.utils.PreferencesHelper;

public class StartUpActivity extends AppCompatActivity {

    private static  final int DELAY=3000;

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate();
            }
        },DELAY);
    }
    private void navigate(){
        Class<? extends Activity> activityClass;

        if(PreferencesHelper.getInstance(this).isLoggedIn()){
            activityClass=MainActivity.class;
        }
        else{
            activityClass=RegistrationActivity.class;
        }
        startActivity(new Intent(this,activityClass));
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
