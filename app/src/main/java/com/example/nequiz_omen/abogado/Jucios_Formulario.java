package com.example.nequiz_omen.abogado;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.BreakIterator;

public class Jucios_Formulario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaración de variables
    private Spinner spinnerPro, spinnerLoc;
    private LinearLayout layout_Cliente,layout_Contrario;
    private ImageView addimage_cliente,addimage_contrario,getAddimage_cliente;
    EditText fecha_pago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jucios__formulario);
        findViewById(R.id.addimage_cliente).setOnClickListener((View.OnClickListener) this);

        //abusqueda de imagen y layout con el id
        addimage_cliente = (ImageView) findViewById(R.id.addimage_cliente);
        layout_Cliente = (LinearLayout) findViewById(R.id.layout_Cliente);

        addimage_contrario = (ImageView) findViewById(R.id.addimage_contrario);
        layout_Contrario = (LinearLayout) findViewById(R.id.layout_Contrario);

        //addimage_cliente.setOnClickListener(onClick());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fecha_pago = (EditText) findViewById(R.id.fecha_pago);
        fecha_pago.setOnClickListener(fecha_pago());
        
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
                R.array.array_Juicios, /*Se carga el array definido en el XML */android.R.layout.simple_spinner_item);

        //Se carga el tipo de vista para el adaptador
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Se aplica el adaptador al Spinner de Juicios
        spinnerPro.setAdapter(adapter);
        //Se aplica listener para saber que item ha sido seleccionado
        //y poder usarlo en el método "onItemSelected"
        spinnerPro.setOnItemSelectedListener(this);
    }//end ON CREATE

  


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Se guarda en array de enteros los arrays de los Juicios
        int[] Etapas = {R.array.array_seleccione,
                R.array.array_Juicio_Ordinario_Civil,
                R.array.array_Juicio_Ordinario_Mercantil,
                R.array.array_Juicio_Oral_Mercantil,
                R.array.array_Juicio_Ejecutivo_Mercantil_Oral,
                R.array.array_Juicio_Ejecutivo_Mercantil,
                R.array.array_Juicio_Ordinario_Penal,
                R.array.array_Amparo_Indirecto,
                R.array.array_Amparo_Directo,
                R.array.array_Incidente_de_Suspensión,
                R.array.array_Juicio_Ordinario_Laboral};
        //Construcción del "adaptador" para el segundo Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                Etapas[position], /*En función del Juicio, se carga el array que corresponda del XML */ android.R.layout.simple_spinner_item);

        //Se carga el tipo de vista para el adaptadori
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Se aplica el adaptador al Spinner de Juicios
        spinnerLoc.setAdapter(adapter);

        Toast.makeText(this, "Seleccionaste el Juicio:" + position + "  id:" +id, Toast.LENGTH_SHORT).show();
    }


    @Override  // este spinner  es para la solucion en caso de que no se seleccione nada
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



    /*
    //  ===================   Par de metodos para crear EditText
    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                layout_Cliente.addView(createEditText());
            }
        };
    }

    private EditText createEditText() {
        final LinearLayout.LayoutParams lparams =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final EditText textView = new EditText(this);
        textView.setLayoutParams(lparams);
        return textView;
}
    */

    /* Metodos para crear y eliminar nuevos editText*/
    public void onAddField(View v) {
            int color;

            View contenedor = v.getRootView();

            switch (v.getId()) {
                case R.id.addimage_cliente:
                    color = Color.parseColor("#80CBC4"); // Verde azulado
                    break;
              /*  case R.id.button2:
                    color = Color.parseColor("#A5D6A7"); // Verde
                    break;
                case R.id.button3:
                    color = Color.parseColor("#C5E1A5"); // Verde claro
                    break;
                case R.id.button4:
                    color = Color.parseColor("#E6EE9C"); // Lima
                    break;  */
                default:
                    color = Color.WHITE; // Blano
            }

            contenedor.setBackgroundColor(color);
            Toast.makeText(this, "Seleccionaste :" + color  , Toast.LENGTH_SHORT).show();
        }





    public void onDelete(View v) {
        layout_Cliente.removeView((View) v.getParent());
    }


    // Metodos para insertar fecha de de pagos en un fragment
    public View.OnClickListener fechaNacimiento() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        };
    }


    private View.OnClickListener fecha_pago() {
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

                fecha_pago.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }

}//end class