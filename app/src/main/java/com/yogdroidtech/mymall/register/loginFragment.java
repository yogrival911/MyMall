package com.yogdroidtech.mymall.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yogdroidtech.mymall.R;


public class loginFragment extends Fragment {
TextView createNewAcc;
FrameLayout frameLayout;
Button sendOTP;
EditText etLoginMobile;
    public loginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        frameLayout = view.findViewById(R.id.frame);
        createNewAcc = view.findViewById(R.id.createNewAcc);
        sendOTP = view.findViewById(R.id.sendOTP);
        etLoginMobile = view.findViewById(R.id.etLoginMobile);
        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = etLoginMobile.getText().toString();
                Fragment fragment = new OTPFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mobile", mobile);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
            }
        });
        createNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SignUpFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}