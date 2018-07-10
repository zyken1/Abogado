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

import com.example.nequiz_omen.abogado.adaptadores.ListaJuiciosAdapter;
import com.example.nequiz_omen.abogado.entidades.Usuario;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;

public class Juicios extends AppCompatActivity {

    ListView vistaJuicios;
    ArrayList<String> listaInformacion;

    ArrayList<Usuario> listaUsuario;
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


        //ADAPTADOR PAR APERSONAS

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listaUsuario=new ArrayList<>();

        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recyclerJuicios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaJuiciosAdapter adapter = new ListaJuiciosAdapter(listaUsuario);
        recyclerViewUsuarios.setAdapter(adapter);
    }



    /*==================METODO PARA CONSUTLAR LAS PERSONAS DE LA BD  =================================*/
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario usuario=null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){  /*CICLO  WHILE  PARA REPETIR LA SENTENCIA*/
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            //usuario.setTelefono(cursor.getString(2));


            listaUsuario.add(usuario);
        }

        //se manda a llamar el metodo para agregarlo a la lista que se solicita aqui
        llenarListaUsuarios();
    }

    private void llenarListaUsuarios() {
        //listaUsuario.add(new Usuario(1,"Daniel","548526"));
        //listaUsuario.add(new Usuario(2,"josesito","1212121212"));
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