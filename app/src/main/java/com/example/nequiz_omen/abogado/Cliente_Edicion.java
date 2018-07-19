package com.example.nequiz_omen.abogado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nequiz_omen.abogado.entidades.Usuario;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente_Edicion extends AppCompatActivity {
    TextView campoId, campoNombre, campoTelefono;

    int globalId;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    ConexionSQLiteHelper conn ;
    /*private int[] tabIcons = { R.drawable.apple, R.drawable.orange, R.drawable.grapes, R.drawable.banana  };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente__edicion);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios",null,1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //soportar  el manifest de la barra de accion

        //campoId = (TextView) findViewById(R.id.campoId);
        //campoNombre = (TextView) findViewById(R.id.campoNombre);
        //campoTelefono = (TextView) findViewById(R.id.campoTelefono);

        /*===============Aqui va el Bundle ======================*/
        Bundle objetoEnviado = getIntent().getExtras();  //instanciar el Bundle
        Usuario user = null;

        if (objetoEnviado != null) {
            user = (Usuario) objetoEnviado.getSerializable("usuario");
            int id = user.getId();
            String nombre = user.getNombre();
            String Tipo = user.getTipo_persona();
            String email = user.getE_mail();
            String genero =user.getGenero();
            String nacimiento = user.getFechaNacimiento();
            String direccion = user.getDireccion();
            String telMovil = user.getTel_movil();
            String telCasa = user.getTel_casa();
            String telOficina = user.getTel_oficina();
            String dependientes = user.getDependientes();
            String notas = user.getNotas();

            globalId = id;
            //campoId.setText(user.getNombre());
            System.out.println("********Objeto Recibido ====>  " + objetoEnviado);
            System.out.println("********Bundle Recibido ====>  " + user);
            System.out.println("********Bundle ID ====>  " + id);



        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //editor.putString("e_mail", texto);
        //editor.putString("nombre", user.getNombre());
        //editor.putString("id", String.valueOf(id));
        editor.putString("nombre",nombre );
        editor.putString("tipoPersona", Tipo);
        editor.putString("e-mail", email);
        editor.putString("genero", genero);
        editor.putString("nacimiento", nacimiento);
        editor.putString("direccion", direccion);
        editor.putString("telMovil", telMovil);
        editor.putString("telCasa", telCasa);
        editor.putString("telOficina", telOficina);
        editor.putString("dependientes", dependientes);
        editor.putString("notas", notas);
        editor.commit();
    }
        /*===============Aqui Termina el Bundle ======================*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        addTabs(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //setupTabIcons();


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                Intent i = new Intent(Cliente_Edicion.this, Cliente.class);
                startActivity(i);
                finish();
            }
        });
    }

     /*private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        //tabLayout.getTabAt(3).setIcon(tabIcons[3]);
     }*/

    @Override  // SE AÑADE MENU DE SETTINGS
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente_edicion, menu);
        return true;
    }


    private void addTabs(ViewPager viewPager) {
        Cliente_Edicion.ViewPagerAdapter adapter = new Cliente_Edicion.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ClienteFragment(), "Datos del Cliente");
        adapter.addFrag(new ClienteJuiciosFragment(), "JuiciosE del Cliente");
        //adapter.addFrag(new HonorariosFragment(), "Honorarios");
        //adapter.addFrag(new BananaFragment(), "Banana");
        viewPager.setAdapter(adapter);
    }



    //==========Aqui vna los metodos a ejecutar en el menu para cliente_edicion
    public void eliminar_cliente(MenuItem item) {
        //System.out.println("********GLOBAL ID ====>  " + globalId);
        SQLiteDatabase db = conn.getReadableDatabase();

        //String [] parametros = {campoId.getText().toString()};  /*PARAMETROS DE CONSULTA*/
        String cadena = String.valueOf(globalId);
        String [] parametros = {cadena};

        //metodo de eliminar el registro y que tabla se eliminara
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID + "=?" ,parametros);
        Toast.makeText(getApplicationContext(),"USUARIO ELIMINADO",Toast.LENGTH_SHORT).show();
        db.close(); //cerrar conexion
        Intent i = new Intent(this, Cliente.class);
        startActivity(i);
        finish();
    }

    public void editar_cliente(MenuItem item) {
        Toast.makeText(this, "Boton para editar", Toast.LENGTH_SHORT).show();
        //System.out.println("********GLOBAL ID ====>  " + globalId);
    }





    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}//end CLASS
