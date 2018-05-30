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
import com.nerkingames.mineclicker.Views.JobControllers.BucketEmpty;
import com.nerkingames.mineclicker.Views.JobControllers.DiamondAxe;
import com.nerkingames.mineclicker.Views.JobControllers.DiamondShovel;
import com.nerkingames.mineclicker.Views.JobControllers.DiamondSword;
import com.nerkingames.mineclicker.Views.JobControllers.GoldAxe;
import com.nerkingames.mineclicker.Views.JobControllers.GoldShovel;
import com.nerkingames.mineclicker.Views.JobControllers.GoldSword;
import com.nerkingames.mineclicker.Views.JobControllers.IronAxe;
import com.nerkingames.mineclicker.Views.JobControllers.IronShovel;
import com.nerkingames.mineclicker.Views.JobControllers.IronSword;
import com.nerkingames.mineclicker.Views.JobControllers.StoneAxe;
import com.nerkingames.mineclicker.Views.JobControllers.StoneShovel;
import com.nerkingames.mineclicker.Views.JobControllers.StoneSword;
import com.nerkingames.mineclicker.Views.JobControllers.UpWorldWorker;
import com.nerkingames.mineclicker.Views.JobControllers.WoodAxe;
import com.nerkingames.mineclicker.Views.JobControllers.WoodShovel;
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
 * Created by vladyslav on 04.03.18.
 */

public class UpWorld extends View implements View.OnTouchListener,IViewGetter {


    private int vWidth, vHeight, Differential;
    private Bitmap backgroundBitmap;
    private Rectangle rect;
    private Context context;
    private FrameLayout thisView;
    private ClickSound clickSound;
    public static final String BUTTON_ID = "UP_WORLD";
    private Random random = new Random();
    private UpWorldWorker upWorldWorker;
    private boolean isClicked = false;
    private RelativeLayout upWorldContainer;
    private FrameLayout help;
    private boolean onetime = true;
    public static int RESOURCES_TYPE = 1;
    public static boolean WORKER_STATUS = true;

    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    private Disposable disposable;

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }




    public UpWorld(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.up_world);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.up_world));
        rect = new Rectangle(context, this);
        vWidth = backgroundBitmap.getWidth();
        vHeight = backgroundBitmap.getHeight();
        thisView.addView(rect.getThisView());
        Differential = (vHeight - rect.getRectangleHeight()) / 2;
        rect.getThisView().setX(vWidth - rect.getRectangleWidth() - 25);
        rect.getThisView().setY(Differential);
        thisView.setOnTouchListener(this);
        thisView.setClickable(false);
        upWorldWorker = UpWorldWorker.getInstance(context);
        upWorldWorker.setUpWorld(this);
        clickSound = ClickSound.getInstance(context);
        help = createHelp(R.mipmap.upworld_help);


    }

    public Observer<Long> finishLoad(){
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                thisView.setClickable(true);
                upWorldContainer = (RelativeLayout) thisView.getParent();
                if (upWorldWorker.getIT().getViewCounter() > 0) {
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                long eventDuration = event.getEventTime() - event.getDownTime();
                if(eventDuration > 500 && onetime){
                    onetime = false;
                    upWorldContainer.addView(help);
                }
                break;
            case MotionEvent.ACTION_UP:

                onetime = true;
                upWorldContainer.removeView(help);

                if(!isClicked && UpWorldWorker.getInstance(context).getIT().getViewCounter()<1) {
                    isClicked = true;


                    if (upWorldWorker.getIT().getViewCounter() <= 0) {
                        int rand = random.nextInt(100);
                        if (rand <= 60) {
                            woodCreator();
                        } else if (rand > 60 && rand < 75) {
                            dirtCreator();
                        } else if (rand >= 75 && rand < 90) {
                            grassCreator();
                        } else if (rand >= 90) {
                            waterCreator();
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

    public void woodCreatorMute(){

        if(DiamondAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 20,1);
        } else
        if(GoldAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 25,1);
        } else
        if(IronAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 35,1);
        } else
        if(StoneAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 40,1);
        } else
        if(WoodAxe.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.woodwork, 50,1);
        } else {
            rect.mPerformClick(R.mipmap.woodwork, 60,1);
        }
    }

    public void woodCreator(){
        if (SoundSwitcher.SOUND_CHEKER == 1) {
            clickSound.playClickWood();
        }
        if(DiamondAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 20,1);
        } else
        if(GoldAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 25,1);
        } else
        if(IronAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 35,1);
        } else
        if(StoneAxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.woodwork, 40,1);
        } else
        if(WoodAxe.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.woodwork, 45,1);
        } else {
            rect.mPerformClick(R.mipmap.woodwork, 50,1);
        }
    }

    public void dirtCreatorMute(){

        if(DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 20,2);
        } else
        if(GoldShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 35,2);
        } else
        if(IronShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 40,2);
        } else
        if(StoneShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 45,2);
        } else
        if(WoodShovel.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.dirtwork, 50,2);
        } else {
            woodCreatorMute();
        }
    }

    public void dirtCreator(){
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodShovel.getInstance(context).getIT().getViewCounter() >= 1) ) {
            clickSound.playClickDirt();
        }
        if(DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 20,2);
        } else
        if(GoldShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 35,2);
        } else
        if(IronShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 40,2);
        } else
        if(StoneShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.dirtwork, 45,2);
        } else
        if(WoodShovel.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.dirtwork, 50,2);
        } else {
            woodCreator();
        }
    }

    public void waterCreatorMute(){

        if(BucketEmpty.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.waterwork, 20,3);
        }else {
            woodCreatorMute();
        }
    }

    public void waterCreator(){

        if (SoundSwitcher.SOUND_CHEKER == 1 &&
                BucketEmpty.getInstance(context).getIT().getViewCounter() >= 1) {
            clickSound.playClickWater();
        }
        if(BucketEmpty.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.waterwork, 20,3);
        }else {
            woodCreator();
        }
    }

    public void grassCreatorMute(){

        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 10,4);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 20,4);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 30,4);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 35,4);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 40,4);
        } else {
            woodCreatorMute();
        }
    }

    public void grassCreator(){
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1) ) {
            clickSound.playClickGrass();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 10,4);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 20,4);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 30,4);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 35,4);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.grasswork, 40,4);
        } else {
            woodCreator();
        }
    }

    public Completable getWorker(){
        return Completable.fromRunnable(() -> {
            int rand = random.nextInt(100);
            if (rand <= 60){
                woodCreatorMute();
            } else if (rand > 60 && rand < 75){
                dirtCreatorMute();
            } else if (rand >= 75 && rand < 95){
                grassCreatorMute();
            } else if (rand >= 95 ){
                waterCreatorMute();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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


