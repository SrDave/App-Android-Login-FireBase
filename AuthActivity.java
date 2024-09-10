package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    EditText user, pass;
    Button buttonReg, buttonLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        buttonReg = findViewById(R.id.buttonReg);
        buttonLog = findViewById(R.id.buttonLog);
        setup();
    }

    private void setup() {
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Registro exitoso
                                        //showHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                                        showRegister();
                                    } else {
                                        showAlert();
                                    }
                                }
                            });
                }
            }
        });
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(user.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Inicio de sesión exitoso
                                        Intent MainIntent = new Intent(AuthActivity.this, MainActivity.class);
                                        startActivity(MainIntent);
                                    } else {
                                        showAlert();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error, usuario o contraseña no validos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acciones a realizar cuando se hace clic en "Aceptar"
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showRegister() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hecho!");
        builder.setMessage("El usuario ha sido registrado correctamente");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acciones a realizar cuando se hace clic en "Aceptar"
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showHome(String email, ProviderType provider) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("email", email);
        homeIntent.putExtra("provider", provider.name());
        startActivity(homeIntent);
    }
}
