package com.example.prog2.eurotravelplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HellenFranchesca on 22/07/2015.
 */
public class DataProvider {



    public static HashMap<String, List<String>> getInfo(String ciudad, String categoria, String subdivision){

        HashMap <String, List<String>> LugaresDetalles = new HashMap<String, List<String>>();

       // int count = 0;// Cuenta la cantidad de registros que tienen la misma ciudad, categoria subdivision
        //int i=0;


            List<String> Lugar = new ArrayList<String>();

            Lugar.add("Direccion 1");

            Lugar.add("Telefono 1");

            Lugar.add("Horario 1");

            LugaresDetalles.put("Primer_Lugar", Lugar);//El primer string debe de ser el nombre del lugar


        return LugaresDetalles;
    }


}
