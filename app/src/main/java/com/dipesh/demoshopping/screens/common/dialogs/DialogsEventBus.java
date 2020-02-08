package com.dipesh.demoshopping.screens.common.dialogs;


import com.dipesh.demoshopping.common.BaseObservable;

public class DialogsEventBus extends BaseObservable<DialogsEventBus.Listener> {

    public interface Listener {
        void onDialogEvent(Object event);
    }

    public void postEvent(Object event) {
        if(getListener() != null) {
            getListener().onDialogEvent(event);
        }
    }

}
