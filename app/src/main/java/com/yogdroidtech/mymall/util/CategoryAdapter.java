package com.yogdroidtech.mymall.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.model.CategoryModel;
import com.yogdroidtech.mymall.products.ProductsActivity;

import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout, parent,false);


        return new CategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        loadImage(holder.ivCategoryItem,categoryModelList.get(position).getIconUrl(),getProgressDrawable(context));
        holder.tvCategoryName.setText(categoryModelList.get(position).getCategoryName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productListIntent = new Intent(holder.itemView.getContext(), ProductsActivity.class);
                productListIntent.putExtra("position", position);
                holder.itemView.getContext().startActivity(productListIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategoryItem;
        TextView tvCategoryName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryItem = itemView.findViewById(R.id.ivProductImage);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }

    public void loadImage(ImageView imageView, String url, CircularProgressDrawable progressDrawable) {
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable);
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(url).override(200,200)
                .into(imageView);
    }
    public CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();
        return cpd;
    }
}
