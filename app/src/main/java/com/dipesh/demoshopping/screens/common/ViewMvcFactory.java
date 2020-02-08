package com.dipesh.demoshopping.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerHelper;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerViewMvc;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerViewMvcImpl;
import com.dipesh.demoshopping.screens.common.toolbar.ToolbarViewMvc;
import com.dipesh.demoshopping.screens.subcategorieslisting.SubCategoriesViewMvc;
import com.dipesh.demoshopping.screens.subcategorieslisting.SubCategoriesViewMvcImpl;
import com.dipesh.demoshopping.screens.subcategorieslisting.listitem.SubCategoriesHeaderItemViewMvc;
import com.dipesh.demoshopping.screens.subcategorieslisting.listitem.SubCategoryItemViewMvc;

import androidx.annotation.Nullable;

public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;
    private final NavDrawerHelper mNavDrawerHelper;

    public ViewMvcFactory(LayoutInflater layoutInflater, NavDrawerHelper navDrawerHelper) {
        mLayoutInflater = layoutInflater;
        mNavDrawerHelper = navDrawerHelper;
    }

    public SubCategoriesViewMvc getSubCategoriesViewMvc(@Nullable ViewGroup parent) {
        return new SubCategoriesViewMvcImpl(mLayoutInflater, parent, this);
    }

    public ToolbarViewMvc getToolbarViewMvc(@Nullable ViewGroup parent) {
        return new ToolbarViewMvc(mLayoutInflater, parent);
    }

    public NavDrawerViewMvc getNavDrawerViewMvc(@Nullable ViewGroup parent) {
        return new NavDrawerViewMvcImpl(mLayoutInflater, parent);
    }

    public SubCategoryItemViewMvc getSubCategoryItemViewMvc(ViewGroup parent) {
        return new SubCategoryItemViewMvc(mLayoutInflater, parent);
    }

    public SubCategoriesHeaderItemViewMvc getSubCategoriesHeaderItemViewMvc(ViewGroup parent) {
        return new SubCategoriesHeaderItemViewMvc(mLayoutInflater, parent);
    }
}
