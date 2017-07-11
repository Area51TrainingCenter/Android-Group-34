package com.jcodee.mod2class1.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jcodee.mod2class1.modelos.Personal;

import java.util.ArrayList;

/**
 * Created by johannfjs on 10/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class MetodoSQL {
    //Creamos una variable para poder obtener la conexión
    private ManageOpenHelper conexion;

    //En el constructor solicitamos que se ingrese el contexto de la aplicación
    public MetodoSQL(Context context) {
        //Inicializamos la conexión
        conexion = new ManageOpenHelper(context);
    }

    public ArrayList<String> obtenerEmpresas() {
        //Asignamos el permiso de lectura de la base de datos
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Obtiene el resultado de la consulta a realizar
        Cursor cursor = db.rawQuery("select * from tb_empresa", null);
        //Creamos una variable para poder devolver el resultado
        ArrayList<String> lista = null;
        //verificamos si se han traído datos
        if (cursor.moveToFirst()) {
            //Inicializamos la variable de respuesta
            lista = new ArrayList<>();
            //Recorremos el resultado para poder agregar los datos al listado
            do {
                //Obtenemos el dato de la empresa
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                //Agregamos el dato a la lista
                lista.add(nombre);

                //Siempre que haya un siguiente resultado va a ejecutar
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public long agregarPersonal(Personal personal) {
        //Agregamos el tipo de permiso que le vamos a asignar
        SQLiteDatabase db = conexion.getWritableDatabase();

        //Creamos un objeto de tipo Content Values
        ContentValues contentValues = new ContentValues();
        //Agregamos los datos a enviar
        contentValues.put("cargo", personal.getCargo());
        contentValues.put("nombre", personal.getNombre());
        contentValues.put("nombre_empresa", personal.getEmpresa());

        //Insertamos el personal
        return db.insert("tb_personal", null, contentValues);
    }

    public ArrayList<Personal> obtenerPersonal() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_personal", null);
        ArrayList<Personal> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String empresa = cursor.getString(cursor.getColumnIndex("nombre_empresa"));
                String cargo = cursor.getString(cursor.getColumnIndex("cargo"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));

                Personal personal = new Personal(empresa, cargo, nombre);
                personal.setId(id);
                lista.add(personal);
            } while (cursor.moveToNext());
        }
        return lista;
    }
}
