package com.jcodee.mod2class7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jcodee.mod2class7.modelos.Post;
import com.jcodee.mod2class7.rest.HelperWS;
import com.jcodee.mod2class7.rest.MetodoWS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnPresionar;

    /*
    url -> web services

    [] -> Lista -> ArrayList<Cliente>
    {} -> Objeto -> Cliente

    public class Cliente(){
        private int userId;
        private int id;
    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPresionar = (Button) findViewById(R.id.btnPresionar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnPresionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MetodoWS metodoWS = HelperWS.obtenerConfiguracion().create(MetodoWS.class);
                Call<ArrayList<Post>> call = metodoWS.obtenerPost();
                call.enqueue(new Callback<ArrayList<Post>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                        ArrayList<Post> posts = response.body();
                        Log.d("TAG", "ok");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                        Log.d("TAG", "fail");
                    }
                });

            }
        });
    }
}
