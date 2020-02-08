package com.dipesh.demoshopping.screens.common.dialogs.variantsdialog;

import android.app.Dialog;
import android.os.Bundle;

import com.dipesh.demoshopping.screens.base.BaseDialog;
import com.dipesh.demoshopping.screens.common.dialogs.DialogsEventBus;
import com.dipesh.demoshopping.screens.common.dialogs.promptdialog.PromptDialog;
import com.dipesh.demoshopping.screens.common.dialogs.promptdialog.PromptDialogEvent;
import com.dipesh.demoshopping.screens.common.dialogs.promptdialog.PromptViewMvc;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class VariantsDialog extends BaseDialog implements PromptViewMvc.Listener {

    protected static final String PRODUCT_ID = "PRODUCT_ID";

    public static PromptDialog newPromptDialog(int productId) {
        PromptDialog promptDialog = new PromptDialog();
        Bundle args = new Bundle(1);
        args.putInt(PRODUCT_ID, productId);
        promptDialog.setArguments(args);
        return promptDialog;
    }

    @Inject
    DialogsEventBus mDialogsEventBus;
    private PromptViewMvc mViewMvc;

    @NonNull
    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new IllegalStateException("arguments mustn't be null");
        }

        mViewMvc = getPresentationComponent().getViewMvcFactory().getPromptViewMvc(null);


        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(mViewMvc.getRootView());

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    @Override
    public void onPositiveButtonClicked() {
        dismiss();
        mDialogsEventBus.postEvent(new PromptDialogEvent(PromptDialogEvent.Button.POSITIVE));
    }

    @Override
    public void onNegativeButtonClicked() {
        dismiss();
        mDialogsEventBus.postEvent(new PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE));
    }

}
