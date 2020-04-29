package com.example.eduhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GPACalculator extends AppCompatActivity {

    private EditText M1gp, M1ch, M2gp, M2ch, M3gp, M3ch, M4gp, M4ch;
    private TextView TotGp, TotCh, GPA;
    private Button Calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_a_calculator);


        M1gp = findViewById(R.id.m1gp);
        M1ch = findViewById(R.id.m1ch);

        M2gp = findViewById(R.id.m2gp);
        M2ch = findViewById(R.id.m2ch);

        M3gp = findViewById(R.id.m3gp);
        M3ch = findViewById(R.id.m3ch);

        M4gp = findViewById(R.id.m4gp);
        M4ch = findViewById(R.id.m4ch);

        TotGp = findViewById(R.id.totgp);
        TotCh = findViewById(R.id.totch);
        GPA = findViewById(R.id.gpa);

        Calculate = findViewById(R.id.calc);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double M1GP = Double.parseDouble(M1gp.getText().toString());
                double M1CH = Double.parseDouble(M1ch.getText().toString());
                double M2GP = Double.parseDouble(M2gp.getText().toString());
                double M2CH = Double.parseDouble(M2ch.getText().toString());
                double M3GP = Double.parseDouble(M3gp.getText().toString());
                double M3CH = Double.parseDouble(M3ch.getText().toString());
                double M4GP = Double.parseDouble(M4gp.getText().toString());
                double M4CH = Double.parseDouble(M4ch.getText().toString());

                double calcTotCH = (M1CH+M2CH+M3CH+M4CH);
                TotCh.setText(String.valueOf(calcTotCH));

                double calcTotGP = (M1CH*M1GP)+(M2CH*M2GP)+(M3CH*M3GP)+(M4CH*M4GP);
                TotGp.setText(String.valueOf(calcTotGP));

                double calcGPA = calcTotGP/calcTotCH;
                GPA.setText(String.valueOf(calcGPA));

            }
        });
    }
}
