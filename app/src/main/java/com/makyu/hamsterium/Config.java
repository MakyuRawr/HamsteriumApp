package com.makyu.hamsterium;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Config extends AppCompatActivity {

    private SeekBar skBarGeneral;
    private SeekBar skBarMusica;
    private SeekBar skBarEfectos;

    private boolean sesionActiva = false;

    //colores
    int color = Color.parseColor("#4A9E8D");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        skBarGeneral = findViewById(R.id.skBarGeneral);
        skBarMusica = findViewById(R.id.skBarMusica);
        skBarEfectos = findViewById(R.id.skBarEfectos);



        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        TextView tvRegistrarse = findViewById(R.id.tvRegistrarse);

        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        RatingBar ratingBar = findViewById(R.id.ratingBar);

        Button btnGps = findViewById(R.id.btnGps);


        //configuración d ratingbar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                Toast.makeText(Config.this, "Calificación: " + rating, Toast.LENGTH_SHORT).show();
            }
        });


        // BOTON INICAR Sesión
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Config.this, Login.class);
                startActivity(intent);
                sesionActiva = true;
                // cerramos el activity actual para evitar que el usuario regrese con "atras"
                finish();

            }
        });



        tvRegistrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //redirige al usuario a la pantalla de registro
                Intent intent = new Intent(Config.this, Register.class);
                startActivity(intent);
            }
        });



        // boton registrarse en el text view
        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Config.this, Register.class);
                startActivity(intent);
            }
        });

        //verifica si la sesión esta iniciada
        if (sesionActiva) {
            //si sesión ya está activa, muestra un Toast y deshabilita el botón de registro
            Toast.makeText(Config.this, "La sesión ya está iniciada", Toast.LENGTH_SHORT).show();
            tvRegistrarse.setEnabled(false);
            //cambiamos el color del texto para mostrar que el boton está deshabilitado
            tvRegistrarse.setTextColor(Color.GRAY);
            btnCerrarSesion.setVisibility(View.VISIBLE);
        } else {
            //si la sesión no está activa, habilita el botón de registro
            tvRegistrarse.setEnabled(true);
            //btnCerrarSesion.setVisibility(View.GONE);

            //ponemos el color por defecto
            tvRegistrarse.setTextColor(color);

        }



        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // borramos los datos almacenados del usuario
                SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //ocultamo el botón de cerrar sesión luego de apretar "cerrar sesión"
                //btnCerrarSesion.setVisibility(View.GONE);

                editor.clear();
                editor.apply();

                // le decimos que se cerró la sesión
                Toast.makeText(Config.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
                sesionActiva = false;



                // llevamos al usuario al login
                Intent intent = new Intent(Config.this, Login.class);
                startActivity(intent);
                // cerramos el activity actual para evitar que el usuario regrese con "atras"
                finish();
            }
        });


        btnGps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), mapaBox.class);
                startActivityForResult(intent, 0);
            }
        });


    }

}

