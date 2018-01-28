package com.bytemark;

import android.app.Application;
import android.content.Context;

import com.bytemark.data.DataManager;
import com.bytemark.di.components.AppComponent;
import com.bytemark.di.components.DaggerAppComponent;
import com.bytemark.di.modules.AppModule;

import javax.inject.Inject;

/**
 * Created by smit on 27/01/18.
 */

public class ByteMarkApplication extends Application {

    @Inject
    DataManager dataManager;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);

    }

    public static ByteMarkApplication get(Context context) {
        return (ByteMarkApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent()
    {
        return appComponent;
    }


}
