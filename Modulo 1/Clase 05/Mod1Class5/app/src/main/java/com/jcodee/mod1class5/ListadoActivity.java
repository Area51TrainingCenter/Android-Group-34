package com.jcodee.mod1class5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jcodee.mod1class5.adapters.NoticiaAdapter;

/**
 * Created by johannfjs on 28/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class ListadoActivity extends AppCompatActivity {
    private ListView lvLista;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listado);

        lvLista = (ListView) findViewById(R.id.lvLista);

        NoticiaAdapter adapter =
                new NoticiaAdapter(ListadoActivity.this, MainActivity.listaNoticia);
        lvLista.setAdapter(adapter);
    }
}
