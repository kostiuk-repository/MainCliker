package com.nerkingames.mineclicker.app;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;

import com.nerkingames.mineclicker.DataBase.DataBase;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;

public class App extends Application implements HasDispatchingActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    private DataBase database;

    public static App getInstance() {
        return instance;
    }

    public static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, DataBase.class, "database")
                .allowMainThreadQueries()
                .build();
        DaggerAppComponent.builder().appModule(new AppModule(getApplicationContext())).build().injectApp(this);
        instance = this;
    }


    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    public DataBase getDatabase() {
        return database;
    }
}
