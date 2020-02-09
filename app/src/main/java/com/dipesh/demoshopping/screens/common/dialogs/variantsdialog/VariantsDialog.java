package com.dipesh.demoshopping.screens.common.dialogs.variantsdialog;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;

import com.dipesh.demoshopping.screens.base.BaseDialog;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class VariantsDialog extends BaseDialog implements FetchVariantsUseCase.Listener {

    public static final String TAG = "VariantsDialog";
    protected static final String PRODUCT_ID = "PRODUCT_ID";

    public static VariantsDialog newVariantDialog(int productId) {
        VariantsDialog variantsDialog = new VariantsDialog();
        Bundle args = new Bundle(1);
        args.putInt(PRODUCT_ID, productId);
        variantsDialog.setArguments(args);
        return variantsDialog;
    }

    @Inject FetchVariantsUseCase mFetchVariantsUseCase;
    private VariantsViewMvc mViewMvc;

    @NonNull
    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new IllegalStateException("arguments mustn't be null");
        }

        getPresentationComponent().injectVariantsDialog(this);
        mViewMvc = getPresentationComponent().getViewMvcFactory().getVariantViewMvc(null);

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(mViewMvc.getRootView());

        Point size = new Point();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(size);
        dialog.getWindow().setLayout((int) (size.x * 0.80), (int) (size.y * 0.80));

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFetchVariantsUseCase.registerListener(this);
        mFetchVariantsUseCase.fetchVariantsAndNotify(getArguments().getInt(PRODUCT_ID));
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchVariantsUseCase.removeListener(this);
    }

    @Override
    public void onVariantsFetched(List<VariantModel> variants) {
        mViewMvc.bindData(variants);
    }
}
