package com.dipesh.demoshopping.screens.common.screensnavigator;

import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.dipesh.demoshopping.screens.subcategorieslisting.SubCategoriesFragment;

public class ScreensNavigator {

    private FragmentFrameHelper mFragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toSubCategoriesFragment(int categoryId) {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(SubCategoriesFragment.newInstance(categoryId));
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }
}
