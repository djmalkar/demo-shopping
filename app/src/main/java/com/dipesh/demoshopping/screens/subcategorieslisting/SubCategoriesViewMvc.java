package com.dipesh.demoshopping.screens.subcategorieslisting;

import com.dipesh.demoshopping.screens.common.views.ObservableViewMvc;

public interface SubCategoriesViewMvc extends ObservableViewMvc<SubCategoriesViewMvc.Listener> {

    public interface Listener {
        void onNavigateUpClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();
}
