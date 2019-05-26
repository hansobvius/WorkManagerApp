package com.example.android.workmanegerapp.workManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.android.workmanegerapp.database.WorkDao;
import com.example.android.workmanegerapp.database.WorkRoomDatabase;
import com.example.android.workmanegerapp.entity.Work;

import java.util.Calendar;

public class MySecondWork extends Worker{

    private static final String TAG =  "WORK_MANAGER";
    private static final String MSG = "SECOND WORK MANAGER";

    private WorkDao mWorkDao;
    private Work work = new Work();
    private Context mContext;

    public MySecondWork(@NonNull Context context, WorkerParameters params){
        super(context, params);
        this.mContext = context;
        WorkRoomDatabase workRoomDatabase = WorkRoomDatabase.getDatabase(mContext);
        mWorkDao = workRoomDatabase.workDao();
    }

    @NonNull
    @Override
    public Result doWork(){
        Log.i(TAG, MSG);
        work.setString(MSG);
        work.setDate(String.valueOf(Calendar.getInstance().getTime()));
        mWorkDao.insert(work);
        return Result.success();
    }
}
