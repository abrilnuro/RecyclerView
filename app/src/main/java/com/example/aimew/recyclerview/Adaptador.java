package com.example.aimew.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aimew.recyclerview.models.Libro;

import java.util.List;

/**
 * Created by aimew on 21/09/2016.
 */

public class Adaptador extends RecyclerView.Adapter<AdapterViewHolder>{

    private List<Libro> listBook;

    //constructor
    public Adaptador(List<Libro> listBook) {
        this.listBook = listBook;
    }


    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        AdapterViewHolder avh = new AdapterViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        holder.nombre.setText(listBook.get(position).getNombre());
        holder.autor.setText(listBook.get(position).getAutor());
        holder.imagen.setImageResource(listBook.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }


}
