package com.yogdroidtech.mymall.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpFragment extends Fragment {
    RetrofitIInterface.RetrofitInterface retrofitInterface;
    EditText etName, etEmail, etMobile;
    Button signUp;
    TextView alreadyUser;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etMobile = view.findViewById(R.id.etMobile);
        signUp = view.findViewById(R.id.signUp);
        alreadyUser = view.findViewById(R.id.alreadyUser);

        Retrofit retrofit = RetrofitClientInstance.getInstance();
        retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String mobile = etMobile.getText().toString();
                sendPost(name, email, mobile);
            }
        });

        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new loginFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void sendPost(String name, String email, String mobile) {
        retrofitInterface.savePost(new SignUpPost(name, email, mobile)).enqueue(new Callback<SignUpResponse>() {
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