package com.example.aimew.recyclerview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aimew.recyclerview.models.Libro;

import java.util.List;

/**
 * Created by aimew on 21/09/2016.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.AdapterViewHolder> {

    private List<Libro> bookList;
    private Context context;
    private RecyclerItemClickListener listener;

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        AdapterViewHolder avh = new AdapterViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(final AdapterViewHolder holder, final int position) {
        holder.nombre.setText(bookList.get(position).getNombre());
        holder.autor.setText(bookList.get(position).getAutor());
        holder.imagen.setImageResource(bookList.get(position).getImagen());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerItemClick(bookList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    //constructor
    public Adaptador(List<Libro> listBook, Context context) {
        this.bookList = listBook;
        this.listener = (RecyclerItemClickListener) context;
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nombre;
        TextView autor;
        ImageView imagen;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.text_nombreLibro);
            autor = (TextView)itemView.findViewById(R.id.text_autor);
            imagen = (ImageView)itemView.findViewById(R.id.image_libro);
            cardView = (CardView)itemView.findViewById(R.id.cardview_item);
        }
    }

    public interface RecyclerItemClickListener {
        public void onRecyclerItemClick(Libro libro);
    }

    //metodo para eliminar un item de la lista
    public void removeItem(int position) {
        bookList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, bookList.size());
    }
}
