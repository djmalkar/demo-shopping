package com.dipesh.demoshopping.screens.common.dialogs;

import android.content.Context;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.base.BaseDialog;
import com.dipesh.demoshopping.screens.common.dialogs.promptdialog.PromptDialog;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DialogsManager {

    private final Context mContext;
    private final FragmentManager mFragmentManager;

    public DialogsManager(Context context, FragmentManager fragmentManager) {
        mContext = context;
        mFragmentManager = fragmentManager;
    }

    public void showExitDialog(@Nullable String tag) {
        DialogFragment dialogFragment = PromptDialog.newPromptDialog(
                getString(R.string.exit_title),
                getString(R.string.exit_message_message),
                getString(R.string.exit_positive_button_caption),
                getString(R.string.exit_negative_button_caption)
        );
        dialogFragment.show(mFragmentManager, tag);
    }

    private String getString(int stringId) {
        return mContext.getString(stringId);
    }

    public @Nullable String getShownDialogTag() {
        for (Fragment fragment : mFragmentManager.getFragments()) {
            if (fragment instanceof BaseDialog) {
                return fragment.getTag();
            }
        }
        return null;
    }
}
