package com.example.nequiz_omen.abogado;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nequiz_omen.abogado.entidades.E_juicio;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;

public class Juicios extends AppCompatActivity {

    ListView vistaJuicios;
    ArrayList<String> listaInformacion;
    ArrayList<E_juicio> listaUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios);
                     /*=============   PARA LA BD   ===========*/
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_juicios", null, 1);
        vistaJuicios = (ListView) findViewById(R.id.vistaJuicios);
        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        vistaJuicios.setAdapter(adaptador);

        vistaJuicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "id: " + listaUsuarios.get(position).getId() + "\n";
                informacion += "Expediente: " + listaUsuarios.get(position).getExpediente() + "\n";
                informacion += "Cliente: " + listaUsuarios.get(position).getCliente() + "\n";

                //LANZA UN MENSAJE  CON LOS DATOS SOLICITADOS
                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();

                E_juicio juicio = listaUsuarios.get(position);

                //Intent intent = new Intent(ConsultarListaListViewActivity.this, DetalleUsuarioActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", juicio);
                //intent.putExtras(bundle);
                //startActivity(intent);
            }
        });
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

        E_juicio usuario = null;
        listaUsuarios=new ArrayList<E_juicio>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_JUICIOS,null);

        while (cursor.moveToNext()){
            usuario=new E_juicio();
            usuario.setId(cursor.getInt(0));
            usuario.setExpediente(cursor.getString(1));
            usuario.setCliente(cursor.getString(2));

            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+" - "
                    +listaUsuarios.get(i).getExpediente());
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //BOTON DE ACCION PARA Acitivity DE JUICIOS_EDICION
     public void editar_juicio(View view) {
         Intent i = new Intent(this, Juicios_Edicion.class);
         startActivity(i);
    }
}