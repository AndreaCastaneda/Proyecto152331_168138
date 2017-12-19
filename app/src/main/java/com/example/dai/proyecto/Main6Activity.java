package com.example.dai.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    private int cU6;
    private Spinner modifica;
    private EditText precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Bundle bundle = this.getIntent().getExtras();
        cU6= Integer.parseInt(bundle.get("id").toString());
        modifica=(Spinner)findViewById(R.id.spA);

        precio=(EditText)findViewById(R.id.precioModifica);
    }

    //Metodo para regresar a la ventana 3
    public void ventanaPrincipal(View v){
        Intent intent= new Intent(Main6Activity.this,Main3Activity.class);
        startActivity(intent);
    }

    //Metodo para llener el spinner de articulos
    public void llenarS(View v){
        ArrayList<String> articulos = new ArrayList<>();
        String query = "select id from articulo where cu="+cU6;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "articulos",null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor = bd.rawQuery(query,null);
        if(cursor.moveToFirst()){
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
                articulos.add(cursor.getString(cursor.getColumnIndex("id")));
        }
        else
            articulos.add("No tienes artículos");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,articulos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modifica.setAdapter(adapter);
    }

    //Metodo para modificar el precio de un artículo
    public void modifica(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String preprecio = precio.getText().toString();
        if(!preprecio.equals("")){
            double  precio = Double.parseDouble(preprecio);
            int id = Integer.parseInt(modifica.getSelectedItem().toString());
            String query = "select nombre from articulo where id="+ id;
            Cursor cursor = bd.rawQuery(query,null);
            if(cursor.moveToFirst()){
                ContentValues registro = new ContentValues();
                registro.put("nombre",cursor.getString(0));
                registro.put("precio",precio);
                registro.put("cu",cU6);
                int cant = bd.update("articulo",registro,"id="+id,null);
                bd.close();
                if(cant==1)
                    Toast.makeText(this,"Se modificó el precio",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"No se modificó el precio",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this,"No existe el artículo",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Ingresa un precio",Toast.LENGTH_SHORT).show();
    }

    //Metodo para eliminar un artículo
    public void elimina(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        int id = Integer.parseInt(modifica.getSelectedItem().toString());
        int cant = bd.delete("articulo","id="+id,null);
        bd.close();
        if(cant==1)
            Toast.makeText(this,"Se eliminó el artículo",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No se eliminó el artículo",Toast.LENGTH_SHORT).show();
    }
}
