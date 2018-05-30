package com.nerkingames.mineclicker.SettingActivity.dagger;

import android.content.Context;

import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonSettingActivity;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.MusicSwitcher;
import com.nerkingames.mineclicker.Views.Buttons.SettingActivityButtons.SoundSwitcher;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

/**
 * Created by vladyslav on 21.02.18.
 */

@Module
public class SettingActivityModule {

    @Provides
    @IntoMap
    @StringKey(MusicSwitcher.NAME_OF_BUTTON)
    SimpleButtonSettingActivity getMusikButton(Context context) {

        return new MusicSwitcher(context);
    }

    @Provides
    @IntoMap
    @StringKey(SoundSwitcher.NAME_OF_BUTTON)
    SimpleButtonSettingActivity getSoundButton(Context context) {

        return new SoundSwitcher(context);
    }


}


