package com.dipesh.demoshopping.screens.subcategorieslisting;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.common.navdrawer.NavDrawerHelper;
import com.dipesh.demoshopping.screens.common.toolbar.ToolbarViewMvc;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;

import java.util.HashMap;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SubCategoriesViewMvc extends BaseObservableViewMvc<SubCategoriesViewMvc.Listener>
        implements SubCategoryRecyclerAdapter.Listener {

    public interface Listener {
        void onProductTypeClicked(ProductTypeTable productType);
    }

    private final ToolbarViewMvc mToolbarViewMvc;
    private final Toolbar mToolbar;
    private final RecyclerView mSubCategoryList;
    private final RelativeLayout mProgressBar;
    private final SubCategoryRecyclerAdapter mAdapter;
    private final NavDrawerHelper mNavDrawerHelper;

    public SubCategoriesViewMvc(LayoutInflater inflater, ViewGroup parent,
                                ViewMvcFactory viewMvcFactory, NavDrawerHelper navDrawerHelper) {

        mNavDrawerHelper = navDrawerHelper;
        setRootView(inflater.inflate(R.layout.fragment_subcategories, parent, false));

        mSubCategoryList = findViewById(R.id.sub_category_list);
        mSubCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SubCategoryRecyclerAdapter(this, viewMvcFactory);
        mSubCategoryList.setAdapter(mAdapter);

        mProgressBar = findViewById(R.id.progress);
        mToolbar = findViewById(R.id.toolbar);

        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView());

        mToolbarViewMvc.setTitle("Categories");

        mToolbarViewMvc.enableHamburgerButtonAndListen(() -> {
            mNavDrawerHelper.openDrawer();
        });
    }

    public void setAdapterData(HashMap<Integer, SubCategoryModel> subCategoryModelHashMap) {
        mAdapter.bindQuestions(subCategoryModelHashMap);
    }

    @Override
    public void onProductTypeClicked(ProductTypeTable productTypeTable) {
        if(getListeners() != null) {
            getListeners().onProductTypeClicked(productTypeTable);
        }
    }
}
