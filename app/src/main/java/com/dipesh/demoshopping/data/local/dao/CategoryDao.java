package com.dipesh.demoshopping.data.local.dao;

import com.dipesh.demoshopping.model.tables.CategoryTable;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<CategoryTable> categories);

    @Query("SELECT * FROM category")
    List<CategoryTable> getAllCategories();

}
