package com.makyu.hamsterium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText etUsuario_L;
    private EditText etContrasena_L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario_L = findViewById(R.id.etUsuario_L);
        etContrasena_L = findViewById(R.id.etContrasena_L);

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsuario_L.getText().toString();
                String password = etContrasena_L.getText().toString();

                //si las credenciales tan bien
                if (validarCredenciales(username, password)) {

                    //le muestra que el inicio fue correcto
                    Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);

                    //si no
                } else {
                    // le dice que las credenciales estan mal
                    Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //hacemos la validación
    private boolean validarCredenciales(String username, String password) {


        return username.equals("usuario") && password.equals("contraseña");
    }
}
