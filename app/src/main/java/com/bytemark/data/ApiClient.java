package com.bytemark.data;

import com.bytemark.commons.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by smit on 26/01/18.
 */

public class ApiClient implements Constants{

    private static Retrofit retrofit ;

    private ApiClient(){}

    public static ApiInterface getApiInterface()
    {
        if(retrofit == null)
        {
             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }


}
