package com.jcodee.mod3class04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    private SimpleDraweeView sdvImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        SharedPreferences sp =
                getSharedPreferences("facebook", MODE_PRIVATE);
        if(sp.contains("nombre")){
            Intent intent=new Intent();
        }

        sdvImagen = (SimpleDraweeView) findViewById(R.id.sdvImagen);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                Toast.makeText(MainActivity.this, "object", Toast.LENGTH_SHORT).show();

                                //opt - optional
                                String rutaImagen =
                                        "https://graph.facebook.com/" +
                                                object.optString("id") +
                                                "/picture?type=large";
                                sdvImagen.setImageURI(Uri.parse(rutaImagen));

                                SharedPreferences.Editor editor =
                                        getSharedPreferences("facebook", MODE_PRIVATE).edit();
                                editor.putString("nombre", object.optString("first_name"));
                                editor.putString("apellido", object.optString("last_name"));
                                editor.apply();

                                //Limpiar datos
                                //editor.clear();

                                //Vamos a otra pantalla
                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                intent.putExtra("imagen", rutaImagen);
                                startActivity(intent);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,birthday,first_name,gender,last_name,email");
                request.setParameters(parameters);
                request.executeAsync();


                Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
