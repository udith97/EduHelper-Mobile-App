package com.example.eduhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Marks_Calculation extends AppCompatActivity {

    private EditText num1;
     private EditText num2;
     private Button cal;
     private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks__calculation);

      num1 = (EditText) findViewById(R.id.ent1);
        num2 = (EditText) findViewById(R.id.ent2);
        cal = (Button) findViewById(R.id.btncal);
        result = (TextView) findViewById(R.id.tvans);

        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int number1 = Integer.parseInt(num1.getText().toString());
                int number2 = Integer.parseInt(num2.getText().toString());
                int sum = number1+number2;
                result.setText("Answer :" + String.valueOf(sum));



            }
        });


    }
}
