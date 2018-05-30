package com.nerkingames.mineclicker.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by vladyslav on 11.03.18.
 */
@Dao
public interface AchievementsDao {

    @Query("SELECT * FROM AchievementsTable")
    List<AchievementsTable> getAllCounters();

    @Query("SELECT * FROM AchievementsTable WHERE id = :arch_id")
    Single<AchievementsTable> getOneCounter(String arch_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AchievementsTable... button_counter);
}
