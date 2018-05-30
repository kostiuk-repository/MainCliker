package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nerkingames.mineclicker.DataBase.DataBase;
import com.nerkingames.mineclicker.DataBase.JobDao;
import com.nerkingames.mineclicker.DataBase.JobTable;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Interfaces.JobButtonSimpleStructure;
import com.nerkingames.mineclicker.app.App;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vladyslav on 11.03.18.
 */

public class BaseJobController extends View implements JobButtonSimpleStructure {


    enum STATUS{
        MINUS,NOTHING
    }


    public void setBaseJobControllerListSubscribers(BaseJobController  baseJobControllers) {
            this.baseJobControllerListSubscribers.add(baseJobControllers);
    }

    public void setBaseJobControllerListSubscriptions(BaseJobController baseJobControllerListSubscriptions) {
        this.baseJobControllerListSubscriptions.add(baseJobControllerListSubscriptions);
    }

    private List<BaseJobController> baseJobControllerListSubscribers;
    private List<BaseJobController> baseJobControllerListSubscriptions;
    private List<Integer> compareList;
    private List<Integer> minusValueList;
    private List<STATUS> statusList;
    private FrameLayout viewContainer;
    private DataBase dataBase;
    private JobDao jobDao;
    private TextView viewCounter;
    private float width;
    private float height;
    private BitmapDrawable bitmapDrawableON;
    private BitmapDrawable bitmapDrawableOFF;
    private JobTable job_entity;
    private Context context;
    private Animation scaleAnimation;
    private AnimationDrawable mAnimationDrawable;
    private FrameLayout help;
    private FrameLayout containerForAnimation;


    public FrameLayout getHelp() {
        return help;
    }

    public BaseJobController getIT() {
        return this;
    }

    public JobDao getJobDao() {
        return jobDao;
    }

    public JobTable getJobEntity() {
        return job_entity;
    }

    public FrameLayout getViewContainer() {
        return viewContainer;
    }

    public View getViewScene() {

        return this;

    }


    public int getViewCounter() {
        try {
            return Integer.valueOf(viewCounter.getText().toString());
        } catch (NumberFormatException e){
            try {
                return job_entity.getCounter();
            } catch (NullPointerException ex) {
                return 0;
            }
        }
    }

    public void setViewCounter(int viewCounter) {
        this.viewCounter.setText(String.valueOf(viewCounter));
    }

    public void setClickable(boolean clickable){
        viewContainer.setClickable(clickable);
    }

    public void offButton(){
        if(mAnimationDrawable != null) {
            if (!mAnimationDrawable.isRunning()) {
                viewContainer.setBackground(bitmapDrawableOFF);
            }
        }
    }

    public void onButton(){
        if(mAnimationDrawable != null) {
            if (!mAnimationDrawable.isRunning()) {
                viewContainer.setBackground(bitmapDrawableON);
            }
        }
    }



    public BaseJobController(Context context) {
        super(context);
    }

    public BaseJobController(Context context,String ID, int on, int off,
                             OnTouchListener onTouchListener,boolean c,
                             List<BaseJobController> baseJobControllerListSubscribers,
                             List<BaseJobController> baseJobControllerListSubscriptions,
                             List<Integer> compareList, List<Integer> minusValueList,
                             List<STATUS> statusList, int helpBack) {
        super(context);
        this.context = context;

        dataBase = App.getInstance().getDatabase();
        jobDao = dataBase.getJobDao();
        mAnimationDrawable = new AnimationDrawable();
        initView(context,on,off, onTouchListener,ID,c,helpBack);
        insertNewEntity(ID);
        checkMyCounter(ID);
        this.baseJobControllerListSubscribers = baseJobControllerListSubscribers;
        this.baseJobControllerListSubscriptions = baseJobControllerListSubscriptions;
        this.compareList = compareList;
        this.minusValueList = minusValueList;
        this.statusList = statusList;

    }


