package com.makyu.hamsterium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Bendicion extends AppCompatActivity {


    //arrelgar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bendicion);

        Button btnBendicion = findViewById(R.id.btnBendicion);
        btnBendicion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), JuegoMesa.class);
                startActivityForResult(intent, 0);
            }
        });


    }
}