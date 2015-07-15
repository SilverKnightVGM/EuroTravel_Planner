package com.example.prog2.eurotravelplanner;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DatosGenerales extends ActionBarActivity {

    ListView lista;
    DbHelper helper = new DbHelper(this);
    List<String> item = null;
    private List<Constructor_Datos> DatosGList = new ArrayList<Constructor_Datos>();
    int conteoPos=0;

    DbHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_generales);

        lista = (ListView) findViewById(R.id.LV_DatosGenerales);
        showDato();
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

    public void showDato() {
        item = new ArrayList<String>();
        String text;
        int contPos = 0;
        int bucle = 0;
        // mostramos datos en la list view
        if (Opciones.pais_actual.equals("paris"))
        {
            bucle = 7;
        helper.where = helper.ID_ciudad +" = 'paris'";
        }
        if (Opciones.pais_actual.equals("madrid"))
        {
            bucle = 7;
            helper.where = helper.ID_ciudad +" = 'madrid'";
        }
        if (Opciones.pais_actual.equals("roma"))
        {
            bucle = 7;
            helper.where = helper.ID_ciudad +" = 'roma'";
        }
        if (Opciones.pais_actual.equals("londres"))
        {   bucle = 6;
            helper.where = helper.ID_ciudad +" = 'londres'";
        }
        if (Opciones.pais_actual.equals("venecia"))
        {   bucle = 6;
            helper.where = helper.ID_ciudad +" = 'venecia'";
        }
        if (Opciones.pais_actual.equals("berlin"))
        {   bucle = 5;
            helper.where = helper.ID_ciudad +" = 'berlin'";
        }

        Cursor cl = helper.getDatos();

        if (cl.moveToFirst()) do {
            if (contPos == 0 || contPos == 1) {
            } else {
                text = cl.getString(contPos);

                if (text != null && !text.toString().isEmpty()) {
                    DatosGList.add(new Constructor_Datos(text));
                }
                else{bucle++;}

            }
            contPos++;
        } while (contPos <= bucle);

        ArrayAdapter<Constructor_Datos> adapter = new MyListAdapter();
        lista.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Constructor_Datos> {
        public MyListAdapter() {
            super(DatosGenerales.this, R.layout.item_view, DatosGList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // aseguramos de tener un view con que trabajar
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            //Dato que estamos utilizando
            Constructor_Datos DatoActual = DatosGList.get(position);

            //dato
            TextView DatoText = (TextView) itemView.findViewById(R.id.tv_dato);
            DatoText.setText(DatoActual.getInfoGeneral());
            //titulo
            TextView TituloText = (TextView) itemView.findViewById(R.id.tv_titulo);

            //verifico segun pais los datos generales que tendra
            if (Opciones.pais_actual.equals("paris") || Opciones.pais_actual.equals("madrid") || Opciones.pais_actual.equals("roma")){
                if (position == 0) {
                    TituloText.setText("Info General");
                }
            if (position == 1) {
                TituloText.setText("Otros nombres");
            }
            if (position == 2) {
                TituloText.setText("Lema");
            }
            if (position == 3) {
                TituloText.setText("Clima");
            }
            if (position == 4) {
                TituloText.setText("Moneda");
            }
            if (position == 5) {
                TituloText.setText("Demografia");
            }
        }


            if (Opciones.pais_actual.equals("londres")){
                if (position == 0) {
                    TituloText.setText("Info General");
                }
                if (position == 1) {
                    TituloText.setText("Lema");
                }
                if (position == 2) {
                    TituloText.setText("Clima");
                }
                if (position == 3) {
                    TituloText.setText("Moneda");
                }
                if (position == 4) {
                    TituloText.setText("Demografia");
                }
            }


            if (Opciones.pais_actual.equals("venecia")){
                if (position == 0) {
                    TituloText.setText("Info General");
                }
                if (position == 1) {
                    TituloText.setText("Otros nombres");
                }
                if (position == 2) {
                    TituloText.setText("Clima");
                }
                if (position == 3) {
                    TituloText.setText("Moneda");
                }
                if (position == 4) {
                    TituloText.setText("Demografia");
                }
            }

            if (Opciones.pais_actual.equals("berlin")){
                if (position == 0) {
                    TituloText.setText("Info General");
                }
                if (position == 1) {
                    TituloText.setText("Clima");
                }
                if (position == 2) {
                    TituloText.setText("Moneda");
                }
                if (position == 3) {
                    TituloText.setText("Demografia");
                }
            }

            return  itemView;
        }
    }
}