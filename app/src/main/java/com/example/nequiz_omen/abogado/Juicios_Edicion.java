package com.example.nequiz_omen.abogado;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class Juicios_Edicion extends AppCompatActivity {
    /*  ESTE ES EL ORDEN DE LOS FRAGMENT
    * JuiciosE
    * tramites
    * Honorarios*/

    private TabLayout tabLayout;
    private ViewPager viewPager;
    int globalIdJuicios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios__edicion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //soportar  el manifest de la barra de accion

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios",null,1);


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
                //Intent i = new Intent(Juicios_Edicion.this, Juicios.class);
                //startActivity(i);
                finish();
            }
        });


         /*===============Aqui va el Bundle ======================*/
        Bundle objetoEnviado = getIntent().getExtras();  //instanciar el Bundle
        JuiciosE juicios = null;

        if (objetoEnviado != null) {
            juicios = (JuiciosE) objetoEnviado.getSerializable("usuario");
            int id = juicios.getIdJuicios();
            String cliente = juicios.getNombreExpediente();
            String clienteExtra = juicios.getCliente_extra();
            String contrario = juicios.getContrario();
            String contrarioExtra =juicios.getContrario_extra();
            String juicio = juicios.getJuicio();
            String asunto = juicios.getAsunto();
            String instancia = juicios.getInstancia();
            String etapa = juicios.getEtapa();
            String tramite = juicios.getTramite();
            String fechaTramite = juicios.getFecha_tramite();
            String tramiteExtra = juicios.getTramite_extra();
            String fechaExtra = juicios.getFechaTramite_extra();
            String costoJuicio = juicios.getCosto_juicio();
            String restaPago = juicios.getResta_pago();
            String abono = juicios.getAbono();
            String fechaPago = juicios.getFecha_pago();
            String AbonoExtra = juicios.getAbono_extra();
            String FechaAbonoExtra = juicios.getFechaAbono_extra();
            //Integer duenio =  juicios.getIdDuenio();

            globalIdJuicios = id;
            //campoId.setText(user.getNombre());
            System.out.println("********Objeto Recibido ====>  " + objetoEnviado);
            System.out.println("********Bundle Recibido ====>  " + juicios);
            System.out.println("********Bundle ID ====>  " + id);
            System.out.println("********Bundle ID ====>  " + cliente);



            SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("id", String.valueOf(id));
            editor.putString("nombre",cliente );
            editor.putString("clienteExtra", clienteExtra);
            editor.putString("contrario", contrario);
            editor.putString("contrarioExtra", contrarioExtra);
            editor.putString("juicio", juicio);
            editor.putString("asunto", asunto);
            editor.putString("instancia", instancia);
            editor.putString("etapa", etapa);
            editor.putString("tramite", tramite);
            editor.putString("fechaTramite", fechaTramite);
            editor.putString("tramiteExtra", tramiteExtra);
            editor.putString("fechaExtra", fechaExtra);
            editor.putString("costoJuicio", costoJuicio);
            editor.putString("restaPago", restaPago);
            editor.putString("abono", abono);
            editor.putString("fechaPago", fechaPago);
            editor.putString("AbonoExtra", AbonoExtra);
            editor.putString("FechaAbonoExtra", FechaAbonoExtra);

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
        adapter.addFrag(new JuiciosFragment(), "Juicios");
        adapter.addFrag(new TramitesFragment(), "Tramites");
        adapter.addFrag(new HonorariosFragment(), "Honorarios");
        //adapter.addFrag(new BananaFragment(), "Banana");
        viewPager.setAdapter(adapter);
    }

    //Aqui van los eventos del click en la imagen de editar  "ic_menu_lapiz"
    public void editar_juicio(MenuItem item) {
        Toast.makeText(this, "Le diste Un click", Toast.LENGTH_SHORT).show();
    }


    /*===========================FRAGMENTO PARA BORRAR CON ALERT ===================================*/
    public void borrar_juicio(MenuItem item) {

        android.app.AlertDialog.Builder quitDialog = new android.app.AlertDialog.Builder(Juicios_Edicion.this);
        quitDialog.setTitle("Advertencia");
        quitDialog.setMessage("¿Realmente deseas borrar el Juicio Actual?");
        quitDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SQLiteDatabase db = conn.getReadableDatabase();

                //String [] parametros = {campoId.getText().toString()};  /*PARAMETROS DE CONSULTA*/
                String cadena = String.valueOf(globalIdJuicios);
                String [] parametros = {cadena};

                //metodo de eliminar el registro y que tabla se eliminara
                db.delete(Utilidades.TABLA_JUICIOS,Utilidades.CAMPO_ID_JUICIO + "=?" ,parametros);
                Toast.makeText(getApplicationContext(),"Juicio eliminado",Toast.LENGTH_SHORT).show();
                db.close(); //cerrar conexion
                System.out.println();

                finish();
                dialog.dismiss();
            }
        });
        quitDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        quitDialog.setCancelable(false);
        android.app.AlertDialog dialog = quitDialog.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));

        //Intent i = new Intent(this, Juicios.class);
        //startActivity(i);
        //finish();

    }    /*================================================================================================================*/



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
