package com.yogdroidtech.mymall.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.model.SliderModel;
import com.yogdroidtech.mymall.util.CategoryAdapter;
import com.yogdroidtech.mymall.util.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    ViewPager viewPager2;
    List<SliderModel> sliderModelList;
    int currentPage = 2;
    Timer timer;
    final private long DELAY_TIME = 2000;
    final private  long INTERVAL = 2000;
    RecyclerView recyclerCategoryHome;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = (ViewPager) view.findViewById(R.id.viewPagerBanner);
        recyclerCategoryHome = view.findViewById(R.id.recyclerCategoryHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCategoryHome.setLayoutManager(linearLayoutManager);
        recyclerCategoryHome.setAdapter(new CategoryAdapter());
        sliderModelList = new ArrayList<>();


        sliderModelList.add(new SliderModel(R.mipmap.veg));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));

        sliderModelList.add(new SliderModel(R.mipmap.veg));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));
        sliderModelList.add(new SliderModel(R.mipmap.veg_foreground));
        sliderModelList.add(new SliderModel(R.mipmap.veg));
        sliderModelList.add(new SliderModel(R.mipmap.veg));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));

        sliderModelList.add(new SliderModel(R.mipmap.veg));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        viewPager2.setClipToPadding(false);
        viewPager2.setPageMargin(20);
        viewPager2.setAdapter(sliderAdapter);
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