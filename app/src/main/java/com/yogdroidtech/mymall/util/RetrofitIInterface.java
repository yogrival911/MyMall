package com.yogdroidtech.mymall.util;

import com.yogdroidtech.mymall.model.Categories;
import com.yogdroidtech.mymall.products.RecommendedProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitIInterface {
    public interface RetrofitInterface {
        @GET("getcategorylist")
        Call<Categories> getCategories();

        @GET("getrecommendedproductslist")
        Call<RecommendedProducts> getrecommendedproductslist();
    }
}
