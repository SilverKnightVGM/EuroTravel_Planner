package com.example.prog2.eurotravelplanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class gallery extends ActionBarActivity {


    HashMap<String, List<String>>lista_lugares;//Guarda los nombres de los lugares
    List<String> lugares_detalles; //Guarda los detalles que se presentan cuando se expande una opcion
    ExpandableListView exp_list; //Variable para el listview expandible
    LugaresAdapter adapter; //Adapter para el list view desplegable
    DbHelper helper = new DbHelper(this);
    private AlertDialog.Builder dialogBuilder;


    // este Integer{} son las imagenes que inserto en la galeria, estas dos imagenes son de prueba

    private Integer[] pics = {R.drawable.flores,R.drawable.music};

    // esto yo lo uso para insertar en un listview, esto es de prueba para ir viendo como se ira viendo la galeria
    // con la lista, se supone que la vamos a cambiar porque aqui va una lista desplegable

    public static Integer [] Images_Lista={R.drawable.flores,R.drawable.music};
    public static String [] Texto_Lista={"Flores","Música"};

    //listas que iran al HashMap y sus titulos
    static List<String> Primer_Lugar = new ArrayList<String>();
       static String StrPrimer;
    static List<String> Segundo_Lugar = new ArrayList<String>();
        static String StrSegundo;
    static List<String> Tercer_Lugar = new ArrayList<String>();
        static String StrTercer;
    static List<String> Cuarto_Lugar = new ArrayList<String>();
        static String StrCuarto;
    static List<String> Quinto_Lugar = new ArrayList<String>();
        static String StrQuinto;
    static List<String> Sexto_Lugar = new ArrayList<String>();
    static String StrSexto;
    static List<String> Septimo_Lugar = new ArrayList<String>();
    static String StrSeptimo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        final Gallery gallery = (Gallery)findViewById(R.id.gallery);

        Intent recupero_id = getIntent();
        String ciudad = recupero_id.getStringExtra("id_cuidades");
        String categoria = recupero_id.getStringExtra("tipo_categoria");
        String subdivicion = recupero_id.getStringExtra("tipo_subdivision");
        LimpiarListas();

        imagenes_Ainsertar(ciudad, categoria, subdivicion);
        ObtenerInfo();
        exp_list = (ExpandableListView)findViewById(R.id.expandableListView);


        lista_lugares = getInfo();//Obtiene el HashMap creado en la clase DataProvider
        //lista_lugares = DataProvider.getInfo(ciudad, categoria, subdivicion);//Obtiene el HashMap creado en la clase DataProvider
        lugares_detalles = new ArrayList<String>(lista_lugares.keySet());//Inicia la lista de descripciones a partir de lo que contiene el HashMap
        adapter = new LugaresAdapter(this, lista_lugares, lugares_detalles);
        exp_list.setAdapter(adapter);

        exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                //Toast.makeText(gallery.this, "You Clicked at " + groupPosition, Toast.LENGTH_SHORT).show();
                gallery.setSelection(groupPosition);
                if ((previousGroup != -1) && (groupPosition != previousGroup)) {
                    exp_list.collapseGroup(previousGroup);
                }
                previousGroup = groupPosition;
            }
        });

        exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Intent recupero_id = getIntent();

                String categori = recupero_id.getStringExtra("tipo_categoria");
                String subdivicio = recupero_id.getStringExtra("tipo_subdivision");


                Object g = parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
                String data = g.toString();
                //Toast.makeText(gallery.this, "You Clicked at " + childPosition, Toast.LENGTH_SHORT).show();
                //Toast.makeText(gallery.this, gg, Toast.LENGTH_SHORT).show();

                if (categori.equals(getString(R.string.text_gastronomia))) {
                    if (subdivicio.equals("Restaurantes") || subdivicio.equals("Pasteleria y Panaderia") || subdivicio.equals("Comida Rapida")) {
                        if (childPosition == 1) {
                            LlamarDialog(data);
                        }
                        if (childPosition == 0) {
                            ubicarDireccion(data);
                        }

                    }
                }
                if (categori.equals(getString(R.string.text_hospedaje))) {
                    if (subdivicio.equals("Hoteles") || subdivicio.equals("Hostel") || subdivicio.equals("Lugares de Acampar")) {
                        if (childPosition == 1) {
                            LlamarDialog(data);
                        }
                        if (childPosition == 0) {
                            ubicarDireccion(data);
                        }

                    }
                }


                return true;
            }
        });



    }


    public void LlamarDialog(final String tel){

        dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle(tel);
        dialogBuilder.setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent ii = new Intent(android.content.Intent.ACTION_DIAL,
                        Uri.parse("tel:" + tel)); //
                startActivity(ii);
            }
        });

        dialogBuilder.setNegativeButton("Calcelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialogDelete = dialogBuilder.create();
        dialogDelete.show();
    }

    public void ubicarDireccion(final String direccion){

        dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle("Desea ubicar esta dirección?");
        dialogBuilder.setPositiveButton("Ubicar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Uri myUri = Uri.parse("geo:0,0?q=" + direccion);
                showMap(myUri);

            }
        });

        dialogBuilder.setNegativeButton("Calcelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialogDelete = dialogBuilder.create();
        dialogDelete.show();
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void LimpiarListas(){
        Primer_Lugar.clear();
        Segundo_Lugar.clear();
        Tercer_Lugar.clear();
        Cuarto_Lugar.clear();
        Quinto_Lugar.clear();
        Sexto_Lugar.clear();
        Septimo_Lugar.clear();

        StrPrimer = "";
        StrSegundo = "";
        StrTercer = "";
        StrCuarto = "";
        StrQuinto = "";
        StrSexto = "";
        StrSeptimo = "";
    }

    private void ObtenerInfo(){
        Cursor cl = helper.getInfo();
        int cantRow = cl.getColumnCount();
        String text = null;


        if (cl.moveToFirst()){
            StrPrimer = cl.getString(4);
            for(int i=5;i<cantRow;i++){
                text = cl.getString(i);
                if(text != null && !text.toString().isEmpty()) {
                    Primer_Lugar.add(text);
                }
            }
        }
        if(cl.moveToNext()){
            StrSegundo = cl.getString(4);
            for(int i=5;i<cantRow;i++){
                text = cl.getString(i);
                if(text != null && !text.toString().isEmpty()) {
                    Segundo_Lugar.add(cl.getString(i));
                }
            }
        }
        if(cl.moveToNext()){
            StrTercer = cl.getString(4);
            for(int i=5;i<cantRow;i++){
                text = cl.getString(i);
                if(text != null && !text.toString().isEmpty()) {
                    Tercer_Lugar.add(cl.getString(i));
                }
            }
        }
        if(cl.moveToNext()){
            StrCuarto = cl.getString(4);
            for(int i=5;i<cantRow;i++){
                text = cl.getString(i);
                if(text != null && !text.toString().isEmpty()) {
                    Cuarto_Lugar.add(cl.getString(i));
                }
            }
        }
        if(cl.moveToNext()){
            StrQuinto = cl.getString(4);
            for(int i=5;i<cantRow;i++){
                text = cl.getString(i);
                if(text != null && !text.toString().isEmpty()) {
                    Quinto_Lugar.add(cl.getString(i));
                }
            }
        }
        if(cl.moveToNext()){
            StrSexto = cl.getString(4);
            for(int i=5;i<cantRow;i++){
                text = cl.getString(i);
                if(text != null && !text.toString().isEmpty()) {
                    Sexto_Lugar.add(cl.getString(i));
                }
            }
        }

        if(cl.moveToNext()){
            StrSeptimo = cl.getString(4);
            for(int i=5;i<cantRow;i++){
                text = cl.getString(i);
                if(text != null && !text.toString().isEmpty()) {
                    Septimo_Lugar.add(cl.getString(i));
                }
            }
        }
    }


    public static HashMap<String, List<String>> getInfo(){
        HashMap <String, List<String>> LugaresDetalles = new HashMap<String, List<String>>();

        if(!Primer_Lugar.isEmpty()) {
            LugaresDetalles.put(StrPrimer, Primer_Lugar);
        }
        if(!Segundo_Lugar.isEmpty()) {
            LugaresDetalles.put(StrSegundo, Segundo_Lugar);
        }
        if(!Tercer_Lugar.isEmpty()) {
            LugaresDetalles.put(StrTercer, Tercer_Lugar);
        }
        if(!Cuarto_Lugar.isEmpty()) {
            LugaresDetalles.put(StrCuarto, Cuarto_Lugar);
        }
        if(!Quinto_Lugar.isEmpty()) {
            LugaresDetalles.put(StrQuinto, Quinto_Lugar);
        }
        if(!Sexto_Lugar.isEmpty()) {
            LugaresDetalles.put(StrSexto, Sexto_Lugar);
        }
        if(!Septimo_Lugar.isEmpty()) {
            LugaresDetalles.put(StrSeptimo, Septimo_Lugar);
        }

        return LugaresDetalles;
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
            helper.where3 = helper.ID_ciudad3 +" = 'paris'";
            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'taxi'";
                }

                if(subdivicion.equals("Renta de Autos")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'renta_autos'";
                }
                if(subdivicion.equals("Transporte Publico")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trans_publico'";

                }
                if(subdivicion.equals("Trenes")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trenes'";
                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){

                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'restaurantes'";
                    Integer[] image = {R.drawable.restaurant_paris_cobea, R.drawable.restaurant_paris_bistrot_chez_france,R.drawable.restaurant_paris_epicure,R.drawable.restaurant_paris_lassommoirr,R.drawable.restaurante_paris_seb_on,R.drawable.restaurant_paris_roomies,R.drawable.resturant_paris_pur_jean_francois_rouquette};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'pasteleria'";
                    Integer[] image = {R.drawable.pasteleria_paris_berties_cupcakery,R.drawable.pasteleria_paris_patisserie_stohrer,R.drawable.pasteleria_paris_le_saotico,R.drawable.pasteleria_paris_pierre_herme,R.drawable.pasteleria_paris_ble_sucre};

                    gallery.setAdapter(new ImageAdapter(this,image));


                }
                if(subdivicion.equals("Comida Rapida")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'comida_rapida'";
                    Integer[] image = {R.drawable.comidarapida_paris_cafe_des_musees,R.drawable.comidarapida_paris_pret_a_manger,R.drawable.comidarapida_paris_cojean,R.drawable.comidarapida_paris_vandermeersch,R.drawable.comidarapida_paris_lentredgeu};

                    gallery.setAdapter(new ImageAdapter(this,image));
                }
                if(subdivicion.equals("Comida Típicas")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'comida_tipica'";
                    Integer[] image = {R.drawable.platostipicos_paris_soupe_a_loignon,R.drawable.platostipicos_paris_coq_au_vin,R.drawable.platostipicos_paris_ratatouille,R.drawable.platostipicos_paris_escargo,R.drawable.platostipicos_paris_canard_a_lorange};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hoteles'";
                    Integer[] p_hotel = {R.drawable.hotel_paris_saint_james_paris_relais_et_chateaux,R.drawable.hotel_paris_four_seasons_hotel_george_v,R.drawable.hotel_paris_le_bristol_paris};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hosteles'";
                    Integer[] p_hostel = {R.drawable.hostel_paris_avalon_paris_hotel,R.drawable.hostel_paris_hotel_boissiere,R.drawable.hostel_paris_hotel_rocroy};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));

                }
                if(subdivicion.equals("Lugares de Acampar")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'lugar_acampar'";
                    Integer[] p_camp = {R.drawable.acampar_paris_huttopia_versailles,R.drawable.acampar_paris_camping_indigo_paris_bois_boulogne,R.drawable.acampar_paris_camping_international_maisons_laffitte};

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
            helper.where3 = helper.ID_ciudad3 +" = 'madrid'";
            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'taxi'";
                }

                if(subdivicion.equals("Renta de Autos")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'renta_autos'";
                }
                if(subdivicion.equals("Transporte Publico")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trans_publico'";

                }
                if(subdivicion.equals("Trenes")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trenes'";
                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'restaurantes'";

                    Integer[] p_rest = {R.drawable.restaurante_madrid_labolataberna,R.drawable.restaurante_madrid_santcelonidos_vdos,R.drawable.restaurante_madrid_rubaiyat};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));
                }
                if(subdivicion.equals("Pasteleria y Panaderia")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'pasteleria'";

                    Integer[] p_rest = {R.drawable.reposteria_madrid_lamallorquina_v,R.drawable.reposteria_madrid_celicioso_v,R.drawable.reposteria_madrid_martinacocina_v};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));
                }
                if(subdivicion.equals("Comida Rapida")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'comida_rapida'";

                    Integer[] p_rest = {R.drawable.comidarapida_madrid_stopmadrid_v,R.drawable.comidarapida_madrid_malaspina_v,R.drawable.comidarapida_madrid_restaurantecasaalberto_v};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));
                }
                if(subdivicion.equals("Comida Típicas")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'comida_tipica'";

                    Integer[] p_rest = {R.drawable.comidatipica_madrid_tapas_v,R.drawable.comidatipica_madrid_ensaladadesanisidro_vdos,R.drawable.comidatipica_madrid_lechemerengada_v,R.drawable.comidatipica_madrid_bartillo_v,R.drawable.comidatipica_madrid_cocidomadrileno_v};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));
                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hoteles'";
                    Integer[] p_hotel = {R.drawable.hotel_madrid_the_westin_palace_madrid_opt,R.drawable.hotel_madrid_jardines_de_sabatini_opt};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hosteles'";
                    Integer[] p_hostel = {R.drawable.hostel_madrid_u_hostels_opt,R.drawable.hostel_madrid_way_hostel_opt};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));


                }
                if(subdivicion.equals("Lugares de Acampar")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'lugar_acampar'";
                    Integer[] p_camp = {R.drawable.acampar_madrid_only_you_hotel_lounge_opt};

                    gallery.setAdapter(new ImageAdapter(this,p_camp));

                }

            }

            if (categoria.equals(getString(R.string.text_entretenimiento))){

                if(subdivicion.equals("Bares")){

                    Integer[] p_bar = {R.drawable.bares_madrid_pajaritos_mojados,R.drawable.bares_madrid_taberna_degusta};

                    gallery.setAdapter(new ImageAdapter(this,p_bar));

                }
                if(subdivicion.equals("Discotecas")){

                    Integer[] p_disco = {R.drawable.discoteca_madrid_teatro_kapital,R.drawable.discoteca_madrid_joy_eslava};

                    gallery.setAdapter(new ImageAdapter(this,p_disco));

                }
                if(subdivicion.equals("Clubes")){

                    Integer[] p_club = {R.drawable.clubes_madrid_macumba,R.drawable.clubes_madrid_maxime};

                    gallery.setAdapter(new ImageAdapter(this,p_club));

                }
                if(subdivicion.equals("Parques de Diversión")){

                    Integer[] p_parques = {R.drawable.parques_madrid_madrid_theme_park,R.drawable.parques_madrid_parque_warner};

                    gallery.setAdapter(new ImageAdapter(this,p_parques));

                }


            }

            if (categoria.equals(getString(R.string.text_lugarInteres))){

                if(subdivicion.equals("Lugares Historicos")){

                    Integer[] p_historia = {R.drawable.lugar_historicos_madrid_real_basilica_de_san_francisco_el_grande,R.drawable.lugar_historicos_madrid_templo_de_debod};

                    gallery.setAdapter(new ImageAdapter(this,p_historia));

                }
                if(subdivicion.equals("Museos")){

                    Integer[] p_museos = {R.drawable.museos_madrid_museo_del_prado,R.drawable.museos_madrid_museo_centro_de_arte_reina_sofia};

                    gallery.setAdapter(new ImageAdapter(this,p_museos));

                }
                if(subdivicion.equals("Playas")){

                }
                if(subdivicion.equals("Tour por la ciudad")){

                    Integer[] p_tour = {R.drawable.tour_madrid_madrid_city_tour};

                    gallery.setAdapter(new ImageAdapter(this,p_tour));

                }
            }

            if (categoria.equals(getString(R.string.text_compras))){

                Integer[] p_compras = {R.drawable.compras_madrid_mercado_san_miguel,R.drawable.compras_madrid_sherry_corner};

                gallery.setAdapter(new ImageAdapter(this,p_compras));

            }

        }

