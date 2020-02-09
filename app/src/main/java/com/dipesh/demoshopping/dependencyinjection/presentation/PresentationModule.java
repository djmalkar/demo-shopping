package com.dipesh.demoshopping.dependencyinjection.presentation;

import android.content.Context;
import android.view.LayoutInflater;

import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameWrapper;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private FragmentActivity activity;

    public PresentationModule(FragmentActivity activity){
        this.activity = activity;
    }

    @Provides
    public Context getContext(){return activity;}

    @Provides
    public FragmentActivity getActivity(){
        return activity;
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    @Provides
    FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    @Provides
    FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }

    @Provides
    FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }
}
