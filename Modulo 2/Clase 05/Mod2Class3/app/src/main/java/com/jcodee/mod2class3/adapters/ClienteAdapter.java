package com.jcodee.mod2class3.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodee.mod2class3.R;
import com.jcodee.mod2class3.database.MetodoSQL;
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
        @BindView(R.id.contenedor)
        CardView contenedor;

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
            final ClienteEntidad clienteEntidad = clientes.get(position);

            myViewHolder.tvAnio.setText(clienteEntidad.getAnio());
            myViewHolder.tvMarca.setText(clienteEntidad.getModelo().getMarca().getDescripcion());
            myViewHolder.tvModelo.setText(clienteEntidad.getModelo().getDescripcion());
            myViewHolder.tvPropietario.setText(clienteEntidad.getPropietario());
            myViewHolder.tvPlaca.setText(clienteEntidad.getPlaca());

            final MetodoSQL metodoSQL = new MetodoSQL();

            //Ejecutamos una acción por cada elemento de la lista
            myViewHolder.contenedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Acción a realizar");
                    builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.item_cliente_edit);

                            final EditText etPlaca = (EditText) dialog.findViewById(R.id.etPlaca);
                            final EditText etAnio = (EditText) dialog.findViewById(R.id.etAnio);
                            final EditText etPropietario = (EditText) dialog.findViewById(R.id.etPropietario);
                            Button btnActualizar = (Button) dialog.findViewById(R.id.btnActualizar);

                            etPlaca.setText(clienteEntidad.getPlaca());
                            etAnio.setText(clienteEntidad.getAnio());
                            etPropietario.setText(clienteEntidad.getPropietario());
                            btnActualizar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ClienteEntidad clienteEntidad1 = new ClienteEntidad();
                                    clienteEntidad1.setId(clienteEntidad.getId());
                                    clienteEntidad1.setModelo(clienteEntidad.getModelo());
                                    clienteEntidad1.setPlaca(etPlaca.getText().toString());
                                    clienteEntidad1.setAnio(etAnio.getText().toString());
                                    clienteEntidad1.setPropietario(etPropietario.getText().toString());
                                    metodoSQL.agregar(clienteEntidad1);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    Toast.makeText(context, "Se actualizo", Toast.LENGTH_SHORT).show();
                                }
                            });


                            dialog.show();

                        }
                    });
                    builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                            builder1.setMessage("Confirmar");
                            builder1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    metodoSQL.eliminarCliente(clienteEntidad.getId());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder1.show();

                        }
                    });
                    builder.show();

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}
