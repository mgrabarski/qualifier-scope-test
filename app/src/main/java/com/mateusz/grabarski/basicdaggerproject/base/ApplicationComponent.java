package com.mateusz.grabarski.basicdaggerproject.base;

import android.app.Application;
import android.content.Context;

import com.mateusz.grabarski.basicdaggerproject.base.qualifiers.ApplicationContext;
import com.mateusz.grabarski.basicdaggerproject.database.DataManager;
import com.mateusz.grabarski.basicdaggerproject.database.DatabaseHelper;
import com.mateusz.grabarski.basicdaggerproject.utils.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

    DatabaseHelper getDbHelper();
}