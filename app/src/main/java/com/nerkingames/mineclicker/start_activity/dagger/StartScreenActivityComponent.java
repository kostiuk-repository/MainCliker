package com.nerkingames.mineclicker.start_activity.dagger;

import com.nerkingames.mineclicker.start_activity.StartScreenActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = StartScreenActivityModule.class)
public interface StartScreenActivityComponent extends AndroidInjector<StartScreenActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<StartScreenActivity> {

    }

}
