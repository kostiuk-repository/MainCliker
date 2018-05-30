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

public class VillagerAutosmelt extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "VILLAGER_AUTO_SMELT";
    private int onMode = R.mipmap.villager_autosmelt_on;
    private int offMode = R.mipmap.villager_autosmelt_off;
    private int help = R.mipmap.villager_autosmelt_craft;
    private BaseJobController baseJobController;
    private static VillagerAutosmelt instance;
    private RelativeLayout jobContainer;
    private boolean onetime = true;
    private Context context;
    private GoldOre goldOre;
    private Coal coal;
    private IronOre ironOre;
    private GoldIngot goldIngot;
    private IronIngot ironIngot;
    private boolean metal = true;
    private boolean gold = true;


    public void setGoldIngot(GoldIngot goldIngot) {
        this.goldIngot = goldIngot;
    }

    public void setIronIngot(IronIngot ironIngot) {
        this.ironIngot = ironIngot;
    }



    public void setGoldOre(GoldOre goldOre) {
        this.goldOre = goldOre;
    }

    public void setCoal(Coal coal) {
        this.coal = coal;
    }

    public void setIronOre(IronOre ironOre) {
        this.ironOre = ironOre;
    }



    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized VillagerAutosmelt getInstance(Context context) {
        if (instance == null) {
            instance = new VillagerAutosmelt(context);
        }
        return instance;
    }


    private VillagerAutosmelt(Context context) {
        super(context);
        this.context = context;
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(1,1)
                .setStatuses(STATUS.MINUS,STATUS.NOTHING)
                .setMinusValueList(-1,0)
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
                if(Kuznica.getInstance(context).getIT().getViewCounter()>=1
                        && EducatedVillager.getInstance(context).getIT().getViewCounter()>=1) {
                    changeStateIfClick();
                    baseJobController.animateOnes(baseJobController);
                    smelt();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                onetime = true;
                jobContainer.removeView(baseJobController.getHelp());
                break;
        }
        return false;
    }


    public void smelt() {
        while ((goldOre.getIT().getViewCounter() > 0 && coal.getIT().getViewCounter() > 0) ||
                (coal.getIT().getViewCounter() > 0 && ironOre.getIT().getViewCounter() > 0)) {
            if (goldOre.getIT().getViewCounter() > 0 && coal.getIT().getViewCounter() > 0
                    && gold) {
                goldOre.getIT().setViewCounter(goldOre.getIT().getViewCounter() - 1);
                goldOre.getIT().changeStateIfCheck();
                goldOre.getIT().resolveTextViewPosition();
                goldOre.getIT().lockUnlockFunction(goldOre.getIT());
                coal.getIT().setViewCounter(coal.getIT().getViewCounter() - 1);
                coal.getIT().changeStateIfCheck();
                coal.getIT().resolveTextViewPosition();
                coal.getIT().lockUnlockFunction(coal.getIT());
                goldIngot.getIT().setViewCounter(goldIngot.getIT().getViewCounter() + 1);
                goldIngot.getIT().changeStateIfCheck();
                goldIngot.getIT().resolveTextViewPosition();
                goldIngot.getIT().lockUnlockFunction(goldIngot.getIT());
                gold = false;
                metal = true;
            } else if(ironOre.getIT().getViewCounter() == 0 &&
                    goldOre.getIT().getViewCounter() > 0 &&
                    coal.getIT().getViewCounter() > 0){
                gold = true;
            }
            if (ironOre.getIT().getViewCounter() > 0 && coal.getIT().getViewCounter() > 0
                    && metal) {
                ironOre.getIT().setViewCounter(ironOre.getIT().getViewCounter() - 1);
                ironOre.getIT().changeStateIfCheck();
                ironOre.getIT().resolveTextViewPosition();
                ironOre.getIT().lockUnlockFunction(ironOre.getIT());
                coal.getIT().setViewCounter(coal.getIT().getViewCounter() - 1);
                coal.getIT().changeStateIfCheck();
                coal.getIT().resolveTextViewPosition();
                coal.getIT().lockUnlockFunction(coal.getIT());
                ironIngot.getIT().setViewCounter(ironIngot.getIT().getViewCounter() + 1);
                ironIngot.getIT().changeStateIfCheck();
                ironIngot.getIT().resolveTextViewPosition();
                ironIngot.getIT().lockUnlockFunction(ironIngot.getIT());
                metal = false;
                gold = true;
            } else if(goldOre.getIT().getViewCounter() == 0 &&
                    ironOre.getIT().getViewCounter() > 0 &&
                    coal.getIT().getViewCounter() > 0){
                metal = true;
            }
        }
    }
}
