package com.example.prog2.eurotravelplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Opciones extends ActionBarActivity {

    ListView categorias;
    Button tips;
    private final static int ALERT_DIALOG=1;
    private List<clase_categorias> miCategoria = new ArrayList<clase_categorias>();
    List<String> template = new ArrayList<String>();
    ImageView imageView_banner;

    public static String pais_actual=null; // variable para saber en que pais se realiza la busqueda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        categorias = (ListView) findViewById(R.id.listView_opciones);
        tips = (Button) findViewById(R.id.buttonTips);
        tips.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Opciones.this.showDialog(ALERT_DIALOG);//Llama al metodo que crea el AlertDialog para los Tips
            }
        });
        inserto_banner();
        populateListCategoria();
        populateListView();


        //aqui hago el click en algun item del listview
        categorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Intent recupero_id = getIntent();
                //String recupero_idBanner = recupero_id.getStringExtra("id_cuidades");
                // idcategoria_clikeada en esta variable guardo la categoria que clikee, ej. Datos Generales, Enretenimieto, Transporte etc
                String idcategoria_clikeada = miCategoria.get(position).getCategoria();

                if (idcategoria_clikeada.equals(getString(R.string.title_activity_datosGenerales))) {

                    Intent recupero_id = getIntent();
                    String recupero_idBanner = recupero_id.getStringExtra("id_cuidades");
                    pais_actual = recupero_idBanner;

                    Intent i = new Intent(Opciones.this, DatosGenerales.class);
                    i.putExtra("id_cuidades",pais_actual);
                    startActivity(i);

                } else if (idcategoria_clikeada.equals(getString(R.string.text_compras))) {

                    Intent recupero_id = getIntent();
                    String recupero_idBanner = recupero_id.getStringExtra("id_cuidades");
                    pais_actual = recupero_idBanner;

                    Toast.makeText(Opciones.this, "You Clicked at " + miCategoria.get(position).getCategoria(), Toast.LENGTH_SHORT).show();


                } else {

                    Intent recupero_id = getIntent();
                    String recupero_idBanner = recupero_id.getStringExtra("id_cuidades");
                    pais_actual = recupero_idBanner;

                    Intent i = new Intent(Opciones.this, subdiviciones_opciones.class);
                    i.putExtra("idcategoria_clikeada", idcategoria_clikeada);
                    i.putExtra("id_cuidades",pais_actual);
                    startActivity(i);
                }


            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog=null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);// Constructor para el AlertDialog de los Tips
        switch (id){
            case ALERT_DIALOG:
                builder.setMessage("Hola").setPositiveButton("Ok", new DialogInterface.OnClickListener(){ //Se establece el mensage y el label del boton para el AlertDialog
                public void onClick(DialogInterface dialog, int id) {

                }

            })
                        .setCancelable(false);
            dialog=builder.create(); //Ejecuta el constructor del AlertDialog de los tips para crearlo
                break;
            default:

        }
        return dialog;
    }


    /* public AdapterView.OnItemClickListener onListClick=new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
            Intent intent = new Intent(getApplicationContext(), Detalles.class);
            intent.putExtra(ID_EXTRA, String.valueOf(pos));
            startActivity(intent);

        }

    };*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//h

        // aqui se ponen las funciones de los botones del action bar
      switch(item.getItemId()){
          case R.id.menu_Home:

              Intent i = new Intent(Opciones.this, Destinos.class);
              startActivity(i);
              return true;

          case R.id.menu_Calculator:
              startActivity(new Intent(Opciones.this, CalculadoraPopUp.class));
              return true;


          default:
              break;
      }
        return true;
    }

    private void populateListCategoria(){
        miCategoria.add(new clase_categorias(getString(R.string.title_activity_datosGenerales),R.drawable.icon_info));
        miCategoria.add(new clase_categorias(getString(R.string.text_transporte),R.drawable.icon_maleta));
        miCategoria.add(new clase_categorias(getString(R.string.text_hospedaje),R.drawable.icon_hospedaje));
        miCategoria.add(new clase_categorias(getString(R.string.text_gastronomia),R.drawable.icon_gastronomia));
        miCategoria.add(new clase_categorias(getString(R.string.text_entretenimiento),R.drawable.icon_entretenimiento));
        miCategoria.add(new clase_categorias(getString(R.string.text_lugarInteres),R.drawable.icon_interes));
        miCategoria.add(new clase_categorias(getString(R.string.text_compras),R.drawable.icon_compras));
    }

    private void populateListView() {
        ArrayAdapter<clase_categorias> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView_opciones);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<clase_categorias> {
        public MyListAdapter() {
            super(Opciones.this, R.layout.list_view_template, miCategoria);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.list_view_template, parent, false);
            }

            //Obtener la posicion de la categoria desde la clase clase_categoria
            clase_categorias posicionCategoria = miCategoria.get(position);

            //Fill the view, foto de la categoria
            ImageView imageVi = (ImageView) itemView.findViewById(R.id.imageView_iconCategoria);
            imageVi.setImageResource(posicionCategoria.getId_imagen());

            //Nombre del categoria
            TextView nombreText = (TextView) itemView.findViewById(R.id.textView_nombreCategoria);
            nombreText.setText(posicionCategoria.getCategoria());

            return itemView;
        }
    }

    public void inserto_banner(){

        Intent recupero_id = getIntent();
        String recupero_idBanner = recupero_id.getStringExtra("id_cuidades");
        pais_actual = recupero_idBanner;

        if (recupero_idBanner.equals("paris")){
            imageView_banner = (ImageView)findViewById(R.id.imageView_banner);
            imageView_banner.setImageResource(R.drawable.banner_paris);
        }

        if (recupero_idBanner.equals("venecia")){
            imageView_banner = (ImageView)findViewById(R.id.imageView_banner);
            imageView_banner.setImageResource(R.drawable.banner_venecia);
        }

        if (recupero_idBanner.equals("madrid")){
            imageView_banner = (ImageView)findViewById(R.id.imageView_banner);
            imageView_banner.setImageResource(R.drawable.banner_madrid);
        }

        if (recupero_idBanner.equals("roma")){
            imageView_banner = (ImageView)findViewById(R.id.imageView_banner);
            imageView_banner.setImageResource(R.drawable.banner_roma);
        }

        if (recupero_idBanner.equals("berlin")){
            imageView_banner = (ImageView)findViewById(R.id.imageView_banner);
            imageView_banner.setImageResource(R.drawable.banner_berlin);
        }

        if (recupero_idBanner.equals("londres")){
            imageView_banner = (ImageView)findViewById(R.id.imageView_banner);
            imageView_banner.setImageResource(R.drawable.banner_londres);
        }


    }
}
