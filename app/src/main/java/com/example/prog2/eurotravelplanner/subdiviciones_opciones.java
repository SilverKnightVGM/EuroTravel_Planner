package com.example.prog2.eurotravelplanner;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class subdiviciones_opciones extends ActionBarActivity {

    ListView listView_subdiviciones;

    //TODO does this needs to be static? can't extract string resource and replace like this.

    public static Integer [] Images_SubTransporte={R.drawable.rent_car,R.drawable.taxi,R.drawable.bus,R.drawable.underground};
    public static String [] Texto_SubTransporte={"Renta de Autos","Taxi","Transporte Publico","Trenes"};

    public static Integer [] Images_SubHospedaje={R.drawable.hotel,R.drawable.hostel,R.drawable.acampar};
    public static String [] Texto_SubHospedaje={"Hoteles","Hostel","Lugares de Acampar"};

    public static Integer [] Images_SubGastronomia={R.drawable.restaurant,R.drawable.pasteleria,R.drawable.fast_food,R.drawable.comida_tipica};
    public static String [] Texto_SubGastronomia={"Restaurantes","Pasteleria y Panaderia","Comida Rapida","Comida Típicas"};

    public static Integer [] Images_SubEntretenimiento={R.drawable.bares,R.drawable.discoteca,R.drawable.club,R.drawable.parque_diversion};
    public static String [] Texto_SubEntretenimiento={"Bares","Discotecas","Clubes","Parques de Diversión"};

    public static Integer [] Images_SubLugaresInteres={R.drawable.lugares_historicos,R.drawable.museos,R.drawable.playas,R.drawable.tour};
    public static String [] Texto_SubLugaresInteres={"Lugares Historicos","Museos","Playas","Tour por la ciudad"};

    public static Integer [] Images_SubLugaresInteresMadrid={R.drawable.lugares_historicos,R.drawable.museos,R.drawable.tour};
    public static String [] Texto_SubLugaresInteresMadrid={"Lugares Historicos","Museos","Tour por la ciudad"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subdiviciones_opciones);

        listView_subdiviciones=(ListView) findViewById(R.id.listView_subdiviciones);

        // en este intent recupero la categoria que clikee, ej. Datos Generales, Enretenimieto, Transporte etc
        final Intent tipo_categoria = getIntent();
        final String string_tipo_categoria = tipo_categoria.getStringExtra("idcategoria_clikeada");

        //creo el metodo para buscar cual categoria clikee para insertar el imagen que deseo de la subdivision.
        inserto_ListViewSubdivisiones(string_tipo_categoria);


// aqui esta el metodo de cuando clikee un item del listview de las subdiviciones
        listView_subdiviciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // aqui recupero el id de la ciudad en que estoy investigando
                Intent recupero_id = getIntent();
                String id_cuidad = recupero_id.getStringExtra("id_cuidades");

                // en este objecto recupero al item que clikee ej. (Renta de Autos si estoy en Transporte, Hoteles si estoy en Hospedate, etc)
                Object listItem = listView_subdiviciones.getItemAtPosition(position);
                String texto_clikeado = listItem.toString();
                Intent e = new Intent(subdiviciones_opciones.this,gallery.class);

                //Toast.makeText(subdiviciones_opciones.this, "You Clicked at " + texto_clikeado, Toast.LENGTH_SHORT).show();


                    // aqui abro el activity donde va a estar la galeria con la lista desplegable
                    // le mando un intent con el id de la ciudad en que estamos y un intent con el tipo de categoria
                    /*osea si estamos en la ciudad paris y en transporte le mandara esos dos string para en el activity
                    de la galeria saber que mostrar en la galeria y el la lista desplegable*/

                    // en el putExtra tipo_subdivicion le mando el item que clikee ej. (Renta de Autos si estoy en Transporte, Hoteles si estoy en Hospedate, etc)

                    e.putExtra("id_cuidades",id_cuidad);
                    e.putExtra("tipo_categoria",string_tipo_categoria);
                    e.putExtra("tipo_subdivision",texto_clikeado);

                    startActivity(e);


            }

        });


    }

    private void inserto_ListViewSubdivisiones(String tipo_categoria){

        // en este método yo valido cual opcion fue lo que clikee (Entretenimiento, hospedaje etc) y luego que valido
        // inserto en el listview las subdivisiones de cada opciones

        Intent recupero_id = getIntent();
        String id_cuidad = recupero_id.getStringExtra("id_cuidades");

        if (tipo_categoria.equals(getString(R.string.text_transporte))){

            CustomList adapter = new CustomList(subdiviciones_opciones.this, Texto_SubTransporte, Images_SubTransporte);

            listView_subdiviciones=(ListView) findViewById(R.id.listView_subdiviciones);

            listView_subdiviciones.setAdapter(adapter);

            getSupportActionBar().setTitle(getString(R.string.text_transporte));

        }


        if (tipo_categoria.equals(getString(R.string.text_gastronomia))){

            CustomList adapter = new CustomList(subdiviciones_opciones.this, Texto_SubGastronomia, Images_SubGastronomia);

            listView_subdiviciones=(ListView) findViewById(R.id.listView_subdiviciones);

            listView_subdiviciones.setAdapter(adapter);

            getSupportActionBar().setTitle(getString(R.string.text_gastronomia));
        }

        if (tipo_categoria.equals(getString(R.string.text_hospedaje))){

            CustomList adapter = new CustomList(subdiviciones_opciones.this, Texto_SubHospedaje, Images_SubHospedaje);

            listView_subdiviciones=(ListView) findViewById(R.id.listView_subdiviciones);

            listView_subdiviciones.setAdapter(adapter);

            getSupportActionBar().setTitle(getString(R.string.text_hospedaje));
        }

        if (tipo_categoria.equals(getString(R.string.text_entretenimiento))){

            CustomList adapter = new CustomList(subdiviciones_opciones.this, Texto_SubEntretenimiento, Images_SubEntretenimiento);

            listView_subdiviciones=(ListView) findViewById(R.id.listView_subdiviciones);

            listView_subdiviciones.setAdapter(adapter);

            getSupportActionBar().setTitle(getString(R.string.text_entretenimiento));
        }

        if (tipo_categoria.equals(getString(R.string.text_lugarInteres))){

            if(id_cuidad.equals("madrid")){

                CustomList adapter = new CustomList(subdiviciones_opciones.this, Texto_SubLugaresInteresMadrid, Images_SubLugaresInteresMadrid);

                listView_subdiviciones=(ListView) findViewById(R.id.listView_subdiviciones);

                listView_subdiviciones.setAdapter(adapter);

            }else {

                CustomList adapter = new CustomList(subdiviciones_opciones.this, Texto_SubLugaresInteres, Images_SubLugaresInteres);

                listView_subdiviciones = (ListView) findViewById(R.id.listView_subdiviciones);

                listView_subdiviciones.setAdapter(adapter);


            }
            getSupportActionBar().setTitle(getString(R.string.text_lugarInteres));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subdiviciones_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_Home:

                Intent i = new Intent(subdiviciones_opciones.this, Destinos.class);
                startActivity(i);
                return true;

            case R.id.menu_Calculator:
                startActivity(new Intent(subdiviciones_opciones.this, CalculadoraPopUp.class));
                return true;

            case R.id.menu_Lista:
                Intent recupero_id = getIntent();
                String recupero_idBanner = recupero_id.getStringExtra("id_cuidades");

                Intent r = new Intent(subdiviciones_opciones.this, Opciones.class);
                r.putExtra("id_cuidades",recupero_idBanner);
                startActivity(r);
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
