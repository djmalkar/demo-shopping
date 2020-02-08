package com.dipesh.demoshopping.data.local.dao;

import com.dipesh.demoshopping.model.tables.ProductTable;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAll(List<ProductTable> products);

    @Query("SELECT * FROM product")
    List<ProductTable> getAllProducts();

}
