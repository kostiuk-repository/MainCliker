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
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.UpWorld;

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

public class UpWorldWorker extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "VILLAGER_UP_WORLD";
    private int onMode = R.mipmap.villager_upworld_fire_on;
    private int offMode = R.mipmap.villager_upworld_fire_off;
    private int help = R.mipmap.villager_upworld_fire_craft;
    private BaseJobController baseJobController;
    private static UpWorldWorker instance;
    private Context context;
    private UpBarLeftSide upBarLeftSide;
    private UpWorld upWorld;
    private RelativeLayout jobContainer;
    private boolean onetime = true;
    private SampleAchievements sampleAchievements;

    public void setParentLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    private RelativeLayout relativeLayout;

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

    public void setUpWorld(UpWorld upWorld) {
        this.upWorld = upWorld;
    }



    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized UpWorldWorker getInstance(Context context) {
        if (instance == null) {
            instance = new UpWorldWorker(context);
        }
        return instance;
    }


    private UpWorldWorker(Context context) {
        super(context);
        this.context = context;
        upBarLeftSide = new UpBarLeftSide(context);
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(15,15,15,15,15,15)
                .setStatuses(STATUS.MINUS,STATUS.MINUS,STATUS.MINUS,STATUS.MINUS,STATUS.MINUS,STATUS.MINUS)
                .setMinusValueList(-15,-15,-15,-15,-15,-15)
                .setOnTouchListener(this)
                .setClickable(true)
                .setBaseJobControllerListSubscriptions()
                .setBaseJobControllerListSubscribers()
                .build();
        sampleAchievements = new SampleAchievements(context,R.mipmap.villager05);
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
                        && VillagerSimple.getInstance(context).getIT().getViewCounter()>=15
                        && StoneShovel.getInstance(context).getIT().getViewCounter()>=15
                        && StoneAxe.getInstance(context).getIT().getViewCounter()>=15
                        && StoneSword.getInstance(context).getIT().getViewCounter()>=15
                        && BucketEmpty.getInstance(context).getIT().getViewCounter()>=15) {

                    sampleAchievements = new SampleAchievements(context,R.mipmap.villager05);
                    if(UpWorldWorker.getInstance(context).getIT().getViewCounter() == 0){
                        if(baseJobController.getJobEntity().getTotalCounter() == 0) {
                            sampleAchievements = new SampleAchievements(context,R.mipmap.villager05);
                            sampleAchievements.insertNewEntity("WORKER");


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
                    baseJobController.animateOnes(baseJobController);
                    if(baseJobController.getIT().getViewCounter()<=1) {
                        disposable = upWorld.getWorker().subscribe();
                    }
                } else if (upBarLeftSide.getTextFieldCounter() >= 150) {


                    if(UpWorldWorker.getInstance(context).getIT().getViewCounter() == 0){
                        if(baseJobController.getJobEntity().getTotalCounter() == 0) {
                            sampleAchievements = new SampleAchievements(context,R.mipmap.villager05);
                            sampleAchievements.insertNewEntity("WORKER");

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

                    baseJobController.animateOnes(baseJobController);
                    int newCounterScope = upBarLeftSide.getTextFieldCounter() - 150;
                    upBarLeftSide.setTextFieldCounter(newCounterScope);
                    upBarLeftSideJob.setTextFieldCounterWithoutChange(newCounterScope);
                    upBarLeftSideUpW.setTextFieldCounterWithoutChange(newCounterScope);
                    baseJobController.changeStateIfClick(1,1);
                    if(baseJobController.getIT().getViewCounter()<=1) {
                        disposable = upWorld.getWorker().subscribe();
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
