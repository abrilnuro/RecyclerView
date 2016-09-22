package com.example.aimew.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by aimew on 22/09/2016.
 */

public class AdapterViewHolder extends RecyclerView.ViewHolder {

    TextView nombre;
    TextView autor;
    ImageView imagen;


    public AdapterViewHolder(View itemView) {
        super(itemView);

        nombre = (TextView)itemView.findViewById(R.id.text_nombreLibro);
        autor = (TextView)itemView.findViewById(R.id.text_autor);
        imagen = (ImageView)itemView.findViewById(R.id.image_libro);
    }
}
