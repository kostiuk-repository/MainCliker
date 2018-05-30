package com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonSettingActivity;
import com.nerkingames.mineclicker.music.ClickSound;
import com.nerkingames.mineclicker.music.MusicPlayer;

/**
 * Created by vladyslav on 23.02.18.
 */

public class SoundSwitcher extends View implements SimpleButtonSettingActivity {

    public static int SOUND_CHEKER = 0;
    public static final String NAME_OF_BUTTON = "SOUND_BUTTON";
    public static int HEIGHT_OF_VIEW;
    public static int WIDTH_OF_VIEW;

    private View thisView;
    private Context context;
    private Bitmap bitmap;
    public MusicPlayer musicPlayer;
    private ClickSound clickSound;

    public SoundSwitcher(Context context) {
        super(context);
        this.context = context;
        initView();
        musicPlayer = MusicPlayer.getInstanse(context);

    }

    @Override
    public View getViewToContainer() {
        return thisView;
    }

    @Override
    public void initView() {

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sound_action_button_01);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && SOUND_CHEKER == 0) {
            thisView.setBackground(getResources().getDrawable(R.mipmap.sound_action_off_button_02));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && SOUND_CHEKER == 1) {
            thisView.setBackground(getResources().getDrawable(R.mipmap.sound_button_01));
        }

        thisView.setOnTouchListener(this);
        thisView.setClickable(true);
        WIDTH_OF_VIEW = bitmap.getWidth();
        HEIGHT_OF_VIEW = bitmap.getHeight();
        clickSound = ClickSound.getInstance(context);

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                clickSound.playClick();
                if (SOUND_CHEKER == 0) {
                    thisView.setBackgroundResource(R.mipmap.sound_action_off_button_01);
                } else {
                    thisView.setBackgroundResource(R.mipmap.sound_action_button_01);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (SOUND_CHEKER == 0) {
                    Log.d("MusSwitc", "You_Click_on_Music_on");
                    SOUND_CHEKER = 1;
                    thisView.setBackgroundResource(R.mipmap.sound_button_01);
                } else {
                    Log.d("SoundSwitcher", "You_Click_on_sound_off");
                    SOUND_CHEKER = 0;
                    thisView.setBackgroundResource(R.mipmap.sound_action_off_button_02);
                }

                break;
        }


        return false;
    }
}
