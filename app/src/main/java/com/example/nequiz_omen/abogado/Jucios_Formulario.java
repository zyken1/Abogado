package com.example.nequiz_omen.abogado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Jucios_Formulario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaración de variables
    private Spinner spinnerPro, spinnerLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jucios__formulario);

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

        //Referenciado de variables del XML
        spinnerPro = (Spinner) findViewById(R.id.spinner_Juicio);
        spinnerLoc = (Spinner) findViewById(R.id.spinner_Etapa);
        //Construcción del "adaptador" para el primer Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.array_provincias, //Se carga el array definido en el XML
                android.R.layout.simple_spinner_item);

        //Se carga el tipo de vista para el adaptador
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Se aplica el adaptador al Spinner de localidades
        spinnerPro.setAdapter(adapter);
        //Se aplica listener para saber que item ha sido seleccionado
        //y poder usarlo en el método "onItemSelected"
        spinnerPro.setOnItemSelectedListener(this);

    }//end ON CREATE


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Se guarda en array de enteros los arrays de las provincias
        int[] localidades = {R.array.array_sevilla,R.array.array_malaga};

        //Construcción del "adaptador" para el segundo Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                localidades[position],//En función de la provincia, se carga el array que corresponda del XML
                android.R.layout.simple_spinner_item);

        //Se carga el tipo de vista para el adaptadori
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Se aplica el adaptador al Spinner de localidades
        spinnerLoc.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}