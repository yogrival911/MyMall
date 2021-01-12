package com.yogdroidtech.mymall.favourite;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.products.ProductRecommededAdapter;
import com.yogdroidtech.mymall.register.SignUpFragment;
import com.yogdroidtech.mymall.register.loginFragment;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FavFragment extends Fragment {
    SharedPreferences sharedPreferences;
    RecyclerView wishListrecycler;
    Retrofit retrofit;
    public FavFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        retrofit = RetrofitClientInstance.getInstance();
        RetrofitIInterface.RetrofitInterface retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);

        wishListrecycler = view.findViewById(R.id.wishListrecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        wishListrecycler.setLayoutManager(gridLayoutManager);
        wishListrecycler.setNestedScrollingEnabled(false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String user_id = sharedPreferences.getString("user_id", "");
        String mobile = sharedPreferences.getString("mobile", "");
        Log.i("fav", mobile+"--"+user_id);
        if(user_id.equals("")){
            Fragment fragment = new loginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
        }
        else{
            Log.i("fav", "Logged in");
            retrofitInterface.getWishList(user_id).enqueue(new Callback<WishListRespsonse>() {
                @Override
                public void onResponse(Call<WishListRespsonse> call, Response<WishListRespsonse> response) {
                    WishListAdapter wishListAdapter = new WishListAdapter(response.body());
                    wishListrecycler.setAdapter(wishListAdapter);
                }

                @Override
                public void onFailure(Call<WishListRespsonse> call, Throwable t) {

                }
            });

        }
        return view;
    }
}