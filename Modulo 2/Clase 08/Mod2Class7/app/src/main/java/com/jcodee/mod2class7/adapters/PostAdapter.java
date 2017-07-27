package com.jcodee.mod2class7.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod2class7.R;
import com.jcodee.mod2class7.modelos.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by johannfjs on 26/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Post> lista;

    public PostAdapter(Context context, ArrayList<Post> lista) {
        this.context = context;
        this.lista = lista;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvCuerpo;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvCuerpo = (TextView) itemView.findViewById(R.id.tvCuerpo);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        if (viewHolder != null) {
            Post post = lista.get(position);

            viewHolder.tvTitulo.setText(post.getTitle());
            viewHolder.tvCuerpo.setText(post.getBody());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
