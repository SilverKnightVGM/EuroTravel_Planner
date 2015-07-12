package com.example.prog2.eurotravelplanner;

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
    public static final String CN_lema= "lema";
    public static final String CN_clima= "clima";
    public static final String CN_moneda= "moneda";
    public static final String CN_demografia= "demografia";


    // Query que creara tabla Datos Generales
    private static final String TABLE_CREATE="CREATE TABLE "+TABLE_NAME+" ("+ID_datos+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " "+ID_ciudad+" text not null, "+CN_info+" text not null, "+CN_segundo_nombre+" text ,"+CN_lema+" text ,"+
            " "+CN_clima+" text not null, "+CN_moneda+" text not null, "+CN_demografia+" text not null)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

        //Insertamos datos fijos de la tabla Datos Generales
        db.execSQL("INSERT INTO "+TABLE_NAME+" values(La ciudad es el destino turístico más popular del mundo',' con más de 42 millones de visitantes extranjeros por año'.', Fluctuat Nec Mergitur',' Navega',' sin ser nunca sumergido, La ciudad Luz, Euro, París posee un clima continental caracterizado por veranos calurosos y sofocantes e inviernos fríos, París es el centro de un área metropolitana con 12292895 habitantes',' la primera de la Unión Europea'.')");

        this.db = db;

    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VESION);

    }

    public Cursor getDatos(){
        String columnas[]={ID_datos,ID_ciudad,CN_info,CN_segundo_nombre,CN_lema,CN_clima,CN_moneda,CN_demografia};
        Cursor c= this.getReadableDatabase().query(TABLE_NAME,columnas,null,null,null,null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }



}
