package com.makyu.hamsterium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;

public class JuegoMesa extends AppCompatActivity {

    // aquí tenemos 12 datos
    int mazo[] = {0,1,2,3,4,5,6,7,8,9,10,11};
    Random random = new Random();
    ImageView imageV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_mesa);
        imageV = findViewById(R.id.imagenNumero);
    }

    public void mostrarNumero(View v) {
        //escoge un numero random
        int escoger = random.nextInt(mazo.length);
        //obtiene el num radom
        int numRandom = mazo[escoger];

        // buscamos una imagen que contenga la m + el numero
        String nombreImagen = "m" + numRandom;


        int imgID = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
        imageV.setImageResource(imgID);

        //hacemos la imagen visible
        imageV.setVisibility(View.VISIBLE);

        //muestra eñ numero random en la tastita    
        Toast.makeText(this, String.valueOf(numRandom), Toast.LENGTH_SHORT).show();

    }
}
