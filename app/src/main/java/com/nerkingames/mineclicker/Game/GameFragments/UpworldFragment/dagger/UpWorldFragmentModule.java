package com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment.dagger;

import android.content.Context;

import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.Food;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.IViewGetter;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.Mine;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.Mobs;
import com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons.UpWorld;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

/**
 * Created by vladyslav on 28.02.18.
 */

@Module
public class UpWorldFragmentModule {


    @Provides
    @Named("upw")
    IViewGetter upWorldProvider(Context context){
        return new UpWorld(context);
    }

    @Provides
    @Named("food")
    IViewGetter FoodProvider(Context context){
        return new Food(context);
    }

    @Provides
    @Named("mine")
    IViewGetter MineProvider(Context context){
        return new Mine(context);
    }

    @Provides
    @Named("mobs")
    IViewGetter MobsProvider(Context context){
        return new Mobs(context);
    }

}
