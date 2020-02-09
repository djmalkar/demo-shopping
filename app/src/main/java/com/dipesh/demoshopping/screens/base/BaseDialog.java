package com.dipesh.demoshopping.screens.base;

import com.dipesh.demoshopping.ShoppingApplication;
import com.dipesh.demoshopping.dependencyinjection.application.ApplicationComponent;
import com.dipesh.demoshopping.dependencyinjection.presentation.PresentationComponent;
import com.dipesh.demoshopping.dependencyinjection.presentation.PresentationModule;

import androidx.fragment.app.DialogFragment;

public abstract class BaseDialog extends DialogFragment {

    protected PresentationComponent getPresentationComponent() {
        return getApplicationComponent().newPresentationComponent(new PresentationModule(getActivity()));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((ShoppingApplication) getActivity().getApplication()).getApplicationComponent();
    }

}
