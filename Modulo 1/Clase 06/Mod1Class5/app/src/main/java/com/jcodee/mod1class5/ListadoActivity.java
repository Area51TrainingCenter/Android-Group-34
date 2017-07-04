package com.jcodee.mod1class5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod1class5.adapters.NoticiaAdapter;
import com.jcodee.mod1class5.modelos.Noticia;

/**
 * Created by johannfjs on 28/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class ListadoActivity extends AppCompatActivity {
    private ListView lvLista;
    private NoticiaAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listado);

        lvLista = (ListView) findViewById(R.id.lvLista);

        adapter =
                new NoticiaAdapter(ListadoActivity.this, MainActivity.listaNoticia);
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Creamos el evento onClick del listado para cada elemento
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //Obtenemos la noticia que se ha seleccionado
                final Noticia noticia = MainActivity.listaNoticia.get(position);

                //Creamos un dialogo de android
                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoActivity.this);
                //Cambiando el titulo a mostrar
                builder.setTitle("Mensaje");
                //Cambiamos el mensaje a mostrar
                builder.setMessage("Acción a realizar");
                //Habilitamos opciones, positiva y negativa
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editar(noticia, position);
                        Toast.makeText(ListadoActivity.this, "Editar", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Eliminamos la noticia seleccionada
                        MainActivity.listaNoticia.remove(position);
                        //Actualizar la lista con el cambio realizado
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ListadoActivity.this, "Eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
                //Mostramos opciones, si no se pone está linea nunca se mostrará el dialogo
                builder.show();

            }
        });
    }

    private void editar(Noticia noticia, final int position) {
        //Dialogo customizado en donde agregaremos un diseño propio
        final Dialog dialog = new Dialog(ListadoActivity.this);

        //Para indicarle al dialogo que diseño es el que tomara
        dialog.setContentView(R.layout.dialogo_editar);

        //Cambiamos el tamaño del dialogo
        dialog.getWindow().setLayout(300, 200);

        //Vinculamos los componentes con las variables
        final Spinner spSeccion = (Spinner) dialog.findViewById(R.id.spSeccion);
        final EditText etTitulo = (EditText) dialog.findViewById(R.id.etTitulo);
        final EditText etContenido = (EditText) dialog.findViewById(R.id.etContenido);
        Button btnActualizar = (Button) dialog.findViewById(R.id.btnActualizar);

        //Obtener los datos de las secciones
        String[] secciones = getResources().getStringArray(R.array.secciones);
        //Creamos un contador para saber en cual elemento estamos
        int i = 0;
        //Recorremos la lista de secciones
        //for (int i = 0; i < secciones.length; i++)
        for (String item : secciones) {
            //Preguntamos si la sección que tenemos almacenada es igual a la sección de las opciones
            if (item.equals(noticia.getSeccion())) {
                //Si son iguales, mostramos la sección
                spSeccion.setSelection(i);
                //Dejamos de recorrer el for
                break;
            }
            //Sumamos en 1 al contador
            i++;
        }
        etTitulo.setText(noticia.getTitulo());
        etContenido.setText(noticia.getContenido());

        //Evento onClick para el boton actualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obtenemos los valores de los componentes
                String seccion = spSeccion.getSelectedItem().toString();
                String titulo = etTitulo.getText().toString();
                String contenido = etContenido.getText().toString();

                //Editamos los datos
                MainActivity.listaNoticia.get(position).setSeccion(seccion);
                MainActivity.listaNoticia.get(position).setTitulo(titulo);
                MainActivity.listaNoticia.get(position).setContenido(contenido);

                //Actualizar el listado
                adapter.notifyDataSetChanged();

                //Cerrar el dialogo
                dialog.dismiss();

                Toast.makeText(ListadoActivity.this, "Actualizar", Toast.LENGTH_SHORT).show();
            }
        });

        //Mostrar el dialogo
        dialog.show();
    }
}
