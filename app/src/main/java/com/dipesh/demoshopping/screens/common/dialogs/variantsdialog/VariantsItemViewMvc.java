package com.dipesh.demoshopping.screens.common.dialogs.variantsdialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.views.BaseViewMvc;

import androidx.annotation.Nullable;

public class VariantsItemViewMvc extends BaseViewMvc {

    private final TextView mColorText;
    private final TextView mSizeText;
    private final TextView mPriceText;

    public VariantsItemViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.item_variant, parent, false));
        mColorText = findViewById(R.id.color_txt);
        mSizeText = findViewById(R.id.size_txt);
        mPriceText = findViewById(R.id.price_txt);
    }

    public void bindData(VariantModel variant) {
        mColorText.setText("Color : " + variant.color);
        mSizeText.setText("Size : " + variant.color);
        mPriceText.setText("Price with tax : " + (variant.price + variant.taxValue));
    }
}
