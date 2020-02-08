package com.dipesh.demoshopping.screens.main;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.screens.base.BaseActivity;
import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerHelper;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerViewMvc;
import com.dipesh.demoshopping.screens.common.screensnavigator.ScreensNavigator;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements FetchCategoriesRankingUseCase.Listener,
        NavDrawerViewMvc.Listener, NavDrawerHelper, FragmentFrameWrapper {

    @Inject FetchCategoriesRankingUseCase mFetchCategoriesRankingUseCase;

    NavDrawerViewMvc mViewMvc;

    @Inject ScreensNavigator mScreensNavigator;

    private boolean mIsFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresentationComponent().injectMainActivity(this);

        mViewMvc = getPresentationComponent().getViewMvcFactory().getNavDrawerViewMvc(null);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchCategoriesRankingUseCase.registerListener(this);

        if(mIsFirstTime) {
            mViewMvc.showProgress();
            mFetchCategoriesRankingUseCase.fetchCategoriesAndNotify();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchCategoriesRankingUseCase.removeListener(this);
    }

    @Override
    public void onNavItemClicked(int categoryId) {
        mScreensNavigator.toSubCategoriesFragment(categoryId);
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
    public FrameLayout getFragmentFrame() {
        return mViewMvc.getFragmentFrame();
    }

    @Override
    public void onCategoriesFetched(List<CategoryTable> categoryTables) {
        mViewMvc.hideProgress();
        mViewMvc.setMenuItems(categoryTables);
        mScreensNavigator.toSubCategoriesFragment(categoryTables.get(0).id);
    }
}
