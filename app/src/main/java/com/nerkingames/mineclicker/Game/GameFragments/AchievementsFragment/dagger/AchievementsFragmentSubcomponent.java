package com.nerkingames.mineclicker.Game.GameFragments.AchievementsFragment.dagger;

import com.nerkingames.mineclicker.Game.GameFragments.AchievementsFragment.AchievementsFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by vladyslav on 28.02.18.
 */
@Subcomponent(modules = AchievementsFragmentModule.class)
public interface AchievementsFragmentSubcomponent extends AndroidInjector<AchievementsFragment>{

        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<AchievementsFragment> {

        }
}
