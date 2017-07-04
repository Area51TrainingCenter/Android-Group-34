package com.jcodee.mod1class5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod1class5.modelos.Noticia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spSeccion;
    private EditText etTitulo, etContenido;
    private Button btnRegistrar, btnListar;

    public static ArrayList<Noticia> listaNoticia = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spSeccion = (Spinner) findViewById(R.id.spSeccion);
        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etContenido = (EditText) findViewById(R.id.etContenido);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnListar = (Button) findViewById(R.id.btnListar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String seccion = spSeccion.getSelectedItem().toString();
                String titulo = etTitulo.getText().toString();
                String contenido = etContenido.getText().toString();

                if (titulo.isEmpty()) {
                    etTitulo.setError("El campo es requerido");
                    return;
                }
                if (contenido.isEmpty()) {
                    etContenido.setError("El campo es requerido");
                    return;
                }

                //Obtenemos todos los datos de los componentes y lo agregamos a nuestra variable
                //de noticia
                Noticia noticia = new Noticia();
                noticia.setId(listaNoticia.size() + 1);
                noticia.setTitulo(titulo);
                noticia.setContenido(contenido);
                noticia.setSeccion(seccion);

                //Agregamos la noticia a nuestra lista
                listaNoticia.add(noticia);

                //Limpiar campos
                etTitulo.setText("");
                etContenido.setText("");
                //Hacemos que e cursor vaya a la caja de titulo
                etTitulo.requestFocus();

                Toast.makeText(MainActivity.this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                startActivity(intent);

            }
        });
    }
}
