package com.dipesh.demoshopping.dependencyinjection.modules;

import android.content.Context;
import android.view.LayoutInflater;

import com.dipesh.demoshopping.data.local.DbHelperImpl;
import com.dipesh.demoshopping.data.remote.ApiRetrofit;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.dipesh.demoshopping.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.dipesh.demoshopping.screens.common.screensnavigator.ScreensNavigator;
import com.dipesh.demoshopping.screens.main.FetchCategoriesRankingUseCase;
import com.dipesh.demoshopping.screens.main.ParseCategoriesRankingToTablesUseCase;
import com.dipesh.demoshopping.screens.products.FetchProductsUseCase;
import com.dipesh.demoshopping.screens.subcategorieslisting.FetchSubCategoryForAdapterUseCase;

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
    ViewMvcFactory getViewMvcFactory(LayoutInflater layoutInflater){
        return new ViewMvcFactory(layoutInflater);
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

    @Provides
    ScreensNavigator getScreenNavigator(Context context, FragmentFrameHelper fragmentFrameHelper) {
        return new ScreensNavigator(context, fragmentFrameHelper);
    }

    @Provides
    ParseCategoriesRankingToTablesUseCase getParseCategoriesRankingToTablesUseCase() {
        return new ParseCategoriesRankingToTablesUseCase();
    }

    @Provides
    FetchCategoriesRankingUseCase getFetchCategoriesRankingUseCase(DbHelperImpl dbHelperImpl, ApiRetrofit apiRetrofit,
                                                                   ParseCategoriesRankingToTablesUseCase parseCategoriesRankingToTablesUseCase) {
        return new FetchCategoriesRankingUseCase(dbHelperImpl, apiRetrofit, parseCategoriesRankingToTablesUseCase);
    }

    @Provides
    FetchSubCategoryForAdapterUseCase getFetchSubCategoryForAdapterUseCase(DbHelperImpl dbHelperImpl) {
        return new FetchSubCategoryForAdapterUseCase(dbHelperImpl);
    }

    @Provides
    FetchProductsUseCase getFetchProductsUseCase(DbHelperImpl dbHelperImpl) {
        return new FetchProductsUseCase(dbHelperImpl);
    }
}
