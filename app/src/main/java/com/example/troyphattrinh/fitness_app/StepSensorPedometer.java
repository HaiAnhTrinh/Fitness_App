package com.example.troyphattrinh.fitness_app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;


public class StepSensorPedometer extends StepSensorBase {
    private final String TAG = "StepSensorPedometer";
    private int liveStep = 0;
    //the type of the sensor
    private int sensorMode = 0;
    //the initial value
    private int b = 0;
    //the switch
    static boolean isRun = true;
    public StepSensorPedometer(Context context, StepCallBack stepCallBack) {
        super(context, stepCallBack);
    }

    //choose the suitable sensor
    @Override
    protected void registerStepListener() {
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensorManager.registerListener(this, detectorSensor, SensorManager.SENSOR_DELAY_GAME)) {
            isAvailable = true;
            sensorMode = 0;
            Log.i(TAG, "Detector Available！");
        }
        else if (sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_GAME)) {
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
        //get the value
        liveStep = (int) event.values[0];
        //save the value
        if(isRun){
            b = liveStep;
            isRun = false;
        }
        //choose the sensor
        if (sensorMode == 0) {
            Log.i(TAG, "Detector Steps："+liveStep);
            StepSensorBase.CURRENT_STEP += liveStep;
        } else if (sensorMode == 1) {
            Log.i(TAG, "Counter Steps："+liveStep);
            StepSensorBase.CURRENT_STEP = liveStep - b;
        }
        stepCallBack.Step(StepSensorBase.CURRENT_STEP);
    }

    //reset the switch
    public static void setIsRun(boolean c){
        isRun = c;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
