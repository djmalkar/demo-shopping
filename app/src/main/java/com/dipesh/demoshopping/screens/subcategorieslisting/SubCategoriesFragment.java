package com.dipesh.demoshopping.screens.subcategorieslisting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dipesh.demoshopping.screens.base.BaseFragment;
import com.dipesh.demoshopping.screens.common.screensnavigator.ScreensNavigator;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SubCategoriesFragment extends BaseFragment implements
        SubCategoriesViewMvc.Listener {

    private static final String CATEGORY_ID = "CATEGORY_ID";

    private static final String DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR";

    private static final String SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE";

    public static SubCategoriesFragment newInstance(String questionId) {
        Bundle args = new Bundle();
        args.putString(CATEGORY_ID, questionId);
        SubCategoriesFragment fragment = new SubCategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private enum ScreenState {
        IDLE, QUESTION_DETAILS_SHOWN, NETWORK_ERROR
    }

    @Inject
    ScreensNavigator mScreensNavigator;

    @Inject
    FetchSubCategoryForAdapterUseCase mFetchSubCategoryForAdapterUseCase;

    private SubCategoriesViewMvc mViewMvc;

    private ScreenState mScreenState = ScreenState.IDLE;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mScreenState = (ScreenState) savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE);
        }
    }

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

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchQuestionDetailsAndNotify();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SCREEN_STATE, mScreenState);
    }

    private void fetchQuestionDetailsAndNotify() {
        mViewMvc.showProgressIndication();
    }

    private String getQuestionId() {
        return getArguments().getString(CATEGORY_ID);
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.navigateUp();
    }

}
