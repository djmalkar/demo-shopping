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
        void onOrderSorted();
        void onViewSorted();
        void onSharedSorted();
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
        mToolbar.inflateMenu(R.menu.menu_drawer);
        mToolbar.setOnMenuItemClickListener(item -> {
            if(getListeners() == null) {
                return false;
            }
            switch (item.getItemId()) {
                case R.id.drawer_menu_viewed : getListeners().onViewSorted();
                    break;
                case R.id.drawer_menu_ordered : getListeners().onOrderSorted();
                    break;
                case R.id.drawer_menu_shared : getListeners().onSharedSorted();
                    break;
            }
            return false;
        });

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
