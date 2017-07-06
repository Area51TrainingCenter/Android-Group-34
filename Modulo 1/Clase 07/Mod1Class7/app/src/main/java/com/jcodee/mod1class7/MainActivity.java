package com.jcodee.mod1class7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jcodee.mod1class7.adapters.ImagenAdapter;
import com.jcodee.mod1class7.modelos.Imagen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gvDatos;
    public static ArrayList<Imagen> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvDatos = (GridView) findViewById(R.id.gvDatos);

        lista = new ArrayList<>();

        Imagen imagen = new Imagen();
        imagen.setId(lista.size());
        imagen.setRuta("http://imagenesdeamorlindas.com/wp-content/uploads/2013/10/imagenes-lindas.jpg");
        imagen.setTexto("Imagen 1");
        lista.add(imagen);

        Imagen imagen1 = new Imagen();
        imagen1.setId(lista.size());
        imagen1.setRuta("http://blogs.universal.org/bispomacedo/es/wp-content/uploads/sites/3/2017/04/2670bp-640x640.jpg");
        imagen1.setTexto("Imagen 2");
        lista.add(imagen1);

        ImagenAdapter adapter = new ImagenAdapter(MainActivity.this, lista);
        gvDatos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("ruta", lista.get(i).getRuta());
                intent.putExtra("texto", lista.get(i).getTexto());
                startActivity(intent);

            }
        });
    }
}
