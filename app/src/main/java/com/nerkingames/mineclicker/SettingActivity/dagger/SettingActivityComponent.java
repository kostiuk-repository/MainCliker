package com.nerkingames.mineclicker.SettingActivity.dagger;

import com.nerkingames.mineclicker.SettingActivity.SettingActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by vladyslav on 21.02.18.
 */
@Subcomponent(modules = SettingActivityModule.class)
public interface SettingActivityComponent extends AndroidInjector<SettingActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SettingActivity> {

    }

}
