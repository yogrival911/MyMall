package com.yogdroidtech.mymall.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.yogdroidtech.mymall.R;

public class ContactFragment  extends Fragment {
    ConstraintLayout contact;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = LayoutInflater.from(getContext()).inflate(R.layout.contact_layout, container, false);
       contact = view.findViewById(R.id.contact);
       contact.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse("tel:0987654321"));
               startActivity(intent);
           }
       });
        return view;

    }
}
