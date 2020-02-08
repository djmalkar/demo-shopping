package com.dipesh.demoshopping.screens.subcategorieslisting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dipesh.demoshopping.screens.base.BaseFragment;
import com.dipesh.demoshopping.screens.common.screensnavigator.ScreensNavigator;

import java.util.HashMap;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SubCategoriesFragment extends BaseFragment implements
        SubCategoriesViewMvc.Listener, FetchSubCategoryForAdapterUseCase.Listener {

    private static final String CATEGORY_ID = "CATEGORY_ID";

    public static SubCategoriesFragment newInstance(int categoryId) {
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID, categoryId);
        SubCategoriesFragment fragment = new SubCategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    ScreensNavigator mScreensNavigator;

    @Inject
    FetchSubCategoryForAdapterUseCase mFetchSubCategoryForAdapterUseCase;

    private SubCategoriesViewMvc mViewMvc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewMvc = getPresentationComponent().getViewMvcFactory().getSubCategoriesViewMvc(container);
        getPresentationComponent().injectSubcategoriesFragment(this);

        return mViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchSubCategoryForAdapterUseCase.registerListener(this);

        mFetchSubCategoryForAdapterUseCase.getAdapterDataAndNotify(getCategoryId());
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    private int getCategoryId() {
        return getArguments().getInt(CATEGORY_ID);
    }

    @Override
    public void onSubCategoriesFetched(HashMap<Integer, SubCategoryModel> subCategoryModelHashMap) {
        mViewMvc.setAdapterData(subCategoryModelHashMap);
    }

}
