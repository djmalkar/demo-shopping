package com.dipesh.demoshopping.dependencyinjection.presentation;

import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.common.dialogs.infodialog.InfoDialog;
import com.dipesh.demoshopping.screens.common.dialogs.promptdialog.PromptDialog;
import com.dipesh.demoshopping.screens.common.dialogs.variantsdialog.VariantsDialog;
import com.dipesh.demoshopping.screens.main.MainActivity;
import com.dipesh.demoshopping.screens.products.ProductsActivity;
import com.dipesh.demoshopping.screens.subcategorieslisting.SubCategoriesFragment;

import dagger.Subcomponent;

@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {

    void injectMainActivity(MainActivity mainActivity);

    void injectSubcategoriesFragment(SubCategoriesFragment subCategoriesFragment);

    void injectProductActivity(ProductsActivity productsActivity);

    void injectVariantsDialog(VariantsDialog variantsDialog);

    void injectPromptDialog(PromptDialog promptDialog);

    void injectInfoDialog(InfoDialog infoDialog);

    ViewMvcFactory getViewMvcFactory();
}
