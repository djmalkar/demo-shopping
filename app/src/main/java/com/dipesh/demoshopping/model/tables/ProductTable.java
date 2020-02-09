package com.dipesh.demoshopping.model.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class ProductTable {
    @PrimaryKey
    public int id;
    public int productTypeId;
    public String name;
    public String date;
    public String taxName;
    public float taxValue;
    public int viewCounts;
    public int orderCounts;
    public int sharedCounts;
}
