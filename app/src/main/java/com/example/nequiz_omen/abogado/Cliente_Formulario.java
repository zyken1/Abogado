package com.example.nequiz_omen.abogado;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nequiz_omen.abogado.Dialogos.DatePickerFragment;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;

public class Cliente_Formulario extends AppCompatActivity {
    LinearLayout layout_for_sides;

    ConexionSQLiteHelper conn ;
    //se declaran las variables
    EditText campoId,campoNombre,campoCorreo,fecha_nacimiento,campoDireccion,campoTelmovil,campoTelCasa,campoTelOficina,campoDependientes,campoNotas;
    String campotipo, campoGenero;
    private RadioButton r1,r2;
    int numEntero = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente__formulario);


        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios",null,1);
        //Busqueda del layout con el id
        layout_for_sides = (LinearLayout) findViewById(R.id.layout_for_sides);

        /* ===============    BUSQUEDA DE ID EN EL  FORMULARIO   son 9 campos en el formulario  y 2 variables de radiobutton ========================*/
        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoCorreo = (EditText) findViewById(R.id.campoCorreo);
        //campoTelefono = (EditText) findViewById(R.id.fecha_nacimiento);
        campoDireccion = (EditText) findViewById(R.id.campoDireccion);
        campoTelmovil = (EditText) findViewById(R.id.campoTelMovil);
        campoTelCasa = (EditText) findViewById(R.id.campoTelCasa);
        campoTelOficina = (EditText) findViewById(R.id.campoTelOficina);
        campoDependientes = (EditText) findViewById(R.id.campoDependientes);
        campoNotas = (EditText) findViewById(R.id.campoNotas);

        fecha_nacimiento = (EditText) findViewById(R.id.fecha_nacimiento);
        fecha_nacimiento.setOnClickListener(fechaNacimiento());

        r1=(RadioButton)findViewById(R.id.radioButton3);
        /* ===============   FIN DE LA BUSQUEDA  ========================*/


          /*CON ESTE  METOO SE CAPTURA LOS DATOS DESDE CUALQUIIER FRAGMENT*/
        SharedPreferences prefs = getApplication().getSharedPreferences("Preferences", 0);

        String ID = prefs.getString("id", "");
        String nombre1 = prefs.getString("nombre", "");
        String tipoPersona = prefs.getString("tipoPersona", "");
        String correo = prefs.getString("e-mail", "");
        String genero = prefs.getString("genero", "");
        String nacimiento = prefs.getString("nacimiento", "");
        String direccion = prefs.getString("direccion", "");
        String telMovil = prefs.getString("telMovil", "");
        String telCasa = prefs.getString("telCasa", "");
        String telOficina = prefs.getString("telOficina", "");
        String dependientes = prefs.getString("dependientes", "");
        String notas = prefs.getString("notas", "");
        //tuTextView.setText(correo_e);

         String valor = ID;
        try {
             numEntero = Integer.parseInt(valor);  //NO RESPETA LA CONDICIONAL ARROJA CERO
            }catch (Exception e){

           }

        if (valor == "1") {
            r1.isChecked();
        }else
        {
        }

        //Se mandan los datos para que aparescan en en el fragment
        //campoId.setText("1");
        campoNombre.setText(nombre1);
        //tipoPersona.setText(tipoPersona);
        campoCorreo.setText(correo);
        //campoGenero .setText(genero);
        fecha_nacimiento .setText(nacimiento);
        campoDireccion .setText(direccion);
        campoTelmovil .setText(telMovil);
        campoTelCasa .setText(telCasa);
        campoTelOficina .setText(telOficina);
        campoDependientes .setText(dependientes);
        campoNotas .setText(notas);
        //System.out.println(correo);

        System.out.println("****************************** ID = " + ID);
        System.out.println("******************************  tipoPersona = " + tipoPersona);
        System.out.println("******************************  valor = " + valor);
        System.out.println("******************************  genero = " + genero);



        /*  PARTE DE CODIGO QUE SIRVE PARA EL AUTO COMPLETADO DEL TEXT VIEW*/
        // Referencia al elemento en la vista
        AutoCompleteTextView textView = (AutoCompleteTextView) campoCorreo;
        // Arreglo con las regiones
        String[] regions = getResources().getStringArray(R.array.arreglo_correo);
        // Le pasamos las regiones al adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, regions);
        // finalmente le asignamos el adaptador a nuestro elemento
        textView.setAdapter(adapter);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                //Intent i = new Intent(Cliente_Formulario.this,Cliente.class);
                //startActivity(i);
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



          /*  METODO GUARDAR EN EL FORMULARIO  */
    public void Guardadito_cliente(MenuItem item) {
        if(campoNombre.getText().toString().trim().length() < 3) {
            campoNombre.setError("Ingrese un nombre valido");
        } else if(numEntero > 0){
            //Toast.makeText(this, "actualizado ", Toast.LENGTH_SHORT).show();
            actualizarUsuario();
            finish();
        }else{
            registrarUsuarios();     //SE CREA UN METODO DE LA ACCION QUE HARA  CUANDO SE DE CLICK
            //registrarUsuariosSQL();   //SE CRE AUN METODO PARA INSERTAR DATOS MEDIANTE SQL
            Toast.makeText(this, "Cliente Guardado ", Toast.LENGTH_SHORT).show();
            //Intent i = new Intent(this, Cliente.class);
            //startActivity(i);
            finish();
        }
    }

    /*======================  AQUI COMIENZA LA BASE DE DATOS  ====================================*/
    private void registrarUsuarios() {
       /*SE INSTANCIA UNA CONEXION Y SE LE COLOCAN LOS PARAMETROS */
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        //Se abre la conexion para poder der editada
        SQLiteDatabase  db = conn .getWritableDatabase();

        ContentValues values= new ContentValues();     //con el content y el put se va agregar una clave y un valor  COMO EN EL HASH
        //values.put(Utilidades.CAMPO_ID,campoId.getText().toString());        //De utilidades escribe en CAMPO_ID  lo que este en el Texto de campoId
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TIPO,campotipo);           //ENTRA EN EL SWITCH  de los RADIOBUTTON y se obtiene el valor en String
        values.put(Utilidades.CAMPO_EMAIL,campoCorreo.getText().toString());
        values.put(Utilidades.CAMPO_GENERO,campoGenero);       //ENTRA EN EL SWITCH  de los RADIOBUTTON y se obtiene el valor en String
        values.put(Utilidades.CAMPO_NACIMIENTO,fecha_nacimiento.getText().toString());
        values.put(Utilidades.CAMPO_DIRECCION,campoDireccion.getText().toString());
        values.put(Utilidades.CAMPO_TELMOVIL,campoTelmovil.getText().toString());
        values.put(Utilidades.CAMPO_TELCASA,campoTelCasa.getText().toString());
        values.put(Utilidades.CAMPO_TELOFICINA,campoTelOficina.getText().toString());
        values.put(Utilidades.CAMPO_DEPENDIENTES,campoDependientes.getText().toString());
        values.put(Utilidades.CAMPO_NOTAS,campoNotas.getText().toString());

        //INSERTAR EN LA BASE DE DATOS
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);  //con values le mandamos todos los parametros correspondientes a ese ID

        Toast.makeText(getApplicationContext(),"Id Registro:" + idResultante,Toast.LENGTH_SHORT).show();
        System.out.println("****************Impresion en BD ====> " +values);
        db.close();   //se cierra la conexion
    }


