package com.dipesh.demoshopping.screens.subcategorieslisting;

import com.dipesh.demoshopping.screens.common.views.ObservableViewMvc;

import java.util.HashMap;

public interface SubCategoriesViewMvc extends ObservableViewMvc<SubCategoriesViewMvc.Listener> {

    interface Listener {
    }

    void showProgressBar();

    void hideProgressBar();

    void setAdapterData(HashMap<Integer, SubCategoryModel> subCategoryModelHashMap);
}
