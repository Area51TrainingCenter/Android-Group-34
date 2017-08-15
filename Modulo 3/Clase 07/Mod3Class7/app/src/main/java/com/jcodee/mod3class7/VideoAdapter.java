package com.jcodee.mod3class7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johannfjs on 14/08/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> lista;

    public VideoAdapter(Context context, ArrayList<String> lista) {
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
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        String dato = lista.get(i);
        viewHolder.tvVideo.setText(dato);
        viewHolder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public void agregar(String texto) {
        lista.add(texto);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tvVideo)
        TextView tvVideo;
        @BindView(R.id.btnEliminar)
        Button btnEliminar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ArrayList<String> getLista() {
        return lista;
    }
}
