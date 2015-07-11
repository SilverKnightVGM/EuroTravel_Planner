package com.example.prog2.eurotravelplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class subdiviciones_opciones extends ActionBarActivity {

    ListView listView_subdiviciones;



    public static Integer [] Images_SubTransporte={R.drawable.rent_car,R.drawable.taxi,R.drawable.bus,R.drawable.underground};
    public static String [] Texto_SubTransporte={"Renta de Autos","Taxi","Transporte Publico","Trenes"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subdiviciones_opciones);

        // en este intent recupero la categoria que clikee, ej. Datos Generales, Enretenimieto, Transporte etc
        Intent tipo_categoria = getIntent();
        String tipo_categoriaa = tipo_categoria.getStringExtra("idcategoria_clikeada");

        //creo el metodo para buscar cual categoria clikee para insertar el imagen que deseo de la subdivision.
        inserto_ListViewSubdivisiones(tipo_categoriaa);

    }

    private void inserto_ListViewSubdivisiones(String tipo_categoria){

        if (tipo_categoria.equals("Transporte")){

            CustomList adapter = new CustomList(subdiviciones_opciones.this, Texto_SubTransporte, Images_SubTransporte);

            listView_subdiviciones=(ListView) findViewById(R.id.listView_subdiviciones);

            listView_subdiviciones.setAdapter(adapter);
        }

        if (tipo_categoria.equals("Gastronomía")){}

        if (tipo_categoria.equals("Hospedaje")){}

        if (tipo_categoria.equals("Entretenimiento")){}

        if (tipo_categoria.equals("Lugares de interés")){}

        if (tipo_categoria.equals("Compras")){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subdiviciones_opciones, menu);
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
}
