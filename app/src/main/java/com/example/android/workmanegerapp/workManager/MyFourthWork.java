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

public class MyFourthWork extends Worker {

    private static final String TAG = "WORK_MANAGER";
    private static final String MSG = "FOURTH WORK MANAGER";
    private static final String PERIODIC_TIME_MSG = "PERIODIC WORK MANAGER";

    private Context mContext;
    private WorkDao mWorkDao;
    private Work mWork;
//    private MainActivity mMainActivity = new MainActivity();

    public MyFourthWork(@NonNull Context context, @NonNull WorkerParameters param){
        super(context, param);
        this.mContext = context;
//        this.mMainActivity = new MainActivity();
        this.mWork = new Work();
        WorkRoomDatabase workRoomDatabase = WorkRoomDatabase.getDatabase(this.mContext);
        mWorkDao = workRoomDatabase.workDao();
    }

    @NonNull
    @Override
    public Result doWork(){
        Log.i(TAG, MSG);
        mWork.setString(PERIODIC_TIME_MSG);
        mWork.setDate(String.valueOf(Calendar.getInstance().getTime()));
        mWorkDao.insert(mWork);
//        mMainActivity.initData(mWorkDao);
        return Result.success();
    }
}
