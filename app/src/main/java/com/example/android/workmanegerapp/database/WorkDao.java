package com.example.android.workmanegerapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.workmanegerapp.entity.Work;

import java.util.List;

@Dao
public interface WorkDao {

    @Insert
    void insert(Work work);

    @Query("DELETE FROM work")
    void deleteAll();

    //This query call the databse result by alphabetic order
    @Query("SELECT * from work ORDER BY worker_name ASC")
    List<Work> getAllOrdered();

    @Query("SELECT * from work")
    List<Work> getAll();
}
