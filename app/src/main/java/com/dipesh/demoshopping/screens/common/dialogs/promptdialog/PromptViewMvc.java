package com.dipesh.demoshopping.screens.common.dialogs.promptdialog;

import com.dipesh.demoshopping.screens.common.views.ObservableViewMvc;

public interface PromptViewMvc extends ObservableViewMvc<PromptViewMvc.Listener> {

    interface Listener {
        void onPositiveButtonClicked();
        void onNegativeButtonClicked();
    }

    void setTitle(String title);
    void setMessage(String message);
    void setPositiveButtonCaption(String caption);
    void setNegativeButtonCaption(String caption);
}

