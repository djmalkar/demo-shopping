package com.dipesh.demoshopping.data.local.dao;

import com.dipesh.demoshopping.model.tables.VariantTable;
import com.dipesh.demoshopping.screens.common.dialogs.variantsdialog.VariantModel;

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

    @Query("SELECT color, size, price, taxValue FROM variant JOIN product ON variant.productId = product.id WHERE productId = :productId")
    Single<List<VariantModel>> getVariantsByProductId(int productId);

}
