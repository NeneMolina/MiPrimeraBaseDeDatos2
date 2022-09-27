package com.example.mibasedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText document, name, password;
    Button registro, consulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        document=(EditText)findViewById(R.id.TextCedula);
        name=(EditText)findViewById(R.id.Textnombre);
        password=(EditText)findViewById(R.id.TextContrasena);
        registro=(Button) findViewById(R.id.Registrar);
        consulta=(Button) findViewById(R.id.Consultar);


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminBD admin = new AdminBD(MainActivity.this,"BaseDatos",null,1);
                SQLiteDatabase BaseDatos = admin.getWritableDatabase();
                String cedula = document.getText().toString();
                String nombre = name.getText().toString();
                String contrasena = password.getText().toString();

                if (!cedula.isEmpty() && !nombre.isEmpty() && !contrasena.isEmpty()){
                    ContentValues registro = new ContentValues();
                    //Convertimos la ariable a enter
                    int id = Integer.parseInt(document.getText().toString());
                    registro.put("cedula",id);
                    registro.put("nombre",nombre);
                    registro.put("contrasena",contrasena);
                    BaseDatos.insert("usuario",null,registro);
                    BaseDatos.close();
                    document.setText("");
                    name.setText("");
                    password.setText("");
                    Toast.makeText(MainActivity.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Ingrese todos los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminBD admin = new AdminBD(MainActivity.this,"BaseDatos",null,1);
                SQLiteDatabase BasedeDatos = admin.getWritableDatabase();
                String cedulCon = document.getText().toString();
                if (!cedulCon.isEmpty()){
                    Cursor fila = BasedeDatos.rawQuery("select nombre, contrasena from usuario where cedula="+cedulCon,null);
                    if (fila.moveToFirst()){
                        name.setText(fila.getString(0));
                        password.setText(fila.getString(1));
                        BasedeDatos.close();
                    }else {
                        Toast.makeText(MainActivity.this,"No se encontro el usuario",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}