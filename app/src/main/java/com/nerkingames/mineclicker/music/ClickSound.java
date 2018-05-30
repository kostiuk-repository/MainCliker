package com.nerkingames.mineclicker.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.nerkingames.mineclicker.R;

/**
 * Created by vladyslav on 24.02.18.
 */

public class ClickSound implements SoundPool.OnLoadCompleteListener {


    private static ClickSound instance;
    private final int MAX_STREAMS = 10;
    private SoundPool sp;
    private int soundClick;
    private int soundClickWood;
    private int soundClickMine;
    private int soundClickFood;
    private int soundClickMobs;
    private int soundClickGrass;
    private int soundClickWater;
    private int soundClickDirt;
    private Context context;

    private ClickSound(Context context) {
        this.context = context;
        soundPoolInit();
    }

    public static ClickSound getInstance(Context context){
        if (instance == null){
            instance = new ClickSound(context);
        }
        return instance;

    }

    public void playClick(){

        sp.play(soundClick, 1, 1, 0, 0, 1);
    }


    public void playClickWood(){

        sp.play(soundClickWood, 1, 1, 0, 0, 1);
    }

    public void playClickGrass(){

        sp.play(soundClickGrass, 1, 1, 0, 0, 1);
    }

    public void playClickWater(){

        sp.play(soundClickWater, 1, 1, 0, 0, 1);
    }

    public void playClickMine(){

        sp.play(soundClickMine, 1, 1, 0, 0, 1);
    }

    public void playClickDirt(){

        sp.play(soundClickDirt, 1, 1, 0, 0, 1);
    }
    public void playClickFood(){

        sp.play(soundClickFood, 1, 1, 0, 0, 1);
    }

    public void playClickMobs(){

        sp.play(soundClickMobs, 1, 1, 0, 0, 1);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {

    }
    private void soundPoolInit() {
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundClick = sp.load(context, R.raw.click, 1);
        soundClickWood = sp.load(context, R.raw.wood, 1);
        soundClickMine = sp.load(context, R.raw.mine2, 1);
        soundClickMobs = sp.load(context, R.raw.damage, 1);
        soundClickDirt = sp.load(context, R.raw.dirt, 1);
        soundClickGrass = sp.load(context, R.raw.grass, 1);
        soundClickWater = sp.load(context, R.raw.water, 1);
        soundClickFood = sp.load(context, R.raw.food, 1);
    }
}
