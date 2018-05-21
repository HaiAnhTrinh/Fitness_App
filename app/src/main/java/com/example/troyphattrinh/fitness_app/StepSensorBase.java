package com.example.jiachenliu.stepcount_3;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class StepSensorBase implements SensorEventListener {
    private Context context;
    protected StepCallBack stepCallBack;
    protected SensorManager sensorManager;
    protected static int CURRENT_STEP1 = 0;
    protected static int CURRENT_STEP2 = 0;
    protected boolean isAvailable = false;

    public StepSensorBase(Context context, StepCallBack stepCallBack) {
        this.context = context;
        this.stepCallBack = stepCallBack;
    }

    public interface StepCallBack {
        void Step(int stepNum1, int stepNum2);
    }

    public boolean registerStep() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            sensorManager = null;
        }
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        registerStepListener();
        return isAvailable;
    }

    protected abstract void registerStepListener();

    public abstract void unregisterStep();
}

