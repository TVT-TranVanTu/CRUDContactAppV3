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
import com.example.contactappcrud.model.Contact;


public class UpdateAndDeleteActivity extends AppCompatActivity {
    private Contact contact;
    private EditText edtName,edtPhone;
    private ImageButton imgBtnBack;
    private Button btnUpdate;
    private Button btnDelete;
    private ContactDatabase contactDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);
        edtName= findViewById(R.id.edt_name);
        edtPhone=findViewById(R.id.edt_phoneNumber);
        contactDatabase = new ContactDatabase(this);

        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");
        edtName.setText(contact.getName());
        edtPhone.setText(contact.getPhone());
        imgBtnBack= findViewById(R.id.btn_back);
        btnUpdate=findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(v);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UpdateAndDeleteActivity.this,HomeContactActivity.class);
                startActivity(intent);
            }
        });
    }
    public void updateContact(View view) {
        contactDatabase.updateContact(contact.getId(),edtName.getText().toString(),edtPhone.getText().toString());
        Toast.makeText(UpdateAndDeleteActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateAndDeleteActivity.this,HomeContactActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
