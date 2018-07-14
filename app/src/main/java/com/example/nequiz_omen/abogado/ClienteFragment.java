package com.example.nequiz_omen.abogado;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nequiz_omen.abogado.entidades.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteFragment extends Fragment {

    TextView campoId,campoNombre,campoTipo, campoGenero,campoCorreo,fecha_nacimiento,campoDireccion,campoTelmovil,campoTelCasa,campoTelOficina,campoDependientes,campoNotas;

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

        String nombre1 = prefs.getString("nombre", "");
        String tipoPersona = prefs.getString("tipoPersona", "");
        String correo = prefs.getString("e-mail", "");
        //tuTextView.setText(correo_e);


        //Se mandan los datos para que aparescan en en el fragment
        //campoId.setText("1");
        campoNombre.setText(nombre1);
        campoTipo.setText(tipoPersona);
        campoCorreo.setText(correo);

        System.out.println(correo);


        //return inflater.inflate(R.layout.fragment_cliente, container, false);
        return view;

    }

}
