package com.nerkingames.mineclicker.AboutActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonAboutActivity;

/**
 * Created by vladyslav on 21.02.18.
 */

public class AboutField extends View implements SimpleButtonAboutActivity {


    public static final String  NAME_OF_BUTTON = "ABOUT_FIELD";
    public static int HEIGHT_OF_VIEW;
    public static int WIDTH_OF_VIEW;

    private View thisView;
    private Context context;
    private Bitmap bitmap;
    private TextView textView;


    public AboutField(Context context) {
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
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.abaout_background_text);
        WIDTH_OF_VIEW = bitmap.getWidth();
        HEIGHT_OF_VIEW = bitmap.getHeight();
        textView = new TextView(context);
        textView.setText(R.string.info_string);
        textView.setTextColor(Color.BLACK);
        FrameLayout.LayoutParams llp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(WIDTH_OF_VIEW/100*10, HEIGHT_OF_VIEW/100*8, WIDTH_OF_VIEW/100*7, HEIGHT_OF_VIEW/100*8); // llp.setMargins(left, top, right, bottom);
        textView.setLayoutParams(llp);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setVerticalFadingEdgeEnabled(true);
        textView.setFadingEdgeLength(35);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.addView(textView);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(WIDTH_OF_VIEW, HEIGHT_OF_VIEW));
        thisView = frameLayout;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            thisView.setBackground(getResources().getDrawable(R.mipmap.abaout_background_text));
        }
        thisView.setOnTouchListener(this);
        thisView.setClickable(false);




    }

    public void resolveTextSize(TextView view, Activity context){

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_HIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XXHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XXXHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE
                && density == DisplayMetrics.DENSITY_HIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        }

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
