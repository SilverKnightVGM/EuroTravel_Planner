package com.example.prog2.eurotravelplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class gallery extends ActionBarActivity {


    HashMap<String, List<String>>lista_lugares;//Guarda los nombres de los lugares
    List<String> lugares_detalles; //Guarda los detalles que se presentan cuando se expande una opcion
    ExpandableListView exp_list; //Variable para el listview expandible
    LugaresAdapter adapter; //Adapter para el list view desplegable


    // este Integer{} son las imagenes que inserto en la galeria, estas dos imagenes son de prueba

    private Integer[] pics = {R.drawable.flores,R.drawable.music};

    // esto yo lo uso para insertar en un listview, esto es de prueba para ir viendo como se ira viendo la galeria
    // con la lista, se supone que la vamos a cambiar porque aqui va una lista desplegable

    public static Integer [] Images_Lista={R.drawable.flores,R.drawable.music};
    public static String [] Texto_Lista={"Flores","Música"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent recupero_id = getIntent();
        String ciudad = recupero_id.getStringExtra("id_cuidades");
        String categoria = recupero_id.getStringExtra("tipo_categoria");
        String subdivicion = recupero_id.getStringExtra("tipo_subdivision");

        imagenes_Ainsertar(ciudad, categoria, subdivicion);

        exp_list = (ExpandableListView)findViewById(R.id.expandableListView);
        lista_lugares = DataProvider.getInfo();//Obtiene el HashMap creado en la clase DataProvider
        lugares_detalles = new ArrayList<String>(lista_lugares.keySet());//Inicia la lista de descripciones a partir de lo que contiene el HashMap
        adapter = new LugaresAdapter(this, lista_lugares, lugares_detalles);
        exp_list.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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

    // este metodo es para insertar las imagenes en la galeria.
    public class ImageAdapter extends BaseAdapter {

        private Context context;
        private Integer[] I_gallery;
        //int imagebackground;

        public ImageAdapter(Context context,Integer[] I_gallery){
            this.context=context;
            this.I_gallery=I_gallery;
        }

        @Override
        public int getCount() {
            return I_gallery.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(I_gallery[position]);
            return imageView;
        }
    }

    public void imagenes_Ainsertar(String ciudad, String categoria, String subdivicion){

        Gallery gallery = (Gallery)findViewById(R.id.gallery);

        // AQUI COMIENZA PARIS
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
        if(ciudad.equals("paris")){

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){

                }

                if(subdivicion.equals("Renta de Autos")){

                }
                if(subdivicion.equals("Transporte Publico")){


                }
                if(subdivicion.equals("Trenes")){

                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){

                    Integer[] image = {R.drawable.restaurant_paris_epicure, R.drawable.restaurante_paris_seb_on,R.drawable.restaurant_paris_roomies,R.drawable.resturant_paris_pur_jean_francois_rouquette,R.drawable.restaurant_paris_lassommoirr,R.drawable.restaurant_paris_bistrot_chez_france,R.drawable.restaurant_paris_cobea};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){

                    Integer[] image = {R.drawable.pasteleria_paris_pierre_herme,R.drawable.pasteleria_paris_le_saotico,R.drawable.pasteleria_paris_berties_cupcakery,R.drawable.pasteleria_paris_patisserie_stohrer,R.drawable.pasteleria_paris_ble_sucre};

                    gallery.setAdapter(new ImageAdapter(this,image));


                }
                if(subdivicion.equals("Comida Rapida")){
                    Integer[] image = {R.drawable.comidarapida_paris_cojean,R.drawable.comidarapida_paris_lentredgeu,R.drawable.comidarapida_paris_cafe_des_musees,R.drawable.comidarapida_paris_pret_a_manger,R.drawable.comidarapida_paris_vandermeersch};

                    gallery.setAdapter(new ImageAdapter(this,image));
                }
                if(subdivicion.equals("Comida Típicas")){

                    Integer[] image = {R.drawable.platostipicos_paris_coq_au_vin,R.drawable.platostipicos_paris_canard_a_lorange,R.drawable.platostipicos_paris_ratatouille,R.drawable.platostipicos_paris_soupe_a_loignon,R.drawable.platostipicos_paris_escargo};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){

                }
                if(subdivicion.equals("Hostel")){

                }
                if(subdivicion.equals("Lugares de Acampar")){

                }

            }

            if (categoria.equals(getString(R.string.text_entretenimiento))){

                if(subdivicion.equals("Bares")){

                }
                if(subdivicion.equals("Discotecas")){

                }
                if(subdivicion.equals("Clubes")){

                }
                if(subdivicion.equals("Parques de Diversión")){

                }


            }

            if (categoria.equals(getString(R.string.text_lugarInteres))){

                if(subdivicion.equals("Lugares Historicos")){

                }
                if(subdivicion.equals("Museos")){

                }
                if(subdivicion.equals("Playas")){

                }
                if(subdivicion.equals("Tour por la ciudad")){

                }
            }

            if (categoria.equals(getString(R.string.text_compras))){

            }

        }

        // AQUI COMIENZA MADIRD
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
        if(ciudad.equals("madrid")){

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){

                }

                if(subdivicion.equals("Renta de Autos")){

                }
                if(subdivicion.equals("Transporte Publico")){


                }
                if(subdivicion.equals("Trenes")){

                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){

                }
                if(subdivicion.equals("Comida Rapida")){

                }
                if(subdivicion.equals("Comida Típicas")){

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){

                }
                if(subdivicion.equals("Hostel")){

                }
                if(subdivicion.equals("Lugares de Acampar")){

                }

            }

            if (categoria.equals(getString(R.string.text_entretenimiento))){

                if(subdivicion.equals("Bares")){

                }
                if(subdivicion.equals("Discotecas")){

                }
                if(subdivicion.equals("Clubes")){

                }
                if(subdivicion.equals("Parques de Diversión")){

                }


            }

            if (categoria.equals(getString(R.string.text_lugarInteres))){

                if(subdivicion.equals("Lugares Historicos")){

                }
                if(subdivicion.equals("Museos")){

                }
                if(subdivicion.equals("Playas")){

                }
                if(subdivicion.equals("Tour por la ciudad")){

                }
            }

            if (categoria.equals(getString(R.string.text_compras))){

            }

        }

