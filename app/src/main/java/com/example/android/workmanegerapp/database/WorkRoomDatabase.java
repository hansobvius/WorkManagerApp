package com.example.android.workmanegerapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android.workmanegerapp.entity.Work;

@Database(entities = {Work.class}, version  = 1)
public abstract class WorkRoomDatabase extends RoomDatabase {

    private static final String WORKER_DATABASE = "WORKER_DATABASE";

    public abstract WorkDao workDao();

    private static volatile WorkRoomDatabase INSTANCE;

    public static WorkRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WorkRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE  = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WorkRoomDatabase.class,
                            WORKER_DATABASE)
                                .addCallback(sRoomDatabaseCallback)
                                .fallbackToDestructiveMigration()
                                .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
