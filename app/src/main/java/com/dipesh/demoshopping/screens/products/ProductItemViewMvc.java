package com.dipesh.demoshopping.screens.products;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;

import androidx.annotation.Nullable;

public class ProductItemViewMvc extends BaseObservableViewMvc<ProductItemViewMvc.Listener> {

    public interface Listener {
        void onProductClicked(ProductTable productTable);
    }

    private final TextView mProductName;
    private final TextView mViews;
    private final TextView mOrders;
    private final TextView mShares;

    private ProductTable mProduct;

    public ProductItemViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.item_product_view, parent, false));

        mProductName = findViewById(R.id.product_name);
        mViews = findViewById(R.id.view_counts);
        mOrders = findViewById(R.id.order_counts);
        mShares = findViewById(R.id.shared_counts);

        getRootView().setOnClickListener(view -> {
            if(getListeners() != null) {
                getListeners().onProductClicked(mProduct);
            }
        });
    }

    public void bindData(ProductTable productTable) {
        mProduct = productTable;
        mProductName.setText(productTable.name);
        mViews.setText("Total Views : " + productTable.viewCounts);
        mOrders.setText("Total Orders : " + productTable.orderCounts);
        mShares.setText("Total Shares : " + productTable.sharedCounts);
    }
}
