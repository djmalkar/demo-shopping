package com.dipesh.demoshopping.screens.subcategorieslisting;

import com.dipesh.demoshopping.common.BaseObservable;
import com.dipesh.demoshopping.data.local.DbHelper;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FetchSubCategoryForAdapterUseCase extends BaseObservable<FetchSubCategoryForAdapterUseCase.Listener> {

    public interface Listener {
        void onSubCategoriesFetched(HashMap<Integer, SubCategoryModel> subCategoryModelHashMap);
    }

    private final DbHelper mDbHelper;
    private HashMap<Integer, SubCategoryModel> mSubCategoryModelHashMap = new HashMap<>();
    private int mCount = 0;

    @Inject
    public FetchSubCategoryForAdapterUseCase(DbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    public void getAdapterDataAndNotify(int categoryId) {
        mDbHelper.getSubCategoriesByCategoryId(categoryId).toObservable()
                .flatMap(subCategoryTables -> Observable.fromIterable(subCategoryTables))
                .flatMap(subCategoryTable -> {
                    addSubCategory(subCategoryTable);
                    return mDbHelper.getProductTypesBySubCategoryId(subCategoryTable.id).toObservable();
                })
                .map(productTypeTables -> {
                    addProductTypes(productTypeTables);
                    return mSubCategoryModelHashMap;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HashMap<Integer, SubCategoryModel>>() {
                    @Override
                    public void onNext(HashMap<Integer, SubCategoryModel> subCategoryModelHashMap) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getListener().onSubCategoriesFetched(mSubCategoryModelHashMap);
                    }
                });
    }

    private void addSubCategory(SubCategoryTable subCategoryTable) {
        SubCategoryModel subCategoryModel = new SubCategoryModel();
        subCategoryModel.name = subCategoryTable.name;
        subCategoryModel.isSubCategory = true;
        mSubCategoryModelHashMap.put(mCount++, subCategoryModel);
    }

    private void addProductTypes(List<ProductTypeTable> productTypeTables) {
        for (ProductTypeTable productTypeTable : productTypeTables) {
            SubCategoryModel subCategoryModel = new SubCategoryModel();
            subCategoryModel.productTypeTable = productTypeTable;
            subCategoryModel.isSubCategory = false;
            mSubCategoryModelHashMap.put(mCount++, subCategoryModel);
        }
    }
}
