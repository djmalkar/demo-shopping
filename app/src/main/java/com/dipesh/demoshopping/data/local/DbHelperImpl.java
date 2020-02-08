package com.dipesh.demoshopping.data.local;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;
import com.dipesh.demoshopping.model.tables.VariantTable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Dipesh on 8/31/2017.
 */

public class DbHelperImpl implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public DbHelperImpl(AppDatabase appDatabase){this.mAppDatabase = appDatabase;}

    @Override
    public Single<List<Long>> insertCategories(List<CategoryTable> categoryTables) {
        return mAppDatabase.categoryDao().insertAll(categoryTables);
    }

    @Override
    public Single<List<CategoryTable>> getAllCategories() {
        return mAppDatabase.categoryDao().getAllCategories();
    }

    @Override
    public Single<List<Long>> insertSubCategories(List<SubCategoryTable> subCategoryTables) {
        return mAppDatabase.subCategoryDao().insertAll(subCategoryTables);
    }

    @Override
    public Single<List<SubCategoryTable>> getSubCategoriesByCategoryId(int categoryId) {
        return mAppDatabase.subCategoryDao().getSubCategoriesByCategoryId(categoryId);
    }

    @Override
    public Single<List<Long>> insertProductTypes(List<ProductTypeTable> productTypeTables) {
        return mAppDatabase.productTypeDao().insertAll(productTypeTables);
    }

    @Override
    public Single<List<ProductTypeTable>> getProductsBySubCategoryId(int subCategoryId) {
        return mAppDatabase.productTypeDao().getProductTypesBySubCategoryId(subCategoryId);
    }

    @Override
    public Single<List<Long>> insertProducts(List<ProductTable> productTables) {
        return mAppDatabase.productDao().insertAll(productTables);
    }

    @Override
    public Single<List<Long>> insertVariants(List<VariantTable> variantTables) {
        return mAppDatabase.variantDao().insertAll(variantTables);
    }
}
