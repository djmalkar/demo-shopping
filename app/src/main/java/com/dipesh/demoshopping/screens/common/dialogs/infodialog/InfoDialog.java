package com.dipesh.demoshopping.screens.common.dialogs.infodialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.base.BaseDialog;
import com.dipesh.demoshopping.screens.common.dialogs.DialogsEventBus;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class InfoDialog extends BaseDialog {

    public static final String TAG = "InfoDialog";
    protected static final String ARG_TITLE = "ARG_TITLE";
    protected static final String ARG_MESSAGE = "ARG_MESSAGE";
    protected static final String ARG_BUTTON_CAPTION = "ARG_BUTTON_CAPTION";

    @Inject
    DialogsEventBus mDialogsEventBus;

    public static InfoDialog newInfoDialog(String title, String message, String buttonCaption) {
        InfoDialog infoDialog = new InfoDialog();
        Bundle args = new Bundle(3);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_BUTTON_CAPTION, buttonCaption);
        infoDialog.setArguments(args);
        return infoDialog;
    }

    private TextView mTxtTitle;
    private TextView mTxtMessage;
    private AppCompatButton mBtnPositive;

    @NonNull
    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new IllegalStateException("arguments mustn't be null");
        }

        getPresentationComponent().injectInfoDialog(this);

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_info);
        dialog.setCancelable(false);

        mTxtTitle = dialog.findViewById(R.id.txt_title);
        mTxtMessage = dialog.findViewById(R.id.txt_message);
        mBtnPositive = dialog.findViewById(R.id.btn_positive);

        mTxtTitle.setText(getArguments().getString(ARG_TITLE));
        mTxtMessage.setText(getArguments().getString(ARG_MESSAGE));
        mBtnPositive.setText(getArguments().getString(ARG_BUTTON_CAPTION));

        mBtnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked();
            }
        });

        return dialog;
    }

    protected void onButtonClicked() {
        dismiss();
        mDialogsEventBus.postEvent(new InfoDialogEvent());
    }

}
