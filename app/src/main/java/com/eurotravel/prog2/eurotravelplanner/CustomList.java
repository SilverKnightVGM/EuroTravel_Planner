package com.eurotravel.prog2.eurotravelplanner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] texto_subdivisiones;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] texto_subdivisiones, Integer[] imageId) {
        super(context, R.layout.list_view_template, texto_subdivisiones);
        this.context = context;
        this.texto_subdivisiones = texto_subdivisiones;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_view_template, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView_nombreCategoria);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView_iconCategoria);
        txtTitle.setText(texto_subdivisiones[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
