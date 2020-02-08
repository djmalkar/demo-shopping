package com.dipesh.demoshopping.screens.common.views;

public abstract class BaseObservableViewMvc<ListenerType> extends BaseViewMvc
        implements ObservableViewMvc<ListenerType> {

    private ListenerType mListeners;

    @Override
    public final void registerListener(ListenerType listener) {
        mListeners = listener;
    }

    @Override
    public final void unregisterListener(ListenerType listener) {
        mListeners = null;
    }

    protected final ListenerType getListeners() {
        return mListeners;
    }
}
