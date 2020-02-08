package com.dipesh.demoshopping.screens.main;

import android.os.Bundle;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.base.BaseActivity;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerHelper;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerViewMvc;
import com.dipesh.demoshopping.screens.common.screensnavigator.ScreensNavigator;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements FetchCategoriesRankingUseCase.Listener,
        NavDrawerViewMvc.Listener, NavDrawerHelper {

    @Inject
    FetchCategoriesRankingUseCase mFetchCategoriesRankingUseCase;

    private NavDrawerViewMvc mViewMvc;
    private ScreensNavigator mScreensNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPresentationComponent().injectMainActivity(this);

        setContentView(mViewMvc.getRootView());

        if (savedInstanceState == null) {
            //mScreensNavigator.toQuestionsList();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFetchCategoriesRankingUseCase.registerListener(this);
        mFetchCategoriesRankingUseCase.fetchCategoriesAndNotify();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFetchCategoriesRankingUseCase.removeListener(this);
    }

    @Override
    public void onQuestionsListClicked() {

    }

    @Override
    public void openDrawer() {
        mViewMvc.openDrawer();
    }

    @Override
    public void closeDrawer() {
        mViewMvc.closeDrawer();
    }

    @Override
    public boolean isDrawerOpen() {
        return mViewMvc.isDrawerOpen();
    }
}
