package com.nerkingames.mineclicker.AboutActivity.dagger;

import android.content.Context;

import com.nerkingames.mineclicker.AboutActivity.AboutActivity;
import com.nerkingames.mineclicker.AboutActivity.AboutActivityPresenter;
import com.nerkingames.mineclicker.AboutActivity.AboutField;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsAboutActivity.InstaButt;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsAboutActivity.VkButton;
import com.nerkingames.mineclicker.Views.Buttons.ButtonsAboutActivity.YouTubeButton;
import com.nerkingames.mineclicker.Views.Buttons.Interfaces.SimpleButtonAboutActivity;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;


@Module
public class AboutActivityModule {

    @Provides
   AboutActivityPresenter provideSecondActivityPresenter(AboutActivity aboutActivity) {
        return new AboutActivityPresenter();
    }


    @Provides
    @IntoMap
    @StringKey(InstaButt.NAME_OF_BUTTON)
    SimpleButtonAboutActivity getInstaButton(Context context){
        return new InstaButt(context);
    }

    @Provides
    @IntoMap
    @StringKey(VkButton.NAME_OF_BUTTON)
    SimpleButtonAboutActivity getVkButton(Context context){
        return new VkButton(context);
    }

    @Provides
    @IntoMap
    @StringKey(YouTubeButton.NAME_OF_BUTTON)
    SimpleButtonAboutActivity getYotubeButton(Context context){
        return new YouTubeButton(context);
    }
    @Provides
    @IntoMap
    @StringKey(AboutField.NAME_OF_BUTTON)
    SimpleButtonAboutActivity getAboutField(Context context){
        return new AboutField(context);
    }
}
