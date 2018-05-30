package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.AchievementsButtons.SampleAchievements;
import com.nerkingames.mineclicker.Views.Buttons.PoolButton.UpBarLeftSide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vladyslav on 17.03.18.
 */

public class VillagerSimple extends BaseJobController implements View.OnTouchListener{


    public void setUpBarLeftSideJob(UpBarLeftSide upBarLeftSideJob) {
        this.upBarLeftSideJob = upBarLeftSideJob;
    }

    public void setUpBarLeftSideUpW(UpBarLeftSide upBarLeftSideUpW) {
        this.upBarLeftSideUpW = upBarLeftSideUpW;
    }



    UpBarLeftSide upBarLeftSideJob;
    UpBarLeftSide upBarLeftSideUpW;
    private UpBarLeftSide upBarLeftSide;

    public static final String ID = "VILLAGER";
    private int onMode = R.mipmap.villager_simple_on;
    private int offMode = R.mipmap.villager_simple_off;
    private int help = R.mipmap.villager_simple_craft;
    private BaseJobController baseJobController;
    private static VillagerSimple instance;
    private RelativeLayout jobContainer;
    private boolean onetime = true;
    private Context context;
    private SampleAchievements sampleAchievements;

    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized VillagerSimple getInstance(Context context) {
        if (instance == null) {
            instance = new VillagerSimple(context);
        }
        return instance;
    }


    private VillagerSimple(Context context) {
        super(context);
        upBarLeftSide = new UpBarLeftSide(context);
        this.context = context;
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(1,1,1,1,0,0,0,0,0,0,0,0)
                .setStatuses(STATUS.MINUS,STATUS.MINUS,STATUS.MINUS,STATUS.MINUS,STATUS.NOTHING
                                ,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING
                        ,STATUS.NOTHING,STATUS.NOTHING,STATUS.NOTHING)
                .setMinusValueList(-1,-1,-1,-1,0,0,0,0,0,0,0,0)
                .setOnTouchListener(this)
                .setClickable(true)
                .setBaseJobControllerListSubscriptions()
                .setBaseJobControllerListSubscribers()
                .build();
        sampleAchievements = new SampleAchievements(context,R.mipmap.villager04);
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
                        .getParent();                break;
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
                if(Storogka.getInstance(context).getIT().getViewCounter()>=1
                        && FermaWithGrass.getInstance(context).getIT().getViewCounter()>=1) {

                    if(VillagerSimple.getInstance(context).getIT().getViewCounter() == 0){
                        if(baseJobController.getJobEntity().getTotalCounter() == 0) {
                            sampleAchievements = new SampleAchievements(context,R.mipmap.villager04);
                            sampleAchievements.insertNewEntity("VILLAGER");

                            int newCounterScope = upBarLeftSide.getTextFieldCounter() + 10;
                            upBarLeftSide.setTextFieldCounter(newCounterScope);
                            upBarLeftSideJob.setTextFieldCounterWithoutChange(newCounterScope);
                            upBarLeftSideUpW.setTextFieldCounterWithoutChange(newCounterScope);
                            Animation scaleAnimation = new ScaleAnimation(0.2f, 1.0f,0.2f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);



                            scaleAnimation.setDuration(500);
                            scaleAnimation.setRepeatCount(0);
                            Animation scaleAnimation2 = new ScaleAnimation(1.0f, 0.2f,1.0f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


                            scaleAnimation2.setDuration(500);
                            scaleAnimation2.setRepeatCount(0);

                            GameActivity.relativeLayout2.addView(sampleAchievements);
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(getResources().getDisplayMetrics().widthPixels/2 - sampleAchievements.getmWidth()/2,
                                    getResources().getDisplayMetrics().heightPixels/2 - sampleAchievements.getmHeight()/2,0,0);
                            sampleAchievements.setLayoutParams(layoutParams);
                            sampleAchievements.startAnimation(scaleAnimation);
                            Observable.timer(2500, TimeUnit.MILLISECONDS)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Long>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(Long aLong) {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {
                                            sampleAchievements.startAnimation(scaleAnimation2);
                                        }
                                    });
                            Observable.timer(3000, TimeUnit.MILLISECONDS)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Long>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(Long aLong) {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {
                                            GameActivity.relativeLayout2.removeView(sampleAchievements);
                                        }
                                    });
                        }
                    }


                    changeStateIfClick();
                    VillagerSimpleAuto.getInstance(context).getIT().setViewCounter(baseJobController.getViewCounter());
                    VillagerSimpleAuto.getInstance(context).getIT().lockUnlockFunction(VillagerSimpleAuto.getInstance(context).getIT());
                    VillagerSimpleAuto.getInstance(context).getIT().resolveTextViewPosition();
                    VillagerSimpleAuto.getInstance(context).getIT().changeStateIfCheck();
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
