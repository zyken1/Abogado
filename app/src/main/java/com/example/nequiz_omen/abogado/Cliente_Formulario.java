package com.example.nequiz_omen.abogado;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Cliente_Formulario extends AppCompatActivity {
    // declaracionde variables locales
    EditText fecha_nacimiento;
    LinearLayout layout_for_sides;

    //se declaran las variables
    EditText campoId,campoNombre,campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente__formulario);

                //Busqueda del layout con el id
        layout_for_sides = (LinearLayout) findViewById(R.id.layout_for_sides);
        //se buscan los ID
        campoId = (EditText)findViewById(R.id.campoId);
        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoTelefono = (EditText) findViewById(R.id.campoTelefono);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fecha_nacimiento = (EditText) findViewById(R.id.fecha_nacimiento);
        fecha_nacimiento.setOnClickListener(fechaNacimiento());


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

    }  //END ON CREATE




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente_formulario, menu);
        return true;
    }



    public void Guardadito_cliente(MenuItem item) {
        //registrarUsuarios();     //SE CREA UN METODO DE LA ACCION QUE HARA  CUANDO SE DE CLICK
        //registrarUsuariosSQL();   //SE CRE AUN METODO PARA INSERTAR DATOS MEDIANTE SQL
        Toast.makeText(this, "Cliente Guardado ", Toast.LENGTH_SHORT).show();
        finish();

    }



    /*=============== AQUI COMIENZA LA BASE DE DATOS  ====================================*/
    private void registrarUsuariosSQL() {
    }







    //  ================================= Metodos para insertar fecha de nacimiento   =======================================
    public View.OnClickListener fechaNacimiento() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        };
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                fecha_nacimiento.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }
}