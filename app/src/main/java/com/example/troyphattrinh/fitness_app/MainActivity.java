package com.example.jiachenliu.stepcount_3;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements StepSensorBase.StepCallBack {
    private TextView mStepText1;
    private StepSensorBase mStepSensor;
    private TextView mStepText2;


    @Override
    public void Step(int stepNum1, int stepNum2) {
        mStepText1.setText("Steps:" + stepNum1);
        mStepText2.setText("Steps:" + stepNum2);
        int criterion = 10000;
        if(stepNum2 == criterion){
            Noti.showNotification(this);
            criterion = 0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStepText1 = (TextView) findViewById(R.id.step_text1);
        mStepText2 = (TextView) findViewById(R.id.step_text2);
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
    /*public void onClick(View v) {
        mStepText.setText("0");
    }*/
}
