package com.jcodee.mod2class3;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by johannfjs on 14/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

//https://realm.io/docs/get-started/android-demo-app/
public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos la base de datos
        Realm.init(getApplicationContext());
        //Creamos la nueva configuración de la base de datos
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("clase3.realm")
                        .schemaVersion(1)
                        .build();
        //Cambiamos la configuración por la que hemos creado
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
