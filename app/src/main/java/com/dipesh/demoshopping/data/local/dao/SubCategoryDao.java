package com.dipesh.demoshopping.data.local.dao;

import com.dipesh.demoshopping.model.tables.SubCategoryTable;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SubCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<SubCategoryTable> subCategories);

    @Query("SELECT * FROM sub_category")
    List<SubCategoryTable> getAllSubCategories();

}
