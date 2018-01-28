package com.bytemark.di.components;

import com.bytemark.MainActivity;
import com.bytemark.di.modules.ActivityModule;
import com.bytemark.di.modules.ForecastModule;
import com.bytemark.di.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by smit on 13/01/18.
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}
