package com.dipesh.demoshopping.screens.base;

import com.dipesh.demoshopping.ShoppingApplication;
import com.dipesh.demoshopping.dependencyinjection.application.ApplicationComponent;
import com.dipesh.demoshopping.dependencyinjection.presentation.PresentationComponent;
import com.dipesh.demoshopping.dependencyinjection.presentation.PresentationModule;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected PresentationComponent getPresentationComponent() {
        return getApplicationComponent().newPresentationComponent(new PresentationModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((ShoppingApplication) getApplication()).getApplicationComponent();
    }
}
