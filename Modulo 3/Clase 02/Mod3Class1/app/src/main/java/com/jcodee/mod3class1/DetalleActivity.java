package com.jcodee.mod3class1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jcodee.mod3class1.database.SentenciaSQL;
import com.jcodee.mod3class1.entidades.Ubicaciones;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {
    private MapFragment mapFragment;
    Ubicaciones ubicacion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                //123456789
                //-12.12345678
                //googleMap.getUiSettings().setMyLocationButtonEnabled(true);

                ArrayList<LatLng> posiciones = new ArrayList<LatLng>();
                for (Ubicaciones item : SentenciaSQL.obtener()) {

                    posiciones.add(new LatLng(item.getLatitud(), item.getLongitud()));

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

                //Agregar una linea en el mapa
                googleMap.addPolyline(
                        new PolylineOptions()
                                .addAll(posiciones)
                                .color(Color.GREEN)
                                .width(5)
                );

                //Creamos un poligono y pintamos el contenido
                googleMap.addPolygon(
                        new PolygonOptions()
                                .addAll(posiciones)
                                .fillColor(getResources().getColor(R.color.colorPrimary))
                );

                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(final Marker marker) {

                        for (Ubicaciones item : SentenciaSQL.obtener()) {
                            if (item.getTitulo().equals(marker.getTitle()) &&
                                    item.getDescripcion().equals(marker.getSnippet())) {
                                ubicacion = item;
                                break;
                            }
                        }
                        /*
                        Intent intent = new Intent(DetalleActivity.this, MapaDetalleActivity.class);
                        startActivity(intent);
                        */
                        Dialog dialog = new Dialog(DetalleActivity.this);
                        dialog.getWindow().setLayout(
                                getWindow().getWindowManager().getDefaultDisplay().getWidth(),
                                getWindow().getWindowManager().getDefaultDisplay().getHeight() / 2);
                        dialog.setContentView(R.layout.item_editar);

                        final EditText etTitulo = (EditText) dialog.findViewById(R.id.etTitulo);
                        final EditText etDescripcion = (EditText) dialog.findViewById(R.id.etDescripcion);
                        Button btnEditar = (Button) dialog.findViewById(R.id.btnEditar);
                        Button btnEliminar = (Button) dialog.findViewById(R.id.btnEliminar);

                        etTitulo.setText(marker.getTitle());
                        etDescripcion.setText(marker.getSnippet());

                        btnEditar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Ubicaciones ubicaciones = new Ubicaciones();
                                ubicaciones.setTitulo(etTitulo.getText().toString());
                                ubicaciones.setDescripcion(etDescripcion.getText().toString());
                                ubicaciones.setId(ubicacion.getId());
                                ubicaciones.setLatitud(ubicacion.getLatitud());
                                ubicaciones.setLongitud(ubicacion.getLongitud());
                                SentenciaSQL.registrar(ubicaciones);

                                googleMap.clear();

                            }
                        });
                        btnEliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SentenciaSQL.eliminar(ubicacion.getId());
                                marker.remove();
                            }
                        });

                        dialog.show();
                        /*
                        marker.remove();
                        ubicacion.getId();


                        marker.getTitle();
                        marker.getPosition();
                        marker.getTag();
                        */


                        Toast.makeText(DetalleActivity.this, "Acción", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
