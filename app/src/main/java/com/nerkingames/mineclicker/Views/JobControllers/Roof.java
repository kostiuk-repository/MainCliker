package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladyslav on 20.03.18.
 */

public class Roof extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "ROOF";
    private int onMode = R.mipmap.krisha_on;
    private int offMode = R.mipmap.krisha_off;
    private int help = R.mipmap.krisha_craft;
    private BaseJobController baseJobController;
    private static Roof instance;
    private RelativeLayout jobContainer;
    private boolean onetime = true;
    private Context context;

    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized Roof getInstance(Context context) {
        if (instance == null) {
            instance = new Roof(context);
        }
        return instance;
    }


    private Roof(Context context) {
        super(context);
        this.context = context;
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(4,4,0,0)
                .setStatuses(STATUS.MINUS,STATUS.MINUS,STATUS.NOTHING,STATUS.NOTHING)
                .setMinusValueList(-4,-4,0,0)
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
                if(TwoTimeHalfRoof.getInstance(context).getIT().getViewCounter()>=4) {
                    changeStateIfClick();
                    RoofCopy.getInstance(context).getIT().setViewCounter(baseJobController.getViewCounter());
                    RoofCopy.getInstance(context).getIT().lockUnlockFunction(RoofCopy.getInstance(context).getIT());
                    RoofCopy.getInstance(context).getIT().resolveTextViewPosition();
                    RoofCopy.getInstance(context).getIT().changeStateIfCheck();
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