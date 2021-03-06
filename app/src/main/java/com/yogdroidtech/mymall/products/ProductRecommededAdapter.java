package com.yogdroidtech.mymall.products;

import android.content.Context;
import android.graphics.Paint;
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

import java.util.ArrayList;
import java.util.List;

public class ProductRecommededAdapter extends RecyclerView.Adapter<ProductRecommededAdapter.RecViewHolder> {
    RecommendedProducts recommendedProducts;
    List<Datum> datumList = new ArrayList<>();

    public ProductRecommededAdapter(RecommendedProducts recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent,false);

        return new ProductRecommededAdapter.RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {

        String img = recommendedProducts.getData().get(position).getImage();
        String url = "http://admin.veggiegram.in/adminuploads/products/" + img;
        loadImage(holder.ivProductItem,url,new CircularProgressDrawable(holder.itemView.getContext()));

        holder.tvProductName.setText(recommendedProducts.getData().get(position).getName());

        holder.tvProductPrice.setPaintFlags(holder.tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvProductPrice.setText(recommendedProducts.getData().get(position).getPrice().toString());

        holder.tvProductSellPrice.setText(recommendedProducts.getData().get(position).getSellprice().toString());

    }

    @Override
    public int getItemCount() {
        return recommendedProducts.getData().size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductItem;
        TextView tvProductName, tvProductPrice, tvProductSellPrice;
        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductItem = itemView.findViewById(R.id.ivWishListItem);
            tvProductName = itemView.findViewById(R.id.tvWishListItemName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductSellPrice = itemView.findViewById(R.id.tvWishListItemPrice);
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
