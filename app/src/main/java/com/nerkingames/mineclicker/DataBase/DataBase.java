package com.nerkingames.mineclicker.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by vladyslav on 11.03.18.
 */

@Database(entities = { JobTable.class, AchievementsTable.class }, version = 1)
public abstract class DataBase extends RoomDatabase {


    public abstract JobDao getJobDao();
    public abstract AchievementsDao getAchievementsDao();
}