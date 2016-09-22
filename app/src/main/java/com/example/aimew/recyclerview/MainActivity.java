package com.example.aimew.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aimew.recyclerview.models.Libro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Libro> listaLibros;
    private LinearLayoutManager llmVertical;
    private LinearLayoutManager llmHorizontal;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciacion
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        listaLibros = new ArrayList<Libro>();
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));
        listaLibros.add(new Libro("Juego de tronos", "Jose Manuel", R.drawable.libro_multiverso));

        //LinearLayoutManager
        llmVertical = new LinearLayoutManager(this);
        llmHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);

        //GridLayoutManager
        GridLayoutManager glm = new GridLayoutManager(this, 3);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (3 - position % 3);
            }
        });

        //decoracion
        int spanCount = 3; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = true;
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        //decoracion
        recyclerView.addItemDecoration(new SpacesItemDecoration(2));


        recyclerView.setLayoutManager(llmVertical);
        adaptador = new Adaptador(listaLibros);
        recyclerView.setAdapter(adaptador);

    }
}
