package com.dipesh.demoshopping.screens.products;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.common.toolbar.ToolbarViewMvc;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewMvc extends BaseObservableViewMvc<ProductViewMvc.Listener> implements ProductRecyclerAdapter.Listener {

    public interface Listener {
        void onBackClicked();
        void onProductClicked(int productId);
    }

    private final ToolbarViewMvc mToolbarViewMvc;
    private final Toolbar mToolbar;
    private final RecyclerView mProductsList;
    private final ProductRecyclerAdapter mAdapter;

    public ProductViewMvc(LayoutInflater inflater, ViewGroup parent,
                          ViewMvcFactory viewMvcFactory) {

        setRootView(inflater.inflate(R.layout.activity_product, parent, false));

        mProductsList = findViewById(R.id.product_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mProductsList.setLayoutManager(layoutManager);
        mProductsList.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
        mAdapter = new ProductRecyclerAdapter(this, viewMvcFactory);
        mProductsList.setAdapter(mAdapter);
        mToolbar = findViewById(R.id.toolbar);

        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView());

        mToolbarViewMvc.enableUpButtonAndListen(() -> {
            if(getListeners() != null) {
                getListeners().onBackClicked();
            }
        });
    }

    public void populateAdapter(List<ProductTable> productTables) {
        mAdapter.bindData(productTables);
    }

    @Override
    public void onProductClicked(ProductTable productTable) {
        if(getListeners() != null) {
            getListeners().onProductClicked(productTable.id);
        }
    }

    public void setTitle(String productType) {
        mToolbarViewMvc.setTitle(productType);
    }
}
