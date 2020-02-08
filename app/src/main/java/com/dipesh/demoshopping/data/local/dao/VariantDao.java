package com.dipesh.demoshopping.data.local.dao;

import com.dipesh.demoshopping.model.tables.VariantTable;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface VariantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAll(List<VariantTable> variants);

    @Query("SELECT * FROM variant")
    List<VariantTable> getAllVariants();

}
