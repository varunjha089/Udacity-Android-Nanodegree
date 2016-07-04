package com.example.android.musique;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NowPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        final Button playPause = (Button) findViewById(R.id.play_music);
        playPause.setOnClickListener(new View.OnClickListener() {
            boolean flag = false;
            @Override
            public void onClick(View view) {
                if(!flag) {
                    playPause.setText(String.valueOf("Pause"));
                    flag = true;
                } else {
                    playPause.setText(String.valueOf("Play"));
                    flag = false;
                }
            }
        });
    }
}
