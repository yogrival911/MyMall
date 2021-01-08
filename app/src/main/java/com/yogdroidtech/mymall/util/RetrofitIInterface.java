package com.yogdroidtech.mymall.util;

import com.yogdroidtech.mymall.model.Categories;
import com.yogdroidtech.mymall.products.RecommendedProducts;
import com.yogdroidtech.mymall.register.SignUpPost;
import com.yogdroidtech.mymall.register.SignUpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitIInterface {
    public interface RetrofitInterface {
        @GET("getcategorylist")
        Call<Categories> getCategories();

        @GET("getrecommendedproductslist")
        Call<RecommendedProducts> getrecommendedproductslist();

        @GET("getproductslistbycatid/{position}")
        Call<RecommendedProducts> getProductslistByCategory(@Path("position") String position);

        @Headers({
                "Content-type: application/json"
        })

        @POST("signup")
        Call<SignUpResponse> savePost(@Body SignUpPost signUpPost);

//        @POST("sendotp")
//        Call<SignUpResponse> sendOTP()


    }
}
