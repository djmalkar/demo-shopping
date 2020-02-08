package com.dipesh.demoshopping.screens.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerHelper;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerViewMvc;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerViewMvcImpl;
import com.dipesh.demoshopping.screens.common.toolbar.ToolbarViewMvc;
import com.dipesh.demoshopping.screens.products.ProductItemViewMvc;
import com.dipesh.demoshopping.screens.products.ProductViewMvc;
import com.dipesh.demoshopping.screens.subcategorieslisting.SubCategoriesViewMvc;
import com.dipesh.demoshopping.screens.subcategorieslisting.listitem.SubCategoriesHeaderItemViewMvc;
import com.dipesh.demoshopping.screens.subcategorieslisting.listitem.SubCategoryItemViewMvc;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public SubCategoriesViewMvc getSubCategoriesViewMvcImpl(@Nullable ViewGroup parent, NavDrawerHelper navDrawerHelper) {
        return new SubCategoriesViewMvc(mLayoutInflater, parent, this, navDrawerHelper);
    }

    public ToolbarViewMvc getToolbarViewMvc(@Nullable ViewGroup parent) {
        return new ToolbarViewMvc(mLayoutInflater, parent);
    }

    public NavDrawerViewMvc getNavDrawerViewMvc(@Nullable ViewGroup parent) {
        return new NavDrawerViewMvcImpl(mLayoutInflater, parent);
    }

    public ProductViewMvc getProductViewMvc(@Nullable ViewGroup parent) {
        return new ProductViewMvc(mLayoutInflater, parent, this);
    }

    public SubCategoryItemViewMvc getSubCategoryItemViewMvc(ViewGroup parent) {
        return new SubCategoryItemViewMvc(mLayoutInflater, parent);
    }

    public SubCategoriesHeaderItemViewMvc getSubCategoriesHeaderItemViewMvc(ViewGroup parent) {
        return new SubCategoriesHeaderItemViewMvc(mLayoutInflater, parent);
    }

    public ProductItemViewMvc getProductItemViewMvc(ViewGroup parent) {
        return new ProductItemViewMvc(mLayoutInflater, parent);
    }
}
