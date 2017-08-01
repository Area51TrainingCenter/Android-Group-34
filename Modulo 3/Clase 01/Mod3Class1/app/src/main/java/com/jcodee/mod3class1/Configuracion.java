package com.jcodee.mod3class1;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by johannfjs on 31/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("mod3class1.realm")
                        .schemaVersion(1)
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
