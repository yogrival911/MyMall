package com.yogdroidtech.mymall.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.model.Categories;
import com.yogdroidtech.mymall.model.CategoryModel;
import com.yogdroidtech.mymall.model.SliderModel;
import com.yogdroidtech.mymall.products.ProductRecommededAdapter;
import com.yogdroidtech.mymall.products.RecommendedProducts;
import com.yogdroidtech.mymall.util.CategoryAdapter;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;
import com.yogdroidtech.mymall.util.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

    ViewPager viewPager2;
    List<SliderModel> sliderModelList;
    List<CategoryModel> categoryModelList;
    int currentPage = 2;
    Timer timer;
    final private long DELAY_TIME = 2000;
    final private  long INTERVAL = 2000;
    RecyclerView recyclerCategoryHome;
    RecyclerView recyclerRecommended;
    FirebaseFirestore firebaseFirestore;
    Retrofit retrofit;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = (ViewPager) view.findViewById(R.id.viewPagerBanner);


        recyclerCategoryHome = view.findViewById(R.id.recyclerCategoryHome);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerCategoryHome.setLayoutManager(gridLayoutManager);
        recyclerCategoryHome.setNestedScrollingEnabled(false);

        recyclerRecommended = view.findViewById(R.id.recyclerRecommended);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerRecommended.setLayoutManager(linearLayoutManager);

        retrofit = RetrofitClientInstance.getInstance();
        RetrofitIInterface.RetrofitInterface retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);

        Call<RecommendedProducts> recommendedProductsCall = retrofitInterface.getrecommendedproductslist();
        recommendedProductsCall.enqueue(new Callback<RecommendedProducts>() {
            @Override
            public void onResponse(Call<RecommendedProducts> call, Response<RecommendedProducts> response) {
                if(response.isSuccessful()){

                    ProductRecommededAdapter productRecommededAdapter = new ProductRecommededAdapter(response.body());
                    recyclerRecommended.setAdapter(productRecommededAdapter);
                }
            }

            @Override
            public void onFailure(Call<RecommendedProducts> call, Throwable t) {

            }
        });

        categoryModelList = new ArrayList<>();
        Call<Categories> dataCall = retrofitInterface.getCategories();
        dataCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if(response.isSuccessful()){
                    for(int i =0; i<response.body().getData().size(); i++){
                        String imgUrl = response.body().getData().get(i).getImage();
                        String actualUrl = "http://admin.veggiegram.in/adminuploads/category/" + imgUrl;
                        categoryModelList.add(new CategoryModel(actualUrl,response.body().getData().get(i).getName()));
                    }
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
                    recyclerCategoryHome.setAdapter(categoryAdapter);
                }
                else{
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });


        firebaseFirestore = FirebaseFirestore.getInstance();

        sliderModelList = new ArrayList<>();

        firebaseFirestore.collection("BANNERS").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                sliderModelList.add(new SliderModel(documentSnapshot.get("url").toString()));
                            }
                            List<SliderModel> sliderModelListNew = new ArrayList<>();
                            sliderModelListNew.add(sliderModelList.get(sliderModelList.size()-2));
                            sliderModelListNew.add(sliderModelList.get(sliderModelList.size()-1));

                            sliderModelListNew.addAll(sliderModelList);

                            sliderModelListNew.add(sliderModelList.get(0));
                            sliderModelListNew.add(sliderModelList.get(1));

                            SliderAdapter sliderAdapter = new SliderAdapter(sliderModelListNew);
                            viewPager2.setClipToPadding(false);
                            viewPager2.setPageMargin(20);
                            viewPager2.setAdapter(sliderAdapter);
                        }
                        else{
                            Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        sliderModelList.add(new SliderModel(R.mipmap.veg));
//        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));
//
//        sliderModelList.add(new SliderModel(R.mipmap.veg));
//        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));
//        sliderModelList.add(new SliderModel(R.mipmap.veg_foreground));
//        sliderModelList.add(new SliderModel(R.mipmap.veg));
//        sliderModelList.add(new SliderModel(R.mipmap.veg));
//        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));
//
//        sliderModelList.add(new SliderModel(R.mipmap.veg));
//        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));
//
//        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
//        viewPager2.setClipToPadding(false);
//        viewPager2.setPageMargin(20);
//        viewPager2.setAdapter(sliderAdapter);
        viewPager2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == viewPager2.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        });

        startBannerSlideShow();
        viewPager2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopSlideShow();
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();

                }
                return false;
            }
        });


        return view;
    }

    private void pageLooper(){
        if(currentPage == sliderModelList.size() -1){
            currentPage = 2;
            viewPager2.setCurrentItem(currentPage,false);
        }
        if(currentPage == 1){
            currentPage = sliderModelList.size() - 3;
            viewPager2.setCurrentItem(currentPage,false);
        }
    }

    private void startBannerSlideShow(){
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                viewPager2.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,INTERVAL);
    }
    private void stopSlideShow(){
        timer.cancel();
    }
}