package com.example.jiachenliu.stepcount_3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;

import java.text.SimpleDateFormat;


public class StepSensorPedometer extends StepSensorBase {
    private final String TAG = "StepSensorPedometer";
    private int liveStep = 0;
    private int sensorMode = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("hh-ss");
    String time = sdf.format(new java.util.Date());
    public StepSensorPedometer(Context context, StepCallBack stepCallBack) {
        super(context, stepCallBack);
    }

    @Override
    protected void registerStepListener() {
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensorManager.registerListener(this, detectorSensor, SensorManager.SENSOR_DELAY_GAME)) {
            isAvailable = true;
            sensorMode = 0;
            Log.i(TAG, "Detector Available！");
        } else if (sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_GAME)) {
            isAvailable = true;
            sensorMode = 1;
            Log.i(TAG, "Counter Available！");
        } else {
            isAvailable = false;
            Log.i(TAG, "Unavailable");
        }
    }

    @Override
    public void unregisterStep() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        liveStep = (int) event.values[0];
        if (sensorMode == 0) {
            Log.i(TAG, "Detector Steps："+liveStep);
            StepSensorBase.CURRENT_STEP2 += liveStep;
            if(time.equals("00-00")){
                StepSensorBase.CURRENT_STEP2 = 0;
            }
        } else if (sensorMode == 1) {
            Log.i(TAG, "Counter Steps："+liveStep);
            StepSensorBase.CURRENT_STEP1 = liveStep;
        }
        stepCallBack.Step(StepSensorBase.CURRENT_STEP1, StepSensorBase.CURRENT_STEP2);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
