package com.nerkingames.mineclicker.Game.GameActivity;


import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nerkingames.mineclicker.Game.GameFragments.AchievementsFragment.AchievementsFragment;
import com.nerkingames.mineclicker.Game.GameFragments.JobFragment.JobFragment;
import com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment.UpWorldFragment;
import com.nerkingames.mineclicker.Game.ViewPager.MyViewPagerFragmentAdapter;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.Views.JobControllers.UpWorldWorker;
import com.nerkingames.mineclicker.music.MusicPlayer;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingFragmentInjector;

public class GameActivity extends AppCompatActivity implements HasDispatchingFragmentInjector,UpWorldFragment.OnFragmentInteractionListener,JobFragment.OnFragmentInteractionListener,AchievementsFragment.OnFragmentInteractionListener {

    private static final String TAG = "Game";
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    public static int CHECK_VAR = 0;
    public static boolean onStop = false;
    private AdView mAdView;
    private  ViewPager viewPager;

    public static volatile MyViewPagerFragmentAdapter myViewPagerFragmentAdapter;
    private MusicPlayer musicPlayer;
    private BroadcastReceiver broadcastReceiver;
    UpWorldWorker upWorldWorker;
    static volatile  RelativeLayout relativeLayout;


    public volatile static RelativeLayout relativeLayout2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        onStop = false;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        setContentView(R.layout.activity_game);

        MobileAds.initialize(this,"ca-app-pub-7435705988077014~3307924235");
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        viewPager = findViewById(R.id.view_pager_game_activity);
        myViewPagerFragmentAdapter = new MyViewPagerFragmentAdapter(getFragmentManager(),viewPager);
        viewPager.setAdapter(myViewPagerFragmentAdapter);
        viewPager.setCurrentItem(1);





        musicPlayer = MusicPlayer.getInstanse(getApplicationContext());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ACtivity onPause state of var onStop: "+ onStop);
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e){

        }
        onStop = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        onStop = false;
        Log.d(TAG, "state of var onStop: "+ onStop);
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
                }
            }
        };
        this.registerReceiver(broadcastReceiver, intentFilterACSD);
        Log.d(TAG, "onResume: pass thr referense");
        relativeLayout = findViewById(R.id.mainStage);
        relativeLayout2 = new RelativeLayout(getApplicationContext());
        relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        relativeLayout.addView(relativeLayout2);

    }

    @Override
    public void onBackPressed() {
        CHECK_VAR = 1;
        /*Intent intent = new Intent(getApplicationContext().getApplicationContext(), GameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);*/
        super.onBackPressed();
        onStop = true;
    }


    @Override
    protected void onStop() {
        CHECK_VAR = 1;
        onStop = true;
        Log.d(TAG, "state of var onStop: "+ onStop);
        Log.d(TAG, "onStop: Game Stop");
        super.onStop();

        viewPager.clearOnPageChangeListeners();
        upWorldWorker = UpWorldWorker.getInstance(getApplicationContext());
        if(upWorldWorker.getDisposable() != null) {
            upWorldWorker.getDisposable().dispose();
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        onStop = false;
    }
}
