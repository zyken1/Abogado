package com.example.nequiz_omen.abogado;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.nequiz_omen.abogado.adaptadores.ListaPersonasAdapter;
import com.example.nequiz_omen.abogado.entidades.Mascota;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;

public class Juicios extends AppCompatActivity {

    ListView vistaJuicios;
    ArrayList<String> listaInformacion;

    ArrayList<Mascota> listaUsuario;
    RecyclerView recyclerViewUsuarios;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                finish();
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juicios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add) {
            Intent i = new Intent(this, Juicios_Formulario.class);
            startActivity(i);
              //finish(); //Finaliza la actividad
            return true;
    }
        return super.onOptionsItemSelected(item);
    }





    //Eventos a ejecutar al darle click alguna de las imagenes que se muestran en JUICIOS
    public void Editar_juicios(View v) {

        //Toast toastCenter = Toast.makeText(getApplicationContext(),"POSICION CENTRO",Toast.LENGTH_SHORT);
        //toastCenter.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        //toastCenter.show();

        //Intent i = new Intent(this,Juicios_Edicion.class);
        //startActivity(i);
    }



  /*===============   CICLOS DE VIDA DEL ACTIVITY   =================*/
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(getApplicationContext(),"onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //System.out.println("***********Activity JUICIOS onResume ");
        //Toast.makeText(getApplicationContext()," onResume ",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //System.out.println("***********PAUSA");
    }
}