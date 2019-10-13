package com.example.contactappcrud.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contactappcrud.R;
import com.example.contactappcrud.model.Contact;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private ArrayList<Contact> arrayList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_layout_row, parent, false);
        TextView tvNumber = convertView.findViewById(R.id.tv_number);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvPhone = convertView.findViewById(R.id.tv_phone);
        Contact contact=arrayList.get(position);
        tvNumber.setText((position+1)+"");
        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhone());
        return convertView;
    }
}

