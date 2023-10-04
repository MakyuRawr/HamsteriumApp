package com.makyu.hamsterium;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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

        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        TextView tvRegistrarse = findViewById(R.id.tvRegistrarse);

        // BOTON INICAR Sesión
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Config.this, Login.class);
                startActivity(intent);
            }
        });


            // boton registrarse en el text view
        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //Intent intent = new Intent(Config.this, Register.class);
                //startActivity(intent);
            }
        });
    }

}

