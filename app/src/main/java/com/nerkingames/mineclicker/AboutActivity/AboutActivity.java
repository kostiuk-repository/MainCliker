package com.nerkingames.mineclicker.AboutActivity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.nerkingames.mineclicker.Views.Buttons.ButtonsAboutActivity.InstaButt;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsAboutActivity.VkButton;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsAboutActivity.YouTubeButton;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonAboutActivity;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.music.MusicPlayer;

import java.util.Map;

import javax.inject.Inject;


import dagger.android.AndroidInjection;


public class AboutActivity extends AppCompatActivity {

    @Inject
    AboutActivityPresenter presenter;

    @Inject
    Map<String,SimpleButtonAboutActivity> buttonAboutActivityMap;
    RelativeLayout aboutActivityContainer;
    private View instagramButton;
    private View vkButton;
    private View youtubeButton;
    private View infoViewField;
    BroadcastReceiver broadcastReceiver;
    MusicPlayer musicPlayer = MusicPlayer.getInstanse(this);
    FrameLayout mainStage;
    BitmapDrawable logo;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.about_activity);


        //
        aboutActivityContainer =  findViewById(R.id.about_layer);

        MobileAds.initialize(this,"ca-app-pub-7435705988077014~3307924235");
        mAdView = findViewById(R.id.adViewAbout);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mainStage = new FrameLayout(getApplicationContext());
        mainStage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logo = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.abaout_logo));
        mainStage.setBackground(logo);
        mainStage.setX(getResources().getDisplayMetrics().widthPixels/2 - logo.getBitmap().getWidth()/2);
        mainStage.setY(getResources().getDisplayMetrics().heightPixels * 0.12f);
        aboutActivityContainer.addView(mainStage);

        //
        instagramButton =  buttonAboutActivityMap.get(InstaButt.NAME_OF_BUTTON).getViewToContainer();
        aboutActivityContainer.addView(instagramButton);
        instagramButton.setX(getResources().getDisplayMetrics().widthPixels/2 - InstaButt.WIDTH_OF_VIEW/2);
        instagramButton.setY(getResources().getDisplayMetrics().heightPixels/4);

        //
        vkButton =  buttonAboutActivityMap.get(VkButton.NAME_OF_BUTTON).getViewToContainer();
        aboutActivityContainer.addView(vkButton);
        vkButton.setX(instagramButton.getX() - InstaButt.WIDTH_OF_VIEW * 1.5f);
        vkButton.setY(getResources().getDisplayMetrics().heightPixels/4);

        //
        youtubeButton =  buttonAboutActivityMap.get(YouTubeButton.NAME_OF_BUTTON).getViewToContainer();
        aboutActivityContainer.addView(youtubeButton);
        youtubeButton.setX(instagramButton.getX() + InstaButt.WIDTH_OF_VIEW * 1.5f);
        youtubeButton.setY(getResources().getDisplayMetrics().heightPixels/4);//

        //
        infoViewField =  buttonAboutActivityMap.get(AboutField.NAME_OF_BUTTON).getViewToContainer();
        aboutActivityContainer.addView(infoViewField);
        infoViewField.setX(getResources().getDisplayMetrics().widthPixels/2 - AboutField.WIDTH_OF_VIEW/2);
        infoViewField.setY(youtubeButton.getY() + InstaButt.HEIGHT_OF_VIEW*1.5f);



    }

    @Override
    protected void onResume() {
        Log.d("About","OnrResume");
        if(musicPlayer.dontWork()&& musicPlayer.isInit("About Activity Check")
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
                }
            }
        };
        this.registerReceiver(broadcastReceiver, intentFilterACSD);
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("About","OnStop");
        unregisterReceiver(broadcastReceiver);

    }

    @Override
    protected void onDestroy() {
        Log.d("About","OnDestroy");
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        Log.d("About","OnPause");

        super.onPause();
       /* if(!musicPlayer.dontWork() && musicPlayer.isInit("About Activity Check"))
            musicPlayer.pause();*/
    }
}
