package com.jcodee.mod3class3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jcodee.mod3class3.rest.HelperWS;
import com.jcodee.mod3class3.rest.MetodoWS;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etDireccion)
    EditText etDireccion;
    @BindView(R.id.tvDireccion)
    TextView tvDireccion;
    @BindView(R.id.tvCoordenadas)
    TextView tvCoordenadas;
    private MapFragment mapFragment;
    private GoogleMap googleMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMaps = googleMap;
            }
        });
    }

    @OnClick(R.id.btnBuscar)
    public void onViewClicked() {

        String direccion = etDireccion.getText().toString();


        MetodoWS metodoWS = HelperWS.obtenerConfiguracion().create(MetodoWS.class);
        Call<DireccionResponse> responseCall =
                metodoWS.buscarDireccion(direccion, "AIzaSyBIRMEmpWQPzy8S1IHWvMKcOnYWpwfXss8", "country:PE");
        responseCall.enqueue(new Callback<DireccionResponse>() {
            @Override
            public void onResponse(Call<DireccionResponse> call, Response<DireccionResponse> response) {
                DireccionResponse direccionResponse = response.body();
                if (direccionResponse != null) {
                    String dir = direccionResponse.getResults().get(0).getFormatted_address();
                    tvDireccion.setText(dir);
                    CoordenadaResponse coor =
                            direccionResponse.getResults().get(0).getGeometry().getLocation();
                    tvCoordenadas.setText(coor.getLat() + " - " + coor.getLng());
                    googleMaps.addMarker(new MarkerOptions()
                            .position(new LatLng(coor.getLat(), coor.getLng()))
                            .title("Titulo")
                            .snippet("Descripción"));
                    googleMaps.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(coor.getLat(), coor.getLng())));
                }
            }

            @Override
            public void onFailure(Call<DireccionResponse> call, Throwable t) {

            }
        });
    }
}
