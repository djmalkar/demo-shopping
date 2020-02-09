package com.dipesh.demoshopping.model.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "variant")
public class VariantTable {
    @PrimaryKey
    public int id;
    public int productId;
    public String color;
    public int size;
    public int price;
}
