package com.example.mibasedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminBD extends SQLiteOpenHelper {

    public AdminBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDatos){
        baseDatos.execSQL("create table usuario(cedula int primary key, nombre text, contrasena text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase baseDato, int oldVersion, int newVersion) {

    }

}
