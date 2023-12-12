package com.makyu.hamsterium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText etUsuario_R;
    private EditText etContrasena_R;
    private EditText etConfirmarContrasena_R;
    private Button btnRegistrarse;



    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText pass;
    private EditText passConfirm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //conexion a la base de datos
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.etUsuario_R);
        pass = findViewById(R.id.etContrasena_R);
        passConfirm = findViewById(R.id.etConfirmarContrasena_R);


        btnRegistrarse = findViewById(R.id.btnRegistrarse);


        //vamos de nuevo
    }

    public void OnStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void RegistrarUser(View view) {

        String email = correo.getText().toString().trim();
        String password = pass.getText().toString();
        String confirmPassword = passConfirm.getText().toString();

        if (!email.isEmpty() && !password.isEmpty() && password.equals(confirmPassword)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                FirebaseUser user = mAuth.getCurrentUser();

                                Toast.makeText(Register.this, "¡Usuario Creado!", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Register.this, Config.class);
                                startActivity(i);

                                //para que no vuelva con el boton <atrás>
                                finish();

                                //updateUI(user);
                            } else {
                                //Toast.makeText(getApplicationContext(), "mínimo de 6 carácteres",Toast.LENGTH_SHORT).show();
                                Toast.makeText(Register.this, "Error al crear el usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });

        } else{
            if (email.isEmpty()) {
                Toast.makeText(Register.this, "El campo de correo electrónico no puede estar vacío", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(Register.this, "El campo de contraseña no puede estar vacío", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }




    }



}
