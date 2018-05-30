package com.nerkingames.mineclicker.Views.Buttons.PoolButton;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nerkingames.mineclicker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by vladyslav on 14.03.18.
 */

public class MineClock extends RelativeLayout {

    private BitmapDrawable bitmapDrawable;
    private TextView textField;
    private int mWidth;
    private int mHeight;
    private String time = "0";

    public int getmWidth() {
        return mWidth;
    }

    public MineClock(Context context,Activity activity) {
        super(context);
        initView(context,activity);

    }

    private void initView(Context context,Activity activity) {
        bitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.up_bar2));
        this.setBackground(bitmapDrawable);
        mWidth = bitmapDrawable.getBitmap().getWidth();
        mHeight = bitmapDrawable.getBitmap().getHeight();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(context.getResources()
                .getDisplayMetrics().widthPixels * 0.1476f),mHeight);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params2.addRule(RelativeLayout.CENTER_VERTICAL);
        this.setLayoutParams(params);
        textField = new TextView(context);
        this.addView(textField);
        resolveTextSize(textField,activity);
        textField.setText(time);
        Typeface face = Typeface.createFromAsset(context.getAssets(),"font1.ttf");
        textField.setTypeface(face);
        textField.setTextColor(Color.YELLOW);
        textField.setLayoutParams(params2);
        Observable.interval(100,1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Calendar cal1 = new GregorianCalendar();
                        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");
                        textField.setText(date_format.format(cal1.getTime()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void resolveTextSize(TextView view, Activity context){

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_HIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,9);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XXHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && density == DisplayMetrics.DENSITY_XXXHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE
                && density == DisplayMetrics.DENSITY_HIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE
                && density == DisplayMetrics.DENSITY_XHIGH) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,21);
        }

    }

}
