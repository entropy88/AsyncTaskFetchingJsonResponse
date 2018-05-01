package com.example.entropy.asynctaskfetchingjsonresponse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText edtUserInput;
    Button getTBtn;
    public static TextView tvTemp;
    public static String userInput=" ";

    public static TextView tvWind;
    public static TextView tvHumidity;
    public static TextView tvDescription;


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

        getTBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                userInput=edtUserInput.getText().toString();
                GetTemperature getT= new GetTemperature ();
                getT.execute();

            }
        });

    }
}
