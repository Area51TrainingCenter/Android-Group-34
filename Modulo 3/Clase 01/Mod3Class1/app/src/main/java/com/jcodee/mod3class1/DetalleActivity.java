package com.jcodee.mod3class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jcodee.mod3class1.database.SentenciaSQL;
import com.jcodee.mod3class1.entidades.Ubicaciones;

public class DetalleActivity extends AppCompatActivity {
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                //123456789
                //-12.12345678

                for (Ubicaciones item : SentenciaSQL.obtener()) {

                    googleMap.addMarker(
                            new MarkerOptions()
                                    //Titulo
                                    .title(item.getTitulo())
                                    //Descripción
                                    .snippet(item.getDescripcion())
                                    //Ubicación o posición
                                    .position(new LatLng(item.getLatitud(), item.getLongitud()))
                                    //Imagen a mostrar en el punto
                                    .icon(BitmapDescriptorFactory.defaultMarker())
                    );

                    //Hacer zoom y ubicar el punto en el mapa
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(item.getLatitud(), item.getLongitud()),
                            15
                    ));

                }

            }
        });
    }
}
