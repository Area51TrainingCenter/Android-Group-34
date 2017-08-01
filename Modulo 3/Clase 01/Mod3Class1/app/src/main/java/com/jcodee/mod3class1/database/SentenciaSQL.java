package com.jcodee.mod3class1.database;

import com.jcodee.mod3class1.entidades.Ubicaciones;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by johannfjs on 31/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class SentenciaSQL {
    //Agregar un dato a la base de datos
    public static void registrar(Ubicaciones ubicaciones) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(ubicaciones);
        realm.commitTransaction();
    }

    //Obtener todos los registros de la base de datos
    public static RealmResults<Ubicaciones> obtener() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Ubicaciones.class).findAll();
    }

    //Obtener el ultimo id
    public static long obtenerId() {
        Realm realm = Realm.getDefaultInstance();
        long cantidad = realm.where(Ubicaciones.class).count();

        /*
        Ubicaciones ubicaciones = realm.where(Ubicaciones.class)
                .findAllSorted("id", Sort.DESCENDING).first();
        */

        return cantidad + 1;
    }
}
