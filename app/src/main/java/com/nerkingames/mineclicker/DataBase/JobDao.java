package com.nerkingames.mineclicker.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by vladyslav on 11.03.18.
 */
@Dao
public interface JobDao {

    @Query("SELECT * FROM JobTable")
    List<JobTable> getAllCounters();

    @Query("SELECT * FROM JobTable WHERE id = :button_id")
    Single<JobTable> getOneCounter(String button_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(JobTable... button_counter);


    @Update
    void update(JobTable... button_counter);

    @Delete
    void delete(JobTable... button_counter);


}
