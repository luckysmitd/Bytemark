package com.bytemark.di.modules;

import com.bytemark.data.DataManager;
import com.bytemark.fragments.CurrentCityForecastFragment;
import com.bytemark.fragments.MultipleCitiesForecastFragment;
import com.bytemark.ui.forecast.ForecastPresenter;
import com.bytemark.ui.forecast.ForecastView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by smit on 27/01/18.
 */

@Module
public class ForecastModule {

    private ForecastView forecastView;

    public ForecastModule(CurrentCityForecastFragment currentCityForecastFragment)
    {
        this.forecastView = currentCityForecastFragment;
    }

    public ForecastModule(MultipleCitiesForecastFragment currentCityForecastFragment)
    {
        this.forecastView = currentCityForecastFragment;
    }

    @Provides
    ForecastView providesForecastView()
    {
        return forecastView;
    }

    @Provides
    ForecastPresenter providesForecastPresenter(ForecastView forecastView, DataManager dataManager)
    {
        return new ForecastPresenter(forecastView, dataManager);
    }

}
