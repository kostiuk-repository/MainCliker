package com.nerkingames.mineclicker.Game.GameActivity.dagger;

import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by vladyslav on 28.02.18.
 */
@Subcomponent(modules = GameActivitySubcomponentBuildersModule.class)
public interface GameActivityComponent extends AndroidInjector<GameActivity> {


    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<GameActivity> {

    }

}
