package com.dipesh.demoshopping.screens.common.navdrawer;

import android.widget.FrameLayout;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.screens.common.views.ObservableViewMvc;

import java.util.List;

public interface NavDrawerViewMvc extends ObservableViewMvc<NavDrawerViewMvc.Listener> {

    interface Listener {
        void onNavItemClicked(int categoryId);
    }
    FrameLayout getFragmentFrame();
    void setMenuItems(List<CategoryTable> categories);
    void openDrawer();
    void closeDrawer();
    void showProgress();
    void hideProgress();
}
