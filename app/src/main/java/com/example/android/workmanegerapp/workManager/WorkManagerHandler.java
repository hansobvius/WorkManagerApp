package com.example.android.workmanegerapp.workManager;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static androidx.work.PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;

public class WorkManagerHandler {

    private static final int INIT_PERIODIC_COUNTER = 1;

    private WorkManager mWorkManager;

    public WorkManagerHandler(WorkManager workManager){
        this.mWorkManager = workManager;
    }

    public void startWorkManager(){

        OneTimeWorkRequest workOne = new OneTimeWorkRequest.Builder(MyWork.class)
                .setConstraints(chargingConstraint())
                .build();

        OneTimeWorkRequest workTwo = new OneTimeWorkRequest.Builder(MySecondWork.class)
                .setConstraints(networkConstraint())
                .build();

        OneTimeWorkRequest workThree = new OneTimeWorkRequest.Builder(MyThirdWork.class)
                .setConstraints(chargingConstraint())
                .build();

        /*For one work only*/
        //OneTimeWorkRequest oneWork = workTwo;
        //mWorkManager.enqueue(oneWork);

        /*request two or more works*/
        //WorkContinuation continuation = WorkManager.getInstance().beginWith(workOne);
        //continuation.then(workTwo).enqueue();

        /*To call tasks in parallel*/
        WorkManager.getInstance().enqueue(Arrays.asList(
                workOne,
                workTwo,
                workThree
        ));
    }

    private void initPeriodicRequest(){
        PeriodicWorkRequest periodicRequest = new PeriodicWorkRequest.Builder(
                MyFourthWork.class,
                MIN_PERIODIC_INTERVAL_MILLIS,
                TimeUnit.MINUTES)
                .setConstraints(constraints())
                .build();
        mWorkManager.enqueue(periodicRequest);
    }

    /*Set the constraints in one single call*/
    private Constraints constraints(){
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        return constraints;
    }

    /*It's possible envoke diferents constraints in differents method's, for each WormManager instace*/
    private Constraints chargingConstraint(){
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .build();
        return constraints;
    }

    private Constraints networkConstraint(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        return constraints;
    }

}
