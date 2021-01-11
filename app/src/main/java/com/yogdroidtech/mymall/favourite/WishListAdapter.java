package com.yogdroidtech.mymall.favourite;

import android.content.Context;
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

public class WishListAdapter  extends RecyclerView.Adapter<WishListAdapter.WishListHolder> {
    WishListRespsonse wishListRespsonse;

    public WishListAdapter(WishListRespsonse wishListRespsonse) {
        this.wishListRespsonse = wishListRespsonse;
    }

    @NonNull
    @Override
    public WishListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_layout, parent, false);

        return new WishListAdapter.WishListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListHolder holder, int position) {
        loadImage(holder.ivWishListItem,wishListRespsonse.getData().get(position).getImage().toString(),new CircularProgressDrawable(holder.itemView.getContext()));
        holder.tvWishListItemName.setText(wishListRespsonse.getData().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return wishListRespsonse.getData().size();
    }

    public class WishListHolder extends RecyclerView.ViewHolder {
        ImageView ivWishListItem;
        TextView tvWishListItemName;
        public WishListHolder(@NonNull View itemView) {
            super(itemView);
            ivWishListItem = itemView.findViewById(R.id.ivWishListItem);
            tvWishListItemName = itemView.findViewById(R.id.tvWishListItemName);
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
