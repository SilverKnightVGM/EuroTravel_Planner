package com.example.prog2.eurotravelplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bernardo Colon on 9/7/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="DB_EuroTravel";
    private static final int DB_SCHEMA_VESION= 1;
    SQLiteDatabase db;

    public static final String TABLE_NAME = "Datos Generales";
    public static final String ID_datos = "_id";
    public static final String ID_ciudad= "nombre_ciudad";
    public static final String CN_info= "info_general";
    public static final String CN_segundo_nombre= "otros nombres";
    public static final String CN_lema= "nombre_ciudad";
    public static final String CN_clima= "clima";
    public static final String CN_moneda= "moneda";
    public static final String CN_demografia= "demografia";




    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VESION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
