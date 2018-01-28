package com.bytemark.di.modules;

import com.bytemark.ByteMarkApplication;
import com.bytemark.data.ApiHelper;
import com.bytemark.data.ApiInterface;
import com.bytemark.data.DataManager;
import com.bytemark.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bytemark.commons.Constants.BASE_URL;

/**
 * Created by smit on 13/01/18.
 */

@Module
public class AppModule {

    private ByteMarkApplication byteMarkApplication;

    public AppModule(ByteMarkApplication byteMarkApplication)
    {
        this.byteMarkApplication = byteMarkApplication;
    }

    @Provides
    @ApplicationScope
    public ByteMarkApplication getByteMarkApplication()
    {
        return byteMarkApplication;
    }

    @Provides
    @ApplicationScope
    public ApiInterface providesApiInterface()
    {
        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @ApplicationScope
    public ApiHelper providesApiHelper(ApiInterface apiInterface)
    {
        return new ApiHelper(apiInterface);
    }


    @Provides
    @ApplicationScope
    public DataManager providesDataManager(ApiHelper apiHelper)
    {
        return new DataManager(apiHelper);
    }

}
