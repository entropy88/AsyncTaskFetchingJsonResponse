package com.example.entropy.asynctaskfetchingjsonresponse;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetTemperature extends AsyncTask<Void, Void, Void>{


String jsonRepsonse="";
String temp;



    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL queryUrl = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + MainActivity.userInput + "&APPID=f21a5053b9d51540759b04f263244380");

            HttpURLConnection httpURLConnection = (HttpURLConnection) queryUrl.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb= new StringBuilder();
            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                    jsonRepsonse=jsonRepsonse+line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



            JSONObject root= new JSONObject(jsonRepsonse);
            JSONObject main = new JSONObject(root.getString("main"));
            double valueT=main.getDouble("temp");
            temp=String.valueOf(valueT);


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void Void) {
        MainActivity.tvResults.setText(jsonRepsonse+" \n"+ temp);
        super.onPostExecute(Void);

    }

    //f21a5053b9d51540759b04f263244380 api key
    //http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=f21a5053b9d51540759b04f263244380
    //example query: http://api.openweathermap.org/data/2.5/weather?q=Plovdiv&APPID=f21a5053b9d51540759b04f263244380
}
