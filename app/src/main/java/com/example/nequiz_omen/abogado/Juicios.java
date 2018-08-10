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
import android.widget.Toast;

import com.example.nequiz_omen.abogado.adaptadores.ListaJuiciosAdapter;
import com.example.nequiz_omen.abogado.entidades.JuiciosE;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;

public class Juicios extends AppCompatActivity {

    ListView vistaJuicios;
    ArrayList<String> listaInformacion;
    ArrayList<JuiciosE> listaMascotas;
    RecyclerView recyclerViewUsuarios;
    int numerito,posicion;
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

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        //ADAPTADOR PARA JUICIOS

        listaMascotas=new ArrayList<>();

        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recyclerJuicios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaJuiciosAdapter adapter = new ListaJuiciosAdapter(listaMascotas);
        recyclerViewUsuarios.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //numerito = listaMascotas.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getIdJuicios();
                //Toast.makeText(getApplication(),listaMascotas.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getIdJuicios(), Toast.LENGTH_LONG).show();
                posicion = recyclerViewUsuarios.getChildAdapterPosition(v);
                detalle_Juicio();
            }
        });
    }



    /*==================METODO PARA CONSUTLAR LAS PERSONAS DE LA BD  =================================*/
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        JuiciosE juicios=null;
        listaMascotas =new ArrayList<JuiciosE>();
        //select * from JuiciosE
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_JUICIOS,null);

        while (cursor.moveToNext()) {
            juicios = new JuiciosE();
            juicios.setIdJuicios(cursor.getInt(0));
            juicios.setNombreExpediente(cursor.getString(1));
            juicios.setCliente_extra(cursor.getString(2));
            juicios.setContrario(cursor.getString(3));
            juicios.setContrario_extra(cursor.getString(4));
            juicios.setJuicio(cursor.getString(5));
            juicios.setAsunto(cursor.getString(6));
            juicios.setInstancia(cursor.getString(7));
            juicios.setEtapa(cursor.getString(8));
            juicios.setTramite(cursor.getString(9));
            juicios.setFecha_tramite(cursor.getString(10));
            juicios.setTramite_extra(cursor.getString(11));
            juicios.setFechaTramite_extra(cursor.getString(12));
            juicios.setCosto_juicio(cursor.getString(13));
            juicios.setResta_pago(cursor.getString(14));
            juicios.setAbono(cursor.getString(15));
            juicios.setFecha_pago(cursor.getString(16));
            juicios.setAbono_extra(cursor.getString(17));
            juicios.setAbono_extra(cursor.getString(18));
            juicios.setFechaAbono_extra(cursor.getString(19));

            //juicios.setAsunto(cursor.getString(8));
            //juicios.setCliente_extra(cursor.getString(3));
            //juicios.setIdDuenio(cursor.getInt(1));

            listaMascotas.add(juicios);
            /*System.out.println("*********************Juicio ==> " + juicios);
            System.out.println("*********************Juicio ==> " + juicios.getIdJuicios());
            System.out.println("*********************Juicio ==> " + juicios.getNombreExpediente());
            System.out.println("*********************Juicio ==> " + juicios.getCliente_extra());*/

        }
        /*se manda a llamar el metodo para agregarlo a la lista que se solicita aqui  ES TEMPORAL
        try{
            if (juicios.toString().length() <= 0){
                llenarListaJuicios();
                }
            }catch (Exception e){
            llenarListaJuicios();
            }*/

        obtenerLista();
    }

    private void llenarListaJuicios() {
            listaMascotas.add(new JuiciosE(10, 11, "Ingresa un Expediente", "Prueba", "-------", "--------", "Tipo de juicio", "Asunto", "---------", "---------", "---------", "---------", "---------", "---------", "---------", "---------", "---------", "---------", "---------", "---------"));
            //listaUsuario.add(new Usuario(2,"josesito","1212121212"));

    }


    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();
        for (int i=0; i<listaMascotas.size();i++){
            listaInformacion.add(listaMascotas.get(i).getIdJuicios()+" - "
                    +listaMascotas.get(i).getNombreExpediente());
        }
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
    public void detalle_Juicio() {
        /*Toast toastCenter = Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT);
        toastCenter.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toastCenter.show();*/

        JuiciosE user = listaMascotas.get(posicion);       //Usuario user = listaUsuario.get(0);

        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", user);

        Intent intent = new Intent(this, Juicios_Edicion.class);
        intent.putExtras(bundle);
        startActivity(intent);
        //finish();
    }



  /*===============   CICLOS DE VIDA DEL ACTIVITY   =================*/

    @Override
    protected void onResume() {
        /*listaMascotas = null;
        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recyclerJuicios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaJuiciosAdapter adapter = new ListaJuiciosAdapter(listaMascotas);
        recyclerViewUsuarios.setAdapter(adapter);
        //System.out.println("***********RESUMEN");*/
        super.onResume();
    }


}