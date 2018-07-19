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
import com.example.nequiz_omen.abogado.entidades.JuiciosE;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;

public class Juicios extends AppCompatActivity {

    ListView vistaJuicios;
    ArrayList<String> listaInformacion;

    ArrayList<JuiciosE> listaMascotas;
    RecyclerView recyclerViewUsuarios;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

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


        //ADAPTADOR PARA JUICIOS

        listaMascotas=new ArrayList<>();

        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recyclerJuicios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaJuiciosAdapter adapter = new ListaJuiciosAdapter(listaMascotas);
        recyclerViewUsuarios.setAdapter(adapter);
    }



    /*==================METODO PARA CONSUTLAR LAS PERSONAS DE LA BD  =================================*/
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        JuiciosE juicios=null;
        listaMascotas =new ArrayList<JuiciosE>();
        //select * from JuiciosE
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_JUICIOS,null);

        while (cursor.moveToNext()){
            juicios=new JuiciosE();
            juicios.setIdDuenio(cursor.getInt(0));
            juicios.setIdJuicios(cursor.getInt(1));
            juicios.setNombreExpediente(cursor.getString(2));
            juicios.setClientes(cursor.getString(3));


            listaMascotas.add(juicios);
        }

        //se manda a llamar el metodo para agregarlo a la lista que se solicita aqui
        llenarListaJuicios();
        //obtenerLista();
    }

    private void llenarListaJuicios() {
        listaMascotas.add(new JuiciosE(12,13,"Nombre de expediente","Cliente"));
        //listaUsuario.add(new Usuario(2,"josesito","1212121212"));
    }


    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        /*for (int i=0; i<listaMascotas.size();i++){
            listaInformacion.add(listaMascotas.get(i).getIdMascota()+" - "
                    +listaMascotas.get(i).getNombreMascota());
        }*/

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