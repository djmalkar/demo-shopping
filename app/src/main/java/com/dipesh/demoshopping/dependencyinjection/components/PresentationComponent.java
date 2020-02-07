package com.dipesh.demoshopping.dependencyinjection.components;

import com.dipesh.demoshopping.dependencyinjection.modules.PresentationModule;

import dagger.Subcomponent;

@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {
}
