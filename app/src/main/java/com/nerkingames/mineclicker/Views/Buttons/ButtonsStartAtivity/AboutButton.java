package com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nerkingames.mineclicker.AboutActivity.AboutActivity;
import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonStartActivity;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.SoundSwitcher;
import com.nerkingames.mineclicker.music.ClickSound;

/**
 * Created by vladyslav on 21.02.18.
 */

public class AboutButton extends View implements SimpleButtonStartActivity {


    public static final String  NAME_OF_BUTTON = "ABOUT_BUTTON";
    public static int HEIGHT_OF_VIEW;
    public static int WIDTH_OF_VIEW;

    private View thisView;
    private Context context;
    private Bitmap bitmap;
    private ClickSound clickSound;


    public AboutButton(Context context) {
        super(context);
        this.context = context;
        initView();
    }



    @Override
    public View getViewToContainer() {
        return thisView;
    }

    @Override
    public void initView() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.abaout_button_01);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.abaout_button_01));
        thisView.setOnTouchListener(this);
        thisView.setClickable(true);
        WIDTH_OF_VIEW = bitmap.getWidth();
        HEIGHT_OF_VIEW = bitmap.getHeight();
        clickSound = ClickSound.getInstance(context);

    }




    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_BUTTON_RELEASE:
                break;
            case MotionEvent.ACTION_DOWN:
                thisView.setBackgroundResource(R.mipmap.abaout_button_02);
                if(SoundSwitcher.SOUND_CHEKER == 1)
                    clickSound.playClick();
                break;
            case MotionEvent.ACTION_UP:
                if(motionEvent.getRawX()>thisView.getX() && motionEvent.getRawX()<thisView.getX()+WIDTH_OF_VIEW
                        && motionEvent.getRawY()> thisView.getY() && motionEvent.getRawY()< thisView.getY()+ HEIGHT_OF_VIEW) {
                    Intent intent = new Intent(context.getApplicationContext(), AboutActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

                thisView.setBackgroundResource(R.mipmap.abaout_button_01);
                break;
        }


        return false;
    }
}
