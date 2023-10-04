package com.makyu.hamsterium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button idJugar = findViewById(R.id.idJugar);
        idJugar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Bendicion.class);
                startActivityForResult(intent, 0);
            }
        });

        Button btnDiario = findViewById(R.id.btnDiario);
        btnDiario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Diario.class);
                startActivityForResult(intent, 0);
            }
        });

        Button btnConfig = findViewById(R.id.btnConfig);
        btnConfig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Config.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}