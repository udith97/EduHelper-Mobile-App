package com.example.eduhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Marks_Calculation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

     private EditText num1;
     private EditText num2;
     private Button cal;
     private TextView result, goToGpaCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks__calculation);

      Spinner spinner = findViewById(R.id.spinner);
      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.modules,android.R.layout.simple_spinner_item);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(adapter);
      spinner.setOnItemSelectedListener(this);



      num1 = (EditText) findViewById(R.id.ent1);
        num2 = (EditText) findViewById(R.id.ent2);
        cal = (Button) findViewById(R.id.btncal);
        result = (TextView) findViewById(R.id.tvans);
        goToGpaCal = findViewById(R.id.gpaCalc);

        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                double number1 = Double.parseDouble(num1.getText().toString());
                double number2 = Double.parseDouble(num2.getText().toString());
                double sum = 45 - (number1+number2);
                result.setText("Answer :" + String.valueOf(sum));



            }
        });

        goToGpaCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GPACalculator.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
