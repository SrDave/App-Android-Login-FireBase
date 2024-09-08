package com.example.loginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class ConfigActivity extends AppCompatActivity {

    FloatingActionButton menu;
    FloatingActionButton aboutus;
    FloatingActionButton log;
    FloatingActionButton home;
    boolean aBoolean=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);

        // Atrás
        ImageButton atras = findViewById(R.id.atras);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the background color when the button is clicked
                atras.setBackgroundColor(getResources().getColor(R.color.onclick));

                // Perform the desired action (e.g., go back)
                onBackPressed();
            }
        });

        // Variables
        menu = findViewById(R.id.menu_d);
        home = findViewById(R.id.home);
        aboutus = findViewById(R.id.aboutus);
        log = findViewById(R.id.loguot);

        // Menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of other icons
                if (aBoolean) {
                    home.show();
                    aboutus.show();
                    log.show();
                    aBoolean =false;
                } else {
                    home.hide();
                    aboutus.hide();
                    log.hide();
                    aBoolean =true;
                }
            }
        });

        //otras activity's
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al presionar el botón "Cerrar Sesión"

                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                showHome(email, ProviderType.BASIC); // Redirigir a HomeActivity con valores de email y provider
            }
        });
    }
    private void showHome(String email, ProviderType provider) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("email", email);
        homeIntent.putExtra("provider", provider.name());
        startActivity(homeIntent);
    }
}
