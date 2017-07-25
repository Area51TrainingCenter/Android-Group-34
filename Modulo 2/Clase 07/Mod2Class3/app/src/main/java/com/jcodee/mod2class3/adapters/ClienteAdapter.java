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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodee.mod2class3.R;
import com.jcodee.mod2class3.database.MetodoSQL;
import com.jcodee.mod2class3.entidades.ClienteEntidad;
import com.jcodee.mod2class3.entidades.MarcaEntidad;
import com.jcodee.mod2class3.entidades.ModeloEntidad;

import java.util.ArrayList;

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

                            cargarModificar(clienteEntidad, metodoSQL);

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

    private RealmResults<MarcaEntidad> marcas;
    private RealmResults<ModeloEntidad> modelos;

    private void cargarModificar(final ClienteEntidad clienteEntidad,
                                 final MetodoSQL metodoSQL) {

        marcas = metodoSQL.obtenerMarcas();

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_cliente_edit);

        final EditText etPlaca = (EditText) dialog.findViewById(R.id.etPlaca);
        final EditText etAnio = (EditText) dialog.findViewById(R.id.etAnio);
        final EditText etPropietario = (EditText) dialog.findViewById(R.id.etPropietario);
        Button btnActualizar = (Button) dialog.findViewById(R.id.btnActualizar);
        Spinner spMarca = (Spinner) dialog.findViewById(R.id.spMarca);
        final Spinner spModelo = (Spinner) dialog.findViewById(R.id.spModelo);

        //Cargar los datos del spinner de marca
        ArrayList<String> lista = new ArrayList<>();
        for (MarcaEntidad item : marcas) {
            lista.add(item.getDescripcion());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                lista
        );
        spMarca.setAdapter(arrayAdapter);

        for (int i = 0; i < marcas.size(); i++) {
            if (marcas.get(i).getDescripcion()
                    .equals(clienteEntidad.getModelo().getMarca().getDescripcion())) {
                spMarca.setSelection(i);
                break;
            }
        }

        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long type) {

                //Obtener la marca seleccionada
                MarcaEntidad marcaEntidad = marcas.get(position);

                modelos = metodoSQL.obtenerModelosPorMarca(marcaEntidad.getId());
                ArrayList<String> listaModelos = new ArrayList<>();
                for (ModeloEntidad item : modelos) {
                    listaModelos.add(item.getDescripcion());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        listaModelos
                );
                spModelo.setAdapter(arrayAdapter);

                for (int i = 0; i < modelos.size(); i++) {
                    if (modelos.get(i).getDescripcion()
                            .equals(clienteEntidad.getModelo().getDescripcion())) {
                        spModelo.setSelection(i);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etPlaca.setText(clienteEntidad.getPlaca());
        etAnio.setText(clienteEntidad.getAnio());
        etPropietario.setText(clienteEntidad.getPropietario());
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos el modelo seleccionado
                String modelo = spModelo.getSelectedItem().toString();

                //Obtener el modelo (objeto) seleccionado
                ModeloEntidad model = null;
                for (ModeloEntidad item : modelos) {
                    if (item.getDescripcion().equals(modelo)) {
                        model = item;
                        break;
                    }
                }

                ClienteEntidad clienteEntidad1 = new ClienteEntidad();
                clienteEntidad1.setId(clienteEntidad.getId());
                clienteEntidad1.setModelo(model);
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

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}
