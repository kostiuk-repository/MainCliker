package com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonStartActivity;
import com.nerkingames.mineclicker.music.ClickSound;


public class StartButon extends View implements SimpleButtonStartActivity {


    public static final String  NAME_OF_BUTTON = "START_BUTTON";
    private static int HEIGHT_OF_VIEW;
    public static int WIDTH_OF_VIEW;

    private View thisView;
    private Context context;
    private SharedPreferences sPref;

    @Override
    public boolean isScrollContainer() {
        return super.isScrollContainer();
    }

    private Bitmap bitmap;
    private ClickSound clickSound;




    public StartButon(Context context) {
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
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.start_button_01);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        thisView.setBackground(getResources().getDrawable(R.mipmap.start_button_01));
        thisView.setOnTouchListener(this);
        thisView.setClickable(true);
        WIDTH_OF_VIEW = bitmap.getWidth();
        HEIGHT_OF_VIEW = bitmap.getHeight();
        clickSound = ClickSound.getInstance(context);


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        return false;
    }



}
