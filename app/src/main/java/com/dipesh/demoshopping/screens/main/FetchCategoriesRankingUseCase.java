package com.dipesh.demoshopping.screens.main;

import android.util.Log;

import com.dipesh.demoshopping.common.BaseObservable;
import com.dipesh.demoshopping.data.local.DbHelper;
import com.dipesh.demoshopping.data.remote.ApiRetrofit;
import com.dipesh.demoshopping.model.tables.CategoryTable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FetchCategoriesRankingUseCase extends BaseObservable<FetchCategoriesRankingUseCase.Listener> {

    public interface Listener {
        void onCategoriesFetched(List<CategoryTable> categoryTables);
        void onFetchingFailed();
    }

    private final DbHelper mDbHelper;
    private final ApiRetrofit mApiRetrofit;
    private final ParseCategoriesRankingToTablesUseCase mParseCategoriesRankingToTablesUseCase;
    private boolean isFirstTime = true;

    private static final String TAG = "FetchCategoriesRanking";

    @Inject
    public FetchCategoriesRankingUseCase(DbHelper dbHelper, ApiRetrofit apiRetrofit,
                                         ParseCategoriesRankingToTablesUseCase parseCategoriesRankingToTablesUseCase) {
        mDbHelper = dbHelper;
        mApiRetrofit = apiRetrofit;
        mParseCategoriesRankingToTablesUseCase = parseCategoriesRankingToTablesUseCase;
    }

    public void fetchCategoriesAndNotify() {

        mDbHelper.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<CategoryTable>>() {
                    @Override
                    public void onSuccess(List<CategoryTable> categoryTables) {
                        if(categoryTables.size() == 0 && isFirstTime) {
                            isFirstTime = false;
                            fetchFromRemoteServer();
                        } else if (categoryTables.size() > 0){
                            getListener().onCategoriesFetched(categoryTables);
                        }
                        Log.d(TAG, "onSuccess: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(getListener() != null) {
                            getListener().onFetchingFailed();
                        }
                    }
                });



    }

    private void fetchFromRemoteServer() {
        mApiRetrofit.getCategoriesRankingService()
                .map(categoriesRankingSchema -> mParseCategoriesRankingToTablesUseCase.parseSchema(categoriesRankingSchema))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<CategoriesTableModel>() {
                    @Override
                    public void onSuccess(CategoriesTableModel categoriesTableModel) {
                        Log.d(TAG, "onSuccess: ");
                        saveInDatabase(categoriesTableModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }
                });
    }

    private void saveInDatabase(CategoriesTableModel categoriesTableModel) {
        Observable.zip(mDbHelper.insertCategories(categoriesTableModel.categoryTables).toObservable(),
                mDbHelper.insertSubCategories(categoriesTableModel.subCategoryTables).toObservable(),
                mDbHelper.insertProductTypes(categoriesTableModel.productTypeTables).toObservable(),
                mDbHelper.insertProducts(categoriesTableModel.productTables).toObservable(),
                mDbHelper.insertVariants(categoriesTableModel.variantTables).toObservable(),
                (categoriesIds, subCategoriesId, productTypeIds, productIds, variantIds) -> true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Boolean>() {

                    @Override
                    public void onNext(Boolean isInserted) {
                        fetchCategoriesAndNotify();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
