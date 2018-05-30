package com.nerkingames.mineclicker.start_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.SoundSwitcher;
import com.nerkingames.mineclicker.music.ClickSound;

/**
 * Created by vladyslav on 17.03.18.
 */

public class MyOnTouch implements View.OnTouchListener {



    ClickSound clickSound;
    View thisView;
    SharedPreferences sPref;
    Context context;
    RelativeLayout relativeLayout;

    public MyOnTouch(Context context, View view, RelativeLayout relativeLayout) {
        this.context = context;
        clickSound = ClickSound.getInstance(context);
        thisView = view;
        this.relativeLayout = relativeLayout;

    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_BUTTON_RELEASE:

                break;
            case MotionEvent.ACTION_DOWN:
                if(SoundSwitcher.SOUND_CHEKER ==1) {
                    clickSound.playClick();
                }
                thisView.setBackgroundResource(R.mipmap.start_button_02);
                break;
            case MotionEvent.ACTION_UP:
                thisView.setBackgroundResource(R.mipmap.start_button_01);
                if (StartScreenActivity.FIRST_LAUNCH) {
                    sPref = context.getSharedPreferences("APP_PREF",context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    ed.putBoolean("RESOLVE_ANIMATION", false);
                    ed.commit();
                    relativeLayout.setVisibility(View.VISIBLE);
                    relativeLayout.getParent().bringChildToFront(relativeLayout);
                    Intent intent = new Intent(context.getApplicationContext(), GameActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_SCREEN_ON);
                    intent.setAction(Intent.ACTION_USER_PRESENT);
                    context.startActivity(intent);
                } else {

                    relativeLayout.setVisibility(View.VISIBLE);
                    relativeLayout.getParent().bringChildToFront(relativeLayout);
                    Intent intent = new Intent(context.getApplicationContext(), GameActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_SCREEN_ON);
                    intent.setAction(Intent.ACTION_USER_PRESENT);
                    context.startActivity(intent);

                }
                break;
        }

        return false;
    }
}
