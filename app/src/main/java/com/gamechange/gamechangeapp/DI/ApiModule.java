package com.gamechange.gamechangeapp.DI;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.gamechange.gamechangeapp.issuelist.IssueDataRepository;
import com.gamechange.gamechangeapp.networking.Api;
import com.gamechange.gamechangeapp.util.Constant;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Api getApiInterface(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    IssueDataRepository getRepository(Api api, Context context) {
        return new IssueDataRepository(api,context);
    }

//    @Provides
//    @Singleton
//    ViewModelProvider.Factory getViewModelFactory(IssueDataRepository issueDataRepository) {
//        return new ViewModelFactory(myRepository);
//    }
}
