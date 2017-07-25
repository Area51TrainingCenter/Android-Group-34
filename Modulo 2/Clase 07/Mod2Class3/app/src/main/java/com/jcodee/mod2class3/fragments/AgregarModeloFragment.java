package com.jcodee.mod2class3.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class3.R;
import com.jcodee.mod2class3.database.MetodoSQL;
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
public class AgregarModeloFragment extends Fragment {


    @BindView(R.id.spMarca)
    Spinner spMarca;
    @BindView(R.id.etModelo)
    EditText etModelo;
    @BindView(R.id.tilModelo)
    TextInputLayout tilModelo;
    Unbinder unbinder;

    private RealmResults<MarcaEntidad> marcas;
    private MetodoSQL metodoSQL;

    public AgregarModeloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_modelo, container, false);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnAgregar)
    public void onViewClicked() {

        String marca = spMarca.getSelectedItem().toString();
        String modelo = etModelo.getText().toString();

        MarcaEntidad marcaEntidad = null;
        for (MarcaEntidad item : marcas) {
            if (item.getDescripcion().equals(marca)) {
                marcaEntidad = item;
                break;
            }
        }

        ModeloEntidad modeloEntidad = new ModeloEntidad();
        modeloEntidad.setId(metodoSQL.obtenerIdModelo());
        modeloEntidad.setMarca(marcaEntidad);
        modeloEntidad.setDescripcion(modelo);
        metodoSQL.agregar(modeloEntidad);
        Toast.makeText(getActivity(), "Se registro correctamente", Toast.LENGTH_SHORT).show();

    }
}
