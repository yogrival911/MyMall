package com.yogdroidtech.mymall.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductsActivity extends AppCompatActivity {
RecyclerView recyclerViewProducts;
ProductsAdapter productsAdapter;
Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewProducts.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        int categoryPosition = position+1;
        String positionString = String.valueOf(categoryPosition);
        Log.i("yogi", String.valueOf(position));

        retrofit = RetrofitClientInstance.getInstance();
        RetrofitIInterface.RetrofitInterface retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);

        Call<RecommendedProducts> productsCall = retrofitInterface.getProductslistByCategory(positionString);
        productsCall.enqueue(new Callback<RecommendedProducts>() {
            @Override
            public void onResponse(Call<RecommendedProducts> call, Response<RecommendedProducts> response) {
                if (response.isSuccessful()){
                    productsAdapter = new ProductsAdapter(response.body());
                    recyclerViewProducts.setAdapter(productsAdapter);
                }
            }

            @Override
            public void onFailure(Call<RecommendedProducts> call, Throwable t) {

            }
        });
    }


}