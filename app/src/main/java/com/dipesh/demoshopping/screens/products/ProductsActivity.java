package com.dipesh.demoshopping.screens.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.screens.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class ProductsActivity extends BaseActivity implements ProductViewMvc.Listener, FetchProductsUseCase.Listener {

    private ProductViewMvc mViewMvc;
    private boolean mIsFirstTime = true;

    @Inject FetchProductsUseCase mFetchProductsUseCase;

    private static final String PRODUCT_TYPE_ID = "PRODUCT_TYPE_ID";
    private static final String PRODUCT_TYPE = "PRODUCT_TYPE";

    public static Intent newInstance(Context context, int productId, String productType) {
        Intent intent = new Intent(context, ProductsActivity.class);
        intent.putExtra(PRODUCT_TYPE_ID, productId);
        intent.putExtra(PRODUCT_TYPE, productType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresentationComponent().injectProductActivity(this);

        mViewMvc = getPresentationComponent().getViewMvcFactory().getProductViewMvc(null);
        mViewMvc.setTitle(getProductType());

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchProductsUseCase.registerListener(this);


        if(mIsFirstTime) {
            mIsFirstTime = false;
            mFetchProductsUseCase.getProductsAndNotify(getIntent().getIntExtra(PRODUCT_TYPE_ID, 0));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchProductsUseCase.removeListener(this);
    }

    @Override
    public void onBackClicked() {
        onBackPressed();
    }

    @Override
    public void onProductClicked(int productId) {

    }

    private String getProductType() {
        return getIntent().getStringExtra(PRODUCT_TYPE);
    }

    @Override
    public void onProductsFetched(List<ProductTable> productTables) {
        mViewMvc.populateAdapter(productTables);
    }
}
