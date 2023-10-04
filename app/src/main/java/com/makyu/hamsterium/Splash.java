package com.makyu.hamsterium;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private ProgressBar pbSplash;
    // esperamos 3 segundos
    private int splashTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);

        pbSplash = findViewById(R.id.pbSplash);

        //inicia la animación de ProgressBar
        pbSplash.setProgress(0);
        pbSplash.setMax(splashTime);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //cuando se cumpla el tiempo, inicia la actividad principal
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                // cierra la actividad actual
                finish();
            }
        }, splashTime);
    }
}

