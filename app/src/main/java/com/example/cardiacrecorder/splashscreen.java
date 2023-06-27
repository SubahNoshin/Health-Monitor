package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class splashscreen extends AppCompatActivity {
    TextView app_name;
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        app_name=findViewById(R.id.app_name);
        lottie=findViewById(R.id.lottie);
        app_name.animate().setDuration(700).setStartDelay(0);
        lottie.animate().setDuration(700).setStartDelay(2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        },2000);
    }
}