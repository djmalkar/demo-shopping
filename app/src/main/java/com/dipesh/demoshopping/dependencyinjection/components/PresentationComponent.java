package com.dipesh.demoshopping.dependencyinjection.components;

import com.dipesh.demoshopping.dependencyinjection.modules.PresentationModule;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.main.MainActivity;
import com.dipesh.demoshopping.screens.subcategorieslisting.SubCategoriesFragment;

import dagger.Subcomponent;

@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {

    void injectMainActivity(MainActivity mainActivity);

    void injectSubcategoriesFragment(SubCategoriesFragment subCategoriesFragment);

    ViewMvcFactory getViewMvcFactory();
}
