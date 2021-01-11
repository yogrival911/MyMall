package com.yogdroidtech.mymall.addresses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yogdroidtech.mymall.R;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewholder> {
AddressRespsonse addressRespsonse;

    public AddressAdapter(AddressRespsonse addressRespsonse) {
        this.addressRespsonse = addressRespsonse;
    }

    @NonNull
    @Override
    public AddressViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout,parent,false);
        return new AddressAdapter.AddressViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewholder holder, int position) {
        String firstName = addressRespsonse.getData().get(position).getFirstname();
        String lastName = addressRespsonse.getData().get(position).getLastname();
        String address = addressRespsonse.getData().get(position).getHouse()
                +""+addressRespsonse.getData().get(position).getStreet()
                + ""+addressRespsonse.getData().get(position).getCity()
                + "" + addressRespsonse.getData().get(position).getDistrict()
                + "" + addressRespsonse.getData().get(position).getState()
                + "" + addressRespsonse.getData().get(position).getPostCode()
                + "\n" + addressRespsonse.getData().get(position).getMobile();
        String landmark = addressRespsonse.getData().get(position).getLandmark();


        holder.textViewName.setText(firstName+" "+ lastName);
        holder.textViewAddress.setText(address);
        holder.textViewLand.setText(landmark);


    }

    @Override
    public int getItemCount() {
        return addressRespsonse.getData().size();
    }

    public class AddressViewholder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewAddress,textViewLand;
        public AddressViewholder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewLand = itemView.findViewById(R.id.textViewLand);

        }
    }
}
