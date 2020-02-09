package com.dipesh.demoshopping.screens.common.dialogs.variantsdialog;

import com.dipesh.demoshopping.common.BaseObservable;
import com.dipesh.demoshopping.data.local.DbHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FetchVariantsUseCase extends BaseObservable<FetchVariantsUseCase.Listener> {

    public interface Listener {
        void onVariantsFetched (List<VariantModel> variants);
    }

    private final DbHelper mDbHelper;

    @Inject
    public FetchVariantsUseCase(DbHelper mDbHelper) {
        this.mDbHelper = mDbHelper;
    }

    public void fetchVariantsAndNotify (int productId) {
        mDbHelper.getVariantsByProductId(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(variantTables -> {
                    if(getListener() != null) {
                        getListener().onVariantsFetched(variantTables);
                    }
                });
    }
}