    public void animateOnes(BaseJobController baseJobController){
        Observable.timer(40, TimeUnit.MILLISECONDS)
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
                        if(mAnimationDrawable.isRunning()){
                            mAnimationDrawable.stop();
                            viewContainer.removeView(containerForAnimation);
                        }
                        mAnimationDrawable = new AnimationDrawable();
                        containerForAnimation = new FrameLayout(context);
                        viewContainer.addView(containerForAnimation);
                        containerForAnimation.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        viewContainer.startAnimation(scaleAnimation);


                        BitmapDrawable frame1 = (BitmapDrawable) getResources().getDrawable(
                                R.mipmap.anim1);
                        BitmapDrawable frame2 = (BitmapDrawable) getResources().getDrawable(
                                R.mipmap.anim2);
                        BitmapDrawable frame3 = (BitmapDrawable) getResources().getDrawable(
                                R.mipmap.anim3);
                        mAnimationDrawable.setOneShot(true);
                        mAnimationDrawable.addFrame(frame1, 60);
                        mAnimationDrawable.addFrame(frame2, 60);
                        mAnimationDrawable.addFrame(frame3, 60);
                        containerForAnimation.setBackground(mAnimationDrawable);
                        if (!mAnimationDrawable.isRunning()) {
                            mAnimationDrawable.setVisible(true, true);
                            mAnimationDrawable.start();
                            Observable.timer(mAnimationDrawable.getDuration(1)
                                    *mAnimationDrawable.getNumberOfFrames() + 50, TimeUnit.MILLISECONDS)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Long>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(Long aLong) {
                                            mAnimationDrawable.stop();
                                            viewContainer.removeView(containerForAnimation);
                                            baseJobController.lockUnlockFunction(baseJobController);

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {}
                                    });
                        }

                    }
                });

    }



    private void initView(Context context, int on, int off, OnTouchListener onTouchListener, String ID, boolean c, int helpBackground) {
        viewContainer = new FrameLayout(context);
        bitmapDrawableON = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(), on));
        bitmapDrawableOFF = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),off));
        width = bitmapDrawableOFF.getBitmap().getWidth();
        height = bitmapDrawableOFF.getBitmap().getHeight();
        viewContainer.setLayoutParams(new FrameLayout.LayoutParams((int)width,(int)height));
        viewCounter = new TextView(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(),"font1.ttf");
        viewCounter.setTypeface(face);
        viewCounter.setTextColor(Color.YELLOW);
        viewCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        viewContainer.addView(viewCounter);
        setClickable(c);
        viewContainer.setBackground(bitmapDrawableOFF);
        viewContainer.setOnTouchListener(onTouchListener);
        scaleAnimation = new ScaleAnimation(1.0f, 0.8f,1.0f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(100);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        viewContainer.setAnimation(scaleAnimation);
        help = createHelp(helpBackground);


    }

    public void resolveTextViewPosition() {
        if(Integer.valueOf(viewCounter.getText().toString()) < 10 ) {
            viewCounter.setPaddingRelative((int) (width * 0.75f), (int) (height * 0.75f), 0, 0);
        } else if(Integer.valueOf(viewCounter.getText().toString()) >= 10 &&  Integer.valueOf(viewCounter.getText().toString()) < 100){
            viewCounter.setPaddingRelative((int)(width*0.65f),(int)(height*0.75f),0,0);
        } else if(Integer.valueOf(viewCounter.getText().toString()) >= 100 &&  Integer.valueOf(viewCounter.getText().toString()) < 1000){
            viewCounter.setPaddingRelative((int)(width*0.55f),(int)(height*0.75f),0,0);
        } else if(Integer.valueOf(viewCounter.getText().toString()) >= 1000 &&  Integer.valueOf(viewCounter.getText().toString()) < 10000){
            viewCounter.setPaddingRelative((int)(width*0.45f),(int)(height*0.75f),0,0);
        } else if(Integer.valueOf(viewCounter.getText().toString()) >= 10000 ){
            viewCounter.setPaddingRelative((int)(width*0.35f),(int)(height*0.75f),0,0);
        }

    }



    public void checkMyCounter(String ID){
        jobDao.getOneCounter(ID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<JobTable>() {
                    @Override
                    public void onSuccess(JobTable jobTable) {
                        viewCounter.setText(String.valueOf(jobTable.getCounter()));
                        resolveTextViewPosition();


                    }

                    @Override
                    public void onError(Throwable e) {
                        viewCounter.setText(String.valueOf(0));
                        resolveTextViewPosition();
                    }
                });
    }


    private void insertNewEntity(String str) {
        jobDao.getOneCounter(str).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<JobTable>() {
                    @Override
                    public void onSuccess(JobTable jobTable) {
                        job_entity = jobTable;
                    }

                    @Override
                    public void onError(Throwable e) {
                        job_entity = new JobTable();
                        job_entity.setId(str);
                        job_entity.setCounter(0);
                        job_entity.setTotalCounter(0);
                        jobDao.insert(job_entity);
                    }
                });
    }


    public List<BaseJobController> subscribersList(){
        return baseJobControllerListSubscribers;
    }

    public List<BaseJobController> subscriptionsList(){
        return baseJobControllerListSubscriptions;
    }

    public List<Integer> compareList() {
        return compareList;
    }


    public void changeStateIfClick(int incrementValue, int totalCounter){
        this.setViewCounter(this.getViewCounter() + incrementValue);
        this.getJobEntity().setCounter(this.getViewCounter());
        this.getJobEntity().setTotalCounter(this.getJobEntity().getTotalCounter() + totalCounter);
        Completable.fromRunnable(() -> {
            this.getJobDao().insert(this.getJobEntity());
        }).subscribeOn(Schedulers.io())
                .subscribe();
        this.resolveTextViewPosition();
    }

    public void changeStateIfCheck(){
        this.getJobEntity().setCounter(this.getViewCounter());
        Completable.fromRunnable(() -> {
            this.getJobDao().insert(this.getJobEntity());
        }).subscribeOn(Schedulers.io())
                .subscribe();
        this.resolveTextViewPosition();
    }

    public void avoidStateInner() {
        int i = 0;
        for(BaseJobController baseJobControllerCycle : this.subscribersList()){
            STATUS localStatus = this.statusList.get(i);
            int localMinusValues = this.minusValueList.get(i);
            this.avoidState(i).subscribe(baseJobControllerCycle.checkState(localStatus,localMinusValues,i));
            i++;
        }
    }


    public Observable<BaseJobController> avoidState(int subscriber) {
        return Observable.just(this.subscribersList().get(subscriber))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }
    public Observer<BaseJobController> checkState(STATUS status, int minus, int position){
        return new Observer<BaseJobController>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseJobController o) {
                if(STATUS.NOTHING == status){
                    lockUnlockFunction(o);
                } else if(STATUS.MINUS == status){
                    o.getIT().setViewCounter(o.getIT().getViewCounter() + minus);
                    o.getIT().resolveTextViewPosition();
                    o.getIT().changeStateIfCheck();
                    lockUnlockFunction(o);
                    for(BaseJobController b : o.getIT().subscriptionsList()){

                        lockUnlockFunction(b);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
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

            if (localCheker == localCounter) {

                baseJobController.getIT().onButton();
            } else {
                baseJobController.getIT().offButton();
            }


    }


    public static class Builder{


        private List<BaseJobController> baseJobControllerListSubscribers;
        private List<BaseJobController> baseJobControllerListSubscriptions;
        private List<Integer> compareList;
        private List<Integer> minusValueList;
        private List<STATUS> statusList;
        private OnTouchListener onTouchListener;
        private Context context;
        private int off;
        private int on;
        private int help;
        private String id;
        private boolean clickable;

        public Builder setBaseJobControllerListSubscribers(BaseJobController... baseJobControllerListSubscribers) {
            for (BaseJobController baseJobController:baseJobControllerListSubscribers) {
                this.baseJobControllerListSubscribers.add(baseJobController);
            }
            return this;
        }

        public Builder setBaseJobControllerListSubscriptions(BaseJobController... baseJobControllerListSubscriptions) {
            if(baseJobControllerListSubscriptions.length == 0){
                this.baseJobControllerListSubscriptions = new ArrayList<>();
            } else  {
                for (BaseJobController baseJobController : baseJobControllerListSubscriptions) {
                    this.baseJobControllerListSubscriptions.add(baseJobController);
                }
            }
            return this;
        }

        public Builder setCompareList(Integer... compareList) {
            for (Integer compare : compareList) {
                this.compareList.add(compare);
            }
            return this;
        }

        public Builder setMinusValueList(Integer... minusValueList) {
            for (Integer minusV : minusValueList) {
                this.minusValueList.add(minusV);
            }
            return this;
        }

        public Builder setStatuses(STATUS ... statuses){
            for (STATUS compare : statuses) {
                this.statusList.add(compare);
            }
            return this;
        }


        public Builder setClickable(boolean c) {
            this.clickable = c;
            return this;
        }


        public Builder setOnTouchListener(OnTouchListener onTouchListener) {
            this.onTouchListener = onTouchListener;
            return this;
        }



        public Builder setId(String id) {
            this.id = id;
            return this;
        }


        public Builder(Context context) {
            this.context = context;
            baseJobControllerListSubscribers = new ArrayList<>();
            baseJobControllerListSubscriptions = new ArrayList<>();
            compareList = new ArrayList<>();
            minusValueList = new ArrayList<>();
            statusList = new ArrayList<>();
        }



        public Builder setOff(int off) {
            this.off = off;
            return this;
        }

        public Builder setHelp(int help) {
            this.help = help;
            return this;
        }

        public Builder setOn(int on) {
            this.on = on;
            return this;
        }

        public BaseJobController build() {
            return new BaseJobController(context,id,on,off,onTouchListener,
                    clickable,baseJobControllerListSubscribers,
                    baseJobControllerListSubscriptions,
                    compareList, minusValueList,statusList,help);
        }



    }

    public FrameLayout createHelp(int recourse){
        FrameLayout helpLocal = new FrameLayout(context);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),recourse));
        helpLocal.setBackground(bitmapDrawable);
        helpLocal.setLayoutParams(new FrameLayout.LayoutParams(bitmapDrawable.getBitmap().getWidth(),bitmapDrawable.getBitmap().getHeight()));
        helpLocal.setX(getResources().getDisplayMetrics().widthPixels/2 - bitmapDrawable.getBitmap().getWidth()/2);
        helpLocal.setY(getResources().getDisplayMetrics().heightPixels * 0.11f);
        return  helpLocal;
    }


}
