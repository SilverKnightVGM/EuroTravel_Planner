package com.example.prog2.eurotravelplanner;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class gallery extends ActionBarActivity {

    ListView listView_prueba;

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

        final Gallery gallery = (Gallery)findViewById(R.id.gallery);
        // create adapter gallery
        gallery.setAdapter(new ImageAdapter(this));





    }

    // este metodo es para insertar el listview de prueba

  /*  private void inserto_List(){

        CustomList adapter = new CustomList(gallery.this, Texto_Lista, Images_Lista);

        listView_prueba =(ListView) findViewById(R.id.listViewExpadible);

    listView_prueba.setAdapter(adapter);


}
*/

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
        int imagebackground;

        public ImageAdapter(Context context){
            this.context=context;
        }

        @Override
        public int getCount() {
            return pics.length;
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
            imageView.setImageResource(pics[position]);
            return imageView;
        }
    }


}

