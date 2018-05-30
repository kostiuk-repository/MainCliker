package com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.nerkingames.mineclicker.R;

public class Switcher extends RelativeLayout {

    GridLayout all;
    FrameLayout wood;
    FrameLayout seeds;
    FrameLayout water;
    FrameLayout dirt;
    Switch workerSwitch;
    boolean state = true;

    public Switcher(Context context) {
        super(context);
        View v = inflate(getContext(), R.layout.up_world_worker_layout,null);
        all = v.findViewById(R.id.all_res);
        wood = v.findViewById(R.id.wood_up_worker);
        seeds = v.findViewById(R.id.grass_up_worker);
        water = v.findViewById(R.id.water_up_worker);
        dirt = v.findViewById(R.id.dirt_up_worker);
        workerSwitch = v.findViewById(R.id.switch_up_w);
        state = UpWorld.WORKER_STATUS;


        all.setOnClickListener(v1 -> {
            UpWorld.RESOURCES_TYPE = 1;
        });

        wood.setOnClickListener(v1 -> {
            UpWorld.RESOURCES_TYPE = 2;
        });

        seeds.setOnClickListener(v1 -> {
            UpWorld.RESOURCES_TYPE = 3;
        });

        water.setOnClickListener(v1 -> {
            UpWorld.RESOURCES_TYPE = 4;
        });

        workerSwitch.setOnClickListener(v1 -> {
            if(state){
                workerSwitch.setChecked(false);
                state = false;
                UpWorld.WORKER_STATUS = false;
            } else {
                workerSwitch.setChecked(true);
                state = true;
                UpWorld.WORKER_STATUS = true;
            }
        });

        this.addView(v);
        this.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

}

