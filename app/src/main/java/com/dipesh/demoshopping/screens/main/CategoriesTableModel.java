package com.dipesh.demoshopping.screens.main;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;
import com.dipesh.demoshopping.model.tables.VariantTable;

import java.util.List;

public class CategoriesTableModel {
    public List<CategoryTable> categoryTables;
    public List<SubCategoryTable> subCategoryTables;
    public List<ProductTypeTable> productTypeTables;
    public List<ProductTable> productTables;
    public List<VariantTable> variantTables;

    public CategoriesTableModel(List<CategoryTable> categoryTables, List<SubCategoryTable> subCategoryTables, List<ProductTypeTable> productTypeTables, List<ProductTable> productTables, List<VariantTable> variantTables) {
        this.categoryTables = categoryTables;
        this.subCategoryTables = subCategoryTables;
        this.productTypeTables = productTypeTables;
        this.productTables = productTables;
        this.variantTables = variantTables;
    }
}
