package com.example.nequiz_omen.abogado;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nequiz_omen.abogado.entidades.Usuario;
import com.example.nequiz_omen.abogado.utilidades.Utilidades;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteFragment extends Fragment {

    TextView campoNombre,campoTipo, campoGenero,campoCorreo,fecha_nacimiento,campoDireccion,campoTelmovil,campoTelCasa,campoTelOficina,campoDependientes,campoNotas;
    String campoId;
    ConexionSQLiteHelper conn ;

    public ClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        //SE BUSCAN POR ID EN EL FRAGMENT
        //campoId = (TextView) view.findViewById(R.id.campoId);
        campoNombre = (TextView) view.findViewById(R.id.campoNombre);
        campoTipo = (TextView) view.findViewById(R.id.campoTipo);
        campoCorreo = (TextView) view.findViewById(R.id.campoCorreo);
        campoGenero = (TextView) view.findViewById(R.id.campoGenero);
        fecha_nacimiento = (TextView) view.findViewById(R.id.fecha_nacimiento);
        campoDireccion = (TextView) view.findViewById(R.id.campoDireccion);
        campoTelmovil = (TextView) view.findViewById(R.id.campoTelMovil);
        campoTelCasa = (TextView) view.findViewById(R.id.campoTelCasa);
        campoTelOficina = (TextView) view.findViewById(R.id.campoTelOficina);
        campoDependientes = (TextView) view.findViewById(R.id.campoDependientes);
        campoNotas = (TextView) view.findViewById(R.id.campoNotas);


        /*CON ESTE  METOO SE CAPTURA LOS DATOS DESDE CUALQUIIER FRAGMENT*/
        SharedPreferences prefs = getActivity().getSharedPreferences("Preferences", 0);

        String ID = prefs.getString("id", "");
        String nombre1 = prefs.getString("nombre", "0");
        String tipoPersona = prefs.getString("tipoPersona", "0");
        String correo = prefs.getString("e-mail", "0");
        //tuTextView.setText(correo_e);

        ID = campoId;
        System.out.println("****************************** ID = " + ID);
        System.out.println("******************************  campoId = " + campoId);

        //consultarSQL();



        //Se mandan los datos para que aparescan en en el fragment
        //campoId.setText("1");
          campoNombre.setText(ID);
          campoTipo.setText(tipoPersona);
        //campoCorreo.setText(correo);

        //System.out.println(correo);


        return view;  //return inflater.inflate(R.layout.fragment_cliente, container, false);
    }
}
