package com.example.prog2.eurotravelplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Destinos extends ActionBarActivity {

    private final static int ALERT_DIALOG=1;


    //final Button convertirMoneda=(Button)findViewById(R.id.btnCalc);

    Button btn_paris,btn_venecia,btn_madrid,btn_roma,btn_berlin,btn_londres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinos);

        btn_paris=(Button)findViewById(R.id.btn_paris);
        btn_venecia=(Button)findViewById(R.id.btn_venecia);
        btn_madrid=(Button)findViewById(R.id.btn_madrid);
        btn_roma=(Button)findViewById(R.id.btn_roma);
        btn_berlin=(Button)findViewById(R.id.btn_berlin);
        btn_londres=(Button)findViewById(R.id.btn_londres);
        /*calculadora=(Button) findViewById(R.id.btnCalc);
        calculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Destinos.this, CalculadoraPopUp.class));
            }


        });*/


        btn_paris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // en este intent abra el activity donde esta la lista de opciones, gracias

                Intent intent_ListaOpciones = new Intent(Destinos.this,Opciones.class);
                intent_ListaOpciones.putExtra("id_cuidades","paris");
                // aqui le mando un intent con el id de la cuidad para con ese id insertar el banner en el activity lista de opciones
                startActivity(intent_ListaOpciones);
            }
        });

        btn_venecia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // en este intent abra el activity donde esta la lista de opciones, gracias
                Intent intent_ListaOpciones = new Intent(Destinos.this,Opciones.class);
                intent_ListaOpciones.putExtra("id_cuidades","venecia");
                // aqui le mando un intent con el id de la cuidad para con ese id insertar el banner en el activity lista de opciones
                startActivity(intent_ListaOpciones);
            }
        });

        btn_madrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // en este intent abra el activity donde esta la lista de opciones, gracias
                Intent intent_ListaOpciones = new Intent(Destinos.this,Opciones.class);
                intent_ListaOpciones.putExtra("id_cuidades","madrid");
                // aqui le mando un intent con el id de la cuidad para con ese id insertar el banner en el activity lista de opciones
                startActivity(intent_ListaOpciones);
            }
        });

        btn_roma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // en este intent abra el activity donde esta la lista de opciones, gracias
                Intent intent_ListaOpciones = new Intent(Destinos.this,Opciones.class);
                intent_ListaOpciones.putExtra("id_cuidades","roma");
                // aqui le mando un intent con el id de la cuidad para con ese id insertar el banner en el activity lista de opciones
                startActivity(intent_ListaOpciones);
            }
        });

        btn_berlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // en este intent abra el activity donde esta la lista de opciones, gracias
                Intent intent_ListaOpciones = new Intent(Destinos.this,Opciones.class);
                intent_ListaOpciones.putExtra("id_cuidades","berlin");
                // aqui le mando un intent con el id de la cuidad para con ese id insertar el banner en el activity lista de opciones
                startActivity(intent_ListaOpciones);
            }
        });

        btn_londres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // en este intent abra el activity donde esta la lista de opciones, gracias
                Intent intent_ListaOpciones = new Intent(Destinos.this,Opciones.class);
                intent_ListaOpciones.putExtra("id_cuidades","londres");
                // aqui le mando un intent con el id de la cuidad para con ese id insertar el banner en el activity lista de opciones
                startActivity(intent_ListaOpciones);
            }
        });


//        convertirMoneda.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }


    /*public class CurrentMoneyDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            convertirMoneda.setMessage(R.string.dialog_fire_missiles)
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_destinos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.menu_Calculator:
                startActivity(new Intent(Destinos.this, CalculadoraPopUp.class));
               return true;

            }

        return super.onOptionsItemSelected(item);
    }

}
