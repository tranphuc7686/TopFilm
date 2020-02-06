package com.example.topfilm;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkHelper {
    public static void GetJsonData( String url, final Callback callback) {

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try  {
                    URL Url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    line = sb.toString();
                    connection.disconnect();
                    is.close();
                    sb.delete(0, sb.length());

                    return line;
                } catch (Exception e) {
                    callback.getStringJsonError( e.getMessage());
                }
                return "";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                callback.getStringJson(s);
            }
        }.execute(url);


    }
    public interface Callback {
        void getStringJson(String json);
        void getStringJsonError(String msg);
    }
}
