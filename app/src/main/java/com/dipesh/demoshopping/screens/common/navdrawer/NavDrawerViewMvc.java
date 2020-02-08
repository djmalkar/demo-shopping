package com.dipesh.demoshopping.screens.common.navdrawer;

import com.dipesh.demoshopping.screens.common.views.ObservableViewMvc;

import androidx.constraintlayout.widget.ConstraintLayout;

public interface NavDrawerViewMvc extends ObservableViewMvc<NavDrawerViewMvc.Listener> {

    interface Listener {
        void onQuestionsListClicked();
    }
    ConstraintLayout getFragmentFrame();
    boolean isDrawerOpen();
    void openDrawer();
    void closeDrawer();

}
