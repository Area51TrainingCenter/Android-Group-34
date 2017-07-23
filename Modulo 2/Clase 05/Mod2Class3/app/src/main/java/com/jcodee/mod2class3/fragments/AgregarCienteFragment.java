package com.jcodee.mod2class3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class3.R;
import com.jcodee.mod2class3.database.MetodoSQL;
import com.jcodee.mod2class3.entidades.ClienteEntidad;
import com.jcodee.mod2class3.entidades.MarcaEntidad;
import com.jcodee.mod2class3.entidades.ModeloEntidad;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarCienteFragment extends Fragment {


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
    Unbinder unbinder;

    private RealmResults<MarcaEntidad> marcas;
    private RealmResults<ModeloEntidad> modelos;
    private MetodoSQL metodoSQL;

    public AgregarCienteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_ciente, container, false);
        unbinder = ButterKnife.bind(this, view);

        metodoSQL = new MetodoSQL();
        marcas = metodoSQL.obtenerMarcas();

        ArrayList<String> lista = new ArrayList<>();
        for (MarcaEntidad item : marcas) {
            lista.add(item.getDescripcion());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                lista
        );
        spMarca.setAdapter(arrayAdapter);

        return view;
    }

    @Override
    public void onResume() {
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
                        getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,
                        listaModelos
                );
                spModelo.setAdapter(arrayAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnAgregar)
    public void onViewClicked() {

        String placa = etPlaca.getText().toString();
        String propietario = etPropietario.getText().toString();
        String anio = etAnio.getText().toString();

        //Validamos que haya al menos un modelo
        if (modelos == null) {
            Toast.makeText(getActivity(),
                    "Debe de seleccionar un modelo", Toast.LENGTH_SHORT).show();
            return;
        }
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

        ClienteEntidad clienteEntidad = new ClienteEntidad();
        clienteEntidad.setId(metodoSQL.obtenerIdCliente());
        clienteEntidad.setAnio(anio);
        clienteEntidad.setModelo(model);
        clienteEntidad.setPlaca(placa);
        clienteEntidad.setPropietario(propietario);
        metodoSQL.agregar(clienteEntidad);
        Toast.makeText(getActivity(), "Se registro correctamente", Toast.LENGTH_SHORT).show();

    }
}
