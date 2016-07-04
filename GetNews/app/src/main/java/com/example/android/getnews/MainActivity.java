package com.example.android.getnews;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int REQUEST_INTERNET_PERMISSION = 1;
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_PERMISSION);
        } else {
            fetchNews();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final int REQUEST_INTERNET_PERMISSION = 1;
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_PERMISSION);
        } else {
            //noinspection SimplifiableIfStatement
            if (id == R.id.action_refresh) {
                fetchNews();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    // Updates News
    public void fetchNews() {
        UpdateNews fetch = new UpdateNews();
        final AsyncTask<Void, Void, ArrayList<ResultStrs>> getJsonData = fetch.execute();
        try {
            ListView lv = (ListView) findViewById(R.id.list);
            String[] setContent = new String[getJsonData.get().size()];
            for (int i = 0; i < getJsonData.get().size(); i++) {
                setContent[i] = String.valueOf(getJsonData.get().get(i).getTitle()) + "\n" +
                        "Section: " + String.valueOf(getJsonData.get().get(i).getSection());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    MainActivity.this,
                    R.layout.list_item,
                    R.id.text_1,
                    setContent);
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Uri uri = null;
                    try {
                        uri = Uri.parse(getJsonData.get().get(i).getWebUrl());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
