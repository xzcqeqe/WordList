package com.example.wordlistsecond;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public  static  final  String CREATE_BOOK ="create table Book("
            + "queryWord text,"
            + "usphonetic text,"
            + "ukphonetic text,"
            + "translation text,"
            + "example text)";
    public  MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version ){
        super(context,name,factory,version);
    }
    public  void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