// AQUI COMIENZA ROMA
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
        if(ciudad.equals("roma")){

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){

                }

                if(subdivicion.equals("Renta de Autos")){

                }
                if(subdivicion.equals("Transporte Publico")){


                }
                if(subdivicion.equals("Trenes")){

                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){

                    Integer[] p_rest = {R.drawable.restaurante_roma_vicinibistrot, R.drawable.restaurante_roma_lla_porta_del_principe, R.drawable.restaurante_roma_tamburellodi_pulcinella};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){

                    Integer[] p_past = {R.drawable.reposteria_roma_biscottificio_lnnocenti, R.drawable.reposteria_roma_opulentia, R.drawable.reposteria_roma_panzerotti_friends};

                    gallery.setAdapter(new ImageAdapter(this,p_past));

                }
                if(subdivicion.equals("Comida Rapida")){

                    Integer[] p_rapidat = {R.drawable.comida_rapida_roma_madame_baguette, R.drawable.comida_rapida_roma_bacio_di_puglia, R.drawable.comida_rapida_roma_lasagnam};

                    gallery.setAdapter(new ImageAdapter(this,p_rapidat));

                }
                if(subdivicion.equals("Comida Típicas")){

                    Integer[] p_tipica = {R.drawable.comida_tipica_roma_tomato_bruschetta_with_ricotta_and, R.drawable.comida_tipica_roma_bucainiall_amatriciana, R.drawable.comida_tipica_roma_panini, R.drawable.comida_tipica_roma_tartufonegro};

                    gallery.setAdapter(new ImageAdapter(this,p_tipica));

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){

                }
                if(subdivicion.equals("Hostel")){

                }
                if(subdivicion.equals("Lugares de Acampar")){

                }

            }

            if (categoria.equals(getString(R.string.text_entretenimiento))){

                if(subdivicion.equals("Bares")){

                }
                if(subdivicion.equals("Discotecas")){

                }
                if(subdivicion.equals("Clubes")){

                }
                if(subdivicion.equals("Parques de Diversión")){

                }


            }

            if (categoria.equals(getString(R.string.text_lugarInteres))){

                if(subdivicion.equals("Lugares Historicos")){

                }
                if(subdivicion.equals("Museos")){

                }
                if(subdivicion.equals("Playas")){

                }
                if(subdivicion.equals("Tour por la ciudad")){

                }
            }

            if (categoria.equals(getString(R.string.text_compras))){

            }

        }
