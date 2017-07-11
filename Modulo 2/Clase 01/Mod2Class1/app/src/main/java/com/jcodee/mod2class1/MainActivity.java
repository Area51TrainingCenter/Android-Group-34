package com.jcodee.mod2class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {
    private Spinner spEmpresa;
    private EditText etCargo, etNombre;
    private Button btnRegistrar;
    private ListView lvLista;

    private MetodoSQL metodoSQL;
    private PersonalAdapter personalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spEmpresa = (Spinner) findViewById(R.id.spEmpresa);
        etCargo = (EditText) findViewById(R.id.etCargo);
        etNombre = (EditText) findViewById(R.id.etNombre);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        lvLista = (ListView) findViewById(R.id.lvLista);

        //Inicializamos la base de datos
        metodoSQL = new MetodoSQL(MainActivity.this);

        //Carga de datos de empresa al spinner
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                metodoSQL.obtenerEmpresas());
        //Guardamos la configuración
        spEmpresa.setAdapter(adapter);

        personalAdapter =
                new PersonalAdapter(MainActivity.this, metodoSQL.obtenerPersonal());
        lvLista.setAdapter(personalAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                //Registrar personal
                Personal personal = new Personal(empresa, cargo, nombre);
                metodoSQL.agregarPersonal(personal);

                personalAdapter =
                        new PersonalAdapter(MainActivity.this, metodoSQL.obtenerPersonal());
                lvLista.setAdapter(personalAdapter);

                etNombre.setText("");
                etCargo.setText("");
                spEmpresa.setSelection(0);

                //Mensaje de confirmación
                Toast.makeText(MainActivity.this, "Se registro", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
