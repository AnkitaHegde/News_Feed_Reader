package com.example.android.newsfeed;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.text.BoringLayout;

import androidx.annotation.Nullable;

import com.example.android.newsfeed.adapter.NewsAdapter;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignLog.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");
       // MyDatabase.execSQL("create Table Config( email TEXT,isLogged BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        //MyDB.execSQL("drop Table if exists Config");
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);


        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    public Boolean checkIsLogged(String email,Boolean isLogged){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        Cursor cursor = MyDatabase.rawQuery("Select isLogged from users where email = ?", new String[]{email});
//        boolean value = cursor.getInt(2) > 0;
//        if(value) {
//            return true;
//        }else {
//            return false;
//        }
//    }

//    public void loggedOut(String email){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        String strSQL = "UPDATE users SET isLogged = false WHERE email = "+ email;
//
//        MyDatabase.execSQL(strSQL);
//    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});


        if(cursor.getCount() >0 ) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

//    public Boolean checkLogg(){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        Cursor cursor = MyDatabase.rawQuery("Select isLogged from users",null);
//
//        if(cursor.getCount()>0){
//            return true;
//        }
//        else {
//            return false;
//        }
//
//    }
}