// AQUI COMIENZA VENECIA
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
        if(ciudad.equals("venecia")){

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){

                }

                if(subdivicion.equals("Renta de Autos")){

                }
                if(subdivicion.equals("Transporte Publico")){


                }
                if(subdivicion.equals("Trenes")){

                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){

                }
                if(subdivicion.equals("Comida Rapida")){

                }
                if(subdivicion.equals("Comida Típicas")){

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){

                }
                if(subdivicion.equals("Hostel")){

                }
                if(subdivicion.equals("Lugares de Acampar")){

                }

            }

            if (categoria.equals(getString(R.string.text_entretenimiento))){

                if(subdivicion.equals("Bares")){

                }
                if(subdivicion.equals("Discotecas")){

                }
                if(subdivicion.equals("Clubes")){

                }
                if(subdivicion.equals("Parques de Diversión")){

                }


            }

            if (categoria.equals(getString(R.string.text_lugarInteres))){

                if(subdivicion.equals("Lugares Historicos")){

                }
                if(subdivicion.equals("Museos")){

                }
                if(subdivicion.equals("Playas")){

                }
                if(subdivicion.equals("Tour por la ciudad")){

                }
            }

            if (categoria.equals(getString(R.string.text_compras))){

            }

        }

// AQUI COMIENZA BERLIN
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
        if(ciudad.equals("berlin")){

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){

                }

                if(subdivicion.equals("Renta de Autos")){

                }
                if(subdivicion.equals("Transporte Publico")){


                }
                if(subdivicion.equals("Trenes")){

                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){

                    Integer[] image = {R.drawable.restaurant_berlin_grill_royal,R.drawable.restaurant_berlin_hofbrau_berlin,R.drawable.restaurant_berlin_zur_letzten_instanz};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){

                    Integer[] image = {R.drawable.pasteleria_berlin_mandragoras,R.drawable.pasteleria_berlin_tigertortchen,R.drawable.pasteleria_berlin_zeit_fuer_brot};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Comida Rapida")){

                    Integer[] image = {R.drawable.comidarapida_berlin_burgermeister,R.drawable.comidarapida_berlin_curry_baude,R.drawable.comidarapida_berlin_vapiano};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Comida Típicas")){

                    Integer[] image = {R.drawable.platotipico_berlin_currywurst,R.drawable.platotipico_berlin_kartoffelsalat,R.drawable.platotipico_berlin_rote_gruetze,R.drawable.platotipico_berlin_eisbein_mit_sauerkraut};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){

                }
                if(subdivicion.equals("Hostel")){

                }
                if(subdivicion.equals("Lugares de Acampar")){

                }
                //g

            }

            if (categoria.equals(getString(R.string.text_entretenimiento))){

                if(subdivicion.equals("Bares")){

                }
                if(subdivicion.equals("Discotecas")){

                }
                if(subdivicion.equals("Clubes")){

                }
                if(subdivicion.equals("Parques de Diversión")){

                }


            }

            if (categoria.equals(getString(R.string.text_lugarInteres))){

                if(subdivicion.equals("Lugares Historicos")){

                }
                if(subdivicion.equals("Museos")){

                }
                if(subdivicion.equals("Playas")){

                }
                if(subdivicion.equals("Tour por la ciudad")){

                }
            }

            if (categoria.equals(getString(R.string.text_compras))){

            }

        }

// AQUI COMIENZA LONDRES
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
        if(ciudad.equals("londres")){

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){

                }

                if(subdivicion.equals("Renta de Autos")){

                }
                if(subdivicion.equals("Transporte Publico")){


                }
                if(subdivicion.equals("Trenes")){

                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){

                }
                if(subdivicion.equals("Comida Rapida")){

                }
                if(subdivicion.equals("Comida Típicas")){

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){

                }
                if(subdivicion.equals("Hostel")){

                }
                if(subdivicion.equals("Lugares de Acampar")){

                }

            }

            if (categoria.equals(getString(R.string.text_entretenimiento))){

                if(subdivicion.equals("Bares")){

                }
                if(subdivicion.equals("Discotecas")){

                }
                if(subdivicion.equals("Clubes")){

                }
                if(subdivicion.equals("Parques de Diversión")){

                }


            }

            if (categoria.equals(getString(R.string.text_lugarInteres))){

                if(subdivicion.equals("Lugares Historicos")){

                }
                if(subdivicion.equals("Museos")){

                }
                if(subdivicion.equals("Playas")){

                }
                if(subdivicion.equals("Tour por la ciudad")){

                }
            }

            if (categoria.equals(getString(R.string.text_compras))){

            }

        }
//----------------------------------------------------------------------------------------------------------------------------------
    }
}

