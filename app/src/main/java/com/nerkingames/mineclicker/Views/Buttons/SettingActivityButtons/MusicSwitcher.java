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
 * Created by vladyslav on 22.02.18.
 */

public class MusicSwitcher extends View implements SimpleButtonSettingActivity {

    public static int MUZIK_CHEKER = 1;
    public static final String  NAME_OF_BUTTON = "MUSIC_BUTTON";
    public static int WIDTH_OF_VIEW;

    private View thisView;
    private Context context;
    private Bitmap bitmap;
    private MusicPlayer musicPlayer;
    private ClickSound clickSound;

    public MusicSwitcher(Context context) {
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

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.music_button_01);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && MUZIK_CHEKER ==0) {
            thisView.setBackground(getResources().getDrawable(R.mipmap.music_action_off_button_02));
        } else
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && MUZIK_CHEKER == 1) {
                thisView.setBackground(getResources().getDrawable(R.mipmap.music_button_01));
            }

        thisView.setOnTouchListener(this);
        thisView.setClickable(true);
        WIDTH_OF_VIEW = bitmap.getWidth();
        clickSound = ClickSound.getInstance(context);

    }




    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN:
                if(SoundSwitcher.SOUND_CHEKER == 1)
                    clickSound.playClick();
                if(MUZIK_CHEKER == 0 ) {
                    thisView.setBackgroundResource(R.mipmap.music_action_off_button_01);
                } else {
                    thisView.setBackgroundResource(R.mipmap.music_action_button_01);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(MUZIK_CHEKER == 0 ) {
                    Log.d("MusSwitc","You_Click_on_Music_on");
                    if(!musicPlayer.isInit("Music_Switcher_Checed_For_Init")){
                        musicPlayer.init();
                        musicPlayer.setInit(!musicPlayer.isInit("MusSwitch_Player_was_Not_init_Now_Its_init"));
                    }
                    musicPlayer.playSong(R.raw.mainmusic);
                    MUZIK_CHEKER = 1;
                    thisView.setBackgroundResource(R.mipmap.music_button_01);
                } else {
                    Log.d("MusSwitc","You_Click_on_Music_off");
                    musicPlayer.pause();
                    MUZIK_CHEKER = 0;
                    thisView.setBackgroundResource(R.mipmap.music_action_off_button_02);
                }

                break;
        }


        return false;
    }
}
