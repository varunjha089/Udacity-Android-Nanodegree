package com.example.android.getnews;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Gets News using Guardian's API
 */
public class UpdateNews extends AsyncTask<Void, Void, ArrayList<ResultStrs>> {

    private final String LOG_TAG = UpdateNews.class.getSimpleName();

    public ArrayList<ResultStrs> getFetchDataFromJson(String fetchJsonStr) throws JSONException {

        JSONObject fetchDataJson = new JSONObject(fetchJsonStr);
        JSONObject response = fetchDataJson.getJSONObject("response");
        JSONArray responseResults = response.getJSONArray("results");

        ArrayList<ResultStrs> resultStr = new ArrayList<ResultStrs>();

        for(int i = 0; i < responseResults.length(); i++) {
            JSONObject responseResultsJSON = responseResults.getJSONObject(i);
            String title = responseResultsJSON.getString("webTitle");
            String sectionName = responseResultsJSON.getString("sectionName");
            String webUrl = responseResultsJSON.getString("webUrl");
            resultStr.add(new ResultStrs(title, sectionName, webUrl));
        }
        return resultStr;
    }

    @Override
    protected ArrayList<ResultStrs> doInBackground(Void... voids) {

        // Will contain raw JSON response as a String
        String fetchJsonStr = null;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            // Construct the URL
            final String NEWS_BASE_URL =
                    "http://content.guardianapis.com/search?";
            final String KEY = "api-key";

            Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
                    .appendQueryParameter(KEY, "test")
                    .build();

            URL url = new URL(builtUri.toString());

            // Create the request to Guardian API, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            fetchJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getFetchDataFromJson(fetchJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }
}
