package com.jcodee.mod2class3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodee.mod2class3.R;
import com.jcodee.mod2class3.adapters.ClienteAdapter;
import com.jcodee.mod2class3.database.MetodoSQL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerClientesFragment extends Fragment {


    @BindView(R.id.rvDatos)
    RecyclerView rvDatos;
    Unbinder unbinder;

    private MetodoSQL metodoSQL;

    public VerClientesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_clientes, container, false);
        unbinder = ButterKnife.bind(this, view);

        metodoSQL = new MetodoSQL();

        ClienteAdapter adapter = new ClienteAdapter(getActivity(), metodoSQL.obtenerClientes());
        rvDatos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDatos.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
