package com.nerkingames.mineclicker.AboutActivity.dagger;

import com.nerkingames.mineclicker.AboutActivity.AboutActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = AboutActivityModule.class)
public interface AboutActivityComponent extends AndroidInjector<AboutActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AboutActivity> {

    }

}
