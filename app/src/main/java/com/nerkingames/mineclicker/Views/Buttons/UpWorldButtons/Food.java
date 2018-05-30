package com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.SoundSwitcher;
import com.nerkingames.mineclicker.Views.JobControllers.DiamondSword;
import com.nerkingames.mineclicker.Views.JobControllers.FoodWorker;
import com.nerkingames.mineclicker.Views.JobControllers.GoldSword;
import com.nerkingames.mineclicker.Views.JobControllers.IronSword;
import com.nerkingames.mineclicker.Views.JobControllers.StoneSword;
import com.nerkingames.mineclicker.Views.JobControllers.WoodSword;
import com.nerkingames.mineclicker.music.ClickSound;

import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by vladyslav on 06.03.18.
 */

public class Food extends View implements View.OnTouchListener,IViewGetter {


    private int vWidth, vHeight, Differential;
    private Bitmap backgroundBitmap;
    private RectFood rect;
    private Context context;
    private FrameLayout thisView;
    ClickSound clickSound;
    public static final String BUTTON_ID = "FOOD";
    private boolean isClicked = false;
    private boolean onetime = true;
    private FrameLayout help;
    private RelativeLayout upWorldContainer;
    private Random random = new Random();
    private FoodWorker foodWorker;
    private Disposable disposable;

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    @Override
    public Disposable getDisposable() {
        return disposable;
    }

    @Override
    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public Observer<Long> finishLoad() {
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                thisView.setClickable(true);
                upWorldContainer = (RelativeLayout) thisView.getParent();
                if (foodWorker.getIT().getViewCounter() > 0) {
                    disposable = getWorker().subscribe();
                    Log.d(TAG, "onNext: "+"I get disposable");
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

    @Override
    public void setClickabled() {
        thisView.setClickable(true);
    }

    @Override
    public void setViewContainer(RelativeLayout rl) {
        upWorldContainer = rl;
    }


    public Food(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        Log.d("Started init ", "food; ");
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.food);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.food));
        rect = new RectFood(context, this);
        vWidth = backgroundBitmap.getWidth();
        vHeight = backgroundBitmap.getHeight();
        thisView.addView(rect.getThisView());
        Differential = (vHeight - rect.getRectangleHeight()) / 2;
        rect.getThisView().setX(vWidth - rect.getRectangleWidth() - 25);
        rect.getThisView().setY(Differential);
        thisView.setOnTouchListener(this);
        thisView.setClickable(false);
        foodWorker = FoodWorker.getInstance(context);
        foodWorker.setFood(this);

        help = createHelp(R.mipmap.food_help);
        clickSound = ClickSound.getInstance(context);



    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие

                break;
            case MotionEvent.ACTION_MOVE: // движение
                long eventDuration = event.getEventTime() - event.getDownTime();
                if(eventDuration > 500 && onetime){
                    onetime = false;
                    upWorldContainer.addView(help);
                }
                break;
            case MotionEvent.ACTION_UP: // отпускание
                onetime = true;
                upWorldContainer.removeView(help);

                if(!isClicked && FoodWorker.getInstance(context).getIT().getViewCounter()<1) {
                    isClicked = true;


                    if (foodWorker.getIT().getViewCounter() <= 0) {
                        int rand = random.nextInt(100);
                        if (rand <= 60) {
                            bambukCreator();
                        } else if (rand > 60 && rand <= 100) {
                            wheatCreator();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if(upWorldContainer != null) {
                    upWorldContainer.removeView(help);
                }
                break;
        }
        return false;
    }

    private void wheatCreatorMute() {

        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 10,1);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 15,1);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 20,1);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 25,1);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.wheat_food, 30,1);
        } else {
            isClicked = false;
        }
    }

    private void wheatCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickFood();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 10,1);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 15,1);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 20,1);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.wheat_food, 25,1);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.wheat_food, 30,1);
        } else {
            isClicked = false;
        }
    }

    private void bambukCreatorMute() {

        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 10,2);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 20,2);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 30,2);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 35,2);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.bambuk_food, 40,2);
        } else {
            isClicked = false;
        }
    }

    private void bambukCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickFood();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 10,2);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 20,2);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 30,2);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.bambuk_food, 35,2);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.bambuk_food, 40,2);
        } else {
            isClicked = false;
        }
    }

    @Override
    public View getThisView() {
        return thisView;
    }

    @Override
    public int getvWidth() {
        return vWidth;
    }

    @Override
    public int getvHeight() {
        return vHeight;
    }

    @Override
    public Completable getWorker() {
        if ( DiamondSword.getInstance(context).getIT().getViewCounter() >= 1 ||
                 GoldSword.getInstance(context).getIT().getViewCounter() >= 1 ||
                 IronSword.getInstance(context).getIT().getViewCounter() >= 1 ||
                 StoneSword.getInstance(context).getIT().getViewCounter() >= 1 ||
                 WoodSword.getInstance(context).getIT().getViewCounter() >= 1)
        {
            return Completable.fromRunnable(() -> {
                int rand = random.nextInt(100);
                if (rand <= 60) {
                    bambukCreatorMute();
                } else if (rand > 60 && rand <= 100) {
                    wheatCreatorMute();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            WoodSword.getInstance(context).getIT().setViewCounter(1);
            return Completable.fromRunnable(() -> {
                int rand = random.nextInt(100);
                if (rand <= 60) {
                    bambukCreatorMute();
                } else if (rand > 60 && rand <= 100) {
                    wheatCreatorMute();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
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
