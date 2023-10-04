package com.makyu.hamsterium;

import android.content.Intent;
import android.content.SharedPreferences;
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

                // Guardamos lo que el usuario escribió
                String usuario = etUsuario_L.getText().toString();
                String contrasena = etContrasena_L.getText().toString();

                // tomamos los datos que estan en mis preferencias
                SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                String usuarioGuardado = sharedPreferences.getString("usuario", "");
                String contrasenaGuardada = sharedPreferences.getString("contrasena", "");

                // verificamos las credecniales
                if (usuario.equals(usuarioGuardado) && contrasena.equals(contrasenaGuardada)) {
                    // si ingresa bien los datos le dira que esta bien
                    Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // sino, le dirá que ta mal
                    Toast.makeText(Login.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //hacemos la validación
    private boolean validarCredenciales(String username, String password) {


        return username.equals("usuario") && password.equals("contraseña");
    }
}
