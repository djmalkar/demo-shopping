package com.dipesh.demoshopping.dependencyinjection.application;

import com.dipesh.demoshopping.dependencyinjection.presentation.PresentationComponent;
import com.dipesh.demoshopping.dependencyinjection.presentation.PresentationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    PresentationComponent newPresentationComponent(PresentationModule presentationModule);
    //void injectShoppingApplication(ShoppingApplication application);
}
