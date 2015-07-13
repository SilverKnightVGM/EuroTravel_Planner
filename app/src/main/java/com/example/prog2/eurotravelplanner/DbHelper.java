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
    private static final int DB_SCHEMA_VESION= 3;
    SQLiteDatabase db;

    public static final String TABLE_NAME = "Datos_Generales";
    public static final String ID_datos = "_id";
    public static final String ID_ciudad= "nombre_ciudad";
    public static final String CN_info= "info_general";
    public static final String CN_segundo_nombre= "otros_nombres";
    public static final String CN_lema= "lema";
    public static final String CN_clima= "clima";
    public static final String CN_moneda= "moneda";
    public static final String CN_demografia= "demografia";

    public static String where = null;


    // Query que creara tabla Datos Generales
    private static final String TABLE_CREATE="CREATE TABLE "+TABLE_NAME+" ("+ID_datos+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " "+ID_ciudad+" text not null, "+CN_info+" text not null, "+CN_segundo_nombre+" text ,"+CN_lema+" text ,"+
            " "+CN_clima+" text not null, "+CN_moneda+" text not null, "+CN_demografia+" text not null)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

        //Insertamos datos fijos de la tabla Datos Generales
        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                "values('paris', 'La ciudad es el destino turístico más popular del mundo, con más de 42 millones de visitantes extranjeros por año.', 'La ciudad Luz', 'Fluctuat Nec Mergitur, Navega, sin ser nunca sumergido', 'París posee un clima continental caracterizado por veranos calurosos y sofocantes e inviernos fríos', 'Euro', 'París es el centro de un área metropolitana con 12,292,895 habitantes, la primera de la Unión Europea.')");

        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                "values('londres', 'Es la capital de Inglaterra y del Reino Unido, y la mayor ciudad y área urbana de Gran Bretaña y de toda la Unión Europea', '“SEÑOR, DIRÍGENOS”', 'El tiempo en Londres varía constantemente durante el día y, aunque no suele haber temperaturas extremas, en una misma jornada es habitual ver nubes, lluvia, frío e incluso sol y calor.', 'Libra Esterlina (GBP)', '8,615,246 hab')");

        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                "values('madrid', 'Es una ciudad cosmopolita que combina las infraestructuras más modernas y su condición de centro económico, financiero, administrativo y de servicios con un inmenso patrimonio cultural y artístico, legado de siglos de historia apasionante.', ' Villa y Corte, La Villa, La Capital del Reino', '«Fui sobre agua edificada, mis muros de fuego son. Esta es mi insignia y blasón»', 'De Madrid te sorprenderán sus cielos azules, intensos y rotundos. Con un clima seco y sin demasiadas precipitaciones a lo largo del año, los veranos son calurosos y los inviernos fríos.', 'Euro', '3,165,235 hab ')");

        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+",  "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                "values('venecia', 'Venecia es un conjunto de 120 islas unidas a través de puentes. Desde Mestre se puede llegar a Venecia utilizando el Puente de la Libertad, que lleva hasta la Piazzale Roma.', 'La Serenissima, La Ciudad de los canales', 'Dependiendo de la época en la que viajes, será un factor determinante para la planificación del viaje.', 'Euro', '270,884 hab')");

        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_info+", "+CN_segundo_nombre+", "+CN_lema+", "+CN_clima+", "+CN_moneda+", "+CN_demografia+") " +
                "values('roma', 'Es la ciudad con la más alta concentración de bienes históricos y arquitectónicos del mundo su centro histórico delimitado por el perímetro que marcan las murallas aurelianas, superposición de huellas de tres milenios, es la expresión del patrimonio histórico, artístico y cultural del mundo occidental europeo.', 'La ciudad eterna', '«Senatus Populusque Romanus», El Senado y el Pueblo de Roma', 'El clima de Roma es mediterráneo y suave, lo que hace que cualquier época sea buena para conocer la ciudad.', 'Euro', '2,874,038 hab')");

        db.execSQL("INSERT INTO "+TABLE_NAME+" ("+ID_ciudad+", "+CN_clima+", "+CN_info+", "+CN_moneda+", "+CN_demografia+") " +
                "values('berlin', 'Es una ciudad mundial y un centro cultural y artístico de primer nivel. Es una de las ciudades más influyentes en el ámbito político de la Unión Europea y en 2006 fue elegida Ciudad Creativa por la Unesco.', 'Generalmente, junio, julio y agosto son los meses más cálidos, aunque no podemos hablar de un calor sofocante en ningún momento del año. Durante el verano hay una temperatura media de 24°C, llegándose a alcanzar máximas de 30ºC', 'Euro', '3,421,829 hab.')");

        this.db = db;

    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VESION);

    }

    public Cursor getDatos(){
        String columnas[]={ID_datos,ID_ciudad,CN_info,CN_segundo_nombre,CN_lema,CN_clima,CN_moneda,CN_demografia};
        Cursor c= this.getReadableDatabase().query(TABLE_NAME,columnas,where,null,null,null,null);
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
