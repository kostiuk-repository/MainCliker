package com.nerkingames.mineclicker.Views.AchievementsButtons;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.DataBase.AchievementsDao;
import com.nerkingames.mineclicker.DataBase.AchievementsTable;
import com.nerkingames.mineclicker.DataBase.DataBase;
import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.app.App;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vladyslav on 15.03.18.
 */

public class SampleAchievements extends FrameLayout {


    private BitmapDrawable bitmapDrawable;
    private int mWidth;
    private int mHeight;
    private DataBase dataBase;
    private AchievementsDao achievementsDao;
    private AchievementsTable achievements_table;

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    RelativeLayout relativeLayout;






    public SampleAchievements(@NonNull Context context, int resourcesId) {
        super(context);
        dataBase = App.getInstance().getDatabase();
        achievementsDao = dataBase.getAchievementsDao();
        init(resourcesId);
        relativeLayout = GameActivity.relativeLayout2;
    }

    public int getmWidth() {
        return mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    private void init(int resId) {
        bitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),resId));
        mWidth = bitmapDrawable.getBitmap().getWidth();
        mHeight = bitmapDrawable.getBitmap().getHeight();
        this.setBackground(bitmapDrawable);
        this.setLayoutParams(new FrameLayout.LayoutParams(mWidth, mHeight));
    }

    public void insertNewEntity(String str) {
        achievementsDao.getOneCounter(str).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<AchievementsTable>() {
                    @Override
                    public void onSuccess(AchievementsTable achievementsTable) {
                        achievements_table = achievementsTable;
                    }

                    @Override
                    public void onError(Throwable e) {
                        achievements_table = new AchievementsTable();
                        achievements_table.setId(str);
                        achievementsDao.insert(achievements_table);
                    }
                });
    }


}
