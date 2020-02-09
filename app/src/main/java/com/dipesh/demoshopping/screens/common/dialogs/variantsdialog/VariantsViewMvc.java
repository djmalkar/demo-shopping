package com.dipesh.demoshopping.screens.common.dialogs.variantsdialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.common.views.BaseViewMvc;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VariantsViewMvc extends BaseViewMvc {

    private final RecyclerView mVariantsList;
    private final VariantsRecyclerAdapter mAdapter;

    public VariantsViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent, ViewMvcFactory viewMvcFactory) {

        setRootView(inflater.inflate(R.layout.dialog_variants, parent, false));

        mVariantsList = findViewById(R.id.variants_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mVariantsList.setLayoutManager(layoutManager);
        mVariantsList.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
        mAdapter = new VariantsRecyclerAdapter(viewMvcFactory);
        mVariantsList.setAdapter(mAdapter);

    }

    public void bindData(List<VariantModel> variants) {
        mAdapter.bindData(variants);
    }
}
