package com.devsat.mvvm.apicall;

import com.devsat.mvvm.Global.Global;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {
   static Retrofit retrofit;

    public static Retrofit getCon() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Global.Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
