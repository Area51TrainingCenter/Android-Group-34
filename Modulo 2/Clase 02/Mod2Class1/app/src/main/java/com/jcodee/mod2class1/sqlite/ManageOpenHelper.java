package com.jcodee.mod2class1.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by johannfjs on 10/07/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class ManageOpenHelper extends SQLiteOpenHelper {
    //Creamos variables para la versión de la base de datos y el nombre
    private final static String NAME = "clasebd.db";
    private final static int VERSION = 1;

    //Constructor para iniciar la base de datos
    public ManageOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }
    //http://www.aulafacil.com/cursos/l13184/informatica/bases-de-datos/mysql/creando-tablas
    //Esto solamente se ejecutará una vez para poder crear la base de datos por primera vez
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación de tablas
        sqLiteDatabase.execSQL("create table tb_empresa(id integer primary key autoincrement," +
                "nombre varchar(100))");
        sqLiteDatabase.execSQL("create table tb_personal(id integer primary key autoincrement," +
                "cargo varchar(100)," +
                "nombre varchar(100)," +
                "nombre_empresa varchar(100))");

        //Inserción de registros
        sqLiteDatabase.execSQL("insert into tb_empresa(nombre) values('Area51')");
        sqLiteDatabase.execSQL("insert into tb_empresa(nombre) values('Sunat')");
        sqLiteDatabase.execSQL("insert into tb_empresa(nombre) values('Reniec')");
    }

    //Para realizar actualizaciones a la base de datos, o cuando haya algún cambio
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
