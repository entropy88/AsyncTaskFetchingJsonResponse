package com.example.entropy.asynctaskfetchingjsonresponse;

import android.os.AsyncTask;
import android.view.View;

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
String temp="";
String description="";
String windSpeed="";
String humidity="";
String city="";
String gtLong;
String gtLat;




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


//get T
            JSONObject root= new JSONObject(jsonRepsonse);
            JSONObject main = new JSONObject(root.getString("main"));
            double valueT=main.getDouble("temp");
            valueT=valueT-273.15;
            int temperature=(int)Math.round (valueT);
            temp=String.valueOf(temperature)+"\u00b0"+ " C";

            //get location name
            city=root.getString("name");

            //get cordinates
JSONObject coord= new JSONObject(root.getString("coord"));
gtLong=coord.getString("lon");
gtLat=coord.getString("lat");


//get description
            JSONArray weather= root.getJSONArray("weather");
            JSONObject weatherObject= weather.getJSONObject(0);
            description=weatherObject.getString("description");

//get humidity
            double humidityPerc=main.getDouble("humidity");
            humidity=String.valueOf(humidityPerc);

            //get wind speed
            JSONObject wind= new JSONObject(root.getString("wind"));
            double speedOfWind=wind.getDouble("speed");
            int windInt= (int) Math.round(speedOfWind);
            windSpeed=String.valueOf(windInt)+" m/s";

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
        MainActivity.tvTemp.setText(temp);

        MainActivity.tvHumidity.setText(humidity+ "%");
        MainActivity.tvWind.setText(windSpeed);
        MainActivity.tvDescription.setText(description);
        MainActivity.tvLocation.setText(city);
        MainActivity.tvCoordinates.setText(gtLong+ " "+gtLat);
        super.onPostExecute(Void);

    }

    //f21a5053b9d51540759b04f263244380 api key
    //http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=f21a5053b9d51540759b04f263244380

    //example query: http://api.openweathermap.org/data/2.5/weather?q=Plovdiv&APPID=f21a5053b9d51540759b04f263244380
}
