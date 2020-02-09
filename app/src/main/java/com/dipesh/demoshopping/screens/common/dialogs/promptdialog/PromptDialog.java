package com.dipesh.demoshopping.screens.common.dialogs.promptdialog;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.view.ViewGroup;

import com.dipesh.demoshopping.screens.base.BaseDialog;
import com.dipesh.demoshopping.screens.common.dialogs.DialogsEventBus;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class PromptDialog extends BaseDialog implements PromptViewMvc.Listener {

    public static final String TAG = "PromptDialog";
    protected static final String ARG_TITLE = "ARG_TITLE";
    protected static final String ARG_MESSAGE = "ARG_MESSAGE";
    protected static final String ARG_POSITIVE_BUTTON_CAPTION = "ARG_POSITIVE_BUTTON_CAPTION";
    protected static final String ARG_NEGATIVE_BUTTON_CAPTION = "ARG_NEGATIVE_BUTTON_CAPTION";

    public static PromptDialog newPromptDialog(String title, String message, String positiveButtonCaption,
                                               String negativeButtonCaption) {
        PromptDialog promptDialog = new PromptDialog();
        Bundle args = new Bundle(4);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_POSITIVE_BUTTON_CAPTION, positiveButtonCaption);
        args.putString(ARG_NEGATIVE_BUTTON_CAPTION, negativeButtonCaption);
        promptDialog.setArguments(args);
        return promptDialog;
    }

    @Inject DialogsEventBus mDialogsEventBus;
    private PromptViewMvc mViewMvc;

    @NonNull
    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new IllegalStateException("arguments mustn't be null");
        }

        getPresentationComponent().injectPromptDialog(this);

        mViewMvc = getPresentationComponent().getViewMvcFactory().getPromptViewMvc(null);

        mViewMvc.setTitle(getArguments().getString(ARG_TITLE));
        mViewMvc.setMessage(getArguments().getString(ARG_MESSAGE));
        mViewMvc.setPositiveButtonCaption(getArguments().getString(ARG_POSITIVE_BUTTON_CAPTION));
        mViewMvc.setNegativeButtonCaption(getArguments().getString(ARG_NEGATIVE_BUTTON_CAPTION));

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(mViewMvc.getRootView());

        Point size = new Point();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(size);
        dialog.getWindow().setLayout((int) (size.x * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);

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