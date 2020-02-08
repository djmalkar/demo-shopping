package com.dipesh.demoshopping.data.local.dao;

import com.dipesh.demoshopping.model.tables.ProductTypeTable;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface ProductTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAll(List<ProductTypeTable> productTypes);

    @Query("SELECT * FROM product_type WHERE subCategoryId = :subCategoryId")
    Single<List<ProductTypeTable>> getProductTypesBySubCategoryId(int subCategoryId);

}
