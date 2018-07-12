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
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nequiz_omen.abogado.adaptadores.ListaPersonasAdapter;
import com.example.nequiz_omen.abogado.entidades.Usuario;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;

public class Cliente extends AppCompatActivity {
    TextView campoId, campoNombre, campoTelefono;
    //VARIABLES
    ArrayList<Usuario> listaUsuario;
    RecyclerView recyclerViewUsuarios;

    ArrayList<String> listaInformacion;

    ConexionSQLiteHelper conn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

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

        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recyclerPersonas);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaPersonasAdapter adapter = new ListaPersonasAdapter(listaUsuario);
        recyclerViewUsuarios.setAdapter(adapter);
}


    /*==================METODO PARA CONSULTAR LAS PERSONAS DE LA BD  =================================*/
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario usuario=null;
        int var = 1;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){  /*CICLO  WHILE  PARA REPETIR LA SENTENCIA*/
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setE_mail(cursor.getString(3));

            listaUsuario.add(usuario);
            System.out.println("*********************  usuario " + usuario);
        }
        //se manda a llamar el metodo para agregarlo a la lista que se solicita aqui
        llenarListaUsuarios();
    }


    private void llenarListaUsuarios() {
        listaUsuario.add(new Usuario(100,"Prueba","MORAL","prueba@prueba.com","Masculino","00/00/00","siempre viva","121212","123456789","23456789","papa y mama","ninguna Nota de momento"));
        //listaUsuario.add(new Usuario(2,"josesito","1212121212"));
    }
    /*==================FIN  DE LA BD  =================================*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add) {
            Intent i = new Intent(this, Cliente_Formulario.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    //========   Eventos a ejecutar al darle click alguna de las imagenes que se muestran en CLIENTES
    public void Detalle_Cliente( View view) {

        System.out.println("LE DISTE CLICK CHAVO ");
        System.out.println("************  " + listaUsuario);

        //Object[] nombres = listaUsuario.toArray();
        //System.out.println(nombres[1]);
        int user = 1;
        System.out.println(listaUsuario.get(user).getNombre());

       /* SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuario = null;

        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO + "WHERE USUARIO " + user , null);

        while (cursor.moveToNext()) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setE_mail(cursor.getString(3));
            }

        System.out.println("*********************  listaUsuario " + listaUsuario);
        System.out.println("*********************  usuario " + usuario);  */


        //LANZA UN MENSAJE  CON LOS DATOS SOLICITADOS
        //Toast.makeText(getApplicationContext(), id , Toast.LENGTH_LONG).show();
        //System.out.println("********************+  ID "+id);

        //Intent i = new Intent(this, Cliente_Edicion.class);
        //startActivity(i);
    }


}//END  CLASS
