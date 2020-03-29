package com.gamechange.gamechangeapp.application;

import android.app.Application;
import android.content.Context;

import com.gamechange.gamechangeapp.DI.ApiModule;
import com.gamechange.gamechangeapp.DI.AppComponent;
import com.gamechange.gamechangeapp.DI.AppModule;
import com.gamechange.gamechangeapp.DI.DaggerAppComponent;

public class GameChangeApplication extends Application {

    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).apiModule(new ApiModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
