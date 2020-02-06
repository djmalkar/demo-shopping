package com.dipesh.demoshopping.dependencyinjection.components;

import com.dipesh.demoshopping.dependencyinjection.modules.ApplicationModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public class ApplicationComponent {
}
