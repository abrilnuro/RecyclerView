package com.example.aimew.recyclerview;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aimew.recyclerview.models.Libro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adaptador.RecyclerItemClickListener, View.OnClickListener{

    private RecyclerView recyclerView;
    private List<Libro> bookList = new ArrayList<Libro>();
    private LinearLayoutManager llmVertical;
    private LinearLayoutManager llmHorizontal;
    private Adaptador adapter;
    private FloatingActionButton fabAddItem;

    //elementos del dialog
    private Dialog dialog;
    private EditText editDialogTitle;
    private EditText editDialogAutor;
    private TextView textDialogCancel;
    private TextView textDialogAdd;

    //elementos de la funcion eliminar item
    private int edit_position;
    private View view;
    private boolean add = false;
    private Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //referenciacion
        fabAddItem = (FloatingActionButton)findViewById(R.id.fab_add_item);
        //asignar listeners
        fabAddItem.setOnClickListener(this);

        initRecyclerView();

    }

    @Override
    public void onRecyclerItemClick(Libro libro) {
        Toast.makeText(this, libro.getAutor(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_add_item:
                addBook();
                break;
            case R.id.text_dialog_add:
                //obtener los datos del libro
                String title = editDialogTitle.getText().toString();
                String autor = editDialogAutor.getText().toString();
                Libro libro = new Libro(title, autor, android.R.drawable.ic_menu_camera);

                //agregar el libro en la lista
                bookList.add(libro);

                //esconder el dialog
                dialog.dismiss();
                break;
            case R.id.text_dialog_cancel:
                dialog.dismiss();
                break;
        }
    }

    //metodo para agregar un elemento a la lista
    public void addBook(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_item);

        //referenciar elementos
        editDialogTitle = (EditText)dialog.findViewById(R.id.edit_dialog_title);
        editDialogAutor = (EditText)dialog.findViewById(R.id.edit_dialog_autor);
        textDialogCancel = (TextView)dialog.findViewById(R.id.text_dialog_cancel);
        textDialogAdd = (TextView) dialog.findViewById(R.id.text_dialog_add);

        //asignar escuchadores
        textDialogAdd.setOnClickListener(this);
        textDialogCancel.setOnClickListener(this);

        dialog.show();
    }

    //metodo que configura la recyclerView
    public void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        llmVertical = new LinearLayoutManager(this);
        llmHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(llmVertical);
        adapter = new Adaptador(bookList, this);
        recyclerView.setAdapter(adapter);

        //llenar lista
        bookList.add(new Libro("Multiverso", "Jose Manuel", R.drawable.libro_multiverso));
        adapter.notifyDataSetChanged();
        initSwipe();
    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    adapter.removeItem(position);
                } else {
                    /*removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit Country");
                    et_country.setText(countries.get(position));
                    alertDialog.show();*/
                   Toast.makeText(getBaseContext(), "moviste", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#00b0ff"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add); //editar
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#d32f2f"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_delete); //eliminar
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
