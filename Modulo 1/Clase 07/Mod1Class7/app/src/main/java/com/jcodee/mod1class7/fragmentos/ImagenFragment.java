package com.jcodee.mod1class7.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod1class7.R;

/**
 * A simple {@link Fragment} subclass.
 * https://i.stack.imgur.com/fRxIQ.png
 */
public class ImagenFragment extends Fragment {
    private SimpleDraweeView sdvImagen;
    private TextView tvTexto;

    public ImagenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_imagen, container, false);

        sdvImagen = (SimpleDraweeView) view.findViewById(R.id.sdvImagen);
        tvTexto = (TextView) view.findViewById(R.id.tvTexto);

        return view;
    }

}
