package com.bytemark.di.modules;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.support.v4.app.FragmentManager;


import com.bytemark.adapters.ViewPagerAdapter;
import com.bytemark.di.qualifiers.ActivityContext;
import com.bytemark.di.scopes.ActivityScope;
import com.bytemark.permissions.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import dagger.Module;
import dagger.Provides;

/**
 * Created by smit on 13/01/18.
 */

@Module
public class ActivityModule {

    private Activity mActivity;
    private FragmentManager fragmentManager;

    public ActivityModule(Activity activity, FragmentManager fragmentManager) {
        mActivity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }


    @Provides
    FragmentManager providesFragmentManager()
    {
        return fragmentManager;
    }

    @Provides
    @ActivityScope
    LocationManager providesLocationManager(@ActivityContext Context context)
    {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @ActivityScope
    PermissionUtils providesPermissionUtils(@ActivityContext Context context)
    {
        return new PermissionUtils(context);
    }

    @Provides
    @ActivityScope
    FusedLocationProviderClient providesFusedLocationProviderClient(@ActivityContext Context context)
    {
        return LocationServices.getFusedLocationProviderClient(context);
    }

    @Provides
    @ActivityScope
    ViewPagerAdapter providesViewPagerAdapter(FragmentManager fragmentManager)
    {
        return new ViewPagerAdapter(fragmentManager);
    }

}
