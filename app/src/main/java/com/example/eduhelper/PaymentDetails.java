package com.example.eduhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentDetails extends AppCompatActivity {

    EditText semester_amount, current_year , current_sem;
    Button calculate;
    TextView totalAmount, RemainAmount, payPer3Month, payPeryear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        //input fields

        semester_amount = findViewById(R.id.semfee);
        current_year = findViewById(R.id.currentyear);
        current_sem = findViewById(R.id.semcurrent);

        // text view

        totalAmount = findViewById(R.id.totpayment);
        RemainAmount = findViewById(R.id.amountR);
        payPeryear = findViewById(R.id.payforyear);
        payPer3Month = findViewById(R.id.per3month);

        //Button
        calculate = findViewById(R.id.semcalsub);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        //inputs
         double seminput = Double.parseDouble(semester_amount.getText().toString());
         double currenty = Double.parseDouble(current_year.getText().toString());
         double currents = Double.parseDouble(current_sem.getText().toString());







                // Remaining Amount
                if (currenty == 1 && currents == 1){
                    double Remaining11 = (seminput * 8);
                    RemainAmount.setText(String.valueOf(Remaining11));
                }
                else if (currenty == 1 && currents == 2){
                    double Remaining12 = (seminput * 7);
                    RemainAmount.setText(String.valueOf(Remaining12));

                }
                else if (currenty == 2 && currents == 1){
                    double Remaining21 = (seminput * 6);
                    RemainAmount.setText(String.valueOf(Remaining21));

                }
                else if (currenty == 2 && currents == 2){
                    double Remaining22 = (seminput * 5);
                    RemainAmount.setText(String.valueOf(Remaining22));

                }
                else if (currenty == 3 && currents == 1){
                    double Remaining31 = (seminput * 4);
                    RemainAmount.setText(String.valueOf(Remaining31));

                }
                else if (currenty == 3 && currents == 2){
                    double Remaining32 = (seminput * 3);
                    RemainAmount.setText(String.valueOf(Remaining32));

                }
                else if (currenty == 4 && currents == 1){
                    double Remaining41 = (seminput * 2);
                    RemainAmount.setText(String.valueOf(Remaining41));

                }
                else{
                    double Remaining42 = seminput;
                    RemainAmount.setText(String.valueOf(Remaining42));
                }


                // full course fee

                double fullCoursefee  = (seminput * 8.0);
                totalAmount.setText(String.valueOf(fullCoursefee));

                // course fee per year

                double feeperY = (seminput * 2.0);
                payPeryear.setText(String.valueOf(feeperY));

                // course fee for 3 months

                double calfee3 = (seminput / 2.0);
                payPer3Month.setText(String.valueOf(calfee3));

                Toast.makeText(PaymentDetails.this, "Calculated", Toast.LENGTH_SHORT);


            }
        });








    }


}
