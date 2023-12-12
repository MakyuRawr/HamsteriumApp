package com.makyu.hamsterium;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText correo;
    private EditText pass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //conexion a la base de datos
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.etUsuario_L);
        pass = findViewById(R.id.etContrasena_L);

        Button btnLogin = findViewById(R.id.btnLogin);


    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    public void IniciarSesion(View view){

        String email = correo.getText().toString().trim();
        String password = pass.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {

            if (!email.isEmpty() && !password.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent i = new Intent(getApplicationContext(), Config.class);
                                    startActivity(i);

                                    //para que no vuelva con el boton "atrás"
                                    finish();

                                    Toast.makeText(getApplicationContext(), "Sesión Iniciada",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(user);
                                } else {

                                    Toast.makeText(getApplicationContext(), "Hubo un problema, intentalo nuevamente",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }

                        });
            }

        } else {
            Toast.makeText(this, "Por favor, ingrese correo electrónico y contraseña", Toast.LENGTH_SHORT).show();
        }
    }
}
