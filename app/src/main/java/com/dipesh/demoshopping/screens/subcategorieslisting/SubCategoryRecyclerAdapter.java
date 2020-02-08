package com.dipesh.demoshopping.screens.subcategorieslisting;

import android.view.ViewGroup;

import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.subcategorieslisting.listitem.SubCategoriesHeaderItemViewMvc;
import com.dipesh.demoshopping.screens.subcategorieslisting.listitem.SubCategoryItemViewMvc;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubCategoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements SubCategoryItemViewMvc.Listener {

    public interface Listener {
        void onProductTypeClicked(ProductTypeTable productTypeTable);
    }

    static class ProductTypeViewHolder extends RecyclerView.ViewHolder {

        private final SubCategoryItemViewMvc mViewMvc;

        public ProductTypeViewHolder(SubCategoryItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }
    }

    static class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        private final SubCategoriesHeaderItemViewMvc mViewMvc;

        public SubCategoryViewHolder(SubCategoriesHeaderItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }
    }

    private static final int VIEW_TYPE_SUB_CATEGORY = 1;
    private static final int VIEW_TYPE_PRODUCT = 2;
    private final Listener mListener;
    private final ViewMvcFactory mViewMvcFactory;

    private Map<Integer, SubCategoryModel> mSubCategoryData = new HashMap<>();

    public SubCategoryRecyclerAdapter(Listener listener, ViewMvcFactory viewMvcFactory) {
        mListener = listener;
        mViewMvcFactory = viewMvcFactory;
    }

    public void bindQuestions(Map<Integer, SubCategoryModel> subCategoryData) {
        mSubCategoryData = new HashMap<>(subCategoryData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SUB_CATEGORY) {
            SubCategoriesHeaderItemViewMvc viewMvc = mViewMvcFactory.getSubCategoriesHeaderItemViewMvc(parent);
            return new SubCategoryViewHolder(viewMvc);
        } else {
            SubCategoryItemViewMvc viewMvc = mViewMvcFactory.getSubCategoryItemViewMvc(parent);
            viewMvc.registerListener(this);
            return new ProductTypeViewHolder(viewMvc);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ProductTypeViewHolder) {
            ((ProductTypeViewHolder) holder).mViewMvc.bindData(mSubCategoryData.get(position).productTypeTable);
        } else {
            ((SubCategoryViewHolder) holder).mViewMvc.bindData(mSubCategoryData.get(position).name);
        }
    }

    @Override
    public int getItemCount() {
        return mSubCategoryData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mSubCategoryData.get(position).isSubCategory) {
            return VIEW_TYPE_SUB_CATEGORY;
        } else {
            return VIEW_TYPE_PRODUCT;
        }
    }

    @Override
    public void onProductTypeClicked(ProductTypeTable productTypeTable) {
        mListener.onProductTypeClicked(productTypeTable);
    }

}
