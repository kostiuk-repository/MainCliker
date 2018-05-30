package com.nerkingames.mineclicker.start_activity;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.AboutButton;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.Achivments;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.SettingButton;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.StartButon;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonStartActivity;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.SoundSwitcher;
import com.nerkingames.mineclicker.music.MusicPlayer;

import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class StartScreenActivity extends AppCompatActivity {


    private Random random = new Random();
    private String[] sign;


    @Inject
    Map<String,SimpleButtonStartActivity> buttonStartActivityMap;
    public static boolean FIRST_LAUNCH = true;

    MusicPlayer musicPlayer = MusicPlayer.getInstanse(this);
    RelativeLayout startActivityLayout;
    View startNewGame;
    View settingButton;
    View aboutButton;
    View disableAd;
    TextView interactiveTitle;
    TextView interactiveTitleShadow;
    private AdView mAdView;
    boolean ActivityBackPressStatus = false;
    BroadcastReceiver broadcastReceiver;
    FrameLayout mainStage;
    FrameLayout mainStage2;
    BitmapDrawable logo;
    BitmapDrawable logo2;
    private SharedPreferences sPref;
    private String TAG = "StartActivity";
    private View achButton;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityBackPressStatus = true;
        if(musicPlayer != null) {
            musicPlayer.pause();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: " + GameActivity.CHECK_VAR);
        Log.d("start_activity","onCreate");
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_start_screen);
        loadMusicFlag();
        RelativeLayout relativeLayout2 = findViewById(R.id.load_screen);
        relativeLayout2.setVisibility(View.INVISIBLE);
        //
        startActivityLayout =  findViewById(R.id.start_acvtivity);
        sign = getResources().getStringArray(R.array.splashs);

        MobileAds.initialize(this,"ca-app-pub-7435705988077014~3307924235");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mainStage = new FrameLayout(getApplicationContext());
        mainStage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.mipmap.minelogo));
        mainStage.setBackground(logo);
        mainStage.setX(getResources().getDisplayMetrics().widthPixels/2 - logo.getBitmap().getWidth()/2);
        mainStage.setY(getResources().getDisplayMetrics().heightPixels * 0.01f);
        startActivityLayout.addView(mainStage);



        //
        startNewGame =  buttonStartActivityMap.get(StartButon.NAME_OF_BUTTON).getViewToContainer();
        startActivityLayout.addView(startNewGame);
        startNewGame.setX(getResources().getDisplayMetrics().widthPixels/2 - StartButon.WIDTH_OF_VIEW/2);
        startNewGame.setY(getResources().getDisplayMetrics().heightPixels/3);
        startNewGame.setOnTouchListener(new MyOnTouch(getApplicationContext(),buttonStartActivityMap.get(StartButon.NAME_OF_BUTTON).getViewToContainer(), relativeLayout2));

        //
        settingButton =  buttonStartActivityMap.get(SettingButton.NAME_OF_BUTTON).getViewToContainer();
        startActivityLayout.addView(settingButton);
        settingButton.setX(getResources().getDisplayMetrics().widthPixels/2 - StartButon.WIDTH_OF_VIEW/2);
        settingButton.setY(getResources().getDisplayMetrics().heightPixels/3 + 1.25f * SettingButton.HEIGHT_OF_VIEW);

        //
        aboutButton =  buttonStartActivityMap.get(AboutButton.NAME_OF_BUTTON).getViewToContainer();
        startActivityLayout.addView(aboutButton);
        aboutButton.setX(getResources().getDisplayMetrics().widthPixels/2 - StartButon.WIDTH_OF_VIEW/2);
        aboutButton.setY(getResources().getDisplayMetrics().heightPixels/3 +
                1.25f * SettingButton.HEIGHT_OF_VIEW
                + 1.25f * SettingButton.HEIGHT_OF_VIEW);

        achButton =  buttonStartActivityMap.get(Achivments.NAME_OF_BUTTON).getViewToContainer();
        startActivityLayout.addView(achButton);
        achButton.setX(getResources().getDisplayMetrics().widthPixels/2 - StartButon.WIDTH_OF_VIEW/2);
        achButton.setY(getResources().getDisplayMetrics().heightPixels/3 +
                1.25f * SettingButton.HEIGHT_OF_VIEW
                + 1.25f * SettingButton.HEIGHT_OF_VIEW
                + 1.25f * SettingButton.HEIGHT_OF_VIEW);



        Animation scaleAnimation = new ScaleAnimation(1.0f, 1.2f,1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        FrameLayout fl = findViewById(R.id.fl);
        fl.setAnimation(scaleAnimation);
        fl.setRotation(-45);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(getResources().getDisplayMetrics().widthPixels/2 - StartButon.WIDTH_OF_VIEW/2 - StartButon.WIDTH_OF_VIEW/6 , (int) (getResources().getDisplayMetrics().heightPixels/3 + 1.5f * SettingButton.HEIGHT_OF_VIEW + 1.5f * SettingButton.HEIGHT_OF_VIEW + 1.5f * SettingButton.HEIGHT_OF_VIEW - SettingButton.HEIGHT_OF_VIEW/4), 0, 0);
        interactiveTitleShadow = findViewById(R.id.textView2);
        interactiveTitle = findViewById(R.id.textView);
        startActivityLayout.bringChildToFront(interactiveTitle);
        startActivityLayout.bringChildToFront(interactiveTitleShadow);
        fl.bringToFront();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("start_activity","onresume");
        RelativeLayout relativeLayout2 = findViewById(R.id.load_screen);
        relativeLayout2.setVisibility(View.INVISIBLE);


        interactiveTitle.setText(sign[random.nextInt(sign.length)]);
        interactiveTitleShadow.setText(interactiveTitle.getText());
        resolveTextSize(interactiveTitle,this);
        resolveTextSize(interactiveTitleShadow,this);

        if(MusicSwitcher.MUZIK_CHEKER == 1){
            if(!musicPlayer.isInit("Start_Activity_Cheking_for_init")){
                musicPlayer.init();
                musicPlayer.playSong(R.raw.mainmusic);
                musicPlayer.setInit(!musicPlayer.isInit("Start_ACtivity_init_player"));
            }
        }

        if(musicPlayer.getMediaPlayer() != null) {
            Log.d("Media player","NOT NULL");
            if (musicPlayer.dontWork() && musicPlayer.isInit("Start Activity Check")
                    && MusicSwitcher.MUZIK_CHEKER == 1) {
                musicPlayer.start();
            }
        }

        IntentFilter intentFilterACSD = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                    Log.d("Start ACtivity","Home button");
                    if(!musicPlayer.dontWork() && musicPlayer.isInit("Start Activity Check")
                            && MusicSwitcher.MUZIK_CHEKER == 1)
                   musicPlayer.pause();
                }
            }
        };
        this.registerReceiver(broadcastReceiver, intentFilterACSD);

        Log.d(TAG, "onResume: " + GameActivity.CHECK_VAR);
    }


    @Override
    protected void onStop() {
        Log.d("start_activity","onStop");
        super.onStop();
        unregisterReceiver(broadcastReceiver);

    }

    @Override
    protected void onDestroy() {
        Log.d("start_activity","onDestroy");
        if(!ActivityBackPressStatus) {
            Log.d("start_activity","DestroyPlayer");
            musicPlayer.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("start_activity","onPause");
        super.onPause();
    }



    void loadMusicFlag() {
        sPref = getSharedPreferences("APP_PREF",MODE_PRIVATE);
        MusicSwitcher.MUZIK_CHEKER  = Integer.valueOf(sPref.getString("RESOLVE_MUSIC", "0"));
        SoundSwitcher.SOUND_CHEKER  = Integer.valueOf(sPref.getString("RESOLVE_SOUND", "1"));
        StartScreenActivity.FIRST_LAUNCH  = sPref.getBoolean("RESOLVE_ANIMATION", true);

    }


    public void resolveTextSize(TextView view, Activity context){

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_HIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XXHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XXXHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE
                && density == DisplayMetrics.DENSITY_HIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        }

    }


}
