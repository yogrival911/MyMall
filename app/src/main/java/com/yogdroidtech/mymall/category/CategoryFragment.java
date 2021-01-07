package com.yogdroidtech.mymall.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.model.Categories;
import com.yogdroidtech.mymall.model.CategoryModel;
import com.yogdroidtech.mymall.util.CategoryAdapter;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CategoryFragment extends Fragment {
RecyclerView recyclerView;
CategoryAdapter adapter;
FirebaseFirestore firebaseFirestore;
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        List<CategoryModel> categoryModelList = new ArrayList<>();

        Retrofit retrofit = RetrofitClientInstance.getInstance();
        RetrofitIInterface.RetrofitInterface retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);


        Call<Categories> dataCall = retrofitInterface.getCategories();
        dataCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Log.i("yogesh", response.body().toString());
                if(response.isSuccessful()){
                    for(int i =0; i<response.body().getData().size(); i++){
                        String imgUrl = response.body().getData().get(i).getImage();
                        String actualUrl = "http://admin.veggiegram.in/adminuploads/category/" + imgUrl;
                        categoryModelList.add(new CategoryModel(actualUrl,response.body().getData().get(i).getName()));
                    }
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
                    recyclerView.setAdapter(categoryAdapter);
                }
                else{
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

//        firebaseFirestore = FirebaseFirestore.getInstance();
//
//        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
//                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString()));
//                            }
//                            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
//                            recyclerView.setAdapter(categoryAdapter);
//                            Log.i("yogesh", categoryModelList.toString());
//                        }
//                        else{
//                            Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        adapter = new CategoryAdapter(categoryModelList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}