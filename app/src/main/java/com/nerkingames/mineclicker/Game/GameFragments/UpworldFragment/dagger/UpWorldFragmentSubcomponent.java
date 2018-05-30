package com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment.dagger;

import com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment.UpWorldFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by vladyslav on 28.02.18.
 */
@Subcomponent(modules = UpWorldFragmentModule.class)
public interface UpWorldFragmentSubcomponent extends AndroidInjector<UpWorldFragment>{

        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<UpWorldFragment> {

        }
}
