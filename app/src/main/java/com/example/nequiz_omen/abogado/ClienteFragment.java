package com.example.nequiz_omen.abogado;


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

    TextView campoId, campoNombre, campoTelefono;

    public ClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        campoId = (TextView) view.findViewById(R.id.campoId);
        campoNombre = (TextView) view.findViewById(R.id.campoNombre);
        campoTelefono = (TextView) view.findViewById(R.id.campoTelefono);


        campoId.setText("1");
        campoNombre.setText("PEDRO");
        campoTelefono.setText("correo@ejemplo.com ");


        //return inflater.inflate(R.layout.fragment_cliente, container, false);
        return view;

    }

}
