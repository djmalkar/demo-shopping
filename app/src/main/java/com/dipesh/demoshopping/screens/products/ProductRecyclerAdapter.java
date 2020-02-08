package com.dipesh.demoshopping.screens.products;

import android.view.ViewGroup;

import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>
        implements ProductItemViewMvc.Listener {

    public interface Listener {
        void onProductClicked(ProductTable productTable);
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ProductItemViewMvc mViewMvc;

        public ProductViewHolder(ProductItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }
    }

    private final Listener mListener;
    private final ViewMvcFactory mViewMvcFactory;

    private List<ProductTable> mProductTables = new ArrayList<>();

    public ProductRecyclerAdapter(Listener listener, ViewMvcFactory viewMvcFactory) {
        mListener = listener;
        mViewMvcFactory = viewMvcFactory;
    }

    public void bindData(List<ProductTable> productTables) {
        mProductTables = new ArrayList<>(productTables);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemViewMvc viewMvc = mViewMvcFactory.getProductItemViewMvc(parent);
        return new ProductViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.mViewMvc.bindData(mProductTables.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductTables.size();
    }

    @Override
    public void onProductClicked(ProductTable productTable) {
        if(mListener != null) {
            mListener.onProductClicked(productTable);
        }
    }

}
