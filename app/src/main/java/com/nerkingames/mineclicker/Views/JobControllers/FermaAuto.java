package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladyslav on 21.03.18.
 */

public class FermaAuto extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "FermaAuto";
    private int onMode = R.mipmap.ferma01_on;
    private int offMode = R.mipmap.ferma01_off;
    private int help = R.mipmap.ferma001_craft;
    private BaseJobController baseJobController;
    private static FermaAuto instance;
    private RelativeLayout jobContainer;
    private boolean onetime = true;
    private Context context;

    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized FermaAuto getInstance(Context context) {
        if (instance == null) {
            instance = new FermaAuto(context);
        }
        return instance;
    }


    private FermaAuto(Context context) {
        super(context);
        this.context = context;
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(1,8,1,8,0,0,0,0,0,0)
                .setStatuses(STATUS.NOTHING,STATUS.MINUS,STATUS.MINUS,STATUS.MINUS,STATUS.NOTHING
                    ,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING)
                .setMinusValueList(0,-8,-1,-8,0,0,0,0,0,0)
                .setOnTouchListener(this)
                .setClickable(true)
                .setBaseJobControllerListSubscriptions()
                .setBaseJobControllerListSubscribers()
                .build();
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

    public void changeStateIfClick(){
        baseJobController.changeStateIfClick(1,1);
        baseJobController.lockUnlockFunction(baseJobController);
        reportState();
    }

    public void reportState(){
        baseJobController.avoidStateInner();
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                jobContainer = (RelativeLayout) baseJobController.getIT().getViewContainer().getParent()
                        .getParent()
                        .getParent()
                        .getParent();
                break;
            case MotionEvent.ACTION_MOVE:
                long eventDuration = event.getEventTime() - event.getDownTime();
                if(eventDuration > 500 && onetime){
                    onetime = false;
                    jobContainer.addView(baseJobController.getHelp());
                }
                break;
            case MotionEvent.ACTION_UP:
                onetime = true;
                jobContainer.removeView(baseJobController.getHelp());
                if(VillagerFermer.getInstance(context).getIT().getViewCounter()>=1
                        && WheatSeeds.getInstance(context).getIT().getViewCounter()>=8
                        && Water.getInstance(context).getIT().getViewCounter()>=1
                        && Dirt.getInstance(context).getIT().getViewCounter()>=8) {
                    changeStateIfClick();
                    FermaWithGrass.getInstance(context).getIT().setViewCounter(baseJobController.getViewCounter());
                    FermaWithGrass.getInstance(context).getIT().lockUnlockFunction(FermaWithGrass.getInstance(context).getIT());
                    FermaWithGrass.getInstance(context).getIT().resolveTextViewPosition();
                    FermaWithGrass.getInstance(context).getIT().changeStateIfCheck();
                    baseJobController.animateOnes(baseJobController);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                onetime = true;
                jobContainer.removeView(baseJobController.getHelp());
                break;
        }
        return false;
    }


}
