package com.example.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.view.View;
import android.content.Intent;
import android.net.Uri;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;




public class MainActivity extends AppCompatActivity {

    ImageView bgapp;
    LinearLayout inicio;
    LinearLayout bienvenida;
    LinearLayout menujuegos;
    Animation homepage;
    FloatingActionButton menu;
    FloatingActionButton aboutus;
    FloatingActionButton log;
    FloatingActionButton conf;
    boolean aBoolean=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asignamos las variables
        bgapp = findViewById(R.id.bgapp);
        bienvenida = findViewById(R.id.bienvenida);
        inicio = findViewById(R.id.inicio);
        menujuegos = findViewById(R.id.menujuegos);
        homepage = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.homepage);

        // Animaciones
        bgapp.animate().translationY(-1650).setDuration(800).setStartDelay(600);
        bienvenida.animate().translationY(-120).alpha(0).setDuration(800).setStartDelay(600);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                inicio.startAnimation(homepage);
                menujuegos.startAnimation(homepage);
            }
        }, 90); // Delay de 600 milisegundos (0.6 segundos)

        // variables menu

        menu = findViewById(R.id.menu_d);
        conf = findViewById(R.id.conf);
        aboutus = findViewById(R.id.aboutus);
        log = findViewById(R.id.loguot);

        // Menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of other icons
                if (aBoolean) {
                    conf.show();
                    aboutus.show();
                    log.show();
                    aBoolean =false;
                } else {
                    conf.hide();
                    aboutus.hide();
                    log.hide();
                    aBoolean =true;
                }
            }
        });

        //otras activity's
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivity(intent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
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


        // Links Games
        int[] linearLayoutIds = {R.id.sudoku, R.id.snake, R.id.tictac, R.id.carta};
        String[] urls = {"https://sudoku.com", "https://www.digimobil.es/minijuegos/snake", "https://papergames.io/es/tres-en-raya", "https://solitarios-online.com"};

        for (int i = 0; i < linearLayoutIds.length; i++) {
            final int index = i;
            LinearLayout linearLayout = findViewById(linearLayoutIds[i]);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // abrir links
                    String url = urls[index];
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            });
        }

    }

    private void showHome(String email, ProviderType provider) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("email", email);
        homeIntent.putExtra("provider", provider.name());
        startActivity(homeIntent);
    }
}
