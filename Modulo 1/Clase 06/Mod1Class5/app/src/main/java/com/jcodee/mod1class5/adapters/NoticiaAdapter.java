package com.jcodee.mod1class5.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod1class5.R;
import com.jcodee.mod1class5.modelos.Noticia;

import java.util.ArrayList;

/**
 * Created by johannfjs on 28/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

//Clase para obtener la información y generar los diseños
public class NoticiaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Noticia> listaNoticias;

    public NoticiaAdapter(Context context, ArrayList<Noticia> listaNoticias) {
        this.context = context;
        this.listaNoticias = listaNoticias;
    }

    //Cantidad de elementos que tenemos en nuestra lista
    @Override
    public int getCount() {
        return listaNoticias.size();
    }

    //Obtener un elemento de la lista, según la posición
    @Override
    public Object getItem(int i) {
        return listaNoticias.get(i);
    }

    //Obtener el identificador unico de cada objeto que hay en la lista
    @Override
    public long getItemId(int i) {
        return listaNoticias.get(i).getId();
    }

    //Generar la vista o diseño que tendrá el item
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Llamar el diseño en el cual vamos a tomar como estructura
        view = LayoutInflater.from(context).inflate(R.layout.item_detalle, viewGroup, false);

        //Vinculamos las variables con nuestro diseño
        TextView tvSeccion = (TextView) view.findViewById(R.id.tvSeccion);
        TextView tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
        TextView tvContenido = (TextView) view.findViewById(R.id.tvContenido);
        //ImageView ivImagen = (ImageView) view.findViewById(R.id.ivImagen);
        SimpleDraweeView ivImagen = (SimpleDraweeView) view.findViewById(R.id.ivImagen);

        //Obtenemos la noticia seleccionado
        Noticia item = (Noticia) getItem(i);

        //Cambiamos los valores de los componentes
        tvSeccion.setText(item.getSeccion());
        tvTitulo.setText(item.getTitulo());
        tvContenido.setText(item.getContenido());

        ivImagen.setImageURI(Uri.parse("res:/" + R.mipmap.ic_launcher));
        //ivImagen.setImageDrawable(context.getResources().getDrawable(R.drawable.fondo));

        //Retornamos el diseño generado
        return view;
    }
}
