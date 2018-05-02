package com.example.entropy.asynctaskfetchingjsonresponse;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    EditText edtUserInput;
    Button getTBtn;
    //get coordinates and make them accessible to GetTemperature.class
    public static String wLongitude="";
    public static String wLatitude="";
    public GoogleApiClient mGoogleApliClient;
    public Location mLastLocation;

    public static TextView tvTemp;
    public static String userInput=" ";

    public static TextView tvWind;
    public static TextView tvHumidity;
    public static TextView tvDescription;

    public static TextView tvLocation;
    public static TextView tvCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserInput= (EditText)findViewById(R.id.edt_city);
        getTBtn= (Button)findViewById(R.id.btn_get_t);
        tvTemp=(TextView) findViewById(R.id.tv_temperature);
        tvWind=(TextView) findViewById(R.id.tv_windspeed);
        tvHumidity=(TextView) findViewById(R.id.tv_humidity);
        tvDescription=(TextView) findViewById(R.id.tv_description);
        tvLocation=(TextView) findViewById(R.id.tv_location);
        tvCoordinates=(TextView)findViewById(R.id.tv_coordinates);
        buildGoogleApiClient();


        getTBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                userInput=edtUserInput.getText().toString();
                GetTemperature getT= new GetTemperature ();
                getT.execute();

            }
        });

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApliClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApliClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApliClient);
        if (mLastLocation!=null) {
            wLongitude=(String.valueOf(mLastLocation.getLongitude()));
            wLatitude=(String.valueOf(mLastLocation.getLatitude()));
            tvCoordinates.setText(wLatitude+" "+ wLongitude);
            GetWeatherByCoordinates getWeatherByCoordinates= new GetWeatherByCoordinates();
            getWeatherByCoordinates.execute();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApliClient != null) {
            mGoogleApliClient.disconnect();
        }
    }
}
