package com.jcodee.mod2class3;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.mod2class3.database.MetodoSQL;
import com.jcodee.mod2class3.entidades.UsuarioEntidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity {

    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.tilNombre)
    TextInputLayout tilNombre;
    @BindView(R.id.etApellido)
    EditText etApellido;
    @BindView(R.id.tilApellido)
    TextInputLayout tilApellido;
    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.tilUsuario)
    TextInputLayout tilUsuario;
    @BindView(R.id.etContrasenia)
    EditText etContrasenia;
    @BindView(R.id.tilContrasenia)
    TextInputLayout tilContrasenia;
    @BindView(R.id.etRepContrasenia)
    EditText etRepContrasenia;
    @BindView(R.id.tilRepContrasenia)
    TextInputLayout tilRepContrasenia;

    private MetodoSQL metodoSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);

        metodoSQL = new MetodoSQL();
    }

    @OnClick(R.id.btnGuardar)
    public void onViewClicked() {

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String contrasenia = etContrasenia.getText().toString();
        String repContrasenia = etRepContrasenia.getText().toString();
        String usuario = etUsuario.getText().toString();

        if (nombre.isEmpty()) {
            tilNombre.setError("Campo es requerido");
            return;
        }
        if (apellido.isEmpty()) {
            tilApellido.setError("Campo es requerido");
            return;
        }
        if (usuario.isEmpty()) {
            tilUsuario.setError("Campo es requerido");
            return;
        }
        if (contrasenia.isEmpty()) {
            tilContrasenia.setError("Campo es requerido");
            return;
        }
        if (repContrasenia.isEmpty()) {
            tilRepContrasenia.setError("Campo es requerido");
            return;
        }
        if (!contrasenia.equals(repContrasenia)) {
            tilRepContrasenia.setError("Contraseñas no coinciden");
            return;
        }

        UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
        usuarioEntidad.setId(metodoSQL.obtenerUsuarioId());
        usuarioEntidad.setNombre(nombre);
        usuarioEntidad.setApellido(apellido);
        usuarioEntidad.setUsuario(usuario);
        usuarioEntidad.setContrasenia(contrasenia);

        UsuarioEntidad usuarioEntidad1 = metodoSQL.validarExisteUsuario(usuario);
        if (usuarioEntidad1 == null) {
            metodoSQL.agregar(usuarioEntidad);
            Toast.makeText(this, "Se registro correctamente " + usuario, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
        }


    }
}
