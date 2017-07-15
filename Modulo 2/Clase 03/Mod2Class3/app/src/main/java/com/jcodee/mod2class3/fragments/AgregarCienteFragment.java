package com.jcodee.mod2class3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodee.mod2class3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarCienteFragment extends Fragment {


    public AgregarCienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_ciente, container, false);
    }

}
