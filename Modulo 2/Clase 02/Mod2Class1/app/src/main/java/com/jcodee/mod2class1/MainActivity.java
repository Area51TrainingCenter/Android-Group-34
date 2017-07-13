package com.jcodee.mod2class1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class1.adapters.PersonalAdapter;
import com.jcodee.mod2class1.modelos.Personal;
import com.jcodee.mod2class1.sqlite.MetodoSQL;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //Base de datos en carpeta
    //data/data/<package name>/databases/<name bd>.db

    @BindView(R.id.spEmpresa)
    Spinner spEmpresa;
    @BindView(R.id.etCargo)
    EditText etCargo;
    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.lvLista)
    ListView lvLista;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;
    @BindView(R.id.btnCancelar)
    Button btnCancelar;

    private MetodoSQL metodoSQL;
    private PersonalAdapter personalAdapter;
    private ArrayList<Personal> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Inicializamos la base de datos
        metodoSQL = new MetodoSQL(MainActivity.this);

        //Carga de datos de empresa al spinner
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                metodoSQL.obtenerEmpresas());
        //Guardamos la configuración
        spEmpresa.setAdapter(adapter);

        lista = metodoSQL.obtenerPersonal();
        personalAdapter =
                new PersonalAdapter(MainActivity.this, lista);
        lvLista.setAdapter(personalAdapter);

        //Inicializamos el btn de registro con -1 para que sea registrar
        btnRegistrar.setTag(-1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Acciones");
                builder.setMessage("Elegir una");
                builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Obtenemos a un objeto del tipo personal
                        Personal personal = lista.get(position);
                        //Cargamos los datos en las cajas de texto
                        etNombre.setText(personal.getNombre());
                        etCargo.setText(personal.getCargo());
                        //Creamos una variable de tipo entero
                        int count = 0;
                        //Recorremos las empresas que hay
                        for (String empresa : metodoSQL.obtenerEmpresas()) {
                            //Validamos en que posición se encuentra la que hemos seleccionado
                            if (empresa.equals(personal.getEmpresa())) {
                                //Seleccionamos la empresa que tenemos
                                spEmpresa.setSelection(count);
                            }
                            //Incrementamos en uno el contador
                            count++;
                        }
                        //Cambiamos el nombre del boton
                        btnRegistrar.setText("Actualizar");
                        //Agregamos el id de nuestro registro al boton
                        btnRegistrar.setTag(personal.getId());

                        btnCancelar.setVisibility(View.VISIBLE);

                    }
                });
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle("Confirmación");
                        builder1.setMessage("Está seguro que desea eliminar?");
                        builder1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //Borra de la base de datos
                                metodoSQL.eliminarPersonal(lista.get(position).getId());
                                //Borra visualmente
                                lista.remove(position);
                                //Actualiza la lista que se está mostrando
                                lista = metodoSQL.obtenerPersonal();
                                personalAdapter =
                                        new PersonalAdapter(MainActivity.this, lista);
                                lvLista.setAdapter(personalAdapter);
                                //lvLista.invalidate();

                            }
                        });
                        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder1.show();

                    }
                });
                builder.show();

            }
        });
    }

    @OnClick(R.id.btnRegistrar)
    public void onViewClicked() {

        //Obtenemos los datos a registrar
        String empresa = spEmpresa.getSelectedItem().toString();
        String cargo = etCargo.getText().toString();
        String nombre = etNombre.getText().toString();

        //Validaciones
        if (cargo.isEmpty()) {
            etCargo.setError("El campo es requerido");
            return;
        }
        if (nombre.isEmpty()) {
            etNombre.setError("El campo es requerido");
            return;
        }

        int valor = (int) btnRegistrar.getTag();

        Personal personal = new Personal(empresa, cargo, nombre);
        if (valor == -1) {
            //Registrar personal
            metodoSQL.agregarPersonal(personal);

            //Mensaje de confirmación
            Toast.makeText(MainActivity.this, "Se registro", Toast.LENGTH_SHORT).show();
        } else {
            personal.setId(valor);
            metodoSQL.actualizarPersonal(personal);
            Toast.makeText(this, "Se actualizo", Toast.LENGTH_SHORT).show();

            btnCancelar.setVisibility(View.GONE);
            btnRegistrar.setTag(-1);
            btnRegistrar.setText("Registrar");
        }

        lista = metodoSQL.obtenerPersonal();
        personalAdapter =
                new PersonalAdapter(MainActivity.this, lista);
        lvLista.setAdapter(personalAdapter);

        etNombre.setText("");
        etCargo.setText("");
        spEmpresa.setSelection(0);


    }

    @OnClick(R.id.btnCancelar)
    public void onClicked() {
        etNombre.setText("");
        etCargo.setText("");
        spEmpresa.setSelection(0);

        btnCancelar.setVisibility(View.GONE);
        btnRegistrar.setText("Registrar");
        btnRegistrar.setTag(-1);
    }
}
