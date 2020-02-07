package com.dipesh.demoshopping.dependencyinjection.components;

import com.dipesh.demoshopping.dependencyinjection.modules.ApplicationModule;
import com.dipesh.demoshopping.dependencyinjection.modules.PresentationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    PresentationComponent newPresentationComponent(PresentationModule presentationModule);
    //void injectShoppingApplication(ShoppingApplication application);
}
