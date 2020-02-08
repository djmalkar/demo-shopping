package com.dipesh.demoshopping.screens.base;

import com.dipesh.demoshopping.ShoppingApplication;
import com.dipesh.demoshopping.dependencyinjection.components.ApplicationComponent;
import com.dipesh.demoshopping.dependencyinjection.components.PresentationComponent;
import com.dipesh.demoshopping.dependencyinjection.modules.PresentationModule;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected PresentationComponent getPresentationComponent() {
        return getApplicationComponent().newPresentationComponent(new PresentationModule(getActivity()));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((ShoppingApplication) getActivity().getApplication()).getApplicationComponent();
    }
}
