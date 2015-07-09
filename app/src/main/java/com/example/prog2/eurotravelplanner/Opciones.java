package com.example.prog2.eurotravelplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Opciones extends ActionBarActivity {

    ListView categorias;
    Button tips;
    private final static int ALERT_DIALOG=1;
    private List<clase_categorias> miCategoria = new ArrayList<clase_categorias>();
    List<String> template = new ArrayList<String>();
    ImageView imageView_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        categorias = (ListView) findViewById(R.id.listView_opciones);
        tips = (Button) findViewById(R.id.buttonTips);
        tips.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                 Opciones.this.showDialog(ALERT_DIALOG);//Llama al metodo que crea el AlertDialog para los Tips
            }
        });

        populateListCategoria();
        populateListView();
        inserto_banner();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateListCategoria(){
        miCategoria.add(new clase_categorias("Datos Generales",R.drawable.icon_info));
        miCategoria.add(new clase_categorias("Transporte",R.drawable.icon_maleta));
        miCategoria.add(new clase_categorias("Hospedaje",R.drawable.icon_hospedaje));
        miCategoria.add(new clase_categorias("Gastronomía",R.drawable.icon_gastronomia));
        miCategoria.add(new clase_categorias("Entretenimiento",R.drawable.icon_entretenimiento));
        miCategoria.add(new clase_categorias("Lugares de interés",R.drawable.icon_interes));
        miCategoria.add(new clase_categorias("Compras",R.drawable.icon_compras));
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

        if (recupero_idBanner.equals("paris")){
            imageView_banner = (ImageView)findViewById(R.id.imageView_banner);
            imageView_banner.setImageResource(R.drawable.banner_paris);
        }

        //Log.i("Debug 1",""+recupero_idBanner);

    }
}
