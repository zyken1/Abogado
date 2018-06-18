package com.example.nequiz_omen.abogado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Jucios_Formulario extends AppCompatActivity {
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


        //SE CREA Y SE MANDA A LLAMAR EL SPINNER  TIPO DE JUICIO
        Spinner spinner = (Spinner) findViewById(R.id.spinner_Juicio);
        String[] tipo_juicio = {"Selecciona el Tipo de Expediente","JUICIO ORDINARIO CIVIL","JUICIO ORDINARIO MERCANTIL","JUICIO ORAL MERCANTIL","JUICIO EJECUTIVO MERCANTIL ORAL","JUICIO EJECUTIVO MERCANTIL","JUICIO ORDINARIO PENAL","AMPARO INDIRECTO","AMPARO DIRECTO","INCIDENTE DE SUSPENSIÓN","JUICIO ORDINARIO LABORAL"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo_juicio));

        //SE CREA Y SE MANDA A LLAMAR EL SPINNER  ETAPA  PROCESAL
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_Etapa);
        String[] etapa_procesal = {"Selecciona la Etapa del proceso", "A","B","C","D","E"};
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, etapa_procesal));

    }//end ON CREATE

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