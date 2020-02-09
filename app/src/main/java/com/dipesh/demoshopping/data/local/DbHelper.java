package com.dipesh.demoshopping.data.local;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;
import com.dipesh.demoshopping.model.tables.VariantTable;
import com.dipesh.demoshopping.screens.common.dialogs.variantsdialog.VariantModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Dipesh on 8/28/2017.
 */

public interface DbHelper {

    Single<List<Long>> insertCategories(List<CategoryTable> categoryTables);
    Single<List<CategoryTable>> getAllCategories();

    Single<List<Long>> insertSubCategories(List<SubCategoryTable> subCategoryTables);
    Single<List<SubCategoryTable>> getSubCategoriesByCategoryId(int categoryId);

    Single<List<Long>> insertProductTypes(List<ProductTypeTable> productTypeTables);
    Single<List<ProductTypeTable>> getProductTypesBySubCategoryId(int subCategoryId);

    Single<List<Long>> insertProducts(List<ProductTable> productTables);
    Single<List<ProductTable>> getProductsByTypeId(int productTypeId);
    Single<List<ProductTable>> getProductsOrderSortByTypeId(int productTypeId);
    Single<List<ProductTable>> getProductsViewSortByTypeId(int productTypeId);
    Single<List<ProductTable>> getProductsShareSortByTypeId(int productTypeId);

    Single<List<Long>> insertVariants(List<VariantTable> variantTables);
    Single<List<VariantModel>> getVariantsByProductId(int productId);

}
