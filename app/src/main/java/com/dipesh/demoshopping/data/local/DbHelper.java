package com.dipesh.demoshopping.data.local;

import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dipesh on 8/28/2017.
 */

public interface DbHelper {

    List<Long> insertCategories(List<CategoryTable> categoryTables);
    Observable<List<CategoryTable>> getAllCategories();

    List<Long> insertSubCategories(List<SubCategoryTable> subCategoryTables);
    Observable<List<SubCategoryTable>> getAllSubCategories();

}
