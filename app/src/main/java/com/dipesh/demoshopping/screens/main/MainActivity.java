package com.dipesh.demoshopping.screens.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.screens.base.BaseActivity;
import com.dipesh.demoshopping.screens.common.dialogs.DialogsEventBus;
import com.dipesh.demoshopping.screens.common.dialogs.DialogsManager;
import com.dipesh.demoshopping.screens.common.dialogs.infodialog.InfoDialog;
import com.dipesh.demoshopping.screens.common.dialogs.infodialog.InfoDialogEvent;
import com.dipesh.demoshopping.screens.common.dialogs.promptdialog.PromptDialog;
import com.dipesh.demoshopping.screens.common.dialogs.promptdialog.PromptDialogEvent;
import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerHelper;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerViewMvc;
import com.dipesh.demoshopping.screens.common.screensnavigator.ScreensNavigator;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements FetchCategoriesRankingUseCase.Listener,
        NavDrawerViewMvc.Listener, NavDrawerHelper, FragmentFrameWrapper, DialogsEventBus.Listener {

    @Inject FetchCategoriesRankingUseCase mFetchCategoriesRankingUseCase;

    private NavDrawerViewMvc mViewMvc;

    @Inject ScreensNavigator mScreensNavigator;

    @Inject DialogsManager mDialogsManager;

    @Inject DialogsEventBus mDialogsEventBus;

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
        mDialogsEventBus.registerListener(this);
        mFetchCategoriesRankingUseCase.registerListener(this);

        if(mIsFirstTime) {
            if(!isNetworkConnected()) {
                mDialogsManager.showRetryDialog(InfoDialog.TAG);
            }
            mIsFirstTime = false;
            mViewMvc.showProgress();
            mFetchCategoriesRankingUseCase.fetchCategoriesAndNotify();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mDialogsEventBus.removeListener(this);
        mFetchCategoriesRankingUseCase.removeListener(this);
    }

    @Override
    public void onBackPressed() {
        mDialogsManager.showExitDialog(PromptDialog.TAG);
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

    @Override
    public void onDialogEvent(Object event) {
        if(event instanceof InfoDialogEvent) {
            mViewMvc.showProgress();
            mFetchCategoriesRankingUseCase.fetchCategoriesAndNotify();
        } else if(event instanceof PromptDialogEvent && ((PromptDialogEvent)event).getClickedButton() == PromptDialogEvent.Button.POSITIVE) {
            super.onBackPressed();
        }
    }
}
