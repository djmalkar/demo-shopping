package com.dipesh.demoshopping.data.local;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Dipesh on 8/31/2017.
 */

public class DbHelperImpl implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public DbHelperImpl(AppDatabase appDatabase){this.mAppDatabase = appDatabase;}

    @Override
    public List<Long> insertCategories(List<CategoryTable> categoryTables) {
        return mAppDatabase.categoryDao().insertAll(categoryTables);
    }

    @Override
    public Observable<List<CategoryTable>> getAllCategories() {
        return Observable.fromCallable(() -> mAppDatabase.categoryDao().getAllCategories());
    }

    @Override
    public List<Long> insertSubCategories(List<SubCategoryTable> subCategoryTables) {
        return mAppDatabase.subCategoryDao().insertAll(subCategoryTables);
    }

    @Override
    public Observable<List<SubCategoryTable>> getAllSubCategories() {
        return Observable.fromCallable(() -> mAppDatabase.subCategoryDao().getAllSubCategories());
    }
}
