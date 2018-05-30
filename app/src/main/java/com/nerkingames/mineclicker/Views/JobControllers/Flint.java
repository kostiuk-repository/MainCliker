package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vladyslav on 19.03.18.
 */

public class Flint extends BaseJobController implements View.OnTouchListener{



    Context context;
    public static final String ID = "FLINT";
    private int onMode = R.mipmap.flint_on;
    private int offMode = R.mipmap.flint_off;
    private int help = R.mipmap.flint_craft;
    private BaseJobController baseJobController;


    private static Flint instance;
    private RelativeLayout jobContainer;
    private boolean onetime = true;


    public BaseJobController getIT() {
        return baseJobController;
    }



    public static synchronized Flint getInstance(Context context) {
        if (instance == null) {
            instance = new Flint(context);
        }
        return instance;
    }

    public Flint(Context context) {
        super(context);
        this.context = context;

        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setOnTouchListener(this)
                .setClickable(true)
                .setStatuses(STATUS.NOTHING)
                .setMinusValueList(0)
                .setCompareList()
                .setBaseJobControllerListSubscribers()
                .setBaseJobControllerListSubscriptions()
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
                break;
            case MotionEvent.ACTION_CANCEL:
                onetime = true;
                jobContainer.removeView(baseJobController.getHelp());
                break;
        }
        return false;
    }

    public void subscribe(Observable<Integer> observer){
        observer.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer o) {
                baseJobController.setViewCounter(baseJobController.getViewCounter() + o);
                baseJobController.changeStateIfClick(0,1);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                reportState();
            }
        });
    }


}
