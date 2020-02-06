package com.dipesh.demoshopping.model.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sub_category")
public class SubCategoryTable {
    @PrimaryKey
    public int id;
    public int categoryId;
    public String name;
}
