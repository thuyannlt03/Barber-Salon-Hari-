package com.example.da_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {


    private static final String KEY_FIRST_INSTALL = "KEY_FIRST_INSTALL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final MySharedPreferences mySharedPreferences = new MySharedPreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               if (mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL)){
                   startActivity(LoginActivity.class);
               }else {
                   startActivity(OnBoarding.class);
                   mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL,true);
               }
            }
        },2500);
    }

    private void startActivity(Class<?>cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}