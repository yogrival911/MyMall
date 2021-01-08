package com.yogdroidtech.mymall.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpFragment extends Fragment {
    RetrofitIInterface.RetrofitInterface retrofitInterface;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        Retrofit retrofit = RetrofitClientInstance.getInstance();
        retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);
        sendPost();
        return view;
    }

    private void sendPost() {
        retrofitInterface.savePost(new SignUpPost("yogesh", "yogrival911@gmail.com", "7696025886")).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.i("yogi", response.body().toString());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });


    }
}