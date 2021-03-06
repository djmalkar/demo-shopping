package com.dipesh.demoshopping;

import android.app.Activity;
import android.app.Application;

import com.dipesh.demoshopping.dependencyinjection.application.ApplicationComponent;
import com.dipesh.demoshopping.dependencyinjection.application.ApplicationModule;
import com.dipesh.demoshopping.dependencyinjection.application.DaggerApplicationComponent;

public class ShoppingApplication extends Application {

    ApplicationComponent component;

    public static ShoppingApplication get(Activity activity) {
        return (ShoppingApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
