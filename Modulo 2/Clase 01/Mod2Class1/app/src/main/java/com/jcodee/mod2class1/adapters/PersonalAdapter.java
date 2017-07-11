package com.jcodee.mod2class1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jcodee.mod2class1.R;
import com.jcodee.mod2class1.modelos.Personal;

import java.util.ArrayList;

/**
 * Created by johannfjs on 10/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class PersonalAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Personal> lista;

    public PersonalAdapter(Context context, ArrayList<Personal> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_personal, viewGroup, false);

        TextView tvEmpresa = (TextView) view.findViewById(R.id.tvEmpresa),
                tvCargo = (TextView) view.findViewById(R.id.tvCargo),
                tvNombre = (TextView) view.findViewById(R.id.tvNombre);

        Personal item = (Personal) getItem(i);
        tvCargo.setText(item.getCargo());
        tvEmpresa.setText(item.getEmpresa());
        tvNombre.setText(item.getNombre());

        return view;
    }
}
