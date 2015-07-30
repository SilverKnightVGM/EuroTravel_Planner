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
        switch(item.getItemId()){
            case R.id.menu_Home:

                Intent i = new Intent(gallery.this, Destinos.class);
                startActivity(i);
                return true;

            case R.id.menu_Calculator:
                startActivity(new Intent(gallery.this, CalculadoraPopUp.class));
                return true;

            case R.id.menu_Lista:
                Intent recupero_id = getIntent();
                String recupero_idBanner = recupero_id.getStringExtra("id_cuidades");

                Intent r = new Intent(gallery.this, Opciones.class);
                r.putExtra("id_cuidades",recupero_idBanner);
                startActivity(r);
                return true;

            default:
                break;
        }
        return true;
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

                    Integer[] p_hotel = {R.drawable.hotel_paris_le_bristol_paris,R.drawable.hotel_paris_four_seasons_hotel_george_v,R.drawable.hotel_paris_saint_james_paris_relais_et_chateaux};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){

                    Integer[] p_hostel = {R.drawable.hostel_paris_avalon_paris_hotel,R.drawable.hostel_paris_hotel_rocroy,R.drawable.hostel_paris_hotel_boissiere};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));

                }
                if(subdivicion.equals("Lugares de Acampar")){

                    Integer[] p_camp = {R.drawable.acampar_paris_camping_indigo_paris_bois_boulogne,R.drawable.acampar_paris_camping_international_maisons_laffitte,R.drawable.acampar_paris_huttopia_versailles};

                     gallery.setAdapter(new ImageAdapter(this,p_camp));

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

                    Integer[] p_hotel = {R.drawable.hotel_madrid_jardines_de_sabatini_opt,R.drawable.hotel_madrid_the_westin_palace_madrid_opt};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){

                    Integer[] p_hostel = {R.drawable.hostel_madrid_way_hostel_opt,R.drawable.hostel_madrid_u_hostels_opt};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));


                }
                if(subdivicion.equals("Lugares de Acampar")){

                    Integer[] p_camp = {R.drawable.acampar_madrid_only_you_hotel_lounge_opt};

                    gallery.setAdapter(new ImageAdapter(this,p_camp));

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

                    Integer[] p_rest = {R.drawable.restaurant_venecia_la_zucca,R.drawable.restaurant_venecia_al_covo,R.drawable.restaurant_venecia_bistrot_de_venise};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){

                    Integer[] p_paste = {R.drawable.pasteleria_venecia_nobile_pasticceria,R.drawable.pasteleria_venecia_rosa_salva,R.drawable.pasteleria_venecia_pasticceria_tonolo};

                    gallery.setAdapter(new ImageAdapter(this,p_paste));

                }
                if(subdivicion.equals("Comida Rapida")){

                    Integer[] p_fast = {R.drawable.comidarapida_venecia_q_food_more,R.drawable.comidarapida_venecia_tiziano_snack_bar,R.drawable.comidarapida_venecia_dal_moros_fresh_pasta_to_go};

                    gallery.setAdapter(new ImageAdapter(this,p_fast));

                }
                if(subdivicion.equals("Comida Típicas")){

                    Integer[] p_tipica = {R.drawable.platotipico_venecia_brioche,R.drawable.platotipico_venecia_risotto_risi_e_bis,R.drawable.platotipico_venecia_fegatto_alla_veneziana,R.drawable.platotipico_venecia_pez_san_pedro,R.drawable.platotipico_venecia_campari};

                    gallery.setAdapter(new ImageAdapter(this,p_tipica));
                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){

                    Integer[] p_hotel = {R.drawable.hotel_venecia_hilton_molino_stucky_venice,R.drawable.hotel_venecia_the_westin_europa_regina,R.drawable.hotel_venecia_carnival_palace};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){

                    Integer[] p_hostel = {R.drawable.hostel_venecia_yha_ostello,R.drawable.hostel_venecia_ostello_santa_fosca,R.drawable.hostel_venecia_lmbarcadero};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));


                }
                if(subdivicion.equals("Lugares de Acampar")){

                    Integer[] p_camp = {R.drawable.acampar_venecia_camping_serenissima,R.drawable.acampar_venecia_camping_fusina};

                    gallery.setAdapter(new ImageAdapter(this,p_camp));

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

                    Integer[] p_hotel = {R.drawable.hotel_berlin_hotel_adlon_kempinski,R.drawable.hotel_berlin_movenpick_hotel_berlin};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){

                    Integer[] p_hostel = {R.drawable.hastel_berlin_cityhostel_berlin,R.drawable.hostel_berlin_sunflower_hostel};
                    gallery.setAdapter(new ImageAdapter(this,p_hostel));

                }
                if(subdivicion.equals("Lugares de Acampar")){

                    Integer[] p_camp = {R.drawable.acampar_berlin_assateague_state_park_camping};
                    gallery.setAdapter(new ImageAdapter(this,p_camp));

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

                    Integer[] p_hotel = {R.drawable.hotel_londres_conrad_london_st_james,R.drawable.hotel_londres_hotel_cuarenta_uno,R.drawable.hotel_londres_the_milestone_hotel};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){

                    Integer[] p_hostel = {R.drawable.hostel_londres_clink_hostel,R.drawable.hostel_londres_smart_russell_square_hostel,R.drawable.hostel_londres_palmer_lodge_swiss_cottage};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));

                }
                if(subdivicion.equals("Lugares de Acampar")){

                    Integer[] p_camp = {R.drawable.acampar_londres_abbey_wood_caravan_club_site,R.drawable.acampar_londres_crystal_palace_caravan_club_site,R.drawable.acampar_londres_lee_valley_camping_and_caravan_park_edmonton};

                    gallery.setAdapter(new ImageAdapter(this,p_camp));
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

