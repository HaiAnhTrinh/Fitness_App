package com.example.troyphattrinh.fitness_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FootstepActivity extends Fragment implements StepSensorBase.StepCallBack {
    private TextView mStepText;
    private StepSensorBase mStepSensor;
    private Button clearBtn, recordBtn;
    private StepSensorPedometer stepSensorPedometer;
    private boolean isRun = true;
    private DatabaseHelper dbh;
    static String email;
    private int stepCounter;
    private static final String TAG = "Footstep";

    @Override
    public void Step(int stepNum) {
        mStepText.setText("Steps:" + stepNum);
        stepCounter = stepNum;
        int criterion = 10000;
        if(stepNum == criterion){
            Noti.showNotification(this);
            criterion = 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_footstep, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbh = new DatabaseHelper(this.getContext());
        email = this.getArguments().getString("EMAIL_KEY");

        stepSensorPedometer = new StepSensorPedometer(this.getContext(), new StepSensorBase.StepCallBack() {
            @Override
            public void Step(int stepNum) {

            }
        });

        recordBtn = view.findViewById(R.id.recordBtn);

        clearBtn = view.findViewById(R.id.clearBtn);
        if(isRun) {
            StepSensorPedometer.setIsRun(true);
            isRun = false;
        }

        mStepText = view.findViewById(R.id.step_text1);
        mStepSensor = new StepSensorPedometer(getContext(), this);
        if (!mStepSensor.registerStep()) {
            Toast.makeText(getContext(), "Unavailable", Toast.LENGTH_SHORT).show();
        }

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbh.addSteps(stepCounter, email);
                Log.i(TAG, "ACTION RECORD");

            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                StepSensorPedometer.setIsRun(true);
                mStepText.setText("Steps:0");
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mStepSensor.unregisterStep();
    }

}
