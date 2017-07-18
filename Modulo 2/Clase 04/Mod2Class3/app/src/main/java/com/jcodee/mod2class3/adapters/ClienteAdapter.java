package com.jcodee.mod2class3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod2class3.R;
import com.jcodee.mod2class3.entidades.ClienteEntidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by johannfjs on 17/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class ClienteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private RealmResults<ClienteEntidad> clientes;

    public ClienteAdapter(Context context, RealmResults<ClienteEntidad> clientes) {
        this.context = context;
        this.clientes = clientes;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPlaca)
        TextView tvPlaca;
        @BindView(R.id.tvPropietario)
        TextView tvPropietario;
        @BindView(R.id.tvAnio)
        TextView tvAnio;
        @BindView(R.id.tvMarca)
        TextView tvMarca;
        @BindView(R.id.tvModelo)
        TextView tvModelo;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_cliente, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        //Validamos que el viewHolder este creado
        if (myViewHolder != null) {
            ClienteEntidad clienteEntidad = clientes.get(position);

            myViewHolder.tvAnio.setText(clienteEntidad.getAnio());
            myViewHolder.tvMarca.setText(clienteEntidad.getModelo().getMarca().getDescripcion());
            myViewHolder.tvModelo.setText(clienteEntidad.getModelo().getDescripcion());
            myViewHolder.tvPropietario.setText(clienteEntidad.getPropietario());
            myViewHolder.tvPlaca.setText(clienteEntidad.getPlaca());
        }
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}
