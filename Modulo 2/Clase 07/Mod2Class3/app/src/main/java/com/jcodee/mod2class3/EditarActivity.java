package com.jcodee.mod2class3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class3.database.MetodoSQL;
import com.jcodee.mod2class3.entidades.ClienteEntidad;
import com.jcodee.mod2class3.entidades.MarcaEntidad;
import com.jcodee.mod2class3.entidades.ModeloEntidad;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

public class EditarActivity extends AppCompatActivity {

    @BindView(R.id.spMarca)
    Spinner spMarca;
    @BindView(R.id.spModelo)
    Spinner spModelo;
    @BindView(R.id.etPlaca)
    EditText etPlaca;
    @BindView(R.id.etAnio)
    EditText etAnio;
    @BindView(R.id.etPropietario)
    EditText etPropietario;


    private RealmResults<MarcaEntidad> marcas;
    private RealmResults<ModeloEntidad> modelos;
    private MetodoSQL metodoSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        ButterKnife.bind(this);

        metodoSQL = new MetodoSQL();
        marcas = metodoSQL.obtenerMarcas();

        //Cargar los datos del spinner de marca
        ArrayList<String> lista = new ArrayList<>();
        for (MarcaEntidad item : marcas) {
            lista.add(item.getDescripcion());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                EditarActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                lista
        );
        spMarca.setAdapter(arrayAdapter);

        for (int i = 0; i < marcas.size(); i++) {
            if (marcas.get(i).getDescripcion()
                    .equals(getIntent().getStringExtra("marca"))) {
                spMarca.setSelection(i);
                break;
            }
        }

        etPlaca.setText(getIntent().getStringExtra("placa"));
        etAnio.setText(getIntent().getStringExtra("anio"));
        etPropietario.setText(getIntent().getStringExtra("propietario"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long type) {

                //Obtener la marca seleccionada
                MarcaEntidad marcaEntidad = marcas.get(position);

                modelos = metodoSQL.obtenerModelosPorMarca(marcaEntidad.getId());
                ArrayList<String> listaModelos = new ArrayList<>();
                for (ModeloEntidad item : modelos) {
                    listaModelos.add(item.getDescripcion());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(
                        EditarActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        listaModelos
                );
                spModelo.setAdapter(arrayAdapter);

                for (int i = 0; i < modelos.size(); i++) {
                    if (modelos.get(i).getDescripcion()
                            .equals(getIntent().getStringExtra("modelo"))) {
                        spModelo.setSelection(i);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.btnActualizar)
    public void onViewClicked() {
        //Obtenemos el modelo seleccionado
        String modelo = spModelo.getSelectedItem().toString();

        //Obtener el modelo (objeto) seleccionado
        ModeloEntidad model = null;
        for (ModeloEntidad item : modelos) {
            if (item.getDescripcion().equals(modelo)) {
                model = item;
                break;
            }
        }

        ClienteEntidad clienteEntidad1 = new ClienteEntidad();
        clienteEntidad1.setId(getIntent().getLongExtra("id", 0));
        clienteEntidad1.setModelo(model);
        clienteEntidad1.setPlaca(etPlaca.getText().toString());
        clienteEntidad1.setAnio(etAnio.getText().toString());
        clienteEntidad1.setPropietario(etPropietario.getText().toString());
        metodoSQL.agregar(clienteEntidad1);
        finish();
        Toast.makeText(EditarActivity.this, "Se actualizo", Toast.LENGTH_SHORT).show();
    }
}
