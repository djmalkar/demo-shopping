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

    private final TextView mText;

    private ProductTable mProduct;

    public ProductItemViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.item_list_view, parent, false));

        mText = findViewById(R.id.text);

        getRootView().setOnClickListener(view -> {
            if(getListeners() != null) {
                getListeners().onProductClicked(mProduct);
            }
        });
    }

    public void bindData(ProductTable productTable) {
        mProduct = productTable;
        mText.setText(productTable.name);
    }
}
