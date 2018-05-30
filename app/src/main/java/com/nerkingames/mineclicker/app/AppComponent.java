package com.nerkingames.mineclicker.app;

import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Component(modules = {AppModule.class, AppScBuildersModule.class, AndroidInjectionModule.class})
public interface AppComponent {
    void injectApp(App app);
}
