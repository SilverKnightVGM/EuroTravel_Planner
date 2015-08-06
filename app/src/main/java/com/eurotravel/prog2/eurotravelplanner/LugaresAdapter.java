package com.eurotravel.prog2.eurotravelplanner;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HellenFranchesca on 22/07/2015.
 */
public class LugaresAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private String subdivision;
    private String categoria;
    private HashMap<String, List<String>> lista_lugares;
    private List<String> lugares_detalles;
    private String gastronomia;
    private String lugares_interes;
    private String comida_tipicas;

    public LugaresAdapter(Context ctx, HashMap<String, List<String>> lista_lugares, List<String> lugares_detalles,
                          String categoria, String subdivision, String gastronomia, String lugares_interes, String comida_tipicas) {
        this.ctx = ctx;
        this.lista_lugares = lista_lugares;
        this.lugares_detalles = lugares_detalles;
        this.categoria = categoria;
        this.subdivision = subdivision;
        this.gastronomia = gastronomia;
        this.lugares_interes = lugares_interes;
        this.comida_tipicas = comida_tipicas;
    }


    @Override
    public int getGroupCount() {
        return lugares_detalles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return lista_lugares.get(lugares_detalles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lugares_detalles.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {
        return lista_lugares.get(lugares_detalles.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String group_title = (String) getGroup(parent);
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.parent_template_listview_expandible, parentView, false);
        }
        TextView parent_textview = (TextView) convertView.findViewById(R.id.parentText);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild,
                             View convertView, ViewGroup parentview) {


        String child_title = (String) getChild(parent, child);

        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.template_listview_expandible, parentview, false);

        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView2);
        textView.setText(child_title);
        TextView textViewTitulo = (TextView) convertView.findViewById(R.id.textViewTitulo);

        if (categoria.equals("Transporte")) {
            if (subdivision.equals("Renta de Autos")) {
                switch (child) {
                    case 0:
                        textViewTitulo.setText("Ubicación");
                        break;
                    case 1:
                        textViewTitulo.setText("Descripción");
                        break;
                    case 2:
                        textViewTitulo.setText("Link");
                        break;
                }
            } else if (subdivision.equals("Taxi")) {
                switch (child) {
                    case 0:
                        textViewTitulo.setText("Descripción");
                        break;
                    case 1:
                        textViewTitulo.setText("Teléfono");
                        break;

                }
            } else {
                switch (child) {
                    case 0:
                        textViewTitulo.setText("Descripción");
                        break;
                }
            }

            } else if (categoria.equals(gastronomia)) {


                if (subdivision.equals(comida_tipicas)) {
                    switch (child) {
                        case 0:
                            textViewTitulo.setText("Descripción");
                            break;

                    }
                } else {
                    switch (child) {
                        case 0:
                            textViewTitulo.setText("Dirección");
                            break;
                        case 1:
                            textViewTitulo.setText("Teléfono");
                            break;
                        case 2:
                            textViewTitulo.setText("Horario");
                            break;
                    }
                }
            } else {

                switch (child) {
                    case 0:
                        textViewTitulo.setText("Dirección");
                        break;
                    case 1:
                        textViewTitulo.setText("Teléfono");
                        break;
                    case 2:
                        textViewTitulo.setText("Rating");
                        break;


                }

            }


        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}