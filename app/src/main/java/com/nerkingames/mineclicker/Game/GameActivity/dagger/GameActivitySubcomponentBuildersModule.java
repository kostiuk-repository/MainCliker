package com.nerkingames.mineclicker.Game.GameActivity.dagger;



import android.app.Fragment;

import com.nerkingames.mineclicker.Game.GameFragments.AchievementsFragment.AchievementsFragment;
import com.nerkingames.mineclicker.Game.GameFragments.AchievementsFragment.dagger.AchievementsFragmentSubcomponent;
import com.nerkingames.mineclicker.Game.GameFragments.JobFragment.JobFragment;
import com.nerkingames.mineclicker.Game.GameFragments.JobFragment.dagger.JobFragmentSubcomponent;
import com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment.UpWorldFragment;
import com.nerkingames.mineclicker.Game.GameFragments.UpworldFragment.dagger.UpWorldFragmentSubcomponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by vladyslav on 28.02.18.
 */

@Module(subcomponents = {AchievementsFragmentSubcomponent.class, JobFragmentSubcomponent.class, UpWorldFragmentSubcomponent.class})
public abstract class GameActivitySubcomponentBuildersModule {

    @Binds
    @IntoMap
    @FragmentKey(AchievementsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindAchievementsFragmentntInjectorFactory(AchievementsFragmentSubcomponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(JobFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindJobFragmentInjectorFactory(JobFragmentSubcomponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UpWorldFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindUpWorldFragmentInjectorFactory(UpWorldFragmentSubcomponent.Builder builder);
}
