package com.dipesh.demoshopping.common;

public abstract class BaseObservable<LISTENER> {

    private LISTENER mListeners;

    public final void registerListener(LISTENER listener) {
        mListeners = listener;
    }

    public final void removeListener(LISTENER listener) {
        mListeners = null;
    }

    protected LISTENER getListener() {
        return mListeners;
    }

}
