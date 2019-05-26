package com.example.android.workmanegerapp.dataManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.workmanegerapp.adapter.WorkAdapter;
import com.example.android.workmanegerapp.database.WorkDao;
import com.example.android.workmanegerapp.database.WorkRoomDatabase;
import com.example.android.workmanegerapp.entity.Work;

import java.util.ArrayList;
import java.util.List;

public class RoomManager implements DataManager{

    private WorkDao mWorkDao;
    private Context mContext;
    private WorkAdapter mWorkAdapter;
    private RecyclerView mRecyclerView;
    private List<Work> mWorkList = new ArrayList<>();

    public RoomManager(Context context, RecyclerView recyclerView){
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mWorkAdapter = new WorkAdapter(context, mWorkList);
        if(this.mContext != null){
            WorkRoomDatabase workRoomDatabase = WorkRoomDatabase.getDatabase(this.mContext);
            this.mWorkDao = workRoomDatabase.workDao();
        }
    }

    @Override
    public void initData() {
//        new initAsyncTask(this.mWorkDao, this.mWorkList).execute();
        AsyncTask<Void, Void, List<Work>> asyncTask = new initAsyncTask(this.mWorkDao, this.mWorkList);
        asyncTask.execute();
    }

    @Override
    public void delete() {
        AsyncTask<Void, Void, Void> asyncTask = new deleteAsyncTask(this.mWorkDao);
        asyncTask.execute();
    }

    @Override
    public boolean getDatabaseListener(){
        AsyncTask<WorkDao, Void, Boolean> databaseListener = new checkDatabaseAsyncTask();
        return databaseListener.execute(this.mWorkDao) != null;
    }

    @SuppressLint("StaticFieldLeak")
    private class initAsyncTask extends AsyncTask<Void, Void, List<Work>> {

        private WorkDao mWorkDao;
        private List<Work> worksList;

        initAsyncTask(WorkDao dao, List<Work> works){
            mWorkDao = dao;
            worksList = works;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Work> doInBackground(Void... voids) {
            worksList = mWorkDao.getAll();
            return worksList;
        }

        @Override
        protected void onPostExecute(List<Work> workList) {
            mWorkAdapter = new WorkAdapter(mContext, workList);
            mRecyclerView.setAdapter(mWorkAdapter);
            super.onPostExecute(workList);

        }
    }

    @SuppressLint("StaticFieldLeak")
    private class deleteAsyncTask extends AsyncTask<Void, Void, Void>{

        private WorkDao workDao;

        deleteAsyncTask(WorkDao dao){
            this.workDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            workDao.deleteAll();
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class checkDatabaseAsyncTask extends AsyncTask<WorkDao, Void, Boolean>{

        @Override
        protected Boolean doInBackground(WorkDao... workDaos) {
            WorkDao workDao = workDaos[0];
            return !workDao.getAll().isEmpty();
        }
    }
}
