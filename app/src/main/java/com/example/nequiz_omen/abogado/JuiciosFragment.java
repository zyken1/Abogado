package com.example.nequiz_omen.abogado;

/**
 * Created by abhiandroid on 9/10/17.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JuiciosFragment extends Fragment {

    TextView campoExpediente;
    String campoId;



    public JuiciosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_juicios, container, false);

        //campoId = (TextView) view.findViewById(R.id.campoId);
        campoExpediente = (TextView) view.findViewById(R.id.campoExpediente);


        /*CON ESTE  METOO SE CAPTURA LOS DATOS DESDE CUALQUIIER FRAGMENT*/
        SharedPreferences prefs = getActivity().getSharedPreferences("Preferences", 0);


        String ID = prefs.getString("id", "");
        String expediente = prefs.getString("nombre", "0");




        //Se mandan los datos para que aparescan en en el fragment
        //campoId.setText("1");
        campoExpediente.setText(expediente);


    return view;
    }

}
