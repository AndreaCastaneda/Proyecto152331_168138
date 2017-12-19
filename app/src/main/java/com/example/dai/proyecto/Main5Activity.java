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

public class Main5Activity extends AppCompatActivity {
    private EditText id,nomArt,precioArt;
    private int cU5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        id=(EditText)findViewById(R.id.etId);
        nomArt=(EditText)findViewById(R.id.etArticulo);
        precioArt=(EditText)findViewById(R.id.etPrecio);
        Bundle bundle = this.getIntent().getExtras();
        cU5= Integer.parseInt(bundle.get("id").toString());
    }

    //Método que depeja los edit texts
    public void limpiar(View v){
        id.setText("");
        nomArt.setText("");
        precioArt.setText("");
    }

    //Método que agrega los artículos
    public void agregaArt(View v){
        String nom;
        String pre;
        String idA;
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        idA= id.getText().toString();
        nom=nomArt.getText().toString();
        pre=precioArt.toString();
        if(!idA.equals("")&&!nom.equals("")&&!pre.equals("")){
            int idArt = Integer.parseInt(idA);
            double precio = Double.parseDouble(pre);
            String query = "select nombre from articulo where id =" +idArt;
            Cursor cursor = bd.rawQuery(query,null);
            if(cursor.moveToFirst()){
                Toast.makeText(this, "La id ya está utilizada", Toast.LENGTH_SHORT).show();
            }
            else{
                ContentValues registro=new ContentValues();
                registro.put("id",idArt);
                registro.put("nombre",nom);
                registro.put("precio",precio);
                registro.put("cu",cU5);
                long i = bd.insert("articulo",null,registro);
                if(i>0){
                    Toast.makeText(this, "Articulo agregado correctamente", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Articulo no agregado", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
            Toast.makeText(this, "Se deben llenar todos los campos", Toast.LENGTH_SHORT).show();
        bd.close();
        limpiar(v);

    }
    //Metodo para regresar a la ventana de búsqueda
    public void regresa(View v){
        Intent intent=new Intent(Main5Activity.this,Main3Activity.class);
        Bundle b = new Bundle();
        b.putInt("id",cU5);
        intent.putExtras(b);
        startActivity(intent);
    }
}
