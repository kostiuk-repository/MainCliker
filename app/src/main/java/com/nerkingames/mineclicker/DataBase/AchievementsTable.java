package com.nerkingames.mineclicker.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by vladyslav on 11.03.18.
 */
@Entity
public class AchievementsTable {

    @NonNull
    @PrimaryKey
    private String id;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }


}
