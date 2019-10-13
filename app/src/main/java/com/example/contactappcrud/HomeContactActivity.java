package com.example.contactappcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;


import com.example.contactappcrud.adapter.CustomAdapter;
import com.example.contactappcrud.data.ContactDatabase;
import com.example.contactappcrud.model.Contact;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeContactActivity extends AppCompatActivity {
    private ListView listView;
    private ImageButton btnAdd;
    private ContactDatabase contactDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_result);
        contactDatabase = new ContactDatabase(this);
        final ArrayList<Contact> listContact;
        listContact = contactDatabase.getAllContacts();
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.activity_layout_row, listContact);
        listView.setAdapter(customAdapter);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeContactActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeContactActivity.this,UpdateAndDeleteActivity.class);
                intent.putExtra("contact", listContact.get(position));
                startActivity(intent);
            }
        });
    }
}