// AQUI COMIENZA ROMA
//-----------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
        if(ciudad.equals("roma")){
            helper.where3 = helper.ID_ciudad3 +" = 'roma'";
            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'taxi'";
                }

                if(subdivicion.equals("Renta de Autos")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'renta_autos'";
                }
                if(subdivicion.equals("Transporte Publico")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trans_publico'";

                }
                if(subdivicion.equals("Trenes")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trenes'";
                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='restaurantes'";
                    Integer[] p_rest = {R.drawable.restaurante_roma_tamburellodi_pulcinella, R.drawable.restaurante_roma_vicinibistrot, R.drawable.restaurante_roma_lla_porta_del_principe};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='pasteleria'";
                    Integer[] p_past = {R.drawable.reposteria_roma_panzerotti_friends, R.drawable.reposteria_roma_biscottificio_lnnocenti, R.drawable.reposteria_roma_opulentia};

                    gallery.setAdapter(new ImageAdapter(this,p_past));

                }
                if(subdivicion.equals("Comida Rapida")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_rapida'";
                    Integer[] p_rapidat = {R.drawable.comida_rapida_roma_madame_baguette, R.drawable.comida_rapida_roma_lasagnam, R.drawable.comida_rapida_roma_bacio_di_puglia};

                    gallery.setAdapter(new ImageAdapter(this,p_rapidat));

                }
                if(subdivicion.equals("Comida Típicas")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_tipica'";
                    Integer[] p_tipica = {R.drawable.comida_tipica_roma_panini, R.drawable.comida_tipica_roma_tomato_bruschetta_with_ricotta_and, R.drawable.comida_tipica_roma_tartufonegro, R.drawable.comida_tipica_roma_bucainiall_amatriciana};

                    gallery.setAdapter(new ImageAdapter(this,p_tipica));

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hoteles'";

                    Integer[] p_tipica = {R.drawable.hospedaje_roma_hearthhote_v, R.drawable.hospedaje_roma_diocleziano_v, R.drawable.hospedaje_roma_campodefiori_v};

                    gallery.setAdapter(new ImageAdapter(this,p_tipica));

                }
                if(subdivicion.equals("Hostel")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hosteles'";

                    Integer[] p_tipica = {R.drawable.hospedaje_roma_motelsalario_v, R.drawable.hospedaje_roma_rhonabb_v, R.drawable.hospedaje_roma_villateresa_v};

                    gallery.setAdapter(new ImageAdapter(this,p_tipica));
                }
                if(subdivicion.equals("Lugares de Acampar")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'lugar_acampar'";

                    Integer[] p_tipica = {R.drawable.hospedaje_roma_miralago_v, R.drawable.hospedaje_roma_camping_v, R.drawable.hospedaje_roma_fabulous_v};

                    gallery.setAdapter(new ImageAdapter(this,p_tipica));
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
            helper.where3 = helper.ID_ciudad3 +" = 'venecia'";
            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'taxi'";
                }

                if(subdivicion.equals("Renta de Autos")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'renta_autos'";
                }
                if(subdivicion.equals("Transporte Publico")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trans_publico'";

                }
                if(subdivicion.equals("Trenes")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trenes'";
                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='restaurantes'";
                    Integer[] p_rest = {R.drawable.restaurant_venecia_bistrot_de_venise,R.drawable.restaurant_venecia_al_covo,R.drawable.restaurant_venecia_la_zucca};

                    gallery.setAdapter(new ImageAdapter(this,p_rest));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='pasteleria'";
                    Integer[] p_paste = {R.drawable.pasteleria_venecia_pasticceria_tonolo,R.drawable.pasteleria_venecia_nobile_pasticceria,R.drawable.pasteleria_venecia_rosa_salva};

                    gallery.setAdapter(new ImageAdapter(this,p_paste));

                }
                if(subdivicion.equals("Comida Rapida")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_rapida'";
                    Integer[] p_fast = {R.drawable.comidarapida_venecia_q_food_more,R.drawable.comidarapida_venecia_dal_moros_fresh_pasta_to_go,R.drawable.comidarapida_venecia_tiziano_snack_bar};

                    gallery.setAdapter(new ImageAdapter(this,p_fast));

                }
                if(subdivicion.equals("Comida Típicas")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_tipica'";
                    Integer[] p_tipica = {R.drawable.platotipico_venecia_pez_san_pedro,R.drawable.platotipico_venecia_fegatto_alla_veneziana,R.drawable.platotipico_venecia_brioche,R.drawable.platotipico_venecia_campari,R.drawable.platotipico_venecia_risotto_risi_e_bis};

                    gallery.setAdapter(new ImageAdapter(this,p_tipica));
                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hoteles'";
                    Integer[] p_hotel = {R.drawable.hotel_venecia_the_westin_europa_regina,R.drawable.hotel_venecia_hilton_molino_stucky_venice,R.drawable.hotel_venecia_carnival_palace};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hosteles'";
                    Integer[] p_hostel = {R.drawable.hostel_venecia_lmbarcadero,R.drawable.hostel_venecia_ostello_santa_fosca,R.drawable.hostel_venecia_yha_ostello};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));


                }
                if(subdivicion.equals("Lugares de Acampar")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'lugar_acampar'";
                    Integer[] p_camp = {R.drawable.acampar_venecia_camping_fusina,R.drawable.acampar_venecia_camping_serenissima};

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
            helper.where3 = helper.ID_ciudad3 +" = 'berlin'";

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){

                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'taxi'";
                }

                if(subdivicion.equals("Renta de Autos")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'renta_autos'";
                }
                if(subdivicion.equals("Transporte Publico")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trans_publico'";

                }
                if(subdivicion.equals("Trenes")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trenes'";
                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='restaurantes'";
                    Integer[] image = {R.drawable.restaurant_berlin_zur_letzten_instanz,R.drawable.restaurant_berlin_grill_royal,R.drawable.restaurant_berlin_hofbrau_berlin};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Pasteleria y Panaderia")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='pasteleria'";
                    Integer[] image = {R.drawable.pasteleria_berlin_mandragoras,R.drawable.pasteleria_berlin_zeit_fuer_brot,R.drawable.pasteleria_berlin_tigertortchen};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Comida Rapida")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_rapida'";
                    Integer[] image = {R.drawable.comidarapida_berlin_curry_baude,R.drawable.comidarapida_berlin_burgermeister,R.drawable.comidarapida_berlin_vapiano};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }
                if(subdivicion.equals("Comida Típicas")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_tipica'";
                    Integer[] image = {R.drawable.platotipico_berlin_eisbein_mit_sauerkraut,R.drawable.platotipico_berlin_kartoffelsalat,R.drawable.platotipico_berlin_rote_gruetze,R.drawable.platotipico_berlin_currywurst};

                    gallery.setAdapter(new ImageAdapter(this,image));

                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hoteles'";
                    Integer[] p_hotel = {R.drawable.hotel_berlin_movenpick_hotel_berlin,R.drawable.hotel_berlin_hotel_adlon_kempinski};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hosteles'";
                    Integer[] p_hostel = {R.drawable.hostel_berlin_sunflower_hostel,R.drawable.hastel_berlin_cityhostel_berlin};
                    gallery.setAdapter(new ImageAdapter(this,p_hostel));

                }
                if(subdivicion.equals("Lugares de Acampar")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'lugar_acampar'";
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
            helper.where3 = helper.ID_ciudad3 +" = 'londres'";

            if (categoria.equals(getString(R.string.text_transporte))){

                if(subdivicion.equals("Taxi")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'taxi'";
                }

                if(subdivicion.equals("Renta de Autos")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'renta_autos'";
                }
                if(subdivicion.equals("Transporte Publico")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trans_publico'";

                }
                if(subdivicion.equals("Trenes")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'trenes'";
                }


            }
            if (categoria.equals(getString(R.string.text_gastronomia))){

                if(subdivicion.equals("Restaurantes")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='restaurantes'";
                }
                if(subdivicion.equals("Pasteleria y Panaderia")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='pasteleria'";
                }
                if(subdivicion.equals("Comida Rapida")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_rapida'";
                }
                if(subdivicion.equals("Comida Típicas")){
                    helper.where3+=" AND "+helper.CN_sub_cat+"='comida_tipica'";
                }

            }

            if (categoria.equals(getString(R.string.text_hospedaje))){

                if(subdivicion.equals("Hoteles")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hoteles'";
                    Integer[] p_hotel = {R.drawable.hotel_londres_the_milestone_hotel,R.drawable.hotel_londres_hotel_cuarenta_uno,R.drawable.hotel_londres_conrad_london_st_james};

                    gallery.setAdapter(new ImageAdapter(this,p_hotel));

                }
                if(subdivicion.equals("Hostel")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'hosteles'";
                    Integer[] p_hostel = {R.drawable.hostel_londres_clink_hostel,R.drawable.hostel_londres_palmer_lodge_swiss_cottage,R.drawable.hostel_londres_clink_hostel};

                    gallery.setAdapter(new ImageAdapter(this,p_hostel));

                }
                if(subdivicion.equals("Lugares de Acampar")){
                    helper.where3 += " AND "+helper.CN_sub_cat+" = 'lugar_acampar'";
                    Integer[] p_camp = {R.drawable.acampar_londres_abbey_wood_caravan_club_site,R.drawable.acampar_londres_lee_valley_camping_and_caravan_park_edmonton,R.drawable.acampar_londres_crystal_palace_caravan_club_site};

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

