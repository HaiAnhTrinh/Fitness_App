package com.example.troyphattrinh.fitness_app;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class BmiActivity extends Fragment {

    //initialization 
    EditText heightTf, weightTf;
    TextView result, weight, height;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_bmi, null);
    }

    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //assign value for xml objects
        heightTf = view.findViewById(R.id.heightTf);
        weightTf = view.findViewById(R.id.weightTf);
        result = view.findViewById(R.id.result);
        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);
        btn = view.findViewById(R.id.btn);

        //assign action for a button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmi();
            }
        });
    }


    private void bmi (){

        String weightValue = weightTf.getText().toString(); //return a string representation of an object
        String heightValue = heightTf.getText().toString(); //return a string representation of an object
        Float weightVl = Float.parseFloat(weightValue); //return a float value
        Float heightVl = Float.parseFloat(heightValue)/100; //return a float value

        String bmiResult = ""; //initialization


        if(weightValue != null && heightValue != null
                && weightVl > 0 && heightVl > 0 )
        {

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

            result.setText(bmiResult); //return a string value for bmiResult
        }
        else
            {
                result.setText("Invalid input"); //return a warning message for invalid input value
            }

    }

}
