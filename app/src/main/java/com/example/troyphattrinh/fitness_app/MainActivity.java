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
        if(stepNum > 100){
            Noti.showNotification(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStepText = (TextView) findViewById(R.id.step_text);
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
        mStepText.setText("0");
    }
}
