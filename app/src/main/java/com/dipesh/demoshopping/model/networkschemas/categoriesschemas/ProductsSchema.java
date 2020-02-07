package com.dipesh.demoshopping.model.networkschemas.categoriesschemas;

import java.util.List;

public class ProductsSchema {
    public int id;
    public String name;
    public String date_added;
    public List<VariantsSchema> variants;
    public TaxSchema tax;
}
