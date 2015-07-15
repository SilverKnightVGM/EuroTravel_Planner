package com.example.prog2.eurotravelplanner;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by HellenFranchesca on 14/07/2015.
 */
public class CalculadoraPopUp extends Activity{
   Spinner monedas1,monedas2;
   ArrayAdapter<CharSequence> adapter1;
   ArrayAdapter<CharSequence> adapter2;
    String mnd1,mnd2;
    ImageButton calcular;
   TextView total;
   @Override
    protected void onCreate(Bundle savedInstanceState) {


       total=(TextView) findViewById(R.id.Total);

       calcular=(ImageButton) findViewById(R.id.calcularButton);


       super.onCreate(savedInstanceState);
       setContentView(R.layout.calculadora);

       DisplayMetrics dm=new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dm);

       int width=dm.widthPixels;
       int height=dm.heightPixels;
       getWindow().setLayout((int)(width*.8), (int)(height*.6));


      monedas1=(Spinner) findViewById(R.id.monedas1);
      monedas2=(Spinner) findViewById(R.id.monedas2);
      adapter1=ArrayAdapter.createFromResource(this, R.array.monedas1, android.R.layout.simple_spinner_item);
       adapter2=ArrayAdapter.createFromResource(this, R.array.monedas2, android.R.layout.simple_spinner_item);
       adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

               mnd2= (String) parent.getItemAtPosition(position);
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent){

           }


       });

      /*calcular.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (mnd1.equals("Peso Dominicano(DOP)")) {
                   switch (mnd2) {
                       case "Euro(EUR)":
                           total.setText("Euro");
                       case "Libra Esterlina(GBP)":
                           total.setText("Libra Esterlina");

                   }

               }

           }
       });*/


   }

   }


