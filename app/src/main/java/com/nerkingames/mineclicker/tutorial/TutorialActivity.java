package com.nerkingames.mineclicker.tutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.music.MusicPlayer;

public class TutorialActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    MusicPlayer musicPlayer = MusicPlayer.getInstanse(this);

    @Override
    protected void onResume() {
        super.onResume();
        if(musicPlayer.getMediaPlayer() != null) {
            Log.d("Media player","NOT NULL");
            if (musicPlayer.dontWork() && musicPlayer.isInit("Start Activity Check")
                    && MusicSwitcher.MUZIK_CHEKER == 1) {
                musicPlayer.start();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_tutorial);

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



    }
}
