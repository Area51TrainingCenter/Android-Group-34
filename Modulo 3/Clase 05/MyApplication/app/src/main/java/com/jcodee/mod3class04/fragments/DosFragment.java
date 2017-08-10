package com.jcodee.mod3class04.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod3class04.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosFragment extends Fragment {

    private TextView tvTexto;

    public DosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dos, container, false);

        tvTexto = (TextView) view.findViewById(R.id.tvTexto);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String dato = bundle.getString("dato");

            if (dato != null)
                tvTexto.setText(dato);
        }
        return view;
    }

}
