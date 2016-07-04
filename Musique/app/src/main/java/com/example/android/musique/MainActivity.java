package com.example.android.musique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button library = (Button) findViewById(R.id.library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent libraryIntent = new Intent(MainActivity.this, libraryActivity.class);
                startActivity(libraryIntent);
            }
        });

        Button nowPlaying = (Button) findViewById(R.id.now_playing);
        nowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nowPlayingIntent = new Intent(MainActivity.this, nowPlaying.class);
                startActivity(nowPlayingIntent);
            }
        });

        Button randomSong = (Button) findViewById(R.id.random_song);
        randomSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Playing...";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
