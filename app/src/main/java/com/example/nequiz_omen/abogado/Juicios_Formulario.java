package com.example.nequiz_omen.abogado;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.example.nequiz_omen.abogado.Dialogos.DatePickerFragment;
import com.example.nequiz_omen.abogado.entidades.Usuario;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class Juicios_Formulario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaración de variables
    private Spinner spinnerJuicio, spinnerEtapa, comboDuenio,comboCliente;
    private LinearLayout layout_Cliente,layout_Contrario,layout_Tramite,layout_Pago;
    private ImageView agregar_cliente,agregar_contrario,agregar_tramite,agregar_pago;

    /* variables para busqueda */
    EditText campoId,campoExpediente,extra_cliente,campoContrario,extra_contrario,campoAsunto,campoInstancia,campoTramite,fecha_tramite,extra_tramite,extra_fecha_tramite,campoCosto_juicio,campoResta_pago,campoAbono,fecha_pago,extra_campoAbono,extra_fecha_pago;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;
    ConexionSQLiteHelper conn;

    //variables globales
    int cliente = 0;
    int cliente_contrario = 0;
    int cliente_tramite = 0;
    int cliente_pago = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juicios__formulario);

        /*==========================    BUSQUEDA DE ID Para inflar los  cliente y los tramites   ===========================*/
        agregar_cliente = (ImageView) findViewById(R.id.add_linear_cliente);
        agregar_contrario = (ImageView) findViewById(R.id.add_linear_contrario);
        agregar_tramite = (ImageView) findViewById(R.id.add_linear_tramite);
        agregar_pago = (ImageView) findViewById(R.id.add_linear_pago);

        layout_Cliente = (LinearLayout) findViewById(R.id.layout_Cliente);
        layout_Contrario = (LinearLayout) findViewById(R.id.layout_Contrario);
        layout_Tramite = (LinearLayout) findViewById(R.id.layout_Etapa_procesal);
        layout_Pago = (LinearLayout) findViewById(R.id.layout_Pago);

        /*==========================    BUSQUEDA DE ID PARA LA BD    ===========================*/
        campoExpediente = (EditText)findViewById(R.id.campoExpediente);
        //extra_cliente = (EditText)findViewById(R.id.extra_cliente);     //try  catch
        campoContrario = (EditText)findViewById(R.id.campoContrario);
        //extra_contrario = (EditText)findViewById(R.id.extra_contrario);     //try  catch
        spinnerJuicio = (Spinner) findViewById(R.id.spinner_Juicio);
        campoAsunto = (EditText)findViewById(R.id.campoAsunto);
        campoInstancia = (EditText)findViewById(R.id.campoInstancia);
        spinnerEtapa = (Spinner)findViewById(R.id.spinner_Etapa);
        campoTramite = (EditText)findViewById(R.id.campoTramite);
        fecha_tramite = (EditText)findViewById(R.id.fecha_tramite);
        //extra_tramite = (EditText)findViewById(R.id.extra_tramite);              //try  catch
        //extra_fecha_tramite = (EditText)findViewById(R.id.extra_fecha_tramite);  //try  catch
        campoCosto_juicio = (EditText)findViewById(R.id.campoCosto_juicio);
        campoResta_pago = (EditText)findViewById(R.id.campoResta_pago);
        campoAbono = (EditText)findViewById(R.id.campoAbono);
        fecha_pago = (EditText) findViewById(R.id.fecha_pago);
        //extra_campoAbono = (EditText) findViewById(R.id.extra_campoAbono);    //try  catch
        //extra_fecha_pago = (EditText) findViewById(R.id.extra_fecha_pago);    //try  catch
        comboDuenio= (Spinner) findViewById(R.id.comboCliente);
        /* ==========================    FIN DE DE LA BUSQUEDA    ===========================*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fecha_pago.setOnClickListener(fecha_pago());
        fecha_tramite.setOnClickListener(fechaTramite());
        
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

        //==============================  ADAPTADOR PARA EL SPINNER
        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);

        comboDuenio.setAdapter(adaptador);
        comboDuenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
           });

        //==============================  Referenciado de variables del XML
        //Construcción del "adaptador" para el primer Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.array_Juicios, /*Se carga el array definido en el XML */android.R.layout.simple_spinner_item);

        //Se carga el tipo de vista para el adaptador
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Se aplica el adaptador al Spinner de JuiciosE
        spinnerJuicio.setAdapter(adapter);

        spinnerJuicio.setOnItemSelectedListener(this);
    }//end ON CREATE



    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Se guarda en array de enteros los arrays de los JuiciosE
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, Etapas[position], /*En función del Juicio, se carga el array que corresponda del XML */ android.R.layout.simple_spinner_item);
        //Se carga el tipo de vista para el adaptadori
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Se aplica el adaptador al Spinner de JuiciosE
        spinnerEtapa.setAdapter(adapter);
        //Toast.makeText(this, "Seleccionaste el Juicio:" + position + "  id:" +id, Toast.LENGTH_SHORT).show();
        //System.out.println("**********Array posicion  =====> " + parent.getItemAtPosition(position).toString() + "  Id ===> " +id);
    }

    @Override  // este spinner  es para la solucion en caso de que no se seleccione nada
    public void onNothingSelected(AdapterView<?> parent) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_juicios_formulario, menu);
        return true;
    }




 /*==================     Metodo guardar al dar click al icono de Guardar  ================*/
    public void Guardar(MenuItem item) {

        if (campoExpediente.getText().toString().trim().length() < 3) {
            campoExpediente.setError("Introduce un Numero de Expediente");
        } else{
            registrarUsuarios();
            //registrarMascota();
            String textLoc = spinnerEtapa.getSelectedItem().toString();
            Toast.makeText(this, textLoc, Toast.LENGTH_SHORT).show();

            //Intent i = new Intent(this, JuiciosE.class);
            //startActivity(i);
        }

    }



     //==================== METODO PARA GUARDAR FORMULARIO======================
    private void registrarUsuarios() {

        String ccuota[] = new String[19];
        ccuota[0]=	campoExpediente.getText().toString();
        ccuota[2]=	campoContrario.getText().toString();
        //ccuota[4]=	spinner_Juicio.getText().toString();
        ccuota[5]=	campoAsunto.getText().toString();
        ccuota[6]=	campoInstancia.getText().toString();
        //ccuota[7]=	spinner_Etapa.getText().toString();
        ccuota[8]=	campoTramite.getText().toString();
        ccuota[9]=	fecha_tramite.getText().toString();
        ccuota[12]=	campoCosto_juicio.getText().toString();
        ccuota[13]=	campoResta_pago.getText().toString();
        ccuota[14]=	campoAbono.getText().toString();
        ccuota[15]=	fecha_pago.getText().toString();
        //ccuota[18]=	comboCliente.getText().toString();


        try{//Código que puede provocar errores
            extra_cliente = (EditText)findViewById(R.id.extra_cliente);
            ccuota[1]=	extra_cliente.getText().toString();
        }
        catch(Exception e){
            //Gestión del
            //extra_cliente.toString().concat("algo");
            ccuota[1]=	"extra_cliente vacio" ;
        }

                    try{//Código que puede provocar errores
                        extra_contrario = (EditText)findViewById(R.id.extra_contrario);
                        ccuota[3]=	extra_contrario.getText().toString();
                    }
                    catch(Exception e){
                        //Gestión del error
                        ccuota[3]= "extra_contrario vacio";
                    }

        try{//Código que puede provocar errores
            extra_tramite = (EditText)findViewById(R.id.extra_tramite);              //extra
            extra_fecha_tramite = (EditText)findViewById(R.id.extra_fecha_tramite);  //extra

            ccuota[10]=	extra_tramite.getText().toString();
            ccuota[11]=	extra_fecha_tramite.getText().toString();
        }
        catch(Exception e){
            //Gestión del error
            ccuota[10]= "extra_tramite vacio";
            ccuota[11]= "extra_fecha_tramite vacio";
        }

                    try{//Código que puede provocar errores
                        extra_campoAbono = (EditText) findViewById(R.id.extra_campoAbono);
                        extra_fecha_pago = (EditText) findViewById(R.id.extra_fecha_pago);

                        ccuota[16]=	extra_campoAbono.getText().toString();
                        ccuota[17]=	extra_fecha_pago.getText().toString();
                    }
                    catch(Exception e){
                        //Gestión del error var1, de tipo Tipo1
                        ccuota[16]= "extra_campoAbono vacio";
                        ccuota[17]= "extra_fecha_pago vacio";
                    }



            int i;
            for(i=0; i<ccuota.length; i++)
            {
                System.out.println("*********** RESULTADO " +i+ " => " +ccuota [i]);
            }

        /*SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_EXPEDIENTE, campoExpediente.getText().toString());
        values.put(Utilidades.CAMPO_CLIENTE_EXTRA, extra_cliente.getText().toString());
        values.put(Utilidades.CAMPO_CONTRARIO, campoContrario.getText().toString());
        values.put(Utilidades.CAMPO_CONTRARIO_EXTRA, extra_contrario.getText().toString());
        //values.put(Utilidades.CAMPO_JUICIO, spinner_Juicio.getText().toString());
        values.put(Utilidades.CAMPO_ASUNTO, campoAsunto.getText().toString());
        values.put(Utilidades.CAMPO_INSTANCIA, campoInstancia.getText().toString());
        //values.put(Utilidades.CAMPO_ETAPA, spinner_Etapa.getText().toString());
        values.put(Utilidades.CAMPO_TRAMITE, campoTramite.getText().toString());
        values.put(Utilidades.CAMPO_FECHA_TRAMITE, fecha_tramite.getText().toString());
        values.put(Utilidades.CAMPO_TRAMITE_EXTRA, extra_tramite.getText().toString());
        values.put(Utilidades.CAMPO_FECHATRAMITE_EXTRA, extra_fecha_tramite.getText().toString());
        values.put(Utilidades.CAMPO_COSTO_JUICIO, campoCosto_juicio.getText().toString());
        values.put(Utilidades.CAMPO_RESTA_PAGO, campoResta_pago.getText().toString());
        values.put(Utilidades.CAMPO_ABONO, campoAbono.getText().toString());
        values.put(Utilidades.CAMPO_FECHA_PAGO, fecha_pago.getText().toString());
        values.put(Utilidades.CAMPO_ABONO_EXTRA, extra_campoAbono.getText().toString());
        values.put(Utilidades.CAMPO_FECHAABONO_EXTRA, extra_fecha_pago.getText().toString());
        //values.put(Utilidades.CAMPO_ID_DUENIO,idDuenio);

        int idCombo = (int) comboDuenio.getSelectedItemId();

        //if (idCombo!=0){
        Log.i("TAMAÑO", personasList.size() + "");
        Log.i("id combo", idCombo + "");
        Log.i("id combo - 1", (idCombo - 1) + "");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo

        int idDuenio = personasList.get(idCombo - 1).getId();
        Log.i("id DUEÑO", idDuenio + "");

        values.put(Utilidades.CAMPO_ID_DUENIO, idDuenio);
        Long idResultante = db.insert(Utilidades.TABLA_JUICIOS, Utilidades.CAMPO_ID_JUICIO, values);


        //INSERTAR EN LA BASE DE DATOS
        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        System.out.println("*********Valores enviados a la BD  ====>  " + values);
        System.out.println("*********Ruta de Conexion en la BD  ====>  " + conn);
        db.close();   //se cierra la conexion

        Toast.makeText(this, "Expediente Guardado ", Toast.LENGTH_SHORT).show();
       }
        }*/
    }


         /*========================================================================*/
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario persona=null;
        personasList =new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            //persona.setTipo_persona(cursor.getString(2));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            //Log.i("Tipo",persona.getTipo_persona());

            personasList.add(persona);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }
    }

    /*===========   Metodos para crear y eliminar nuevos editText ==================*/
    public void onAddField(View v) {
      /*if (v.getId()== agregar_cliente.getId())
      {
          Toast.makeText(this, "Click en la primer opcion", Toast.LENGTH_SHORT).show();
      }*/
        switch(v.getId())
        {
            case R.id.add_linear_cliente:
                if(cliente == 0)
                {
                    cliente = 1;
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View rowView = inflater.inflate(R.layout.rm_etxt_cliente, null);
                    // Add the new row before the add field button.
                    layout_Cliente.addView(rowView);
                }else{
                    Toast.makeText(this, "Solo se puede agregar un Cliente", Toast.LENGTH_SHORT).show();
                     }
                break;

            case R.id.add_linear_contrario:
                if(cliente_contrario == 0)
                {
                    cliente_contrario = 1;
                    LayoutInflater infl = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View row = infl.inflate(R.layout.rm_etxt_contrario, null);
                    // Add the new row before the add field button.
                    layout_Contrario.addView(row);
                }else{
                    Toast.makeText(this, "Solo se puede agregar un Contrario", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.add_linear_tramite:
                if(cliente_tramite == 0)
                {
                    cliente_tramite = 1;
                    LayoutInflater dus = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View fila = dus.inflate(R.layout.rm_etxt_tramite, null);
                    // Add the new row before the add field button.
                    layout_Tramite.addView(fila);
                }else{
                    Toast.makeText(this, "Solo se puede agregar un Tramite", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_linear_pago:
                if(cliente_pago == 0)
                {
                    cliente_pago = 1;
                    LayoutInflater nexxx = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View fila = nexxx.inflate(R.layout.rm_etxt_pago, null);
                    // Add the new row before the add field button.
                    layout_Pago.addView(fila);
                }else{
                    //Toast.makeText(this, "Solo se puede agregar un Tramite", Toast.LENGTH_SHORT).show();
                }
               break;
        }
        //System.out.println("EL ID QUE SE CREO FUE: " + v   );
    }//end del metodo onAddField




    public void onDelete(View v) {
        switch(v.getId())
        {
            case R.id.addimage_cliente:
                //Toast.makeText(this, " Cliente eliminado", Toast.LENGTH_SHORT).show();
                layout_Cliente.removeView((View) v.getParent());
                cliente = 0;  //se resetea para que entre en el if else  del case
                break;
            case R.id.addimage_cliente_contrario:
                //Toast.makeText(this, " Contrario Eliminado", Toast.LENGTH_SHORT).show();
                layout_Contrario.removeView((View) v.getParent());
                cliente_contrario = 0;  //se resetea para que entre en el if else  del case
                break;
            case R.id.addimage_cliente_tramite:
                //Toast.makeText(this, " Tramite Eliminado", Toast.LENGTH_SHORT).show();
                layout_Tramite.removeView((View) v.getParent());
                cliente_tramite = 0;  //se resetea para que entre en el if else  del case
                break;
            case R.id.addimage_cliente_pago:
                //Toast.makeText(this, " CLICK", Toast.LENGTH_SHORT).show();
                layout_Pago.removeView((View) v.getParent());
                break;
        }
        //layout_Cliente.removeView((View) v.getParent());
    }



  /*===================0 Metodos para insertar fecha de de pagos en un fragment  ================================*/
    public View.OnClickListener fechaTramite() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2();
            }
        };
    }

    private void showDatePickerDialog2() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                fecha_tramite.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }

    //=========================================================
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



    /*==============================YA NO SE OCUPA PERO SE DEJA DE REFERENCIA==========================================*/
        private void registrarMascota() {
        /*
            SQLiteDatabase db=conn.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE_EXPEDIENTE,campoExpediente.getText().toString());
            values.put(Utilidades.CAMPO_CLIENTES,campoCliente.getText().toString());

            int idCombo= (int) comboDuenio.getSelectedItemId();

            if (idCombo!=0){
                Log.i("TAMAÑO",personasList.size()+"");
                Log.i("id combo",idCombo+"");
                Log.i("id combo - 1",(idCombo-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo

                int idDuenio = personasList.get(idCombo-1).getId();
                Log.i("id DUEÑO",idDuenio+"");

                values.put(Utilidades.CAMPO_ID_DUENIO,idDuenio);

                Long idResultante=db.insert(Utilidades.TABLA_JUICIOS,Utilidades.CAMPO_ID_JUICIO,values);

                Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
                db.close();

            }else{
                Toast.makeText(getApplicationContext(),"Debe seleccionar un Dueño",Toast.LENGTH_LONG).show();
            }*/

    }
}//end class