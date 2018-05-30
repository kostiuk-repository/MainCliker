package com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.JobControllers.Dirt;
import com.nerkingames.mineclicker.Views.JobControllers.UpWorldWorker;
import com.nerkingames.mineclicker.Views.JobControllers.Water;
import com.nerkingames.mineclicker.Views.JobControllers.WheatSeeds;
import com.nerkingames.mineclicker.Views.JobControllers.WoodButton;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Rectangle extends View {

    private static final String TAG = "UPW_RECTANGLE";
    private Bitmap bitmap;
    private int Width, Height;
    private ProgressBar progressBar;
    private FrameLayout frameLayoutProgressBar;
    private FrameLayout frameLayoutCustomView;
    private FrameLayout thisView;
    private Context context;
    private IViewGetter upWorld;
    private Bitmap backgroundBitmap;
    private WoodButton woodButton;
    private Dirt dirt;
    private Water water;
    private WheatSeeds wheatSeeds;
    private Handler handler = new Handler();

    public View getThisView() {
        return thisView;
    }


    public void mPerformClick(int recourse,int timeForOneCycle, int type){
        createResource(recourse);
        increaseTheCounter(timeForOneCycle, type);
    }

    public Rectangle(Context context, IViewGetter upWorld) {
        super(context);
        this.context = context;
        initRectangle();
        this.upWorld = upWorld;
    }


    private void initRectangle() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.button_kraft);
        Width = bitmap.getWidth();
        Height = bitmap.getHeight();
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.button_kraft));
        woodButton = WoodButton.getInstance(context);
        dirt = Dirt.getInstance(context);
        water = Water.getInstance(context);
        wheatSeeds = WheatSeeds.getInstance(context);

    }



    public void createResource(int pictureOfResource){

        handler.post(() -> {
            frameLayoutCustomView = new FrameLayout(context);
            frameLayoutCustomView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayoutCustomView.setBackground(getResources().getDrawable(pictureOfResource));
            progressBar =  new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#00796B"), PorterDuff.Mode.SRC_IN);
            backgroundBitmap = BitmapFactory.decodeResource(getResources(), pictureOfResource);
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
            frameLayoutProgressBar.removeAllViews();
            thisView.removeAllViews();
            if (UpWorldWorker.getInstance(context).getIT().getViewCounter()>0){
                if(!GameActivity.onStop) {
                    upWorld.setDisposable(upWorld.getWorker().subscribe());
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
                    upWorld.setClicked(false);
                        Log.d("Stop Loop", "onNext: was disposed");
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
                upWorld.setClicked(false);
                if(type == 1) {
                    woodButton.subscribe(Observable.just(Integer.valueOf(1)));
                } else if (type == 2){
                    dirt.subscribe(Observable.just(Integer.valueOf(1)));
                } else if (type == 3){
                    water.subscribe(Observable.just(Integer.valueOf(1)));
                } else if (type == 4){
                    wheatSeeds.subscribe(Observable.just(Integer.valueOf(1)));
                }

            }
        });
    }




}
