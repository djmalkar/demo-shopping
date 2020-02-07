package com.dipesh.demoshopping.dependencyinjection.modules;

import android.content.Context;

import com.dipesh.demoshopping.data.local.DbHelperImpl;
import com.dipesh.demoshopping.screens.main.CategoriesTablesUseCase;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private AppCompatActivity activity;

    public PresentationModule(AppCompatActivity activity){
        this.activity = activity;
    }

    @Provides
    public Context context(){return activity;}

    @Provides
    public AppCompatActivity activity(){
        return activity;
    }

    @Provides
    CategoriesTablesUseCase getCategoriesTablesUseCase(DbHelperImpl dbHelperImpl) {
        return new CategoriesTablesUseCase(dbHelperImpl);
    }
}
