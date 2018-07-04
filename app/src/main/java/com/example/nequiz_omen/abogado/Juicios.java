package com.example.nequiz_omen.abogado;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nequiz_omen.abogado.adaptadores.ListaJuiciosAdapter;
import com.example.nequiz_omen.abogado.entidades.E_juicio;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;

public class Juicios extends AppCompatActivity {

    ListView vistaJuicios;
    ArrayList<String> listaInformacion;

    ArrayList<E_juicio> listaUsuario;
    RecyclerView recyclerViewUsuarios;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios);
                     /*=============   PARA LA BD   ===========*/
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_juicios", null, 1);
        listaUsuario = new ArrayList<>();

        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recyclerJuicios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaJuiciosAdapter adapter = new ListaJuiciosAdapter(listaUsuario);
        recyclerViewUsuarios.setAdapter(adapter);


                /*=============   AQUI TERMINA LO DE  LA BD   ===========*/


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


             /*   ==============   METODO CONSULTAR PERSONAS EN LA BD  ==================0*/
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        E_juicio usuario=null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_JUICIOS,null);

        while (cursor.moveToNext()){  /*CICLO  WHILE  PARA REPETIR LA SENTENCIA*/
            usuario = new E_juicio();
            usuario.setId(cursor.getInt(0));
            usuario.setExpediente(cursor.getString(1));
            usuario.setCliente(cursor.getString(2));


            listaUsuario.add(usuario);
        }
        //se manda a llamar el metodo para agregarlo a la lista que se solicita aqui
        //llenarListaUsuarios();
    }

         /*=====================     AQUI TENMINAN LOS METODOS DE LA BD    ========================*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juicios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

        Toast toastCenter = Toast.makeText(getApplicationContext(),"POSICION CENTRO",Toast.LENGTH_SHORT);
        toastCenter.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toastCenter.show();

        //Intent i = new Intent(this,Juicios_Edicion.class);
        //startActivity(i);
    }


}