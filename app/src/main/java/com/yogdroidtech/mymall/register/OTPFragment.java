package com.yogdroidtech.mymall.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yogdroidtech.mymall.R;
import com.yogdroidtech.mymall.favourite.FavFragment;
import com.yogdroidtech.mymall.util.RetrofitClientInstance;
import com.yogdroidtech.mymall.util.RetrofitIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OTPFragment extends Fragment {
    Button verify;
    EditText etOTP;
    RetrofitIInterface.RetrofitInterface retrofitInterface;
    String otp;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public OTPFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_o_t_p, container, false);
        String mobile = getArguments().getString("mobile");
        Log.i("yogi", mobile);
        etOTP = view.findViewById(R.id.etOTP);
        verify = view.findViewById(R.id.verify);
        Retrofit retrofit = RetrofitClientInstance.getInstance();
        retrofitInterface = retrofit.create(RetrofitIInterface.RetrofitInterface.class);
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedpreferences.edit();


        retrofitInterface.sendOTP(new SendOTPObject(mobile, "")).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                Log.i("yogi", response.body().toString());
                otp = response.body().getData().get(0).getOtp();

            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredOTP = etOTP.getText().toString();
                if(otp.equals(enteredOTP)){

                    retrofitInterface.signIn(new SignInObject(mobile)).enqueue(new Callback<LogInResponse>() {
                        @Override
                        public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                            Log.i("yogi", response.body().toString());
                            Toast.makeText(getContext().getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            editor.putString("user_id", response.body().getData().get(0).getUserid().toString());
                            editor.putString("email",response.body().getData().get(0).getEmail().toString());
                            editor.putString("mobile",response.body().getData().get(0).getMobile().toString());
//                            editor.putString("photo",response.body().getData().get(0).getPhoto().toString());
                            editor.commit();

                            Fragment fragment = new FavFragment();
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.replace(R.id.frame, fragment);
                            fragmentTransaction.commit();


//
                        }

                        @Override
                        public void onFailure(Call<LogInResponse> call, Throwable t) {

                            Log.i("yogi", t.toString());
                        }
                    });


                }
                else{
                    Toast.makeText(getContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
                }


            }
        });




        return view;
    }
}