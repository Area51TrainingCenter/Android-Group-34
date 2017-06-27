package com.jcodee.mod1class1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alumno on 6/23/17.
 */

public class RegistroActivity extends AppCompatActivity {
    private EditText etNombre, etApellido;
    private RadioButton rbMayor, rbMenor;
    private Spinner spGenero, spEdad;
    private CheckBox cbToma, cbFuma;
    private Button btnProcesar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Definimos de que clase XML se va a usar el diseño
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        rbMayor = (RadioButton) findViewById(R.id.rbMayor);
        rbMenor = (RadioButton) findViewById(R.id.rbMenor);
        spGenero = (Spinner) findViewById(R.id.spGenero);
        spEdad = (Spinner) findViewById(R.id.spEdad);
        cbToma = (CheckBox) findViewById(R.id.cbToma);
        cbFuma = (CheckBox) findViewById(R.id.cbFuma);
        btnProcesar = (Button) findViewById(R.id.btnProcesar);

        //Creamos una lista la cual tendrá las edades
        ArrayList<String> edades = new ArrayList<>();
        //Creamos los números del 15 al 65 para poder llenar la lista
        for (int i = 15; i < 65; i++) {
            //Agregamos los número a la lista
            //String.valueOf -> vuelve texto el número que le mandamos
            edades.add(String.valueOf(i));
        }
        //Creamos el adapter que se usará para poder reemplazar el contenido del spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                RegistroActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                edades);
        //Cambiamos los datos a mostrar en nuestro spinner
        spEdad.setAdapter(arrayAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*rbMayor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rbMayor.isChecked()) {
                    rbMayor.setChecked(false);
                } else {
                    rbMenor.setChecked(false);
                }
            }
        });*/

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obtenemos todos los datos de los componentes
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String genero = spGenero.getSelectedItem().toString();
                String edad = spEdad.getSelectedItem().toString();
                boolean mayor = rbMayor.isChecked();
                boolean menor = rbMenor.isChecked();
                boolean toma = cbToma.isChecked();
                boolean fuma = cbFuma.isChecked();

                int edadEntero = Integer.parseInt(edad);

                //Validamos que los campos tengan datos
                if (nombre.trim().isEmpty()) {
                    //Si el dato está vacío
                    etNombre.setError("El campo es requerido");
                    return;
                } else {
                    etNombre.setError(null);
                }

                if (apellido.trim().isEmpty()) {
                    etApellido.setError("El campo es requerido");
                    return;
                } else {
                    etApellido.setError(null);
                }

                //Validamos que los datos estén cargados
                if (!mayor && !menor) {
                    Toast.makeText(RegistroActivity.this,
                            "Debe de seleccionar una opción Mayor o Menor", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Creamos un mensaje
                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                //Agregamos el titulo del mensaje
                builder.setTitle("Datos");
                //Agregamos el contenido del mensaje
                builder.setMessage(
                        "Nombre->" + nombre + "\n" +
                                "Apellido->" + apellido + "\n" +
                                "Genero->" + genero + "\n" +
                                "Edad->" + edad + "\n" +
                                //"Genero?->" + (masculino ? "Masculino" : "Femenino") + "\n" +
                                "Mayor?->" + (mayor ? "Si" : "No") + "\n" +
                                "Toma?->" + (toma ? "Si" : "No") + "\n" +
                                "Fuma?->" + (fuma ? "Si" : "No")
                );
                String resultado = "";
                if (mayor) {
                    resultado = "Si";
                } else {
                    resultado = "No";
                }

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                //Creamos el mensaje y lo mostramos
                builder.show();

            }
        });
    }
}
