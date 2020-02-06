package com.dipesh.demoshopping.model.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class CategoryTable {
    @PrimaryKey
    public int id;
    public String name;
}
