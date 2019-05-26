package com.example.android.workmanegerapp;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.android.workmanegerapp.adapter.WorkAdapter;
import com.example.android.workmanegerapp.dataManager.RoomManager;
import com.example.android.workmanegerapp.database.WorkDao;
import com.example.android.workmanegerapp.database.WorkRoomDatabase;
import com.example.android.workmanegerapp.entity.Work;
import com.example.android.workmanegerapp.workManager.MyFourthWork;
import com.example.android.workmanegerapp.workManager.MySecondWork;
import com.example.android.workmanegerapp.workManager.MyThirdWork;
import com.example.android.workmanegerapp.workManager.MyWork;
import com.example.android.workmanegerapp.workManager.WorkManagerHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static androidx.work.OneTimeWorkRequest.from;
import static androidx.work.PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;

public class MainActivity extends AppCompatActivity {

    private WorkManager mWorkManager;
    private RecyclerView mRecyclerView;
    private Button mAddButton;
    private Button mDeleteButton;
    private RoomManager mRoomManager;
    private WorkManagerHandler mWorkManagerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWorkManager = WorkManager.getInstance();
        mWorkManagerHandler = new WorkManagerHandler(mWorkManager);

        mRecyclerView = findViewById(R.id.main_recycler);

        mRoomManager = new RoomManager(MainActivity.this, mRecyclerView);

        mAddButton = findViewById(R.id.add_room);
        mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mWorkManagerHandler.startWorkManager();
                initDataView();
            }}
        );

        mDeleteButton = findViewById(R.id.delete_room);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRoomManager.delete();
                initDataView();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        initDataView();
    }

    private void initDataView(){
        if(this.mRoomManager.getDatabaseListener()){
            mRoomManager.initData();
        }
    }
}
