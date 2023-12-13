package com.makyu.hamsterium;

import android.content.Intent;
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
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Login extends AppCompatActivity {

    // Token
    private static String mqttHost = "tcp://hamsterium.cloud.shiftr.io:1883";
    private static String mqttUser = "hamsterium";
    private static String mqttPass = "iUPKBhWOaAGC29jQ";

    //varibles mttq
    private MqttAndroidClient clienteMQTT;
    private MqttConnectOptions opcionesMQTT;

    //firebase
    private EditText correo;
    private EditText pass;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //conexión a la base de datos Firebase
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.etUsuario_L);
        pass = findViewById(R.id.etContrasena_L);

        //iniciar mqtt
        initMQTT();

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarSesion();
            }
        });
    }

    private void initMQTT() {
        // inica el cliente MQTT y configuramos las opciones
        clienteMQTT = new MqttAndroidClient(getApplicationContext(), mqttHost, FirebaseAuth.getInstance().getUid());
        opcionesMQTT = new MqttConnectOptions();
        opcionesMQTT.setUserName(mqttUser);
        opcionesMQTT.setPassword(mqttPass.toCharArray());

        //Conectamos al broker MQTT
        try {
            IMqttToken token = clienteMQTT.connect(opcionesMQTT);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(Login.this, "Conexión MQTT exitosa", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(Login.this, "Conexión MQTT fallida", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void IniciarSesion(){
        String email = correo.getText().toString().trim();
        String password = pass.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //enviar mensaje MQTT al iniciar sesión con éxito
                                enviarMensajeMQTT("usuario/conectado", "El usuario ha iniciado sesión");

                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), Config.class);
                                startActivity(i);
                                finish();

                                Toast.makeText(getApplicationContext(), "Sesión Iniciada",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Hubo un problema, intentalo nuevamente",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Por favor, ingrese correo electrónico y contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    private void enviarMensajeMQTT(String topic, String msg) {
        if (clienteMQTT.isConnected()) {
            try {
                clienteMQTT.publish(topic, msg.getBytes(), 0, false);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Cliente MQTT no está conectado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //desconecta del broker MQTT al destruir la actividad >:D
        if (clienteMQTT != null) {
            try {
                clienteMQTT.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
