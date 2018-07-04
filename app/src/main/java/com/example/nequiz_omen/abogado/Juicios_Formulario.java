package com.example.nequiz_omen.abogado;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nequiz_omen.abogado.utilidades.Utilidades;

public class Juicios_Formulario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaración de variables
    private Spinner spinnerPro, spinnerLoc;
    private LinearLayout layout_Cliente,layout_Contrario,layout_Tramite;
    private ImageView agregar_cliente,agregar_contrario,agregar_tramite;

    /* variables para busqueda */
    EditText campoId,campoExpediente,campoCliente,campoContrario,campoTipo_juicio,campoAsunto,campoInstancia,campoEtapa_procesal,campoTramite,campoCosto_juicio,campoResta_pago,campoAbono,fecha_pago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios__formulario);

        //abusqueda de imagen y layout con el id
        agregar_cliente = (ImageView) findViewById(R.id.add_linear_cliente);
        agregar_contrario = (ImageView) findViewById(R.id.add_linear_contrario);
        agregar_tramite = (ImageView) findViewById(R.id.add_linear_tramite);
        layout_Cliente = (LinearLayout) findViewById(R.id.layout_Cliente);
        layout_Contrario = (LinearLayout) findViewById(R.id.layout_Contrario);
        layout_Tramite = (LinearLayout) findViewById(R.id.layout_Etapa_procesal);

                /*==========================    BUSQUEDA DE ID PARA LA BD    ===========================*/
                campoExpediente = (EditText)findViewById(R.id.campoExpediente);
                campoCliente = (EditText)findViewById(R.id.cliente);
                campoContrario = (EditText)findViewById(R.id.campoContrario);
                //campoTipo_juicio = (EditText)findViewById(R.id.campoTipo_juicio);
                campoAsunto = (EditText)findViewById(R.id.campoAsunto);
                campoInstancia = (EditText)findViewById(R.id.campoInstancia);
                //campoEtapa_procesal = (EditText)findViewById(R.id.campoEtapa_procesal);
                campoTramite = (EditText)findViewById(R.id.campoTramite);
                campoCosto_juicio = (EditText)findViewById(R.id.campoCosto_juicio);
                campoResta_pago = (EditText)findViewById(R.id.campoResta_pago);
                campoAbono = (EditText)findViewById(R.id.campoAbono);
                fecha_pago = (EditText) findViewById(R.id.fecha_pago);
                /*==========================    FIN DE DE LA BUSQUEDA    ===========================*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
;
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
        //Toast.makeText(this, "Seleccionaste el Juicio:" + position + "  id:" +id, Toast.LENGTH_SHORT).show();
        System.out.println("**********Array posicion  =====> " +position+ "  Id ===> " +id);
    } // end array

    @Override  // este spinner  es para la solucion en caso de que no se seleccione nada
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juicios_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*========   Metodos para crear y eliminar nuevos editText ==================*/
    public void onAddField(View v) {
      /*if (v.getId()== agregar_cliente.getId())
      {
          Toast.makeText(this, "Click en la primer opcion", Toast.LENGTH_SHORT).show();
      }*/
      switch(v.getId())
      {
          case R.id.add_linear_cliente:
              //Toast.makeText(this, "Se ha creado nuevo cliente", Toast.LENGTH_SHORT).show();
              LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              final View rowView = inflater.inflate(R.layout.rm_etxt_cliente, null);
              // Add the new row before the add field button.
              layout_Cliente.addView(rowView);
          break;

          case R.id.add_linear_contrario:
              //Toast.makeText(this, "Contrario Añadido", Toast.LENGTH_SHORT).show();
              LayoutInflater infl = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              final View row = infl.inflate(R.layout.rm_etxt_contrario, null);
              // Add the new row before the add field button.
              layout_Contrario.addView(row);
          break;

          case R.id.add_linear_tramite:
              //Toast.makeText(this, "Nuevo Tramite Añadido", Toast.LENGTH_SHORT).show();
              LayoutInflater dus = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              final View fila = dus.inflate(R.layout.rm_etxt_tramite, null);
              // Add the new row before the add field button.
              layout_Tramite.addView(fila);
              break;
       }
        System.out.println("SWITCH DE CREACION"  );
            System.out.println("EL ID QUE SE CREO FUE: " + v   );
    }//end del metodo onAddField


    public void onDelete(View v) {
        System.out.println(v );
        switch(v.getId())
        {
            case R.id.addimage_cliente:
                Toast.makeText(this, " Se ha eliminado cliente", Toast.LENGTH_SHORT).show();
                layout_Cliente.removeView((View) v.getParent());
                break;
            case R.id.addimage_cliente_contrario:
                Toast.makeText(this, " Contrario Eliminado", Toast.LENGTH_SHORT).show();
                layout_Contrario.removeView((View) v.getParent());
                break;
            case R.id.addimage_cliente_tramite:
                Toast.makeText(this, " Tramite Eliminado", Toast.LENGTH_SHORT).show();
                layout_Tramite.removeView((View) v.getParent());
                break;
        }
        //layout_Cliente.removeView((View) v.getParent());
        System.out.println("SWITCH TERMINADO ONDELETE"  );
        System.out.println( "EL ID QUE SE ELIMINO FUE: " + v  );
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




     /*==================== METODO PARA GUARDAR FORMULARIO======================*/
     public void Guardar(MenuItem item) {
         /*SE INSTANCIA UNA CONEXION Y SE LE COLOCAN LOS PARAMETROS */
         ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_juicios",null,1);

         //Se abre la conexion para poder der editada
         SQLiteDatabase  db = conn .getWritableDatabase();

         ContentValues values= new ContentValues();
         values.put(Utilidades.CAMPO_ID,campoCliente.getText().toString());
         values.put(Utilidades.CAMPO_EXPEDIENTE,campoExpediente.getText().toString());
         //values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

         //INSERTAR EN LA BASE DE DATOS
         Long idResultante = db.insert(Utilidades.TABLA_JUICIOS,Utilidades.CAMPO_ID,values);

         Toast.makeText(getApplicationContext(),"Id Registro:" + idResultante,Toast.LENGTH_SHORT).show();
         db.close();   //se cierra la conexion
     }


}//end class