package com.nerkingames.mineclicker.Views.Buttons.Interfaces;

import android.view.View;

/**
 * Created by vladyslav on 22.02.18.
 */

public interface SimpleButtonSettingActivity extends View.OnTouchListener {

    View getViewToContainer();
    void initView();
}
