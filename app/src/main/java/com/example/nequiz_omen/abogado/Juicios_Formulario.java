package com.example.nequiz_omen.abogado;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Objects;

public class Juicios_Formulario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaración de variables
    private Spinner spinnerJuicio, spinnerEtapa, comboDuenio;
    private LinearLayout layout_Cliente, layout_Contrario, layout_Tramite, layout_Pago;
    private ImageView agregar_cliente, agregar_contrario, agregar_tramite, agregar_pago;

    /* variables para busqueda */
    EditText campoId, campoExpediente, extra_cliente, campoContrario, extra_contrario, campoAsunto, campoInstancia, campoTramite, fecha_tramite, extra_tramite, extra_fecha_tramite, campoCosto_juicio, campoResta_pago, campoAbono, fecha_pago, extra_campoAbono, extra_fecha_pago;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;
    ConexionSQLiteHelper conn;

    //variables globales
    int cliente = 0;
    int cliente_contrario = 0;
    int cliente_tramite = 0;
    int cliente_pago = 0;
    int numEntero = 0;
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
        campoExpediente = (EditText) findViewById(R.id.campoExpediente);
        //extra_cliente = (EditText)findViewById(R.id.extra_cliente);     //try  catch
        campoContrario = (EditText) findViewById(R.id.campoContrario);
        //extra_contrario = (EditText)findViewById(R.id.extra_contrario);     //try  catch
        spinnerJuicio = (Spinner) findViewById(R.id.spinner_Juicio);
        campoAsunto = (EditText) findViewById(R.id.campoAsunto);
        campoInstancia = (EditText) findViewById(R.id.campoInstancia);
        spinnerEtapa = (Spinner) findViewById(R.id.spinner_Etapa);
        campoTramite = (EditText) findViewById(R.id.campoTramite);
        fecha_tramite = (EditText) findViewById(R.id.fecha_tramite);
        //extra_tramite = (EditText)findViewById(R.id.extra_tramite);              //try  catch
        //extra_fecha_tramite = (EditText)findViewById(R.id.extra_fecha_tramite);  //try  catch
        campoCosto_juicio = (EditText) findViewById(R.id.campoCosto_juicio);
        campoResta_pago = (EditText) findViewById(R.id.campoResta_pago);
        campoAbono = (EditText) findViewById(R.id.campoAbono);
        fecha_pago = (EditText) findViewById(R.id.fecha_pago);
        //extra_campoAbono = (EditText) findViewById(R.id.extra_campoAbono);    //try  catch
        //extra_fecha_pago = (EditText) findViewById(R.id.extra_fecha_pago);    //try  catch
        comboDuenio = (Spinner) findViewById(R.id.comboCliente);
        /* ==========================    FIN DE DE LA BUSQUEDA    ===========================*/

        /*CON ESTE  METOO SE CAPTURA LOS DATOS DESDE CUALQUIIER FRAGMENT*/
        SharedPreferences prefs = getSharedPreferences("Preferences", 0);

        String ID = prefs.getString("id", "");
        String NoExpediente = prefs.getString("nombre", "0");
        String cliente = prefs.getString("clienteExtra", "0");
        String Contrario = prefs.getString("contrario", "0");
        //String ContrarioExtra = prefs.getString("contrarioExtra", "0");
        String Tipo = prefs.getString("juicio", "0");
        String Asunto = prefs.getString("asunto", "0");
        String Instancia = prefs.getString("instancia", "0");
        String etapa = prefs.getString("etapa", "0");
        String tramite = prefs.getString("tramite", "0");
        String fechaTramite = prefs.getString("fechaTramite", "0");
        String costoJuicio = prefs.getString("costoJuicio", "0");
        String restaPago = prefs.getString("restaPago", "0");
        String abono = prefs.getString("abono", "0");
        String fechaPago = prefs.getString("fechaPago", "0");

        String valor = ID;
        try {
            numEntero = Integer.parseInt(valor);  //NO RESPETA LA CONDICIONAL ARROJA CERO
        } catch (Exception e) {
        }

        System.out.println("Datos Shared preference==>" +ID+"__"+NoExpediente +"__"+cliente + "__" + numEntero);
        Toast.makeText(this, ID+"__"+NoExpediente +"__"+cliente ,Toast.LENGTH_SHORT).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fecha_pago.setOnClickListener(Calendario());
        fecha_tramite.setOnClickListener(Calendario());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                //Intent i = new Intent(Juicios_Formulario.this, Juicios.class);
                //startActivity(i);
                finish();
            }
        });

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        //==============================  ADAPTADOR PARA EL SPINNER
        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersonas);

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_Juicios, /*Se carga el array definido en el XML */android.R.layout.simple_spinner_item);

        //Se carga el tipo de vista para el adaptador
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Se aplica el adaptador al Spinner de JuiciosE
        spinnerJuicio.setAdapter(adapter);

        spinnerJuicio.setOnItemSelectedListener(this);




        /*================= EVENTOS ON FOCUS  PARA HACER LAS OPERACIONES =========================*/
        campoCosto_juicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                try {
                    if (!hasFocus) {
                        System.out.println("FOCUS" + hasFocus);
                        int costo_juicio = Integer.parseInt(campoCosto_juicio.getText().toString());
                        int campo_Abono = Integer.parseInt(campoAbono.getText().toString());

                        int result = costo_juicio - campo_Abono;
                        campoResta_pago.setText(String.valueOf(result));
                    } else {
                        //System.out.println("FOCUS" + hasFocus);
                    }

                } catch (Exception e) {
                    System.out.println("FOCUS" + hasFocus);
                    String costo_juicio = campoCosto_juicio.getText().toString();
                    campoResta_pago.setText(costo_juicio);
                }
            }
        });

        campoAbono.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                try {
                    if (!hasFocus) {
                        System.out.println("FOCUS" + hasFocus);
                        int costo_juicio = Integer.parseInt(campoCosto_juicio.getText().toString());
                        int campo_Abono = Integer.parseInt(campoAbono.getText().toString());

                        int result = costo_juicio - campo_Abono;
                        campoResta_pago.setText(String.valueOf(result));
                    } else {
                        //System.out.println("FOCUS" + hasFocus);
                    }

                } catch (Exception e) {
                }
            }
        });


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
        String textoJuicio = spinnerJuicio.getSelectedItem().toString();
        System.out.println(textoJuicio);

        if (campoExpediente.getText().toString().trim().length() < 3) {
            campoExpediente.setError("Introduce un Numero de Expediente");
        } else if(Objects.equals(textoJuicio, "Selecciona un Tipo de Juicio")){
            Toast.makeText(getApplicationContext(),"Debes seleccionar un tipo de Juicio",Toast.LENGTH_LONG).show();
        }else if(numEntero > 0)
        {
            actualizarJuicio();
            Toast.makeText(this, "actualizado ", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this, "Juicio Guardado ", Toast.LENGTH_SHORT).show();
            registrarUsuarios();
            //Intent i = new Intent(this, Juicios.class);
            //startActivity(i);}
            finish();
        }
    }



     //==================== METODO PARA GUARDAR FORMULARIO======================
    private void registrarUsuarios() {

        String textoJuicio = spinnerJuicio.getSelectedItem().toString();
        String textoEtapa = spinnerEtapa.getSelectedItem().toString();

        //SE ABRE LA BASE DE DATOS Y SE CONTROLAN LAS EXCEPCIONES
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        try{//Código que puede provocar errores
            extra_cliente = (EditText)findViewById(R.id.extra_cliente);
            values.put(Utilidades.CAMPO_CLIENTE_EXTRA, extra_cliente.getText().toString());
        }
        catch(Exception e){
            //Gestión del error
            values.put(Utilidades.CAMPO_CLIENTE_EXTRA, "vacio");
        }

                    try{//Código que puede provocar errores
                        extra_contrario = (EditText)findViewById(R.id.extra_contrario);
                        values.put(Utilidades.CAMPO_CONTRARIO_EXTRA, extra_contrario.getText().toString());
                    }
                    catch(Exception e){
                        //Gestión del error
                        values.put(Utilidades.CAMPO_CONTRARIO_EXTRA, "vacio");
                    }

        try{//Código que puede provocar errores
            extra_tramite = (EditText)findViewById(R.id.extra_tramite);              //extra
            extra_fecha_tramite = (EditText)findViewById(R.id.extra_fecha_tramite);  //extra

            values.put(Utilidades.CAMPO_TRAMITE_EXTRA, extra_tramite.getText().toString());
            values.put(Utilidades.CAMPO_FECHATRAMITE_EXTRA, extra_fecha_tramite.getText().toString());

        }
        catch(Exception e){
            //Gestión del error
            values.put(Utilidades.CAMPO_TRAMITE_EXTRA, "vacio");
            values.put(Utilidades.CAMPO_FECHATRAMITE_EXTRA, "0");
        }

                    try{//Código que puede provocar errores
                        extra_campoAbono = (EditText) findViewById(R.id.extra_campoAbono);
                        extra_fecha_pago = (EditText) findViewById(R.id.extra_fecha_pago);

                        values.put(Utilidades.CAMPO_ABONO_EXTRA, extra_campoAbono.getText().toString());
                        values.put(Utilidades.CAMPO_FECHAABONO_EXTRA, extra_fecha_pago.getText().toString());

                    }
                    catch(Exception e){
                        //Gestión del error var1, de tipo Tipo1
                        values.put(Utilidades.CAMPO_ABONO_EXTRA, "0");
                        values.put(Utilidades.CAMPO_FECHAABONO_EXTRA, "0");
                    }




        values.put(Utilidades.CAMPO_NOMBRE_EXPEDIENTE, campoExpediente.getText().toString());
        values.put(Utilidades.CAMPO_CONTRARIO, campoContrario.getText().toString());

        values.put(Utilidades.CAMPO_JUICIO, textoJuicio);
        values.put(Utilidades.CAMPO_ASUNTO, campoAsunto.getText().toString());
        values.put(Utilidades.CAMPO_INSTANCIA, campoInstancia.getText().toString());
        values.put(Utilidades.CAMPO_ETAPA, textoEtapa);
        values.put(Utilidades.CAMPO_TRAMITE, campoTramite.getText().toString());
        values.put(Utilidades.CAMPO_FECHA_TRAMITE, fecha_tramite.getText().toString());

        values.put(Utilidades.CAMPO_COSTO_JUICIO, campoCosto_juicio.getText().toString());
        values.put(Utilidades.CAMPO_RESTA_PAGO, campoResta_pago.getText().toString());
        values.put(Utilidades.CAMPO_ABONO, campoAbono.getText().toString());

        values.put(Utilidades.CAMPO_FECHA_PAGO, fecha_pago.getText().toString());

        int idCombo = (int) comboDuenio.getSelectedItemId();

        if (idCombo!=0){
        Log.i("TAMAÑO", personasList.size() + "");
        Log.i("id combo", idCombo + "");
        Log.i("id combo - 1", (idCombo - 1) + "");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo

        int idDuenio = personasList.get(idCombo - 1).getId();
        Log.i("id DUEÑO", idDuenio + "");

        values.put(Utilidades.CAMPO_ID_DUENIO, idDuenio);
        }else{
            //Toast.makeText(getApplicationContext(),"Debe seleccionar un Dueño",Toast.LENGTH_LONG).show();
            values.put(Utilidades.CAMPO_ID_DUENIO,0);
        }


        Long idResultante = db.insert(Utilidades.TABLA_JUICIOS, Utilidades.CAMPO_ID_JUICIO, values);
        //INSERTAR EN LA BASE DE DATOS
        //Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        System.out.println("*********Valores enviados a la BD  ====>  " + values);
        System.out.println("*********Ruta de Conexion en la BD  ====>  " + conn);
        System.out.println("*********Ruta de Conexion en la BD  ====>  " + idResultante);
        db.close();   //se cierra la conexion

        Toast.makeText(this, "Expediente Guardado ", Toast.LENGTH_SHORT).show();
    }


    /*======================  AQUI se Actualiza el Cliente en  LA BASE DE DATOS  ====================================*/
    private void actualizarJuicio() {
        SQLiteDatabase db=conn.getWritableDatabase();

        String cadena = Integer.toString(numEntero);
        String[] parametros={cadena};
        ContentValues values= new ContentValues();     //con el content y el put se va agregar una clave y un valor  COMO EN EL HASH
        /*values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TIPO,campotipo);           //ENTRA EN EL SWITCH  de los RADIOBUTTON y se obtiene el valor en String
        values.put(Utilidades.CAMPO_EMAIL,campoCorreo.getText().toString());
        values.put(Utilidades.CAMPO_GENERO,campoGenero);       //ENTRA EN EL SWITCH  de los RADIOBUTTON y se obtiene el valor en String
        values.put(Utilidades.CAMPO_NACIMIENTO,fecha_nacimiento.getText().toString());
        values.put(Utilidades.CAMPO_DIRECCION,campoDireccion.getText().toString());
        values.put(Utilidades.CAMPO_TELMOVIL,campoTelmovil.getText().toString());
        values.put(Utilidades.CAMPO_TELCASA,campoTelCasa.getText().toString());
        values.put(Utilidades.CAMPO_TELOFICINA,campoTelOficina.getText().toString());
        values.put(Utilidades.CAMPO_DEPENDIENTES,campoDependientes.getText().toString());
        values.put(Utilidades.CAMPO_NOTAS,campoNotas.getText().toString());*/

        //INSERTAR EN LA BASE DE DATOS
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizó el usuario",Toast.LENGTH_LONG).show();
        System.out.println("****************Impresion en BD ====> " +values);
        db.close();

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




    /*===========   Metodos para crear y eliminar nuevos editText  AL iNFLAR LOS LAYOUT==================*/
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
                    layout_Tramite.addView(fila);

                    /*SE AÑADE UN BUSCADOR PARA ENCONTRAR EL  EDITTEXT  Y PODER ANEXARLE UN NUEVO METODO  QUE ES EL DEL CALENDARIO*/
                    extra_fecha_tramite = (EditText)findViewById(R.id.extra_fecha_tramite);  //extra
                    extra_fecha_tramite.setOnClickListener(Calendario()); //SE AÑADEN NUEVOS EVENTOS
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
                    layout_Pago.addView(fila);

                    extra_fecha_pago = (EditText) findViewById(R.id.extra_fecha_pago);
                    extra_fecha_pago.setOnClickListener(Calendario()); //SE AÑADEN NUEVOS EVENTOS
                }else{
                    //Toast.makeText(this, "Solo se puede agregar un Tramite", Toast.LENGTH_SHORT).show();
                }
               break;
        }
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



  /*=================== Metodos para insertar Un calendario  en un fragment e insertarlo en los text  ================================*/
    public View.OnClickListener Calendario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId())
                {
                    case  R.id.fecha_tramite:
                        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                // +1 because january is zero
                                final String selectedDate = day + " / " + (month+1) + " / " + year;
                                fecha_tramite.setText(selectedDate);
                            }
                        });
                        newFragment.show(getFragmentManager(), "datePicker");
                    break;

                    case  R.id.fecha_pago:
                        DatePickerFragment newFragment2 = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                // +1 because january is zero
                                final String selectedDate = day + " / " + (month+1) + " / " + year;
                                fecha_pago.setText(selectedDate);
                            }
                        });
                        newFragment2.show(getFragmentManager(), "datePicker");
                    break;

                    case  R.id.extra_fecha_tramite:
                    DatePickerFragment newFragment3 = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            // +1 because january is zero
                            final String selectedDate = day + " / " + (month+1) + " / " + year;
                            extra_fecha_tramite.setText(selectedDate);
                        }
                    });
                    newFragment3.show(getFragmentManager(), "datePicker");
                    break;

                    case  R.id.extra_fecha_pago:
                        DatePickerFragment newFragment4 = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                // +1 because january is zero
                                final String selectedDate = day + " / " + (month+1) + " / " + year;
                                extra_fecha_pago.setText(selectedDate);
                            }
                        });
                        newFragment4.show(getFragmentManager(), "datePicker");
                    break;
                }
                //System.out.println("EL ID ES EL SIGUIENTE "+ v);
            }
        };
    }





    /*=========================================================

     private View.OnClickListener fecha_pago() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();   //METODO
                }
            }
        };
    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                fecha_tramite.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }  */


    /*==============================YA NO SE OCUPA PERO SE DEJA DE REFERENCIA==========================================*/
        /*private void registrarMascota() {

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
            }
    }*/


}//end class