package com.makyu.hamsterium;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class Config extends AppCompatActivity {

    private SeekBar skBarGeneral;
    private SeekBar skBarMusica;
    private SeekBar skBarEfectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        skBarGeneral = findViewById(R.id.skBarGeneral);
        skBarMusica = findViewById(R.id.skBarMusica);
        skBarEfectos = findViewById(R.id.skBarEfectos);
    }

}

