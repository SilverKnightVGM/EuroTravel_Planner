package com.eurotravel.prog2.eurotravelplanner;

/**
 * Created by user on 7/11/2015.
 */
public class Objeto_Datos {

    String NomCiudad,InfoGeneral,SegNombre,Lema,Clima,Moneda,Demografia;

    public String getNomCiudad() {
        return NomCiudad;
    }

    public void setNomCiudad(String nomCiudad) {
        NomCiudad = nomCiudad;
    }

    public String getInfoGeneral() {
        return InfoGeneral;
    }

    public void setInfoGeneral(String infoGeneral) {
        InfoGeneral = infoGeneral;
    }

    public String getSegNombre() {
        return SegNombre;
    }

    public void setSegNombre(String segNombre) {
        SegNombre = segNombre;
    }

    public String getLema() {
        return Lema;
    }

    public void setLema(String lema) {
        Lema = lema;
    }

    public String getClima() {
        return Clima;
    }

    public void setClima(String clima) {
        Clima = clima;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String moneda) {
        Moneda = moneda;
    }

    public String getDemografia() {
        return Demografia;
    }

    public void setDemografia(String demografia) {
        Demografia = demografia;
    }

    public Objeto_Datos() {
    }

    public Objeto_Datos(String infoGeneral) {
        InfoGeneral = infoGeneral;
    }
}
