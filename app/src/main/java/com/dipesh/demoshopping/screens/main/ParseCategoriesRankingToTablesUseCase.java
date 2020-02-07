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

public class ParseCategoriesRankingToTablesUseCase {

    private ArrayList<CategoriesSchema> mCategoriesSchemas = new ArrayList<>();
    private HashMap<Integer, Boolean> mCategoriesTracker = new HashMap<>();

    private List<CategoryTable> mCategoryTables = new ArrayList<>();
    private List<SubCategoryTable> mSubCategoryTables = new ArrayList<>();
    private List<ProductTypeTable> mProductTypeTables = new ArrayList<>();
    private List<ProductTable> mProductTables = new ArrayList<>();
    private List<VariantTable> mVariantTables = new ArrayList<>();

    public void categoriesSchema(CategoriesRankingSchema categoriesRankingSchema) {
        for (CategoriesSchema categoriesSchema : categoriesRankingSchema.categories) {
            mCategoriesSchemas.add(categoriesSchema.id, categoriesSchema);
            if(!categoriesSchema.child_categories.isEmpty()) {
                mCategoriesTracker.put(categoriesSchema.id, false);
            }
        }

        for (int i = 0; i < categoriesRankingSchema.rankings.size(); i++) {
            switch (i) {
                case 0 :
                    Type viewList = new TypeToken<List<RankingViewedProducts>>(){}.getType();
                    List<RankingViewedProducts> viewRanking = new Gson().fromJson(categoriesRankingSchema.rankings.get(i).ranking, viewList);
                    break;
                case 1 :
                    Type orderList = new TypeToken<List<RankingOrderedProducts>>(){}.getType();
                    List<RankingOrderedProducts> orderRanking = new Gson().fromJson(categoriesRankingSchema.rankings.get(i).ranking, orderList);
                    break;
                case 2 :
                    Type sharedList = new TypeToken<List<RankingSharedProducts>>(){}.getType();
                    List<RankingSharedProducts> sharedRanking = new Gson().fromJson(categoriesRankingSchema.rankings.get(i).ranking, sharedList);
                    break;
            }

        }

        processCategories();
    }

    private void processCategories() {
        for (Map.Entry<Integer,Boolean> entry : mCategoriesTracker.entrySet()) {
            if(!entry.getValue()) {
                entry.setValue(true);
                CategoriesSchema categoriesSchema = mCategoriesSchemas.get(entry.getKey());
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
            CategoriesSchema categoriesSchema = mCategoriesSchemas.get(subCategoryId);
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
            CategoriesSchema categoriesSchema = mCategoriesSchemas.get(productTypeId);
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
            /*productTable.viewCounts;
            productTable.orderCounts;
            productTable.sharedCounts;*/
            mProductTables.add(productTable);
            processVariants(product.variants, productTable.id);
        }
    }

    private void processVariants(List<VariantsSchema> variantsSchemas, int productId) {
        for (VariantsSchema variantsSchema : variantsSchemas) {
            VariantTable variantTable = new VariantTable();
            variantTable.id = variantsSchema.id;
            variantTable.productId = productId;
            variantTable.size = variantsSchema.size;
            variantTable.price = variantsSchema.price;
            mVariantTables.add(variantTable);
        }
    }
}
