package com.example.prog2.eurotravelplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HellenFranchesca on 22/07/2015.
 */
public class DataProvider {
    public static HashMap<String, List<String>> getInfo(){
        HashMap <String, List<String>> LugaresDetalles = new HashMap<String, List<String>>();
        List<String> Primer_Lugar = new ArrayList<String>();
        Primer_Lugar.add("Direccion 1");
        Primer_Lugar.add("Telefono 1");
        Primer_Lugar.add("Horario 1");
        List<String> Segundo_Lugar = new ArrayList<String>();
        Segundo_Lugar.add("Direccion 2");
        Segundo_Lugar.add("Telefono 2");
        Segundo_Lugar.add("Horario 2");
        List<String> Tercer_Lugar = new ArrayList<String>();
        Tercer_Lugar.add("Direccion 3");
        Tercer_Lugar.add("Telefono 3");
        Tercer_Lugar.add("Horario 3");

        LugaresDetalles.put("Primer_Lugar", Primer_Lugar);
        LugaresDetalles.put("Segundo_Lugar", Segundo_Lugar);
        LugaresDetalles.put("Tercer_Lugar", Tercer_Lugar);

        return LugaresDetalles;
    }
}
