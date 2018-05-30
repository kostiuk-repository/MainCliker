package com.nerkingames.mineclicker.Views.Buttons.PoolButton;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.Game.GameFragments.JobFragment.JobFragment;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.music.MusicPlayer;

import static android.content.ContentValues.TAG;

/**
 * Created by vladyslav on 14.03.18.
 */

public class AdShow extends RelativeLayout implements View.OnClickListener, RewardedVideoAdListener {

    private BitmapDrawable bitmapDrawable;
    private int mWidth;
    private int mHeight;
    private Dialog dialog;
    private RewardedVideoAd mRewardedVideoAd;
    private MusicPlayer musicPlayer;




    public AdShow(Activity activity,Context context) {
        super(context);
        initView(context, activity);

    }

    private void initView(Context context, Activity activity) {
        musicPlayer = MusicPlayer.getInstanse(context);
        MobileAds.initialize(context,
                "ca-app-pub-7435705988077014~3307924235");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-7435705988077014/7143227465",
                new AdRequest.Builder().build());
        bitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.up_bar3));
        this.setBackground(bitmapDrawable);
        mWidth = bitmapDrawable.getBitmap().getWidth();
        mHeight = bitmapDrawable.getBitmap().getHeight();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(context.getResources()
                .getDisplayMetrics().widthPixels * 0.4291f),mHeight);
        this.setLayoutParams(params);
        this.setOnClickListener(this);
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.ad_dialog);
        Button dialogButtonYes =  dialog.findViewById(R.id.btn_yes);
        dialogButtonYes.setOnClickListener(v1 -> {
            dialog.dismiss();
//            loadRewardedVideoAd();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
            } else {
                
            }
        });
        Button dialogButtonNo =  dialog.findViewById(R.id.btn_no);
        dialogButtonNo.setOnClickListener(v1 -> dialog.dismiss());
    }

    @Override
    public void onClick(View v) {
        dialog.show();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-7435705988077014/7143227465",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {
        GameActivity.onStop = !GameActivity.onStop;
    }

    @Override
    public void onRewardedVideoStarted() {
        if (!musicPlayer.dontWork() && musicPlayer.isInit("Start Activity Check")
                && MusicSwitcher.MUZIK_CHEKER == 1) {
            musicPlayer.pause();
        }
    }

    @Override
    public void onRewardedVideoAdClosed() {
        if (musicPlayer.dontWork() && musicPlayer.isInit("Start Activity Check")
                && MusicSwitcher.MUZIK_CHEKER == 1) {
            musicPlayer.start();
        }
        GameActivity.onStop = !GameActivity.onStop;
        loadRewardedVideoAd();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.d(TAG, "onRewarded: before add_coins");
        addCoins(rewardItem.getAmount());
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        loadRewardedVideoAd();
    }

    private void addCoins(int coins) {
        JobFragment.getUpBarLeftSide().setTextFieldCounter(JobFragment.getUpBarLeftSide().getTextFieldCounter()+coins);

    }


}
