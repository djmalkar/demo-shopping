package com.dipesh.demoshopping.screens.subcategorieslisting.listitem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;

import androidx.annotation.Nullable;

public class SubCategoryItemViewMvc extends BaseObservableViewMvc<SubCategoryItemViewMvc.Listener> {

    public interface Listener {
        void onSubCategoryClicked(ProductTypeTable productTypeTable);
    }

    private final TextView mText;

    private ProductTypeTable mProductType;

    public SubCategoryItemViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.item_list_view, parent, false));

        mText = findViewById(R.id.text);

        getRootView().setOnClickListener(view -> {
            if(getListeners() != null) {
                getListeners().onSubCategoryClicked(mProductType);
            }
        });
    }

    public void bindData(ProductTypeTable productTypeTable) {
        mProductType = productTypeTable;
        mText.setText(productTypeTable.name);
    }
}
