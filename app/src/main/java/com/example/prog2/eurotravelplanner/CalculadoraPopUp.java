package com.example.prog2.eurotravelplanner;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CalculadoraPopUp extends Activity {

        Spinner monedas1, monedas2, cambio;
    Double monto2;
        ArrayAdapter<CharSequence> adapter1;
        ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;
        String mnd1, mnd2, cmb;//Almacena las monedas escogidas por el usuario
        Button calcular;
        TextView total;
        EditText monto4;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calculadora);


            total = (TextView) findViewById(R.id.Total);
            monto4=(EditText) findViewById(R.id.monto);

            calcular = (Button) findViewById(R.id.calcularbutton);
            calcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CambioMoneda cambioMoneda=new CambioMoneda();
                    monto2= Double.parseDouble(monto4.getText().toString());
                    total.setText(cambioMoneda.cambioM(monto2,mnd1, mnd2, cmb));


                }
            });

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;
            int height = dm.heightPixels;
            getWindow().setLayout((int) (width * .8), (int) (height * .6));


            monedas1 = (Spinner) findViewById(R.id.monedas1);
            monedas2 = (Spinner) findViewById(R.id.monedas2);
            cambio= (Spinner) findViewById(R.id.cambios);
            adapter1 = ArrayAdapter.createFromResource(this, R.array.monedas1, android.R.layout.simple_spinner_item);
            adapter2 = ArrayAdapter.createFromResource(this, R.array.monedas2, android.R.layout.simple_spinner_item);
            adapter3=ArrayAdapter.createFromResource(this, R.array.cambios, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cambio.setAdapter(adapter3);

                monedas1.setAdapter(adapter1);
                monedas2.setAdapter(adapter2);



            monedas1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    mnd1 = (String) parent.getItemAtPosition(position);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });
            monedas2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    mnd2 = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }


            });
            cambio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    cmb = (String) parent.getItemAtPosition(position);

                    if(cmb.equals("Comprar")) {
                        monedas1.setAdapter(adapter1);
                        monedas2.setAdapter(adapter2);
                    }
                    if(cmb.equals("Vender")) {
                        monedas2.setAdapter(adapter1);
                        monedas1.setAdapter(adapter2);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });


        }

    }


