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
import com.nerkingames.mineclicker.Views.JobControllers.DiamondPickaxe;
import com.nerkingames.mineclicker.Views.JobControllers.DiamondShovel;
import com.nerkingames.mineclicker.Views.JobControllers.GoldPickaxe;
import com.nerkingames.mineclicker.Views.JobControllers.GoldShovel;
import com.nerkingames.mineclicker.Views.JobControllers.IronPickaxe;
import com.nerkingames.mineclicker.Views.JobControllers.IronShovel;
import com.nerkingames.mineclicker.Views.JobControllers.MineWorker;
import com.nerkingames.mineclicker.Views.JobControllers.StonePickaxe;
import com.nerkingames.mineclicker.Views.JobControllers.StoneShovel;
import com.nerkingames.mineclicker.Views.JobControllers.WoodPickaxe;
import com.nerkingames.mineclicker.Views.JobControllers.WoodShovel;
import com.nerkingames.mineclicker.music.ClickSound;

import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vladyslav on 06.03.18.
 */

public class Mine extends View implements View.OnTouchListener,IViewGetter {


    private static final String TAG = "my_Tag";
    private int vWidth, vHeight, Differential;
    private Bitmap backgroundBitmap;
    private RectMine rect;
    private Context context;
    private FrameLayout thisView;
    private ClickSound clickSound;
    public static final String BUTTON_ID = "MINE";
    Random random = new Random();
    private boolean isClicked = false;
    private boolean onetime = true;
    private RelativeLayout upWorldContainer;
    private FrameLayout help;
    private Disposable disposable;
    private MineWorker mineWorker;

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
                if (mineWorker.getIT().getViewCounter() > 0) {
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


    public Mine(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mine);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.mine));
        rect = new RectMine(context, this);
        vWidth = backgroundBitmap.getWidth();
        vHeight = backgroundBitmap.getHeight();
        thisView.addView(rect.getThisView());
        Differential = (vHeight - rect.getRectangleHeight()) / 2;
        rect.getThisView().setX(vWidth - rect.getRectangleWidth() - 25);
        rect.getThisView().setY(Differential);
        thisView.setOnTouchListener(this);
        thisView.setClickable(false);
        mineWorker = MineWorker.getInstance(context);
        mineWorker.setMine(this);
        clickSound = ClickSound.getInstance(context);
        help = createHelp(R.mipmap.mine_help);



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

                if(!isClicked && MineWorker.getInstance(context).getIT().getViewCounter()<1) {
                    isClicked = true;


                    if (mineWorker.getIT().getViewCounter() <= 0) {
                        int rand = random.nextInt(100);
                        if (rand <= 55){
                            stoneCreator();
                        } else if (rand > 55 && rand <= 65){
                            coalCreator();
                        } else if (rand > 65 && rand <= 73){
                            ironCreator();
                        } else if (rand > 73 && rand <= 82){
                            gravelCreator();
                        } else if (rand > 82 && rand <= 89){
                            goldCreator();
                        } else if (rand > 89 && rand <= 94){
                            diamondCreator();
                        } else if (rand > 94 && rand <= 100){
                            lavaCreator();
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

    private void gravelCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneShovel.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodShovel.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMine();
        }
        if(DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 10,4);
        } else
        if(GoldShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 20,4);
        } else
        if(IronShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 30,4);
        } else
        if(StoneShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 40,4);
        } else
        if(WoodShovel.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.gravel_mine, 50,4);
        } else {
            if(WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1
                    || StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1
                    || IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1
                    || GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1
                    || DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1) {
                stoneCreator();
            } else {
                isClicked = false;
            }
        }
    }

    private void gravelCreatorMute() {

        if(DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 10,4);
        } else
        if(GoldShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 20,4);
        } else
        if(IronShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 30,4);
        } else
        if(StoneShovel.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.gravel_mine, 40,4);
        } else
        if(WoodShovel.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.gravel_mine, 50,4);
        }
    }

    private void lavaCreator() {
        if (SoundSwitcher.SOUND_CHEKER == 1 &&
                BucketEmpty.getInstance(context).getIT().getViewCounter() >= 1) {
            clickSound.playClickWater();
        }
        if(BucketEmpty.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.lava_mine, 10,7);
        } else
        {
            stoneCreator();
        }
    }

    private void lavaCreatorMute() {

        if(BucketEmpty.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.lava_mine, 10,7);
        } else
        {
            stoneCreatorMute();
        }
    }



    private void diamondCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMine();
        }
        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.diamondore_mine, 70,6);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.diamondore_mine, 80,6);
        } else
        {
            stoneCreator();
        }
    }

    private void diamondCreatorMute() {

        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.diamondore_mine, 70,6);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.diamondore_mine, 80,6);
        } else
        {
            stoneCreatorMute();
        }
    }

    private void goldCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMine();
        }
        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.goldore_mine, 50,5);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.goldore_mine, 60,5);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.goldore_mine, 70,5);
        } else

        {
            stoneCreator();
        }
    }

    private void goldCreatorMute() {

        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.goldore_mine, 50,5);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.goldore_mine, 60,5);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.goldore_mine, 70,5);
        } else
        {
            stoneCreatorMute();
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
        if(WoodShovel.getInstance(context).getIT().getViewCounter() >= 1
                || StoneShovel.getInstance(context).getIT().getViewCounter() >= 1
                || GoldShovel.getInstance(context).getIT().getViewCounter() >= 1
                || IronShovel.getInstance(context).getIT().getViewCounter() >= 1
                || DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1
                || WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1
                || StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1
                || IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1
                || GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1
                || DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1) {
            return Completable.fromRunnable(() -> {
                int rand = random.nextInt(100);
                if (rand <= 55) {
                    stoneCreatorMute();
                } else if (rand > 55 && rand <= 65) {
                    coalCreatorMute();
                } else if (rand > 65 && rand <= 73) {
                    ironCreatorMute();
                } else if (rand > 73 && rand <= 82) {
                    gravelCreatorMute();
                } else if (rand > 82 && rand <= 89) {
                    goldCreatorMute();
                } else if (rand > 89 && rand <= 94) {
                    diamondCreatorMute();
                } else if (rand > 94 && rand <= 100) {
                    lavaCreatorMute();
                }
            }).subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            WoodShovel.getInstance(context).getIT().setViewCounter(1);
            WoodPickaxe.getInstance(context).getIT().setViewCounter(1);
            return Completable.fromRunnable(() -> {
                int rand = random.nextInt(100);
                if (rand <= 55) {
                    stoneCreatorMute();
                } else if (rand > 55 && rand <= 65) {
                    coalCreatorMute();
                } else if (rand > 65 && rand <= 73) {
                    ironCreatorMute();
                } else if (rand > 73 && rand <= 82) {
                    gravelCreatorMute();
                } else if (rand > 82 && rand <= 89) {
                    goldCreatorMute();
                } else if (rand > 89 && rand <= 94) {
                    diamondCreatorMute();
                } else if (rand > 94 && rand <= 100) {
                    lavaCreatorMute();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    public void stoneCreator(){
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMine();
        }
        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 15,1);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 20,1);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 30,1);
        } else
        if(StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 40,1);
        } else
        if(WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.stone_mine, 50,1);
        } else {
            if(WoodShovel.getInstance(context).getIT().getViewCounter() >= 1
                    || StoneShovel.getInstance(context).getIT().getViewCounter() >= 1
                    || GoldShovel.getInstance(context).getIT().getViewCounter() >= 1
                    || IronShovel.getInstance(context).getIT().getViewCounter() >= 1
                    || DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1) {
                gravelCreator();
            } else {
                isClicked = false;
            }
        }
    }

    public void stoneCreatorMute(){

        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 15,1);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 20,1);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 30,1);
        } else
        if(StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.stone_mine, 40,1);
        } else
        if(WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.stone_mine, 50,1);
        }
    }


    public void coalCreator(){
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMine();
        }
        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 20,2);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 30,2);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 40,2);
        } else
        if(StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 50,2);
        } else
        if(WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.coal_mine, 70,2);
        } else {
                if(WoodShovel.getInstance(context).getIT().getViewCounter() >= 1
                        || StoneShovel.getInstance(context).getIT().getViewCounter() >= 1
                        || GoldShovel.getInstance(context).getIT().getViewCounter() >= 1
                        || IronShovel.getInstance(context).getIT().getViewCounter() >= 1
                        || DiamondShovel.getInstance(context).getIT().getViewCounter() >= 1) {
                    gravelCreator();
                } else {
                    isClicked = false;
                }
        }
    }
    public void coalCreatorMute(){

        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 20,2);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 30,2);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 40,2);
        } else
        if(StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.coal_mine, 50,2);
        } else
        if(WoodPickaxe.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.coal_mine, 70,2);
        }
    }

    public void ironCreator(){
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1) ) {
            clickSound.playClickMine();
        }
        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 30,3);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 40,3);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 50,3);
        } else
        if(StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 60,3);
        } else
            {
                stoneCreator();
            }
    }

    public void ironCreatorMute(){

        if(DiamondPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 30,3);
        } else
        if(GoldPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 40,3);
        } else
        if(IronPickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 50,3);
        } else
        if(StonePickaxe.getInstance(context).getIT().getViewCounter() >= 1){
            rect.mPerformClick(R.mipmap.iron_mine, 60,3);
        } else
        {
            stoneCreatorMute();
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
