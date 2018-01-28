package com.bytemark.di.components;

import com.bytemark.di.modules.ForecastModule;
import com.bytemark.di.scopes.ActivityScope;
import com.bytemark.fragments.CurrentCityForecastFragment;
import com.bytemark.fragments.MultipleCitiesForecastFragment;

import dagger.Subcomponent;

/**
 * Created by smit on 13/01/18.
 */
@ActivityScope
@Subcomponent(modules = {ForecastModule.class})
public interface ForecastComponent {

    void inject(CurrentCityForecastFragment currentCityForecastFragment);
    void inject(MultipleCitiesForecastFragment multipleCitiesForecastFragment);
}
