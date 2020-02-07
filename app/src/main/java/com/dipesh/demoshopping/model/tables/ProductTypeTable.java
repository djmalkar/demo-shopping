package com.dipesh.demoshopping.model.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_type")
public class ProductTypeTable {
    @PrimaryKey
    public int id;
    public int subCategoryId;
    public String name;
}
