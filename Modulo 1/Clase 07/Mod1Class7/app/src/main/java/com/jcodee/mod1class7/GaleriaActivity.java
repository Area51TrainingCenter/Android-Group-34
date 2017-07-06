package com.jcodee.mod1class7;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcodee.mod1class7.adapters.GaleriaAdapter;

public class GaleriaActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //SupportFragmentManager es la clase maestra que puede manipular los fragmentos
        GaleriaAdapter galeriaAdapter = new GaleriaAdapter(getSupportFragmentManager());
        viewPager.setAdapter(galeriaAdapter);
    }
}
