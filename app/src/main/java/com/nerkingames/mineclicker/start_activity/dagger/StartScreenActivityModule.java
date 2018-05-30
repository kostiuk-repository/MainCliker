package com.nerkingames.mineclicker.start_activity.dagger;


import android.content.Context;

import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.Achivments;
import com.nerkingames.mineclicker.start_activity.StartScreenActivityPresenter;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.AboutButton;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.SettingButton;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsStartAtivity.StartButon;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonStartActivity;


import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;


@Module
public class StartScreenActivityModule {


    @Provides
    StartScreenActivityPresenter provideFirstActivityPresenter() {
        return new StartScreenActivityPresenter();
    }



    @Provides
    @IntoMap
    @StringKey(StartButon.NAME_OF_BUTTON)
    SimpleButtonStartActivity getStartButton(Context context){
        return new StartButon(context);
    }

    @Provides
    @IntoMap
    @StringKey(SettingButton.NAME_OF_BUTTON)
    SimpleButtonStartActivity getSettingButton(Context context){
        return new SettingButton(context);
    }

    @Provides
    @IntoMap
    @StringKey(AboutButton.NAME_OF_BUTTON)
    SimpleButtonStartActivity getAboutButton(Context context){
        return new AboutButton(context);
    }

    @Provides
    @IntoMap
    @StringKey(Achivments.NAME_OF_BUTTON)
    SimpleButtonStartActivity getAchButton(Context context){
        return new Achivments(context);
    }



}
