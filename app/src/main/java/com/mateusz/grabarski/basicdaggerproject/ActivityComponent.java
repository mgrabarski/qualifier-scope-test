package com.mateusz.grabarski.basicdaggerproject;

import com.mateusz.grabarski.basicdaggerproject.base.ApplicationComponent;
import com.mateusz.grabarski.basicdaggerproject.base.scopes.PerActivity;

import dagger.Component;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
