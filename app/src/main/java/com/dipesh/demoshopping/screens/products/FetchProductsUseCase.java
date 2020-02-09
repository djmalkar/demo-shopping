package com.dipesh.demoshopping.screens.products;

import com.dipesh.demoshopping.common.BaseObservable;
import com.dipesh.demoshopping.data.local.DbHelper;
import com.dipesh.demoshopping.model.tables.ProductTable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FetchProductsUseCase extends BaseObservable<FetchProductsUseCase.Listener> {

    public interface Listener {
        void onProductsFetched(List<ProductTable> productTables);
    }

    private final DbHelper mDbHelper;

    @Inject
    public FetchProductsUseCase(DbHelper mDbHelper) {
        this.mDbHelper = mDbHelper;
    }

    public void getProductsAndNotify(int productTypeId) {
        mDbHelper.getProductsByTypeId(productTypeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<ProductTable>>() {
                    @Override
                    public void onSuccess(List<ProductTable> productTables) {
                        if(getListener() != null) {
                            getListener().onProductsFetched(productTables);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
