package com.yogdroidtech.mymall.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yogdroidtech.mymall.R;

public class ProductsActivity extends AppCompatActivity {
RecyclerView recyclerViewProducts;
ProductsAdapter productsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        productsAdapter = new ProductsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewProducts.setLayoutManager(linearLayoutManager);
        recyclerViewProducts.setAdapter(productsAdapter);

    }
}