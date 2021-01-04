package com.yogdroidtech.mymall.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.products.ProductsActivity;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout, parent,false);


        return new CategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productListIntent = new Intent(holder.itemView.getContext(), ProductsActivity.class);
                holder.itemView.getContext().startActivity(productListIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
