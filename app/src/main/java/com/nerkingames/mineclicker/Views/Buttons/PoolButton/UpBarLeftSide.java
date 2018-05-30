package com.nerkingames.mineclicker.Views.Buttons.PoolButton;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nerkingames.mineclicker.R;
import com.nerkingames.mineclicker.Views.JobControllers.AggressiveMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.FoodWorker;
import com.nerkingames.mineclicker.Views.JobControllers.GoodMobsWorker;
import com.nerkingames.mineclicker.Views.JobControllers.MineWorker;
import com.nerkingames.mineclicker.Views.JobControllers.UpWorldWorker;

/**
 * Created by vladyslav on 13.03.18.
 */

public class UpBarLeftSide extends FrameLayout {

    private BitmapDrawable bitmapDrawable;
    private TextView textField;
    private TextView textFieldBlackClone;
    private int mWidth;
    private int mHeight;
    private static String counter = "0";
    private SharedPreferences sPref;
    private Context context;
    private UpWorldWorker upWorldWorker;
    private FoodWorker foodWorker;
    private MineWorker mineWorker;
    private AggressiveMobsWorker aggressiveMobsWorker;
    private GoodMobsWorker goodMobsWorker;


    public UpBarLeftSide(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        bitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.up_bar1));
        this.setBackground(bitmapDrawable);
        mWidth = bitmapDrawable.getBitmap().getWidth();
        mHeight = bitmapDrawable.getBitmap().getHeight();
        this.setLayoutParams(new FrameLayout.LayoutParams((int)(context.getResources()
                            .getDisplayMetrics().widthPixels * 0.4238f),mHeight));
        textField = new TextView(context);
        textFieldBlackClone = new TextView(context);
        textField.setY(mHeight*0.5f);
        textFieldBlackClone.setY(mHeight*0.5f);
        this.addView(textFieldBlackClone);
        this.addView(textField);
        sPref = context.getSharedPreferences("APP_PREF",context.MODE_PRIVATE);
        counter = sPref.getString("MINE_COINS","50");
        textField.setText(counter);
        textField.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        textFieldBlackClone.setText(counter);
        textFieldBlackClone.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        Typeface face = Typeface.createFromAsset(context.getAssets(),"font1.ttf");
        textField.setTypeface(face);
        textFieldBlackClone.setTypeface(face);
        textField.setTextColor(Color.YELLOW);
        textFieldBlackClone.setTextColor(Color.BLACK);
        textField.setX(mWidth*0.75f);
        textFieldBlackClone.setX(mWidth*0.75f);
        textFieldBlackClone.setPadding(4,4,0,0);

    }


    public int getmWidth() {
        return mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public int getTextFieldCounter() {
        return Integer.valueOf(counter);
    }

    public void setTextFieldCounter(int textField) {
        upWorldWorker = UpWorldWorker.getInstance(context);
        foodWorker = FoodWorker.getInstance(context);
        mineWorker = MineWorker.getInstance(context);
        aggressiveMobsWorker = AggressiveMobsWorker.getInstance(context);
        goodMobsWorker = GoodMobsWorker.getInstance(context);
        counter = String.valueOf(textField);
        this.textField.setText(String.valueOf(counter));
        sPref = context.getSharedPreferences("APP_PREF",context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("MINE_COINS", String.valueOf(counter));
        ed.commit();
        upWorldWorker.lockUnlockFunction(upWorldWorker.getIT());
        foodWorker.lockUnlockFunction(foodWorker.getIT());
        mineWorker.lockUnlockFunction(mineWorker.getIT());
        aggressiveMobsWorker.lockUnlockFunction(aggressiveMobsWorker.getIT());
        goodMobsWorker.lockUnlockFunction(goodMobsWorker.getIT());
    }
    public void setTextFieldCounterWithoutChange(int textField) {
        upWorldWorker = UpWorldWorker.getInstance(context);
        foodWorker = FoodWorker.getInstance(context);
        mineWorker = MineWorker.getInstance(context);
        aggressiveMobsWorker = AggressiveMobsWorker.getInstance(context);
        goodMobsWorker = GoodMobsWorker.getInstance(context);
        this.textField.setText(String.valueOf(textField));
        this.textFieldBlackClone.setText(String.valueOf(textField));
        upWorldWorker.lockUnlockFunction(upWorldWorker.getIT());
        foodWorker.lockUnlockFunction(foodWorker.getIT());
        mineWorker.lockUnlockFunction(mineWorker.getIT());
        aggressiveMobsWorker.lockUnlockFunction(aggressiveMobsWorker.getIT());
        goodMobsWorker.lockUnlockFunction(goodMobsWorker.getIT());
    }
}
