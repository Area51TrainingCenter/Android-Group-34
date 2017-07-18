package com.jcodee.mod2class3.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class3.R;
import com.jcodee.mod2class3.database.MetodoSQL;
import com.jcodee.mod2class3.entidades.MarcaEntidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarMarcaFragment extends Fragment {


    @BindView(R.id.etMarca)
    EditText etMarca;
    @BindView(R.id.tilMarca)
    TextInputLayout tilMarca;
    Unbinder unbinder;

    public AgregarMarcaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_marca, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnAgregar)
    public void onViewClicked() {

        String marca = etMarca.getText().toString();

        if (marca.isEmpty()) {
            tilMarca.setError("El campo es requerido");
            return;
        } else {
            tilMarca.setError(null);
        }

        MetodoSQL metodoSQL = new MetodoSQL();

        MarcaEntidad marcaEntidad = new MarcaEntidad();
        marcaEntidad.setId(metodoSQL.obtenerIdMarca());
        marcaEntidad.setDescripcion(marca);
        metodoSQL.agregarMarca(marcaEntidad);
        Toast.makeText(getActivity(), "Se registro correctamente", Toast.LENGTH_SHORT).show();

    }
}
