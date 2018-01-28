package com.bytemark.di.components;


import com.bytemark.ByteMarkApplication;
import com.bytemark.di.modules.AppModule;
import com.bytemark.di.modules.ActivityModule;
import com.bytemark.di.modules.ForecastModule;
import com.bytemark.di.scopes.ApplicationScope;

import dagger.Component;

/**
 * Created by smit on 13/01/18.
 */

@ApplicationScope
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(ByteMarkApplication byteMarkApplication);

    ActivityComponent getControllerComponent(ActivityModule activityModule);

    ForecastComponent getForecastComponent(ForecastModule forecastModule);

}
