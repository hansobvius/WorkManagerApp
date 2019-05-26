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

public class MyWork extends Worker {

    private static final String TAG = "WORK_MANAGER";
    private static final String MSG = "FIRST WORK MANAGER";

    private Context mContext;
    private WorkDao mWorkDao;
    private Work work = new Work();

    public MyWork(@NonNull Context context, @NonNull WorkerParameters params){
        super(context, params);
        this.mContext = context;
        WorkRoomDatabase workRoomDatabase = WorkRoomDatabase.getDatabase(this.mContext);
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
