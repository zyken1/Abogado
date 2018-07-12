package com.example.nequiz_omen.abogado;

import android.database.Cursor;
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

import java.util.ArrayList;
import java.util.List;

public class Cliente_Edicion extends AppCompatActivity {
    TextView campoId, campoNombre, campoTelefono;
    /*  ESTE ES EL ORDEN DE LOS FRAGMENT
    * Datos del Cliente
    * Juicios del cliente*/

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
        setContentView(R.layout.activity_cliente__edicion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //soportar  el manifest de la barra de accion

        campoId = (TextView) findViewById(R.id.campoId);
        campoNombre = (TextView) findViewById(R.id.campoNombre);
        campoTelefono = (TextView) findViewById(R.id.campoTelefono);

        /*===============Aqui va el Bundle ======================*/
        Bundle objetoEnviado=getIntent().getExtras();  //instanciar el Bundle
        Usuario user=null;

        if(objetoEnviado!=null){

            user= (Usuario) objetoEnviado.getSerializable("usuario");
            campoId.setText(user.getId().toString());
            campoNombre.setText(user.getNombre().toString());
            campoTelefono.setText(user.getE_mail().toString());

            System.out.println("********Objeto Recibido ====>  " +objetoEnviado);
            System.out.println("********Bundle Recibido ====>  " + user);
        }
        /*===============Aqui va el Bundle ======================*/

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
    }


    @Override  // SE AÑADE MENU DE SETTINGS
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente_edicion, menu);
        return true;
    }

     /*private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        //tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }*/

    private void addTabs(ViewPager viewPager) {
        Cliente_Edicion.ViewPagerAdapter adapter = new Cliente_Edicion.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ClienteFragment(), "Datos del Cliente");
        adapter.addFrag(new ClienteJuiciosFragment(), "Juicios del Cliente");
        //adapter.addFrag(new HonorariosFragment(), "Honorarios");
        //adapter.addFrag(new BananaFragment(), "Banana");
        viewPager.setAdapter(adapter);
    }


    //Aqui vna los metodos a ejecutar en el menu para cliente_edicion
    public void eliminar_cliente(MenuItem item) {
        Toast.makeText(this, "Boton para eliminar", Toast.LENGTH_SHORT).show();
    }

    public void editar_cliente(MenuItem item) {
        Toast.makeText(this, "Boton para editar", Toast.LENGTH_SHORT).show();
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
