package com.example.loginfirebase;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;







public class AboutUsActivity extends AppCompatActivity {
    FloatingActionButton menu;
    FloatingActionButton conf;
    FloatingActionButton log;
    FloatingActionButton home;
    boolean aBoolean=true;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_activity);

        TextView textView = findViewById(R.id.textViewAboutUs);
        textView.setText("Nuestro proyecto consiste de una aplicación que pone a disposición los juegos más famosos y apreciados en Internet.");
        TextView textView2 = findViewById(R.id.creadores);
        textView2.setText("App creada por:" );

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
        conf = findViewById(R.id.conf);
        log = findViewById(R.id.loguot);

        // Menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of other icons
                if (aBoolean) {
                    home.show();
                    conf.show();
                    log.show();
                    aBoolean =false;
                } else {
                    home.hide();
                    conf.hide();
                    log.hide();
                    aBoolean =true;
                }
            }
        });

            //otras activity's
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUsActivity.this, ConfigActivity.class);
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

        // Referencias
        ListView listView = findViewById(R.id.listaRef);
            // Strings
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("ChatGPT");
        itemList.add("Flaticon");
        itemList.add("Menu Flotante Tutorial");
        itemList.add("FireBase");
        itemList.add("MoureDev (streamer)");


        // Links
        ArrayList<String> linkList = new ArrayList<>();
        linkList.add("https://chat.openai.com");
        linkList.add("https://www.flaticon.es");
        linkList.add("https://www.youtube.com/watch?v=g8kQnc0yKuw");
        linkList.add("https://youtu.be/dpURgJ4HkMk");
        linkList.add("https://twitter.com/MoureDev?t=RlXS77IVOdD7u59-2ood7Q&s=09");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Open the link for the selected item
                String url = linkList.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
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

