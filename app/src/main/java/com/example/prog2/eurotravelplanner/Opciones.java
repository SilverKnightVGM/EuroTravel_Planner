package com.example.prog2.eurotravelplanner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Opciones extends ActionBarActivity {

    ListView categorias;
    private List<clase_categorias> miCategoria = new ArrayList<clase_categorias>();
    List<String> template = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        categorias = (ListView) findViewById(R.id.listView_opciones);

        populateListCategoria();
        populateListView();
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
        miCategoria.add(new clase_categorias("Gastronom�a",R.drawable.icon_gastronomia));
        miCategoria.add(new clase_categorias("Entretenimiento",R.drawable.icon_entretenimiento));
        miCategoria.add(new clase_categorias("Lugares de inter�s",R.drawable.icon_interes));
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
}
