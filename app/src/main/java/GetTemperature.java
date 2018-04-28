import android.os.AsyncTask;
import android.widget.Toast;

import com.example.entropy.asynctaskfetchingjsonresponse.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetTemperature extends AsyncTask <Void, Void, Void>{

String jsonRepsonse;
String temp;
    @Override
    protected Void doInBackground(Void... voids) {
        URL queryUrl = null;
        try {
            queryUrl = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + MainActivity.userInput + "&APPID=f21a5053b9d51540759b04f263244380");
        } catch (MalformedURLException e) {
           MainActivity.tvResults.setText("Malformed url");
            e.printStackTrace();
        }
//            URL url= new URL ("https://world.openfoodfacts.org/api/v0/product/737628064502.json");
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) queryUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line="";
                while (line != null) {
                    try {
                        line = bufferedReader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
              jsonRepsonse = jsonRepsonse + line;

        try {
            JSONObject root = new JSONObject(jsonRepsonse);
            JSONObject main = root.getJSONObject("main");
       double valueT=main.getDouble("temp");
       temp=String.valueOf(valueT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void Void) {
        super.onPostExecute(Void);
        MainActivity.tvResults.setText(temp);
    }

    //f21a5053b9d51540759b04f263244380 api key
    //http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=f21a5053b9d51540759b04f263244380
    //example query: http://api.openweathermap.org/data/2.5/weather?q=Plovdiv&APPID=f21a5053b9d51540759b04f263244380
}
