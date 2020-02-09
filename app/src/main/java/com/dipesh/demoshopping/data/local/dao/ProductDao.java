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

    @Query("SELECT * FROM product WHERE productTypeId = :typeId")
    Single<List<ProductTable>> getProductsByTypeId(int typeId);

    @Query("SELECT * FROM product WHERE productTypeId = :typeId ORDER BY orderCounts")
    Single<List<ProductTable>> getProductsOrderSortByTypeId(int typeId);

    @Query("SELECT * FROM product WHERE productTypeId = :typeId ORDER BY viewCounts")
    Single<List<ProductTable>> getProductsViewSortByTypeId(int typeId);

    @Query("SELECT * FROM product WHERE productTypeId = :typeId ORDER BY sharedCounts")
    Single<List<ProductTable>> getProductsShareSortByTypeId(int typeId);

}
