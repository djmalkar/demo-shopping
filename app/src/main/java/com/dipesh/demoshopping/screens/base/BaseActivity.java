package com.dipesh.demoshopping.screens.base;

import com.dipesh.demoshopping.ShoppingApplication;
import com.dipesh.demoshopping.dependencyinjection.components.ApplicationComponent;
import com.dipesh.demoshopping.dependencyinjection.components.PresentationComponent;
import com.dipesh.demoshopping.dependencyinjection.modules.PresentationModule;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected PresentationComponent getPresentationComponent() {
        return getApplicationComponent().newPresentationComponent(new PresentationModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((ShoppingApplication) getApplication()).getApplicationComponent();
    }
}
