package com.example.hqng.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

     EditText heightTf, weightTf;
     TextView result, weight, height;
     Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        heightTf = (EditText) findViewById(R.id.heightTf);
        weightTf = (EditText) findViewById(R.id.weightTf);
        result = (TextView) findViewById(R.id.result);
        weight = (TextView) findViewById(R.id.weight);
        height = (TextView) findViewById(R.id.height);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmi();
            }
        });


    }


        private void bmi (){


            String weightValue = weightTf.getText().toString();
            String heightValue = heightTf.getText().toString();

            String bmiResult = "";


            if(weightValue != null && heightValue != null)
            {
                Float weightVl = Float.parseFloat(weightValue);
                Float heightVl = Float.parseFloat(heightValue)/100;

                Float bmi = (weightVl/heightVl)/heightVl;

                if(bmi>0 && bmi<18.5)
                {
                    bmiResult = "You are Underweight !!!";
                }
                else if(bmi>18.5 && bmi<24.9)
                {
                    bmiResult = "You are healthy !";
                }

                else if(bmi>25 && bmi<29.9)
                {
                    bmiResult = "You are overweight !!";
                }

                else if(bmi>30)
                {
                    bmiResult = "You are obese !!!";
                }

            }

            result.setText(bmiResult);




        }

    }

