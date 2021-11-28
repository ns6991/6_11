package com.example.a6_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button nextButton;
    Switch aSwitch;
    EditText a1ET , dET;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = (Button)findViewById(R.id.button);
        aSwitch = (Switch)findViewById(R.id.switch1);
        a1ET = (EditText)findViewById(R.id.edA1);
        dET = (EditText)findViewById(R.id.edD);

    }

    public void clickNext(View view) {
        Intent si = new Intent(this, results.class);
        if(aSwitch.isChecked()) type=1;
        else type=0;

//check valid input and send the elements
        if(a1ET.getText().toString().isEmpty() || dET.getText().toString().isEmpty())
            Toast.makeText(this, "Invalid value, please verify that the value you entered is a valid, integer or decimal number", Toast.LENGTH_SHORT).show();
        else{
            si.putExtra("type",type);
            si.putExtra("x1", Double.parseDouble(a1ET.getText().toString()));
            si.putExtra("d",Double.parseDouble(dET.getText().toString()));
            startActivityForResult(si,1);
        }

    }


}