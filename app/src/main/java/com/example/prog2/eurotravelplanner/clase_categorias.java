package com.example.prog2.eurotravelplanner;

/**
 * Created by HellenFranchesca on 30/06/2015.
 */
public class clase_categorias {

    int id;
    String categoria;
    int id_imagen;

    public clase_categorias(int id, String categoria, int id_imagen) {
        this.id = id;
        this.categoria = categoria;
        this.id_imagen = id_imagen;
    }

    public clase_categorias(String categoria, int id_imagen) {
        this.categoria = categoria;
        this.id_imagen = id_imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId_imagen() {
        return id_imagen;
    }

    @Override
    public String toString() {
        return "clase_categorias{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", id_imagen=" + id_imagen +
                '}';
    }

    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }
}
