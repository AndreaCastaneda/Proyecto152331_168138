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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private EditText busqueda;
    private ListView listaP;
    private int cU3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        busqueda=(EditText)findViewById(R.id.etBusqueda);
        listaP = (ListView)findViewById(R.id.lvListView);
        Bundle bundle = this.getIntent().getExtras();
        cU3=Integer.parseInt(bundle.get("id").toString());
    }

    //Método que depeja los edit texts
    public void limpiar(View v){

        busqueda.setText("");
    }
    //Metodo para buscar un articulo
    public void buscar(View v){
        String nom;
        ArrayList<String> lista = new ArrayList<>();
        nom = busqueda.getText().toString();
        if(!nom.equals("")){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
            String query = "select nombre,precio from articulo where nombre='"+ nom + "'";
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(query,null);
            if(cursor.moveToFirst()){
                StringBuilder str = new StringBuilder();

                while (cursor.moveToNext()){
                    str.append(cursor.getString(0));
                    str.append(cursor.getDouble(1));
                    lista.add(str.toString());
                    str.setLength(0);
                }
                ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,lista);
                listaP.setAdapter(data);
            }
            else
                Toast.makeText(this,"No hay artículos",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Ingresa algo en en el campo de busqueda",Toast.LENGTH_SHORT).show();
    }
    //Metodo para pasar a la ventana de inciciar sesión
    public void ventana1(View v){
        Intent intent=new Intent(Main3Activity.this,MainActivity.class);
        startActivity(intent);
    }
    //Método para pasar a la ventana de la pagina web
    public void ventana4(View v){
        Intent intent=new Intent(Main3Activity.this,Main4Activity.class);
        startActivity(intent);
    }
    //Metodo para pasar a la ventana de registrar un artículo
    public  void ventana5(View v){
        Intent intent =new Intent(Main3Activity.this,Main5Activity.class);
        Bundle b = new Bundle();
        b.putInt("id",cU3);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void ventana6(View v){
        Intent intent =new Intent(Main3Activity.this,Main6Activity.class);
        Bundle b = new Bundle();
        b.putInt("id",cU3);
        intent.putExtras(b);
        startActivity(intent);
    }
}
