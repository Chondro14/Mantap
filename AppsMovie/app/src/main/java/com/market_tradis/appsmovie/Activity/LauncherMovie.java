package com.market_tradis.appsmovie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.market_tradis.appsmovie.R;
import com.wang.avi.AVLoadingIndicatorView;

public class LauncherMovie extends AppCompatActivity {
    private AVLoadingIndicatorView loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_movie);

        int directly=4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LauncherMovie.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },directly);
        loading=findViewById(R.id.avi);
        loading.show();
    }
}
