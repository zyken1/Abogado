package com.example.nequiz_omen.abogado;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.nequiz_omen.abogado.entidades.JuiciosE;

import java.util.ArrayList;
import java.util.List;

public class Juicios_Edicion extends AppCompatActivity {
    /*  ESTE ES EL ORDEN DE LOS FRAGMENT
    * JuiciosE
    * tramites
    * Honorarios*/

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.apple,
            R.drawable.orange,
            R.drawable.grapes,
            R.drawable.banana
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios__edicion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //soportar  el manifest de la barra de accion

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
                finish();
            }
        });


         /*===============Aqui va el Bundle ======================*/
        Bundle objetoEnviado = getIntent().getExtras();  //instanciar el Bundle
        JuiciosE juicios = null;

        if (objetoEnviado != null) {
            juicios = (JuiciosE) objetoEnviado.getSerializable("usuario");
            int id = juicios.getIdJuicios();
            String nombre = juicios.getNombreExpediente();
            /*String Tipo = juicios.getTipo_persona();
            String email = juicios.getE_mail();
            String genero =juicios.getGenero();
            String nacimiento = juicios.getFechaNacimiento();
            String direccion = juicios.getDireccion();
            String telMovil = juicios.getTel_movil();
            String telCasa = juicios.getTel_casa();
            String telOficina = juicios.getTel_oficina();
            String dependientes = juicios.getDependientes();
            String notas = juicios.getNotas();*/

            //globalId = id;
            //campoId.setText(user.getNombre());
            System.out.println("********Objeto Recibido ====>  " + objetoEnviado);
            System.out.println("********Bundle Recibido ====>  " + juicios);
            System.out.println("********Bundle ID ====>  " + id);
            System.out.println("********Bundle ID ====>  " + nombre);



            SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("id", String.valueOf(id));
            editor.putString("nombre",nombre );
            /*editor.putString("tipoPersona", Tipo);
            editor.putString("e-mail", email);
            editor.putString("genero", genero);
            editor.putString("nacimiento", nacimiento);
            editor.putString("direccion", direccion);
            editor.putString("telMovil", telMovil);
            editor.putString("telCasa", telCasa);
            editor.putString("telOficina", telOficina);
            editor.putString("dependientes", dependientes);
            editor.putString("notas", notas);*/
            editor.commit();
        }
        /*===============Aqui Termina el Bundle ======================*/
    }//en d on create

    @Override  // SE AÑADE MENU DE SETTINGS
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juicios_edicion, menu);
        return true;
    }


    /*private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        //tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }*/

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new JuiciosFragment(), "JuiciosE");
        adapter.addFrag(new TramitesFragment(), "Tramites");
        adapter.addFrag(new HonorariosFragment(), "Honorarios");
        //adapter.addFrag(new BananaFragment(), "Banana");
        viewPager.setAdapter(adapter);
    }

    //Aqui van los eventos del click en la imagen de editar  "ic_menu_lapiz"
    public void editar_juicio(MenuItem item) {
        Toast.makeText(this, "Le diste Un click", Toast.LENGTH_SHORT).show();
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



}
