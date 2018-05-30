package com.nerkingames.mineclicker.Game.GameFragments.JobFragment.dagger;

import com.nerkingames.mineclicker.Game.GameFragments.JobFragment.JobFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by vladyslav on 28.02.18.
 */
@Subcomponent(modules = JobFragmentModule.class)
public interface JobFragmentSubcomponent extends AndroidInjector<JobFragment>{

        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<JobFragment> {

        }
}
