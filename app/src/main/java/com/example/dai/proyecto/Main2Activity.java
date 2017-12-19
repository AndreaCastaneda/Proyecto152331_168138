package com.example.dai.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText cu,nombre,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nombre=(EditText)findViewById(R.id.etNombre);
        cu=(EditText)findViewById(R.id.etCU);
        pwd=(EditText)findViewById(R.id.etPwd2);
    }

    //Método que depeja los edit texts
    public void limpiar(View v){
        nombre.setText("");
        cu.setText("");
        pwd.setText("");
    }

    //Método para dirigirse a la ventana de inicio
    public void ventana1(View v){
        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }

    //Método para registrar nuevos usuarios
    public void registro1(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase bd =admin.getWritableDatabase();
        String nom,contrasena;
        String clavU;
        nom=nombre.getText().toString();
        clavU=cu.getText().toString();
        contrasena=pwd.getText().toString();
        if(!nom.equals("")&&!clavU.equals("")&&!contrasena.equals("")){
            String query = "select nombre from usuario where cu = "+ clavU;
            Cursor cursor = bd.rawQuery(query,null);
            if(cursor.moveToFirst())
                Toast.makeText(this,"Ya hay un usario con esa clave",Toast.LENGTH_SHORT).show();
            else {
                int cU = Integer.parseInt(clavU);
                ContentValues registro = new ContentValues();
                registro.put("cu", cU);
                registro.put("nombre", nom);
                registro.put("contrasena", contrasena);
                long i = bd.insert("usuario", null, registro);
                if (i > 0) {
                    Toast.makeText(this, "Usuario agregado correctamente", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "No se agregò el usuario", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this,"Se necesitan llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
        limpiar(v);
        bd.close();
    }
}
