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
    public static TextView tvResults;
    public static String userInput=" ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserInput= (EditText)findViewById(R.id.edt_city);
        getTBtn= (Button)findViewById(R.id.btn_get_t);
        tvResults=(TextView) findViewById(R.id.tv_temperature);



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
