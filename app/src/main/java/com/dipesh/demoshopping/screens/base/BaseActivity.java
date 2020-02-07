package com.dipesh.demoshopping.screens.base;

import android.os.Bundle;

import com.dipesh.demoshopping.ShoppingApplication;
import com.dipesh.demoshopping.dependencyinjection.components.ApplicationComponent;
import com.dipesh.demoshopping.dependencyinjection.components.PresentationComponent;
import com.dipesh.demoshopping.dependencyinjection.modules.PresentationModule;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private PresentationComponent mPresentationComponent;

    protected PresentationComponent getPresentationComponent() {
        return getApplicationComponent().newPresentationComponent(new PresentationModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((ShoppingApplication) getApplication()).getApplicationComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
