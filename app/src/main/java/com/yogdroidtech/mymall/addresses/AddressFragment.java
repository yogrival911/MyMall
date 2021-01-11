package com.yogdroidtech.mymall.addresses;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.register.loginFragment;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddressFragment extends Fragment {
    RecyclerView recyclerAddress;
    Retrofit retrofit;
    SharedPreferences sharedPreferences;

    public AddressFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_address, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String user_id = sharedPreferences.getString("user_id","");
        retrofit = RetrofitClientInstance.getInstance();
        RetrofitIInterface.RetrofitInterface retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);

        recyclerAddress = view.findViewById(R.id.recyclerAddress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerAddress.setLayoutManager(linearLayoutManager);

        if(user_id.isEmpty()){
            Fragment fragment = new loginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
        }
        else {

            retrofitInterface.getAddressList(user_id).enqueue(new Callback<AddressRespsonse>() {
                @Override
                public void onResponse(Call<AddressRespsonse> call, Response<AddressRespsonse> response) {
                    Log.i("yogi", response.body().toString());
                    AddressAdapter addressAdapter = new AddressAdapter(response.body());
                    recyclerAddress.setAdapter(addressAdapter);
                }

                @Override
                public void onFailure(Call<AddressRespsonse> call, Throwable t) {

                }
            });
        }




        return view;
    }
}