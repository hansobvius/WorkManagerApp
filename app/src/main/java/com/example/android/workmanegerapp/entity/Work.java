package com.example.android.workmanegerapp.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "work")
public class Work {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "worker_name")
    public String mString;

    @NonNull
    @ColumnInfo(name = "timedate")
    public String mDate;

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        this.mString = string;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}
