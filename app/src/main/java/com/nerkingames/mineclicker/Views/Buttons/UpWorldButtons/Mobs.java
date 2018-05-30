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
import com.nerkingames.mineclicker.Views.JobControllers.AggressiveMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.DiamondSword;
import com.nerkingames.mineclicker.Views.JobControllers.GoldSword;
import com.nerkingames.mineclicker.Views.JobControllers.GoodMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.IronSword;
import com.nerkingames.mineclicker.Views.JobControllers.Lead;
import com.nerkingames.mineclicker.Views.JobControllers.StoneSword;
import com.nerkingames.mineclicker.Views.JobControllers.WoodSword;
import com.nerkingames.mineclicker.music.ClickSound;

import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.content.ContentValues.TAG;

/**
 * Created by vladyslav on 06.03.18.
 */

public class Mobs extends View implements View.OnTouchListener,IViewGetter {


    private int vWidth, vHeight, Differential;
    private Bitmap backgroundBitmap;
    private RectMobs rect;
    private Context context;
    private FrameLayout thisView;
    private GoodMobsWorker goodMobsWorker;
    private AggressiveMobsWorker aggressiveMobsWorker;
    private ClickSound clickSound;
    public static final String BUTTON_ID = "MOBS";
    private boolean isClicked = false;
    private Random random = new Random();
    private RelativeLayout upWorldContainer;
    private FrameLayout help;
    private boolean onetime = true;
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
                if (goodMobsWorker.getIT().getViewCounter() > 0
                        || aggressiveMobsWorker.getIT().getViewCounter() > 0) {
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


    public Mobs(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mobs);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.mobs));
        rect = new RectMobs(context, this);
        vWidth = backgroundBitmap.getWidth();
        vHeight = backgroundBitmap.getHeight();
        thisView.addView(rect.getThisView());
        Differential = (vHeight - rect.getRectangleHeight()) / 2;
        rect.getThisView().setX(vWidth - rect.getRectangleWidth() - 25);
        rect.getThisView().setY(Differential);
        thisView.setOnTouchListener(this);
        thisView.setClickable(false);
        aggressiveMobsWorker = AggressiveMobsWorker.getInstance(context);
        aggressiveMobsWorker.setMobs(this);
        goodMobsWorker = GoodMobsWorker.getInstance(context);
        goodMobsWorker.setMobs(this);
        clickSound = ClickSound.getInstance(context);
        help = createHelp(R.mipmap.mobs_help);


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

                if(!isClicked && GoodMobsWorker.getInstance(context).getIT().getViewCounter()<1
                        && AggressiveMobsWorker.getInstance(context).getIT().getViewCounter()<1) {
                    isClicked = true;


                    if (goodMobsWorker.getIT().getViewCounter() <= 0
                            && aggressiveMobsWorker.getIT().getViewCounter() <= 0) {
                        int rand = random.nextInt(100);
                        if (rand <= 15) {
                            skeletonCreator();
                        } else if (rand > 15 && rand <= 30) {
                            spiderCreator();
                        } else if (rand > 30 && rand <= 45) {
                            slimeCreator();
                        } else if (rand > 45 && rand <= 60) {
                            chickenCreator();
                        } else if (rand > 60 && rand <= 75) {
                            cowCreator();
                        } else if (rand > 75 && rand <= 90) {
                            sheepCreator();
                        } else if (rand > 90) {
                            horseCreator();
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

    private void horseCreator() {
        if (SoundSwitcher.SOUND_CHEKER == 1 && Lead.getInstance(context).getIT().getViewCounter() >= 1) {
            clickSound.playClickMobs();
        }
        if(Lead.getInstance(context).getIT().getViewCounter() >= 1) {
            Lead.getInstance(context).getIT().setViewCounter(Lead.getInstance(context).getIT().getViewCounter()-1);
            rect.mPerformClick(R.mipmap.horse_mob, 30,7);
        } else {
            slimeCreator();
        }
    }

    private void horseCreatorMute() {
        if(Lead.getInstance(context).getIT().getViewCounter() >= 1) {
            Lead.getInstance(context).getIT().setViewCounter(Lead.getInstance(context).getIT().getViewCounter()-1);
            rect.mPerformClick(R.mipmap.horse_mob, 80,7);
        } else {
            slimeCreatorMute();
        }
    }

    private void sheepCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMobs();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 10, 6);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 20, 6);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 30, 6);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 40, 6);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 50,6);
        } else {
            isClicked = false;
        }
    }

    private void sheepCreatorMute() {

        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 10, 6);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 20, 6);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 30, 6);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 40, 6);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.sheep_mob, 50,6);
        } else {
            isClicked = false;
        }
    }

    private void cowCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMobs();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 10, 5);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 20, 5);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 30, 5);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 40, 5);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 50,5);
        } else {
            isClicked = false;
        }
    }

    private void cowCreatorMute() {
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 10, 5);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 20, 5);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 30, 5);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 40, 5);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.cow_mob, 50,5);
        } else {
            isClicked = false;
        }
    }

    private void chickenCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMobs();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 10, 4);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 20, 4);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 30, 4);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 40, 4);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 50,4);
        } else {
            isClicked = false;
        }
    }

    private void chickenCreatorMute() {
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 10, 4);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 20, 4);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 30, 4);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 40, 4);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.chiken_mob, 50,4);
        } else {
            isClicked = false;
        }
    }

    private void slimeCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMobs();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 10, 3);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 20, 3);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 30, 3);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 40, 3);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 50,3);
        } else {
            isClicked = false;
        }
    }

    private void slimeCreatorMute() {

        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 10, 3);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 20, 3);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 30, 3);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 40, 3);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.slime_mob, 50,3);
        } else {
            isClicked = false;
        }
    }

    private void spiderCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMobs();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 10, 2);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 20, 2);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 30, 2);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 40, 2);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 50,2);
        } else {
            isClicked = false;
        }
    }

    private void spiderCreatorMute() {
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 10, 2);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 20, 2);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 30, 2);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 40, 2);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.spider_mob, 50,2);
        } else {
            isClicked = false;
        }
    }

    private void skeletonCreator() {
        if ((SoundSwitcher.SOUND_CHEKER == 1 && DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && GoldSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && IronSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && StoneSword.getInstance(context).getIT().getViewCounter() >= 1) ||
                (SoundSwitcher.SOUND_CHEKER == 1 && WoodSword.getInstance(context).getIT().getViewCounter() >= 1)) {
            clickSound.playClickMobs();
        }
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 10, 1);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 20, 1);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 30, 1);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 40, 1);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 50,1);
        } else {
            isClicked = false;
        }
    }

    private void skeletonCreatorMute() {
        if(DiamondSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 10, 1);
        } else
        if(GoldSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 20, 1);
        } else
        if(IronSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 30, 1);
        } else
        if(StoneSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 40, 1);
        } else
        if(WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            rect.mPerformClick(R.mipmap.skelet_mob, 50,1);
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
                WoodSword.getInstance(context).getIT().getViewCounter() >= 1) {
            if (GoodMobsWorker.getInstance(context).getIT().getViewCounter() > 0
                    && AggressiveMobsWorker.getInstance(context).getIT().getViewCounter() > 0) {
                return Completable.fromRunnable(() -> {
                    int rand = random.nextInt(100);
                    if (rand <= 15) {
                        skeletonCreatorMute();
                    } else if (rand > 15 && rand <= 30) {
                        spiderCreatorMute();
                    } else if (rand > 30 && rand <= 45) {
                        slimeCreatorMute();
                    } else if (rand > 45 && rand <= 60) {
                        chickenCreatorMute();
                    } else if (rand > 60 && rand <= 75) {
                        cowCreatorMute();
                    } else if (rand > 75 && rand <= 90) {
                        sheepCreatorMute();
                    } else if (rand > 90) {
                        horseCreatorMute();
                    }
                });
            } else if (AggressiveMobsWorker.getInstance(context).getIT().getViewCounter() > 0) {
                return Completable.fromRunnable(() -> {
                    int rand = random.nextInt(100);
                    if (rand <= 33) {
                        skeletonCreatorMute();
                    } else if (rand > 33 && rand <= 66) {
                        spiderCreatorMute();
                    } else if (rand > 66) {
                        slimeCreatorMute();
                    }
                });
            } else if (GoodMobsWorker.getInstance(context).getIT().getViewCounter() > 0) {
                return Completable.fromRunnable(() -> {
                    int rand = random.nextInt(100);
                    if (rand <= 25) {
                        chickenCreatorMute();
                    } else if (rand > 25 && rand <= 50) {
                        cowCreatorMute();
                    } else if (rand > 50 && rand <= 75) {
                        sheepCreatorMute();
                    } else if (rand > 75) {
                        horseCreatorMute();
                    }
                });
            }
        } else {
            WoodSword.getInstance(context).getIT().setViewCounter(1);
            if (GoodMobsWorker.getInstance(context).getIT().getViewCounter() > 0
                    && AggressiveMobsWorker.getInstance(context).getIT().getViewCounter() > 0) {
                return Completable.fromRunnable(() -> {
                    int rand = random.nextInt(100);
                    if (rand <= 15) {
                        skeletonCreatorMute();
                    } else if (rand > 15 && rand <= 30) {
                        spiderCreatorMute();
                    } else if (rand > 30 && rand <= 45) {
                        slimeCreatorMute();
                    } else if (rand > 45 && rand <= 60) {
                        chickenCreatorMute();
                    } else if (rand > 60 && rand <= 75) {
                        cowCreatorMute();
                    } else if (rand > 75 && rand <= 90) {
                        sheepCreatorMute();
                    } else if (rand > 90) {
                        horseCreatorMute();
                    }
                });
            } else if (AggressiveMobsWorker.getInstance(context).getIT().getViewCounter() > 0) {
                return Completable.fromRunnable(() -> {
                    int rand = random.nextInt(100);
                    if (rand <= 33) {
                        skeletonCreatorMute();
                    } else if (rand > 33 && rand <= 66) {
                        spiderCreatorMute();
                    } else if (rand > 66) {
                        slimeCreatorMute();
                    }
                });
            } else if (GoodMobsWorker.getInstance(context).getIT().getViewCounter() > 0) {
                return Completable.fromRunnable(() -> {
                    int rand = random.nextInt(100);
                    if (rand <= 25) {
                        chickenCreatorMute();
                    } else if (rand > 25 && rand <= 50) {
                        cowCreatorMute();
                    } else if (rand > 50 && rand <= 75) {
                        sheepCreatorMute();
                    } else if (rand > 75) {
                        horseCreatorMute();
                    }
                });
            }
        }
         return null;
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
