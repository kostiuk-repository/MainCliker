package com.nerkingames.mineclicker.Views.Buttons.ButtonsAboutActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonAboutActivity;

/**
 * Created by vladyslav on 21.02.18.
 */

public class InstaButt extends View implements SimpleButtonAboutActivity {


    public static final String  NAME_OF_BUTTON = "INSTAGRAM_BUTTON";
    public static int HEIGHT_OF_VIEW;
    public static int WIDTH_OF_VIEW;

    private View thisView;
    private Context context;
    private Bitmap bitmap;


    public InstaButt(Context context) {
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
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.instagram_icon);
        thisView = new FrameLayout(context);
        thisView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            thisView.setBackground(getResources().getDrawable(R.mipmap.instagram_icon));
        }
        thisView.setOnTouchListener(this);
        thisView.setClickable(true);
        WIDTH_OF_VIEW = bitmap.getWidth();
        HEIGHT_OF_VIEW = bitmap.getHeight();

    }




    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN:
                thisView.setBackgroundResource(R.mipmap.instagram_icon);
                break;
            case MotionEvent.ACTION_UP:
                Uri uri = Uri.parse("https://www.instagram.com/nerkin_/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                likeIng.setPackage("com.instagram.android");

                try {
                    context.startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/nerkin_/")));
                }
                thisView.setBackgroundResource(R.mipmap.instagram_icon);
                break;
        }


        return false;
    }
}
