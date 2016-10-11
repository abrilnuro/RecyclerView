package com.example.aimew.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

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


        //referenciacion
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //hacer clickable la recyclerView
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //obtener los datos del libro de la posicion que se seleccionó de la recyclerView
                        String nombreLibro = listaLibros.get(position).getNombre();
                        String autorLibro = listaLibros.get(position).getAutor();

                        //mandar los datos obtenidos a la siguiente Activity
                        Intent intent = new Intent(MainActivity.this, LibroDetalleActivity.class);
                        intent.putExtra("nombre", nombreLibro);
                        intent.putExtra("autor", autorLibro);
                        startActivity(intent);
                    }
                })
        );

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
