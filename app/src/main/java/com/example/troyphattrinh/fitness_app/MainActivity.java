package com.example.jiachenliu.stepcount_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements StepSensorBase.StepCallBack {
    private TextView mStepText;
    private StepSensorBase mStepSensor;


    @Override
    public void Step(int stepNum) {
        mStepText.setText("Steps:" + stepNum);
        int criterion = 10000;
        if(stepNum == criterion){
            Noti.showNotification(this);
            criterion = 0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStepText = (TextView) findViewById(R.id.step_text1);
        mStepSensor = new StepSensorPedometer(this, this);
        if (!mStepSensor.registerStep()) {
            Toast.makeText(this, "Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStepSensor.unregisterStep();
    }
    public void onClick(View v) {
        StepSensorPedometer.setIsRun(true);
        mStepText.setText("Steps: 0");
    }
}
