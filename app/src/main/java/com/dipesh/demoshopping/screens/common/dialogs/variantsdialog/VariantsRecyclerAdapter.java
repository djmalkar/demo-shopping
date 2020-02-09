package com.dipesh.demoshopping.screens.common.dialogs.variantsdialog;

import android.view.ViewGroup;

import com.dipesh.demoshopping.screens.common.ViewMvcFactory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VariantsRecyclerAdapter extends RecyclerView.Adapter<VariantsRecyclerAdapter.VariantViewHolder> {

    static class VariantViewHolder extends RecyclerView.ViewHolder {

        private final VariantsItemViewMvc mViewMvc;

        public VariantViewHolder(VariantsItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }
    }

    private final ViewMvcFactory mViewMvcFactory;

    private List<VariantModel> mVariants = new ArrayList<>();

    public VariantsRecyclerAdapter(ViewMvcFactory viewMvcFactory) {
        mViewMvcFactory = viewMvcFactory;
    }

    public void bindData(List<VariantModel> variants) {
        mVariants = new ArrayList<>(variants);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VariantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VariantsItemViewMvc viewMvc = mViewMvcFactory.getVariantItemViewMvc(parent);
        return new VariantViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull VariantViewHolder holder, int position) {
        holder.mViewMvc.bindData(mVariants.get(position));
    }

    @Override
    public int getItemCount() {
        return mVariants.size();
    }

}
