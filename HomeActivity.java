package com.example.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;



public class HomeActivity extends AppCompatActivity {
    TextView emailTextView, providerTextView;
    Button buttonCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonCerrarSesion = findViewById(R.id.buttonCerrarSesion);
        emailTextView = findViewById(R.id.EmailTextView);
        providerTextView = findViewById(R.id.ProviderTextView);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        String provider = bundle.getString("provider");
        setup(email, provider);
    }

    private void setup(String email, String provider) {
        emailTextView.setText(email);
        providerTextView.setText(provider);

        buttonCerrarSesion.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(HomeActivity.this, AuthActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
