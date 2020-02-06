package com.dipesh.demoshopping.model.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tax")
public class TaxTable {
    @PrimaryKey
    public int id;
    public String name;
    public String value;
}
