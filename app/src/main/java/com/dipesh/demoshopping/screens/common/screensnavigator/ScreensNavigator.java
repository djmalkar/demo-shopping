package com.dipesh.demoshopping.screens.common.screensnavigator;

import android.content.Context;

import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.dipesh.demoshopping.screens.products.ProductsActivity;
import com.dipesh.demoshopping.screens.subcategorieslisting.SubCategoriesFragment;

import javax.inject.Inject;

public class ScreensNavigator {

    private FragmentFrameHelper mFragmentFrameHelper;
    private Context mContext;

    @Inject
    public ScreensNavigator(Context context, FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
        mContext = context;
    }

    public void toSubCategoriesFragment(int categoryId) {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(SubCategoriesFragment.newInstance(categoryId));
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }

    public void toProductActivity(int productTypeId, String productType) {
        mContext.startActivity(ProductsActivity.newInstance(mContext, productTypeId, productType));
    }
}
