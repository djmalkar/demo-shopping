package com.dipesh.demoshopping.screens.subcategorieslisting.listitem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.views.BaseViewMvc;

import androidx.annotation.Nullable;

public class SubCategoriesHeaderItemViewMvc extends BaseViewMvc {

    private final TextView mHeaderText;

    public SubCategoriesHeaderItemViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.item_header_list_view, parent, false));

        mHeaderText = findViewById(R.id.text);
    }

    public void bindData(String name) {
        mHeaderText.setText(name);
    }
}
