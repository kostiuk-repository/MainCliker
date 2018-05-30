package com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.JobControllers.AggressiveMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.Arrow;
import com.nerkingames.mineclicker.Views.JobControllers.Father;
import com.nerkingames.mineclicker.Views.JobControllers.GoodMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.Horse;
import com.nerkingames.mineclicker.Views.JobControllers.Leather;
import com.nerkingames.mineclicker.Views.JobControllers.Slime;
import com.nerkingames.mineclicker.Views.JobControllers.Sttring;
import com.nerkingames.mineclicker.Views.JobControllers.WoodButton;
import com.nerkingames.mineclicker.Views.JobControllers.Wool;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vladyslav on 14.03.18.
 */

public class RectMobs extends View {

    private Bitmap bitmap;
    private int Width, Height;
    private ProgressBar progressBar;
    private FrameLayout frameLayoutProgressBar;
    private FrameLayout frameLayoutCustomView;
    private FrameLayout thisView;
    private Context context;
    private IViewGetter mobs;
    private Bitmap backgroundBitmap;
    WoodButton woodButton;
    private Handler handler = new Handler();
    private Arrow skeleton;
    private Sttring spider;
    private Slime slime;
    private Father chicken;
    private Leather cow;
    private Wool sheep;
    private Horse horse;


    public View getThisView() {
        return thisView;
    }


    public void mPerformClick(int recourse,int timeForOneCycle, int type){
        createResource(recourse);
        increaseTheCounter(timeForOneCycle, type);
    }

    public RectMobs(Context context, IViewGetter mobs) {
        super(context);
        this.context = context;
        initRectangle();
        this.mobs = mobs;
    }


    private void initRectangle() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.button_kraft);
        Width = bitmap.getWidth();
        Height = bitmap.getHeight();
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.button_kraft));
        skeleton = Arrow.getInstance(context);
        spider = Sttring.getInstance(context);
        chicken = Father.getInstance(context);
        slime = Slime.getInstance(context);
        cow = Leather.getInstance(context);
        sheep = Wool.getInstance(context);
        horse = Horse.getInstance(context);
    }



    public void createResource(int pictureOfResource){

        handler.post(() -> {
            frameLayoutCustomView = new FrameLayout(context);
            frameLayoutCustomView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayoutCustomView.setBackground(getResources().getDrawable(pictureOfResource));
            backgroundBitmap = BitmapFactory.decodeResource(getResources(), pictureOfResource);
            progressBar =  new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#00796B"), PorterDuff.Mode.SRC_IN);
            frameLayoutCustomView.setY(bitmap.getHeight()*0.18f);
            frameLayoutCustomView.setX(bitmap.getWidth()/2 - backgroundBitmap.getWidth()/2);
            frameLayoutProgressBar = new FrameLayout(context);
            if(thisView.getChildCount() == 0) {
                thisView.addView(frameLayoutCustomView);
                thisView.addView(frameLayoutProgressBar);
                frameLayoutProgressBar.addView(progressBar);
            } else {
                frameLayoutProgressBar.removeAllViews();
                thisView.removeAllViews();
                thisView.addView(frameLayoutCustomView);
                thisView.addView(frameLayoutProgressBar);
                frameLayoutProgressBar.addView(progressBar);
            }
            frameLayoutProgressBar.setLayoutParams(new FrameLayout.LayoutParams((int)(bitmap.getWidth()*0.6f), ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayoutProgressBar.setY(backgroundBitmap.getHeight() + frameLayoutCustomView.getY() - bitmap.getHeight()*0.06f);
            frameLayoutProgressBar.setX(bitmap.getHeight()*0.2f);

        });


    }


    public void clearResource() {
        handler.post(()->{
            thisView.removeAllViews();
            frameLayoutProgressBar.removeAllViews();
            if (GoodMobsWorker.getInstance(context).getIT().getViewCounter()>0
                    || AggressiveMobsWorker.getInstance(context).getIT().getViewCounter()>0){
                if(!GameActivity.onStop) {
                    mobs.setDisposable(mobs.getWorker().subscribe());
                }

            }
        });

    }

    public int getRectangleWidth() {
        return Width;
    }

    public int getRectangleHeight() {
        return Height;
    }

    public void increaseTheCounter(int period, int type){
        Observable<Long> observable = Observable.intervalRange(1,100,0,period, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Long>() {

            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Long aLong) {
                if(GameActivity.onStop){
                    disposable.dispose();
                    mobs.setClicked(false);
                        frameLayoutProgressBar.removeAllViews();
                        thisView.removeAllViews();
                }
                progressBar.setProgress(aLong.intValue());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                clearResource();
                mobs.setClicked(false);
                if(type == 1) {
                    skeleton.subscribe(Observable.just(Integer.valueOf(1)));
                } else if (type == 2){
                    spider.subscribe(Observable.just(Integer.valueOf(2)));
                } else if (type == 3){
                    slime.subscribe(Observable.just(Integer.valueOf(2)));
                } else if (type == 4){
                    chicken.subscribe(Observable.just(Integer.valueOf(2)));
                } else if (type == 5){
                    cow.subscribe(Observable.just(Integer.valueOf(1)));
                } else if (type == 6){
                    sheep.subscribe(Observable.just(Integer.valueOf(1)));
                } else if (type == 7){
                    horse.subscribe(Observable.just(Integer.valueOf(1)));
                }
            }
        });
    }


}
