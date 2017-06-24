package com.jcodee.mod1class1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alumno on 6/23/17.
 */

public class RegistroActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Definimos de que clase XML se va a usar el diseño
        setContentView(R.layout.activity_registro);
    }
}
