package com.mateusz.grabarski.basicdaggerproject;

import android.app.Activity;
import android.content.Context;

import com.mateusz.grabarski.basicdaggerproject.base.qualifiers.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
