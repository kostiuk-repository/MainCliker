package com.nerkingames.mineclicker.app;

import android.app.Activity;

import com.nerkingames.mineclicker.AboutActivity.AboutActivity;
import com.nerkingames.mineclicker.AboutActivity.dagger.AboutActivityComponent;
import com.nerkingames.mineclicker.Game.GameActivity.GameActivity;
import com.nerkingames.mineclicker.Game.GameActivity.dagger.GameActivityComponent;
import com.nerkingames.mineclicker.SettingActivity.SettingActivity;
import com.nerkingames.mineclicker.SettingActivity.dagger.SettingActivityComponent;
import com.nerkingames.mineclicker.start_activity.StartScreenActivity;
import com.nerkingames.mineclicker.start_activity.dagger.StartScreenActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;


@Module(subcomponents = {StartScreenActivityComponent.class, AboutActivityComponent.class, SettingActivityComponent.class
,GameActivityComponent.class})
public abstract class AppScBuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(StartScreenActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindStartScreenActivityInjectorFactory(StartScreenActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(AboutActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindAboutActivityInjectorFactory(AboutActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SettingActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindSettingActivityInjectorFactory(SettingActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(GameActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindGameActivityInjectorFactory(GameActivityComponent.Builder builder);

}