/*======================  AQUI se Actualiza el Cliente en  LA BASE DE DATOS  ====================================*/
private void actualizarUsuario() {
    SQLiteDatabase db=conn.getWritableDatabase();

    String cadena = Integer.toString(numEntero);
    String[] parametros={cadena};
    ContentValues values= new ContentValues();     //con el content y el put se va agregar una clave y un valor  COMO EN EL HASH
    values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
    values.put(Utilidades.CAMPO_TIPO,campotipo);           //ENTRA EN EL SWITCH  de los RADIOBUTTON y se obtiene el valor en String
    values.put(Utilidades.CAMPO_EMAIL,campoCorreo.getText().toString());
    values.put(Utilidades.CAMPO_GENERO,campoGenero);       //ENTRA EN EL SWITCH  de los RADIOBUTTON y se obtiene el valor en String
    values.put(Utilidades.CAMPO_NACIMIENTO,fecha_nacimiento.getText().toString());
    values.put(Utilidades.CAMPO_DIRECCION,campoDireccion.getText().toString());
    values.put(Utilidades.CAMPO_TELMOVIL,campoTelmovil.getText().toString());
    values.put(Utilidades.CAMPO_TELCASA,campoTelCasa.getText().toString());
    values.put(Utilidades.CAMPO_TELOFICINA,campoTelOficina.getText().toString());
    values.put(Utilidades.CAMPO_DEPENDIENTES,campoDependientes.getText().toString());
    values.put(Utilidades.CAMPO_NOTAS,campoNotas.getText().toString());

    //INSERTAR EN LA BASE DE DATOS
    db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
    Toast.makeText(getApplicationContext(),"Ya se actualizó el usuario",Toast.LENGTH_LONG).show();
    System.out.println("****************Impresion en BD ====> " +values);
    db.close();

}

    /*=========   PARA LOS RADIOBUTTON  ==============*/
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // hacemos un case con lo que ocurre cada vez que pulsemos un botón
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    //FISICA
                    //Toast.makeText(getApplicationContext(),"FISICA",Toast.LENGTH_SHORT).show();
                    campotipo = "Fisica";
                    break;
            case R.id.radioButton2:
                if (checked)
                    //MORAL
                    //Toast.makeText(getApplicationContext(),"MORAL" ,Toast.LENGTH_SHORT).show();
                    campotipo = "Moral";
                    break;
            case R.id.radioButton3:
                if (checked)
                    // HOMBRE
                    //Toast.makeText(getApplicationContext(),"HOMBRE" ,Toast.LENGTH_SHORT).show();
                    campoGenero = "Hombre";
                    break;
            case R.id.radioButton4:
                if (checked)
                    // MUJER
                    //Toast.makeText(getApplicationContext(),"MUJER" ,Toast.LENGTH_SHORT).show();
                    campoGenero = "Mujer";
                    break;
        }
    }

    //================================= Metodos para insertar fecha de nacimiento   =======================================
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
                final String selectedDate = day + " / " +(month+1)+ " / " + year;  //se agrego un cero a peticion
                fecha_nacimiento.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }

}