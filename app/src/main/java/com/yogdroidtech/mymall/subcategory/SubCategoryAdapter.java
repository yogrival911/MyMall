package com.yogdroidtech.mymall.subcategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yogdroidtech.mymall.R;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryHolder> {
SubCategoryRespsonse subCategoryRespsonse;

    public SubCategoryAdapter(SubCategoryRespsonse subCategoryRespsonse) {
        this.subCategoryRespsonse = subCategoryRespsonse;
    }

    @NonNull
    @Override
    public SubCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_layout, parent, false);

        return new SubCategoryAdapter.SubCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryHolder holder, int position) {
        holder.subCategoryName.setText(subCategoryRespsonse.getData().get(position).getName());

    }

    @Override
    public int getItemCount() {
        return subCategoryRespsonse.getData().size();
    }

    public class SubCategoryHolder extends RecyclerView.ViewHolder {
        Button subCategoryName;
        public SubCategoryHolder(@NonNull View itemView) {
            super(itemView);
            subCategoryName = itemView.findViewById(R.id.subCategoryName);
        }
    }
}
