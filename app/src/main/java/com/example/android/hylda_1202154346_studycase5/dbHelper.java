package com.example.android.hylda_1202154346_studycase5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 25/03/2018.
 */

public class dbHelper extends SQLiteOpenHelper {

    //inisiasi nama dan versi database yang akan dibuat
    private static final String DATABASE_NAME = "hylda.db";
    private static final int DATABASE_VERSION = 2;
    SQLiteDatabase db;

    public dbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

        //deklarasi database yang dapat dituliskan
        db =  getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //membuat tabel pada database sesuai dengan skema yang ditentukan pada kelas dbContract

        db.execSQL("create table "+ dbContract.DatabaseScheme.TABLE_NAME + " ( " +
                dbContract.DatabaseScheme.ID_DATABASE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                dbContract.DatabaseScheme.TODO_NAME + " TEXT, " +
                dbContract.DatabaseScheme.DESCRIPTION + " TEXT, "+
                dbContract.DatabaseScheme.PRIORITY + " TEXT);"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop tabel dengan nama sama jika sudah ada lalu membuat yang baru
        db.execSQL("DROP TABLE IF EXISTS " +dbContract.DatabaseScheme.TABLE_NAME);

        onCreate(db);

    }



    public boolean insertData(String todo, String desc, String prio){

        //memasukkan data kedalam tabel berdasarkan inputan yang diinginkan
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbContract.DatabaseScheme.TODO_NAME,todo);
        contentValues.put(dbContract.DatabaseScheme.DESCRIPTION,desc);
        contentValues.put(dbContract.DatabaseScheme.PRIORITY,prio);
        //if data is not inserted results will be -1
        long result = db.insert(dbContract.DatabaseScheme.TABLE_NAME,null,contentValues);

        return result != -1;
    }




    public Cursor getAllData(){
        db = getWritableDatabase();
        return db.rawQuery("select * from "+dbContract.DatabaseScheme.TABLE_NAME,null);

    }



    public boolean deleteDataSwipping(String id){
        return db.delete(dbContract.DatabaseScheme.TABLE_NAME, dbContract.DatabaseScheme.ID_DATABASE + "=" + id, null) > 0;
    }



}
