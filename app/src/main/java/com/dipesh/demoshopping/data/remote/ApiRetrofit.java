package com.dipesh.demoshopping.data.remote;

import com.dipesh.demoshopping.model.networkschemas.categoriesschemas.CategoriesRankingSchema;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiRetrofit {

    @GET("/json")
    Single<CategoriesRankingSchema> getCategoriesRankingService();

}
