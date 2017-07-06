package com.jcodee.mod1class7.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jcodee.mod1class7.MainActivity;
import com.jcodee.mod1class7.fragmentos.ImagenFragment;

/**
 * Created by johannfjs on 5/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class GaleriaAdapter extends FragmentStatePagerAdapter {
    public GaleriaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Creamos un fragmento
        ImagenFragment imagenFragment = new ImagenFragment();

        //Creamos una variable bundle para poder pasar información
        Bundle bundle = new Bundle();
        //Enviamos los datos a través del bundle
        bundle.putInt("posicion", position);
        //Agregamos los parametros a nuestro fragmento
        imagenFragment.setArguments(bundle);

        //retornamos el fragmento con los datos cargados
        return imagenFragment;
    }

    @Override
    public int getCount() {
        //Cantidad de elementos que tiene la lista
        return MainActivity.lista.size();
    }
}
