package com.dipesh.demoshopping.data.local.dao;

import com.dipesh.demoshopping.model.tables.TaxTable;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TaxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<TaxTable> taxes);

    @Query("SELECT * FROM tax")
    List<TaxTable> getAllTaxes();

}
