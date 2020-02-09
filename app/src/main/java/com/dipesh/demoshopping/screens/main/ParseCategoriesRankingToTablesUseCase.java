package com.dipesh.demoshopping.screens.main;

import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.CategoriesRankingSchema;
import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.CategoriesSchema;
import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.ProductsSchema;
import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.RankingOrderedProducts;
import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.RankingSharedProducts;
import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.RankingViewedProducts;
import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.VariantsSchema;
import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;
import com.dipesh.demoshopping.model.tables.VariantTable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class ParseCategoriesRankingToTablesUseCase {

    private CategoriesSchema[] mCategoriesSchemas;
    private HashMap<Integer, Boolean> mCategoriesTracker = new HashMap<>();
    private HashMap<Integer, Integer> mViewRanking = new HashMap<>();
    private HashMap<Integer, Integer> mOrderRanking = new HashMap<>();
    private HashMap<Integer, Integer> mSharedRanking = new HashMap<>();

    private List<CategoryTable> mCategoryTables = new ArrayList<>();
    private List<SubCategoryTable> mSubCategoryTables = new ArrayList<>();
    private List<ProductTypeTable> mProductTypeTables = new ArrayList<>();
    private List<ProductTable> mProductTables = new ArrayList<>();
    private List<VariantTable> mVariantTables = new ArrayList<>();

    @Inject
    public ParseCategoriesRankingToTablesUseCase() { }

    public CategoriesTableModel parseSchema(CategoriesRankingSchema categoriesRankingSchema) {
        // for convenience take one extra size
        mCategoriesSchemas = new CategoriesSchema[categoriesRankingSchema.categories.size() + 1];

        for (CategoriesSchema categoriesSchema : categoriesRankingSchema.categories) {
            mCategoriesSchemas[categoriesSchema.id] = categoriesSchema;
            if(!categoriesSchema.child_categories.isEmpty()) {
                mCategoriesTracker.put(categoriesSchema.id, false);
            }
        }

        Type listType = new TypeToken<List<RankingViewedProducts>>() {}.getType();
        List<RankingViewedProducts> viewRanking = new Gson().fromJson(categoriesRankingSchema.rankings.get(0).products.toString(), listType);
        processViewRanking(viewRanking);

        listType = new TypeToken<List<RankingOrderedProducts>>() {}.getType();
        List<RankingOrderedProducts> orderRanking = new Gson().fromJson(categoriesRankingSchema.rankings.get(1).products.toString(), listType);
        processOrderRanking(orderRanking);

        listType = new TypeToken<List<RankingSharedProducts>>() {}.getType();
        List<RankingSharedProducts> sharedRanking = new Gson().fromJson(categoriesRankingSchema.rankings.get(2).products.toString(), listType);
        processSharedRanking(sharedRanking);

        processCategories();

        CategoriesTableModel categoriesTableModel = new CategoriesTableModel(mCategoryTables,
                mSubCategoryTables, mProductTypeTables, mProductTables, mVariantTables);

        return categoriesTableModel;
    }

    private void processViewRanking(List<RankingViewedProducts> viewRanking) {
        for (RankingViewedProducts rankingViewedProducts : viewRanking) {
            mViewRanking.put(rankingViewedProducts.id, rankingViewedProducts.view_count);
        }
    }

    private void processOrderRanking(List<RankingOrderedProducts> orderRanking) {
        for (RankingOrderedProducts rankingOrderedProducts : orderRanking) {
            mOrderRanking.put(rankingOrderedProducts.id, rankingOrderedProducts.order_count);
        }
    }

    private void processSharedRanking(List<RankingSharedProducts> sharedRanking) {
        for (RankingSharedProducts rankingSharedProducts : sharedRanking) {
            mSharedRanking.put(rankingSharedProducts.id, rankingSharedProducts.shares);
        }
    }

    private void processCategories() {
        for (Map.Entry<Integer,Boolean> entry : mCategoriesTracker.entrySet()) {
            if(!entry.getValue()) {
                entry.setValue(true);
                CategoriesSchema categoriesSchema = mCategoriesSchemas[entry.getKey()];
                CategoryTable categoryTable = new CategoryTable();
                categoryTable.id = categoriesSchema.id;
                categoryTable.name = categoriesSchema.name;
                mCategoryTables.add(categoryTable);
                processSubCategories(categoriesSchema.child_categories, categoriesSchema.id);
            }
        }
    }

    private void processSubCategories(List<Integer> subCategoriesIds, int categoryId) {
        for (Integer subCategoryId : subCategoriesIds) {
            mCategoriesTracker.put(subCategoryId, true);
            CategoriesSchema categoriesSchema = mCategoriesSchemas[subCategoryId];
            SubCategoryTable subCategoryTable = new SubCategoryTable();
            subCategoryTable.categoryId = categoryId;
            subCategoryTable.id = categoriesSchema.id;
            subCategoryTable.name = categoriesSchema.name;
            mSubCategoryTables.add(subCategoryTable);
            processProductsType(categoriesSchema.child_categories, subCategoryTable.id);
        }
    }

    private void processProductsType(List<Integer> productTypeIds, int subCategoryId) {
        for (Integer productTypeId : productTypeIds) {
            CategoriesSchema categoriesSchema = mCategoriesSchemas[productTypeId];
            ProductTypeTable productTypeTable = new ProductTypeTable();
            productTypeTable.subCategoryId = subCategoryId;
            productTypeTable.id = productTypeId;
            productTypeTable.name = categoriesSchema.name;
            mProductTypeTables.add(productTypeTable);
            processProducts(categoriesSchema.products, productTypeTable.id);
        }
    }

    private void processProducts(List<ProductsSchema> products, int productTypeId) {
        for (ProductsSchema product : products) {
            ProductTable productTable = new ProductTable();
            productTable.id = product.id;
            productTable.productTypeId = productTypeId;
            productTable.name = product.name;
            productTable.date = product.date_added;
            productTable.taxName = product.tax.name;
            productTable.taxValue = product.tax.value;
            productTable.viewCounts = mViewRanking.get(product.id) == null ? 0 : mViewRanking.get(product.id);
            productTable.orderCounts = mOrderRanking.get(product.id) == null ? 0 : mOrderRanking.get(product.id);
            productTable.sharedCounts = mSharedRanking.get(product.id) == null ? 0 : mSharedRanking.get(product.id);
            mProductTables.add(productTable);
            processVariants(product.variants, productTable.id);
        }
    }

    private void processVariants(List<VariantsSchema> variantsSchemas, int productId) {
        for (VariantsSchema variantsSchema : variantsSchemas) {
            VariantTable variantTable = new VariantTable();
            variantTable.id = variantsSchema.id;
            variantTable.productId = productId;
            variantTable.color = variantsSchema.color;
            variantTable.size = variantsSchema.size;
            variantTable.price = variantsSchema.price;
            mVariantTables.add(variantTable);
        }
    }
}
