package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.UpBarLeftSide;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.Mobs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

import static android.content.ContentValues.TAG;

/**
 * Created by vladyslav on 19.03.18.
 */

public class GoodMobsWorker extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "VILLAGER_GOOD_MOBS";
    private int onMode = R.mipmap.villager_goodmob_fire_on;
    private int offMode = R.mipmap.villager_goodmob_fire_off;
    private int help = R.mipmap.villager_goodmob_fire_craft;
    private BaseJobController baseJobController;
    private static GoodMobsWorker instance;
    private Context context;
    private UpBarLeftSide upBarLeftSide;
    private Mobs mobs;
    private RelativeLayout jobContainer;
    private boolean onetime = true;

    public Disposable getDisposable() {
        return disposable;
    }

    Disposable disposable;

    public void setUpBarLeftSideJob(UpBarLeftSide upBarLeftSideJob) {
        this.upBarLeftSideJob = upBarLeftSideJob;
    }

    public void setUpBarLeftSideUpW(UpBarLeftSide upBarLeftSideUpW) {
        this.upBarLeftSideUpW = upBarLeftSideUpW;
    }

    public void setUpBarLeftSideAch(UpBarLeftSide upBarLeftSideAch) {
        this.upBarLeftSideAch = upBarLeftSideAch;
    }

    UpBarLeftSide upBarLeftSideJob;
    UpBarLeftSide upBarLeftSideUpW;
    UpBarLeftSide upBarLeftSideAch;

    public void setMobs(Mobs mobs) {
        this.mobs = mobs;
    }



    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized GoodMobsWorker getInstance(Context context) {
        if (instance == null) {
            instance = new GoodMobsWorker(context);
        }
        return instance;
    }


    private GoodMobsWorker(Context context) {
        super(context);
        this.context = context;
        upBarLeftSide = new UpBarLeftSide(context);
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(25,25,25,25)
                .setStatuses(STATUS.MINUS,STATUS.MINUS,STATUS.MINUS,STATUS.MINUS)
                .setMinusValueList(-25,-25,-25,-25)
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


                if(upBarLeftSide.getTextFieldCounter() < 150
                        && VillagerSimple.getInstance(context).getIT().getViewCounter()>=25
                        && ZaborUpdate.getInstance(context).getIT().getViewCounter()>=25
                        && StoneSword.getInstance(context).getIT().getViewCounter()>=25) {
                    changeStateIfClick();
                    baseJobController.animateOnes(baseJobController);
                    if(baseJobController.getIT().getViewCounter()<=1
                            && AggressiveMobsWorker.getInstance(context).getIT().getViewCounter() == 0) {
                        disposable = mobs.getWorker().subscribe();
                    }
                } else if (upBarLeftSide.getTextFieldCounter() >= 150) {
                    baseJobController.animateOnes(baseJobController);
                    int newCounterScope = upBarLeftSide.getTextFieldCounter() - 150;
                    upBarLeftSide.setTextFieldCounter(newCounterScope);
//                    upBarLeftSideAch.setTextFieldCounterWithoutChange(newCounterScope);
                    upBarLeftSideJob.setTextFieldCounterWithoutChange(newCounterScope);
                    upBarLeftSideUpW.setTextFieldCounterWithoutChange(newCounterScope);
                    baseJobController.changeStateIfClick(1,1);
                    if(baseJobController.getIT().getViewCounter()<=1
                            && AggressiveMobsWorker.getInstance(context).getIT().getViewCounter() == 0) {
                        disposable = mobs.getWorker().subscribe();
                    }
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                onetime = true;
                jobContainer.removeView(baseJobController.getHelp());
                break;
        }
        return false;
    }

    public void lockUnlockFunction(BaseJobController baseJobController) {

        int localCounter = 0;
        int localCheker = 0;

        for (BaseJobController b : baseJobController.getIT().subscribersList()) {
            Log.d(TAG, "lockUnlockFunction: " + PlankButton.getInstance(context).getIT().getViewCounter());

            if(baseJobController.getIT().compareList().size() == 0){
                localCheker = 1;
                break;
            } else
            if(b.getIT().getViewCounter() >= baseJobController.getIT().compareList().get(localCounter)){
                localCheker++;
            }
            localCounter++;

        }

        if (localCheker == localCounter || upBarLeftSide.getTextFieldCounter() >= 150) {

            baseJobController.getIT().onButton();

        } else {
            baseJobController.getIT().offButton();
        }
        baseJobController.getIT().setClickable(true);
    }


}
