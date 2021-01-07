package com.yogdroidtech.mymall.products;

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
import com.yogdroidtech.mymall.productdetail.ProductDetailActivity;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
RecommendedProducts recommendedProducts;

    public ProductsAdapter(RecommendedProducts recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,parent,false);

        return new ProductsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(recommendedProducts.getData().get(position).getName());
        holder.quantity.setText(recommendedProducts.getData().get(position).getUnit()+" "+ recommendedProducts.getData().get(position).getUnitname());
        holder.price.setText(" " +recommendedProducts.getData().get(position).getPrice());

        String img = recommendedProducts.getData().get(position).getImage();
        String url = "http://admin.veggiegram.in/adminuploads/products/" + img;

        loadImage(holder.ivProductImage,url, new CircularProgressDrawable(holder.itemView.getContext()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToProductDetail = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                holder.itemView.getContext().startActivity(goToProductDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedProducts.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView productName, quantity, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            productName = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
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
