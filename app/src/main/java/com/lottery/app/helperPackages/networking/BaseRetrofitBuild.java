package com.lottery.app.helperPackages.networking;
import android.content.Context;

import com.lottery.app.helperPackages.baseClasses.LotteryApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofitBuild {

    private volatile static BaseRetrofitBuild baseRetrofitBuildInstance;

    private static volatile Retrofit.Builder retrofitBuilder;

    protected static final Gson gson = new GsonBuilder().create();


    /**
     * creates a new instance of retrofit builder
     *
     * @return
     */
    private static final Retrofit.Builder getRetrofitBuilder() {
        if (retrofitBuilder == null) {
            synchronized (Retrofit.Builder.class) {
                retrofitBuilder = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(gson));
            }
        }
        return retrofitBuilder;
    }

    /**
     * gets instance and sets the provided base url
     *
     * @param baseUrl
     * @return
     */
    public Retrofit build(String baseUrl) {
        Retrofit retrofit = getRetrofitBuilder()
                .baseUrl(baseUrl)
                .client(getClient(LotteryApplication.getContext()))
                .build();
        return retrofit;
    }

    public Retrofit build() {
        Retrofit retrofit = getRetrofitBuilder()
                .build();
        return retrofit;
    }

    public static BaseRetrofitBuild getInstance() {
        if (baseRetrofitBuildInstance == null) {
            synchronized (BaseRetrofitBuild.class) {
                baseRetrofitBuildInstance = new BaseRetrofitBuild();
            }
        }
        return baseRetrofitBuildInstance;
    }

    private OkHttpClient getClient(Context context) {
        return new OkHttpClient.Builder()
                // Add a ChuckInterceptor instance to your OkHttp client
                .addInterceptor(new ChuckInterceptor(context))
                .build();
    }
}
