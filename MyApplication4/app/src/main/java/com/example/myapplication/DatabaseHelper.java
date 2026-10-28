package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "ContactsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // No upgrade needed for database version 1.
    }

    // INSERT
    public boolean insertContact(String name, String phone) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);

        return db.insert("contacts", null, values) != -1;
    }

    // VIEW ALL
    public Cursor getContacts() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM contacts", null);
    }

    // SEARCH BY ID
    public Cursor searchContact(int id) {
        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM contacts WHERE id = ?",
                new String[]{String.valueOf(id)}
        );
    }

    // UPDATE
    public boolean updateContact(int id,
                                 String name, String phone) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);

        return db.update(
                "contacts",
                values,
                "id = ?",
                new String[]{String.valueOf(id)}
        ) > 0;
    }

    // DELETE
    public boolean deleteContact(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(
                "contacts",
                "id = ?",
                new String[]{String.valueOf(id)}
        ) > 0;
    }
}
