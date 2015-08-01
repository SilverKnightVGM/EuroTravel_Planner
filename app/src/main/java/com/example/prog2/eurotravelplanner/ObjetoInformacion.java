package com.example.prog2.eurotravelplanner;

/**
 * Created by Bolívar on 7/31/2015.
 */
public class ObjetoInformacion {
    String ciudad,categoria,subCat,nombre,dato1,dato2,dato3;

    public ObjetoInformacion(String ciudad, String categoria, String subCat, String nombre, String dato1) {
        this.ciudad = ciudad;
        this.categoria = categoria;
        this.subCat = subCat;
        this.nombre = nombre;
        this.dato1 = dato1;
    }

    public ObjetoInformacion(String ciudad, String categoria, String subCat, String nombre, String dato1, String dato2, String dato3) {
        this.ciudad = ciudad;
        this.categoria = categoria;
        this.subCat = subCat;
        this.nombre = nombre;
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.dato3 = dato3;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubCat() {
        return subCat;
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    public String getDato3() {
        return dato3;
    }

    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }
}
