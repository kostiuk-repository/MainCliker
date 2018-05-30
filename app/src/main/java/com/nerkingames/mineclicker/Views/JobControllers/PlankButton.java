package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.R;

import java.util.ArrayList;
import java.util.List;

public class PlankButton extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "PLANK";
    private static final String TAG = "Plank_Button";
    private int onMode = R.mipmap.wood_desk_on;
    private int offMode = R.mipmap.wood_desk_off;
    private int help = R.mipmap.wood_desk_craft;
    private BaseJobController baseJobController;
    private static PlankButton instance;
    private boolean onetime = true;
    private Context context;
    private RelativeLayout jobContainer;

    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized PlankButton getInstance(Context context) {
        if (instance == null) {
            instance = new PlankButton(context);
        }
        return instance;
    }


    private PlankButton(Context context) {
        super(context);
        this.context = context;
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(1,0,0,0,0,0,0,0,0,0,0,0)
                .setStatuses(STATUS.MINUS,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING
                        ,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING
                        ,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING)
                .setMinusValueList(-1,0,0,0,0,0,0,0,0,0,0,0)
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
        baseJobController.changeStateIfClick(4,4);
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
                if(WoodButton.getInstance(context).getIT().getViewCounter()>=1) {
                    changeStateIfClick();
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
