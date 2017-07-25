package com.jcodee.mod2class3;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class IniciarActivity extends AppCompatActivity {

    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.tilUsuario)
    TextInputLayout tilUsuario;
    @BindView(R.id.etContrasenia)
    EditText etContrasenia;
    @BindView(R.id.tilContrasenia)
    TextInputLayout tilContrasenia;

    private MetodoSQL metodoSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);
        ButterKnife.bind(this);

        /*
        getWindow().getWindowManager().getDefaultDisplay().getWidth();
        getWindow().getWindowManager().getDefaultDisplay().getHeight();
        */

        SharedPreferences sharedPreferences = getSharedPreferences("class3", MODE_PRIVATE);
        String usuario = sharedPreferences.getString("usuario", "");
        if (!usuario.isEmpty()) {
            Intent intent = new Intent(IniciarActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        metodoSQL = new MetodoSQL();
    }

    @OnClick(R.id.btnIniciar)
    public void onBtnIniciarClicked() {

        String usuario = etUsuario.getText().toString();
        String contrasenia = etContrasenia.getText().toString();

        if (usuario.isEmpty()) {
            tilUsuario.setError("Campo es requerido");
            return;
        }
        if (contrasenia.isEmpty()) {
            tilContrasenia.setError("Campo es requerido");
            return;
        }

        UsuarioEntidad usuarioEntidad = metodoSQL.validarUsuario(usuario, contrasenia);
        if (usuarioEntidad == null) {
            Toast.makeText(this, "Usuario y/o contraseña es incorrecto", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor =
                    getSharedPreferences("class3", MODE_PRIVATE).edit();
            editor.putString("usuario", usuarioEntidad.getUsuario());
            editor.putString("nombre", usuarioEntidad.getNombre());
            editor.putString("apellido", usuarioEntidad.getApellido());
            editor.apply();

            Intent intent = new Intent(IniciarActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.btnRegistrar)
    public void onBtnRegistrarClicked() {
        Intent intent = new Intent(IniciarActivity.this, RegistroActivity.class);
        startActivity(intent);
    }
}
