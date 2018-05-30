package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.AchievementsButtons.SampleAchievements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladyslav on 22.03.18.
 */

public class TheEnd extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "THEEND";
    private int onMode = R.mipmap.the_end_on;
    private int offMode = R.mipmap.the_end_on;
    private int help = R.mipmap.the_end_on;
    private BaseJobController baseJobController;
    private static TheEnd instance;
    private Context context;
    private SampleAchievements sampleAchievements;

    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized TheEnd getInstance(Context context) {
        if (instance == null) {
            instance = new TheEnd(context);
        }
        return instance;
    }


    private TheEnd(Context context) {
        super(context);
        this.context = context;
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(1)
                .setStatuses(STATUS.NOTHING)
                .setMinusValueList(0)
                .setOnTouchListener(this)
                .setClickable(true)
                .setBaseJobControllerListSubscriptions()
                .setBaseJobControllerListSubscribers()
                .build();
        baseJobController.getIT().getViewContainer().removeAllViews();
        sampleAchievements = new SampleAchievements(context,R.mipmap.end30);

    }


    public View getViewScene(int subscribersCounter,int subscriptionsCounter,BaseJobController ... baseJobControllers) {
        List<BaseJobController> localList = new ArrayList<>();
        for (BaseJobController b :baseJobControllers) {
            localList.add(b);
        }
        for (int i = 0; i < subscribersCounter; i++) {
            baseJobController.setBaseJobControllerListSubscribers(localList.get(i));
        }
        for (int i = 0; i < subscriptionsCounter; i++) {
            baseJobController.setBaseJobControllerListSubscriptions(localList.get(subscribersCounter + i));
        }

        return baseJobController.getViewContainer();
    }

    public View getViewScene(){
        return baseJobController.getViewContainer();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                if(CastleBest.getInstance(context).getIT().getViewCounter()>=1) {
                    sampleAchievements.insertNewEntity("THE_END");

                }

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }


}
