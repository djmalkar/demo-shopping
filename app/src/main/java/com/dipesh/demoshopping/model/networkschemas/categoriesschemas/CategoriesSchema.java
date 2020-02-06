package com.dipesh.demoshopping.model.networkschemas.categoriesschemas;

import java.util.List;

public class CategoriesSchema {
    public int id;
    public String name;
    public List<ProductsSchema> products;
    public List<Integer> child_categories;
}
