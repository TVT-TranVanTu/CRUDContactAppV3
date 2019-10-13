package com.example.contactappcrud.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.contactappcrud.model.Contact;

import java.util.ArrayList;

public class ContactDatabase extends SQLiteOpenHelper {
    public static  String DATABASE_NAME ="Contactdatabase";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_CONTACT = "contact_app";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String CREATE_TABLE_CONTACT = "CREATE TABLE "
            + TABLE_CONTACT + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT, "+ KEY_NUMBER + " TEXT );";

    public ContactDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_CONTACT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_CONTACT + "'");
        onCreate(db);
    }

    public long addContact(String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_NUMBER, number);
        // insert row in students table
        long insert = db.insert(TABLE_CONTACT, null, values);

        return insert;
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> list = new ArrayList<Contact>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Contact contact= new Contact();
                contact.setId(c.getInt(0));
                contact.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                contact.setPhone(c.getString(c.getColumnIndex(KEY_NUMBER)));
                // adding to Students list
                list.add(contact);
            } while (c.moveToNext());
        }
        return list;
    }

    public int updateContact(int id, String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_NUMBER, number);
        // update row in students table base on students.is value
        return db.update(TABLE_CONTACT, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteContact(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
