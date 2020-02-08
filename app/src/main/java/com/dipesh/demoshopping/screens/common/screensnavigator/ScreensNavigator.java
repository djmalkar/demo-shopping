package com.dipesh.demoshopping.screens.common.screensnavigator;

import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameHelper;

public class ScreensNavigator {

    private FragmentFrameHelper mFragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }
}
