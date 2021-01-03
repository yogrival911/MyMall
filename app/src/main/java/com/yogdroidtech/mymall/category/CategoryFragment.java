package com.yogdroidtech.mymall.category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.util.CategoryAdapter;


public class CategoryFragment extends Fragment {
RecyclerView recyclerView;
CategoryAdapter adapter;
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }
}