package com.dipesh.demoshopping.data.remote;

import android.database.Observable;

import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.CategoriesRankingSchema;

import retrofit2.http.GET;

public interface ApiRetrofit {

    @GET("/json")
    Observable<CategoriesRankingSchema> getCategoriesRankingService();

}
