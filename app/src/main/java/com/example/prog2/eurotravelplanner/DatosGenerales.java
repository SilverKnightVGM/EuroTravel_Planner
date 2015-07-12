package com.example.prog2.eurotravelplanner;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;


public class DatosGenerales extends ActionBarActivity {

    ListView lista;
    DbHelper helper = new DbHelper(this);
    List<String> item = null;
    private List<Constructor_Datos> DatosGList = new ArrayList<Constructor_Datos>();

    DbHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_generales);

        lista = (ListView)findViewById(R.id.LV_DatosGenerales);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datos_generales, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDato(){
        Cursor cursor = helper.getDatos();
        String[] fromFieldNames = new String[]{DbHelper.CN_info};
        int[] toViewIDs = new int[]{R.id.tv_dato};

        startManagingCursor(cursor);

        //Creamos adaptador que asignara columnas de la fila a cada elemento de la ListV
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item_view,
                cursor,
                fromFieldNames,
                toViewIDs
        );

        //Asignamos el adaptador que ira a la listview
        lista.setAdapter(myCursorAdapter);
    }

    private class MyListAdapter extends ArrayAdapter<Constructor_Datos>{
        public MyListAdapter(){
            super(DatosGenerales.this, R.layout.item_view, DatosGList);
        }
    }
}
