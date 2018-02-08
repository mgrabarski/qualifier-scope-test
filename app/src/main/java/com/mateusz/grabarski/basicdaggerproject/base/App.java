package com.mateusz.grabarski.basicdaggerproject.base;

import android.app.Application;
import android.content.Context;

import com.mateusz.grabarski.basicdaggerproject.database.DataManager;

import javax.inject.Inject;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */

public class App extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}
