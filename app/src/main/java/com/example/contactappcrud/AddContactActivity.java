package com.example.contactappcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.contactappcrud.data.ContactDatabase;


public class AddContactActivity extends AppCompatActivity {
    private EditText edtName, edtPhone;
    private Button btnSave;
    private ContactDatabase contactDatabase;
    private ImageButton imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phoneNumber);
        btnSave = findViewById(R.id.btn_save);
        imgBtnBack= findViewById(R.id.btn_back);
        contactDatabase = new ContactDatabase(this);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddContactActivity.this,HomeContactActivity.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDatabase.addContact(edtName.getText().toString(), edtPhone.getText().toString());
                edtName.setText("");
                edtPhone.setText("");
                Toast.makeText(AddContactActivity.this, "Save succesfull", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

