package com.makyu.hamsterium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText etUsuario_R;
    private EditText etContrasena_R;
    private EditText etConfirmarContrasena_R;
    private Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsuario_R = findViewById(R.id.etUsuario_R);
        etContrasena_R = findViewById(R.id.etContrasena_R);
        etConfirmarContrasena_R = findViewById(R.id.etConfirmarContrasena_R);

        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // obtenemos los datos que ingresa el user
                String usuario = etUsuario_R.getText().toString();
                String contrasena = etContrasena_R.getText().toString();
                String confirmarContrasena = etConfirmarContrasena_R.getText().toString();

                //veroficamos si las pass coinciden
                if (contrasena.equals(confirmarContrasena)) {

                    // tomamos los datos en SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("usuario", usuario);
                    editor.putString("contrasena", contrasena);
                    editor.apply();

                    // Redirigir al usuario a la actividad de inicio de sesión
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                } else {
                    // Mostrar un mensaje de error
                    Toast.makeText(Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
