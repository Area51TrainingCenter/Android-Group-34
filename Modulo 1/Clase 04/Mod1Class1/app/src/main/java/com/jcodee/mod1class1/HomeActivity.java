package com.jcodee.mod1class1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private EditText texto;
    private Button procesar, pasar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Agregamos el diseño a mostrar y hacemos referencia al archivo XML
        setContentView(R.layout.activity_home);


        texto = (EditText) findViewById(R.id.etTexto);
        procesar = (Button) findViewById(R.id.btnPresionar);
        resultado = (TextView) findViewById(R.id.tvResultado);
        pasar = (Button) findViewById(R.id.btnPasar);

        //Balsamiq Mockups
        //Adobe XD
        //Sketch

        Log.d("TAG", "->onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "->onResume");

        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegistroActivity.class);

                startActivity(intent);
            }
        });

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Evento onClick

                //Obtenemos el texto que escribimos
                String textoEscrito = texto.getText().toString();

                //Validamos que el texto que se ha escrito tenga texto
                if (textoEscrito.trim().isEmpty()) {
                    //Si es que no tiene texto, se mostrara un mensaje de error
                    //Toast.makeText(HomeActivity.this,
                    //        getResources().getString(R.string.error),
                    //        Toast.LENGTH_SHORT).show();

                    Toast toast = Toast.makeText(HomeActivity.this,
                            getResources().getString(R.string.error),
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else {
                    //Si es que todo está ok, se muestra el texto escrito

                    //Cambiamos el texto a mostrar
                    resultado.setText(textoEscrito);
                    //Integer.parseInt()
                    //Double.parseDouble()
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "->onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "->onRestart");
    }
}
