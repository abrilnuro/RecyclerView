package com.example.aimew.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LibroDetalleActivity extends AppCompatActivity {

    private TextView textNombre;
    private TextView textAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_detalle);

        //referenciacion
        textNombre = (TextView)findViewById(R.id.text_nombreLibro3);
        textAutor = (TextView)findViewById(R.id.text_autorLibro3);

        //cachar el intent
        String nombre = getIntent().getStringExtra("nombre");
        String autor = getIntent().getStringExtra("autor");

        //mostrar los datos en el textView
        textNombre.setText(nombre);
        textAutor.setText(autor);



    }
}
