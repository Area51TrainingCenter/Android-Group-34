package com.jcodee.mod2class7.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod2class7.R;
import com.jcodee.mod2class7.modelos.Post;
import com.jcodee.mod2class7.modelos.Usuario;

import java.util.ArrayList;

/**
 * Created by johannfjs on 26/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class ClienteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Usuario> lista;

    public ClienteAdapter(Context context, ArrayList<Usuario> lista) {
        this.context = context;
        this.lista = lista;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsuario;
        TextView tvNombre;
        TextView tvCorreo;
        TextView tvDireccion;
        TextView tvCompania;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvUsuario = (TextView) itemView.findViewById(R.id.tvUsuario);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvCorreo = (TextView) itemView.findViewById(R.id.tvCorreo);
            tvDireccion = (TextView) itemView.findViewById(R.id.tvDireccion);
            tvCompania = (TextView) itemView.findViewById(R.id.tvCompania);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        if (viewHolder != null) {
            Usuario usuario = lista.get(position);

            viewHolder.tvNombre.setText(usuario.getName());
            viewHolder.tvUsuario.setText(usuario.getUsername());
            viewHolder.tvCorreo.setText(usuario.getEmail());
            viewHolder.tvCompania.setText(usuario.getCompany().getName());
            viewHolder.tvDireccion.setText(usuario.getAddress().getCity());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
