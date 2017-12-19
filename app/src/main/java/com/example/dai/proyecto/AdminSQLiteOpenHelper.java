package com.example.dai.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dai on 30/11/2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //Creas la tabla si no existe
        sqLiteDatabase.execSQL("create table usuario(cu integer primary key, nombre text, contrasena text)");
        sqLiteDatabase.execSQL("create table articulo(id integer primary key, nombre text, precio decimal,cu integer references usuario)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { //Dejas la tabla anterior si existe y la creas
        sqLiteDatabase.execSQL("drop table if exists articulos");
        sqLiteDatabase.execSQL("drop table if exists usuarios");
        sqLiteDatabase.execSQL("create table usuario(cu integer primary key, nombre text, contraseña varchar)");
        sqLiteDatabase.execSQL("create table articulo(id integer primary key , nombre text, precio decimal,cu integer references usuario)");

    }
}
