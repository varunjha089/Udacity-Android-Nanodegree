package com.example.android.musique;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class LibraryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        final int REQUEST_PERMISSION_SD_CARD = 1;
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_SD_CARD);
        } else {
            listAudioFiles();
        }
    }

    public void listAudioFiles() {

        ListView audioView = (ListView) findViewById(R.id.songView);

        ArrayList<String> audioList = new ArrayList<>();
        String[] proj = { MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME };

        Cursor audioCursor = getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        proj,
                        null, null, null);

        if(audioCursor != null) {
            if(audioCursor.moveToFirst()){
                do {
                    int audioIndex = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);

                    audioList.add(audioCursor.getString(audioIndex));
                } while(audioCursor.moveToNext());
            }
        }
        audioCursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1, audioList);
        audioView.setAdapter(adapter);
    }
}
