package com.jcodee.mod2class3.database;

import com.jcodee.mod2class3.entidades.ClienteEntidad;
import com.jcodee.mod2class3.entidades.MarcaEntidad;
import com.jcodee.mod2class3.entidades.ModeloEntidad;
import com.jcodee.mod2class3.entidades.UsuarioEntidad;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by johannfjs on 14/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class MetodoSQL {
    public void agregarMarca(MarcaEntidad entidad) {
        //Obtenemos la configuración de la base de datos
        Realm realm = Realm.getDefaultInstance();
        //Abrimos una transacción nueva
        realm.beginTransaction();
        //Insertamos el registro a nuestra base de datos
        realm.copyToRealm(entidad);
        //Se guarda el registro en la base de datos
        realm.commitTransaction();
    }

    public long obtenerIdMarca() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MarcaEntidad.class).count() + 1;
    }

    public void agregar(Object object) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate((RealmModel) object);
        realm.commitTransaction();
    }

    public void eliminarCliente(long idCliente) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();

            //Obtenemos el registro de cliente por el id
            ClienteEntidad clienteEntidad =
                    realm.where(ClienteEntidad.class)
                            .equalTo("id", idCliente)
                            .findFirst();

            //Verificamos si el registro es diferente de vacío
            if (clienteEntidad != null) {
                //Eliminamos el registro
                clienteEntidad.deleteFromRealm();
            }

            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public RealmResults<MarcaEntidad> obtenerMarcas() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MarcaEntidad.class).findAll();
    }

    public long obtenerIdModelo() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(ModeloEntidad.class).count() + 1;
    }

    public RealmResults<ModeloEntidad> obtenerModelosPorMarca(long idMarca) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(ModeloEntidad.class).equalTo("marca.id", idMarca).findAll();
    }

    public long obtenerIdCliente() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(ClienteEntidad.class).count() + 1;
    }

    public RealmResults<ClienteEntidad> obtenerClientes() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(ClienteEntidad.class).findAll();
    }

    public long obtenerUsuarioId() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UsuarioEntidad.class).count() + 1;
    }

    public UsuarioEntidad validarExisteUsuario(String usuario) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UsuarioEntidad.class)
                .equalTo("usuario", usuario)
                .findFirst();
    }

    public UsuarioEntidad validarUsuario(String usuario, String contrasenia) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UsuarioEntidad.class)
                .equalTo("usuario", usuario)
                .equalTo("contrasenia", contrasenia)
                .findFirst();
    }


}
