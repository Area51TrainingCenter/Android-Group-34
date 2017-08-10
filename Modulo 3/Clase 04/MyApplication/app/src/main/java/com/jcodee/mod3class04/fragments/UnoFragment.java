package com.jcodee.mod3class04.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jcodee.mod3class04.MenuActivity;
import com.jcodee.mod3class04.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnoFragment extends Fragment {
    private EditText etCampo;
    private Button btnEnviar;


    public UnoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_uno, container, false);

        etCampo = (EditText) view.findViewById(R.id.etCampo);
        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String texto = etCampo.getText().toString();

                //Indicamos que el getActivity del fragment es de tipo MenuActivity
                /****************************/
                ((MenuActivity) getActivity()).pasarDato(texto);

            }
        });
    }
}
