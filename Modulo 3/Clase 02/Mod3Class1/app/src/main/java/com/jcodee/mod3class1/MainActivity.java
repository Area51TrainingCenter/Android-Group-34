package com.jcodee.mod3class1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod3class1.database.SentenciaSQL;
import com.jcodee.mod3class1.entidades.Ubicaciones;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTitulo)
    EditText etTitulo;
    @BindView(R.id.etDescripcion)
    EditText etDescripcion;
    @BindView(R.id.etLatitud)
    EditText etLatitud;
    @BindView(R.id.etLongitud)
    EditText etLongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnGrabar)
    public void onBtnGrabarClicked() {

        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String latitud = etLatitud.getText().toString();
        String longitud = etLongitud.getText().toString();

        if (titulo.isEmpty()) {
            etTitulo.setError("El campo es requerido");
            return;
        }
        if (descripcion.isEmpty()) {
            etDescripcion.setError("El campo es requerido");
            return;
        }
        if (latitud.isEmpty()) {
            etLatitud.setError("El campo es requerido");
            return;
        }
        if (longitud.isEmpty()) {
            etLongitud.setError("El campo es requerido");
            return;
        }

        Ubicaciones ubicaciones = new Ubicaciones(
                SentenciaSQL.obtenerId(),
                titulo,
                descripcion,
                Double.parseDouble(latitud),
                Double.parseDouble(longitud));

        SentenciaSQL.registrar(ubicaciones);
        Toast.makeText(this, "Se registro", Toast.LENGTH_SHORT).show();
        etTitulo.setText("");
        etDescripcion.setText("");
        etLatitud.setText("");
        etLongitud.setText("");
        //Indicador del cursor
        etTitulo.requestFocus();
    }

    @OnClick(R.id.btnVerMapa)
    public void onBtnVerMapaClicked() {
        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        startActivity(intent);
    }
}
