package com.example.dai.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText cu,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cu=(EditText)findViewById(R.id.etCU);
        pwd=(EditText)findViewById(R.id.etPwd);
    }
    //Método que depeja los edit texts
    public void limpiar(View v){
        cu.setText("");
        pwd.setText("");
    }

    //Método para dirigirse a la ventana de Registro
    public void ventana2(View v){
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

    //Método para dirigirse a la ventana de Opciones (Busqueda o Registro de articulos)
    public void ventana3(View v){
        String pass;
        int cU;
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        cU=int.class.cast(cu.getText().toString());
        pass=pwd.getText().toString();
        Cursor fila=db.rawQuery("select contraseña from usuario where cu="+cU+" and contraseña ="+pwd,null);
        if(fila.moveToFirst()){
            Intent intent=new Intent(MainActivity.this,Main3Activity.class);
            Bundle b = new Bundle();
            b.putInt("id",cU);
            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            limpiar(v);
            Toast.makeText(this,"Clave única o usuarios incorrectos",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
