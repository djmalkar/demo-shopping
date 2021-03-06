/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.dipesh.demoshopping.data.local;

import com.dipesh.demoshopping.data.local.dao.CategoryDao;
import com.dipesh.demoshopping.data.local.dao.ProductDao;
import com.dipesh.demoshopping.data.local.dao.ProductTypeDao;
import com.dipesh.demoshopping.data.local.dao.SubCategoryDao;
import com.dipesh.demoshopping.data.local.dao.VariantDao;
import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.model.tables.ProductTable;
import com.dipesh.demoshopping.model.tables.ProductTypeTable;
import com.dipesh.demoshopping.model.tables.SubCategoryTable;
import com.dipesh.demoshopping.model.tables.VariantTable;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CategoryTable.class, ProductTypeTable.class, ProductTable.class, SubCategoryTable.class,
        VariantTable.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    abstract CategoryDao categoryDao();

    abstract ProductTypeDao productTypeDao();

    abstract ProductDao productDao();

    abstract SubCategoryDao subCategoryDao();

    abstract VariantDao variantDao();
}
