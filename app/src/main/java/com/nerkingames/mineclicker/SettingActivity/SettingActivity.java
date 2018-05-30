package com.nerkingames.mineclicker.SettingActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonSettingActivity;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.SoundSwitcher;
import com.nerkingames.mineclicker.music.MusicPlayer;
import com.nerkingames.mineclicker.tutorial.TutorialActivity;

import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by vladyslav on 21.02.18.
 */

public class SettingActivity extends AppCompatActivity {


    @Inject
    Map<String,SimpleButtonSettingActivity> buttonAboutActivityMap;
    RelativeLayout settingActivityContainer;
    private View musikButton;
    private View soundButton;
    MusicPlayer musicPlayer = MusicPlayer.getInstanse(this);
    BroadcastReceiver broadcastReceiver;
    FrameLayout mainStage;
    FrameLayout mainStage2;
    BitmapDrawable logo;
    BitmapDrawable logo2;
    private AdView mAdView;
    private SharedPreferences sPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        Log.d("Setting","OnCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.setting_activity);



        //
        settingActivityContainer =  findViewById(R.id.setting_activity);



        MobileAds.initialize(this,"ca-app-pub-7435705988077014~3307924235");
        mAdView = findViewById(R.id.adViewSetting);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mainStage = new FrameLayout(getApplicationContext());
        mainStage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.setting_logo));
        mainStage.setBackground(logo);
        mainStage.setX(getResources().getDisplayMetrics().widthPixels/2 - logo.getBitmap().getWidth()/2);
        mainStage.setY(getResources().getDisplayMetrics().heightPixels * 0.12f);
        settingActivityContainer.addView(mainStage);

        //
        musikButton =  buttonAboutActivityMap.get(MusicSwitcher.NAME_OF_BUTTON).getViewToContainer();
        settingActivityContainer.addView(musikButton);
        musikButton.setX(getResources().getDisplayMetrics().widthPixels/2 - MusicSwitcher.WIDTH_OF_VIEW/2);
        musikButton.setY(getResources().getDisplayMetrics().heightPixels/3);


        //
        soundButton =  buttonAboutActivityMap.get(SoundSwitcher.NAME_OF_BUTTON).getViewToContainer();
        settingActivityContainer.addView(soundButton);
        soundButton.setX(getResources().getDisplayMetrics().widthPixels/2 - SoundSwitcher.WIDTH_OF_VIEW/2);
        soundButton.setY(getResources().getDisplayMetrics().heightPixels/3 + 1.5f * SoundSwitcher.HEIGHT_OF_VIEW);

        mainStage2 = new FrameLayout(getApplicationContext());
        mainStage2.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo2 = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.tutorial_on));
        mainStage2.setBackground(logo2);
        mainStage2.setX(getResources().getDisplayMetrics().widthPixels/2 - logo2.getBitmap().getWidth()/2);
        mainStage2.setY(getResources().getDisplayMetrics().heightPixels/3 + 3f * SoundSwitcher.HEIGHT_OF_VIEW);
        settingActivityContainer.addView(mainStage2);
        mainStage2.setClickable(true);
        mainStage2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mainStage2.setBackgroundResource(R.mipmap.tutorial_off);
                        break;
                    case MotionEvent.ACTION_UP:
                            mainStage2.setBackgroundResource(R.mipmap.tutorial_on);
                            Intent intent = new Intent(getApplicationContext().getApplicationContext(), TutorialActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setAction(Intent.ACTION_SCREEN_ON);
                            intent.setAction(Intent.ACTION_USER_PRESENT);
                            getApplicationContext().startActivity(intent);
                        break;
                }
                return false;
            }
        });



    }


    @Override
    protected void onResume() {
        Log.d("Setting","OnResume");
        if(musicPlayer.dontWork() && musicPlayer.isInit("Setting Activity Check")
                && MusicSwitcher.MUZIK_CHEKER == 1){
            musicPlayer.start();
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
                    saveMusicFlag();
                }
            }
        };
        this.registerReceiver(broadcastReceiver, intentFilterACSD);



        super.onResume();
    }

    @Override
    protected void onStop() {
        saveMusicFlag();
        Log.d("Setting","OnStop");
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveMusicFlag();
    }

    @Override
    protected void onDestroy() {
        Log.d("Setting","OnDestroy");
        saveMusicFlag();
        super.onDestroy();
        this.unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onPause() {
        Log.d("Setting","OnPause");
        super.onPause();
        saveMusicFlag();
    }

    void saveMusicFlag() {
        sPref = getSharedPreferences("APP_PREF",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("RESOLVE_MUSIC", String.valueOf(MusicSwitcher.MUZIK_CHEKER));
        ed.putString("RESOLVE_SOUND", String.valueOf(SoundSwitcher.SOUND_CHEKER));
        ed.commit();
    }




}
