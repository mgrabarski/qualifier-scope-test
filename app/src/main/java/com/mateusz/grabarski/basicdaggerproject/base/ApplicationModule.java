package com.mateusz.grabarski.basicdaggerproject.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mateusz.grabarski.basicdaggerproject.base.qualifiers.ApplicationContext;
import com.mateusz.grabarski.basicdaggerproject.base.qualifiers.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
